package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.business.domain.RelZoneStore;
import com.adinfi.seven.presentation.views.util.Constants;
import com.google.common.base.Joiner;

public class DAOCatStoreImpl extends AbstractDaoImpl<CatStore> implements DAOCatStore {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	public CatStore getCatStore(Integer idStore) {
		String sentence = "from CatStore catStore where catStore.idStore = ? ";
		List cs = getHibernateTemplate().find(sentence, idStore);
		return (CatStore)cs.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatStore> getCatStore(List<Integer> idsStore) {
		String sentence = "from CatStore catStore where catStore.idStore in ("+StringUtils.join(idsStore, ",")+") ";
		return getHibernateTemplate().find(sentence);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CatStore> getCatStoreList(CatStore catStore) throws GeneralException {
		String sentence = "FROM CatStore";
		List<CatStore> list = getHibernateTemplate().find(sentence);
		return list;
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CatStore> getCatStoreListbyZone(Integer idZone) {
		String sentence = "FROM RelZoneStore AS rzStore JOIN rzStore.catStore WHERE rzStore.id.idZone = ?";
		List<Object[]> lrz = getHibernateTemplate().find(sentence, idZone);
		List<CatStore> l = new ArrayList<>();
		if(!lrz.isEmpty()){
			for(Object[] obj : lrz){
				if(obj[1] instanceof CatStore){
					l.add((CatStore)obj[1]);
				}
			}
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatStore> getCatStoreListbyZone(List<Integer> ids) {
		String in = Joiner.on(",").join(ids);
		List<CatStore> l = new ArrayList<>();
		if(!in.isEmpty()){
			String sentence = "FROM RelZoneStore AS rzStore JOIN rzStore.catStore WHERE rzStore.id.idZone in ("+in+")";
			List<Object[]> lrz = getHibernateTemplate().find(sentence);
			if(!lrz.isEmpty()){
				for(Object[] obj : lrz){
					RelZoneStore zs = (RelZoneStore)obj[0];
					CatStore cs = (CatStore)obj[1];
					cs.setRelZoneStores(new HashSet<RelZoneStore>());
					cs.getRelZoneStores().add(zs);
					l.add(cs);
				}
			}
		}
		return l;
	}
}
