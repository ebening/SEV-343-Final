package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.engine.SessionFactoryImplementor;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.presentation.views.util.Constants;
import com.google.common.base.Joiner;

public class DAOCatZoneImpl extends AbstractDaoImpl<CatZone> implements DAOCatZone {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public CatZone getCatZone(Integer idZone) {
		Iterator<CatZone> tblCatZoneIterator = getHibernateTemplate()
				.iterate(
						"from CatZone catZone where catZone.idZone = ? ",
						new Object[] { idZone });
		return toInitializedInstance(tblCatZoneIterator);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CatZone> getCatZoneList(CatZone catZone) throws GeneralException {
		Iterator<CatZone> tblCatZoneIterator = getHibernateTemplate()
				.iterate(" from CatZone catZone where catZone.idZone= ?", catZone.getIdZone());
		return toInitializedList(tblCatZoneIterator);
	}
	
	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CatZone> getCatZoneByIds(List<Integer> ids){
		String in = Joiner.on(",").join(ids);
		if(!in.isEmpty()){
			return getHibernateTemplate().find("select z from CatZone z join fetch z.catGZone gz where z.idZone in ("+in+")");
		}
		return new ArrayList<>();
	}
	
	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CatZone> getCatZonesByGrupoZonas(List<Integer> idGrupoZona) {
		String in = Joiner.on(",").join(idGrupoZona);
		if(!in.isEmpty()){
			return getHibernateTemplate().find("select distinct catZone from CatZone catZone join catZone.relZoneStores rzs where catZone.catGZone.idGrupoZona in ("+in+")");
		}
		return new ArrayList<>();
	}
	
	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<Object[]> getCatZonesWithCatGZoneByZonas(String zoneList) {
		String schema = ((SessionFactoryImplementor)getHibernateTemplate().getSessionFactory()).getSettings().getDefaultSchemaName();
		return getSession().createSQLQuery("SELECT {Z.*}, {GZ.*} FROM "+schema+".CAT_ZONE Z JOIN "+schema+".CAT_G_ZONE GZ ON GZ.ID_GRUPO_ZONA = Z.ID_GRUPO_ZONA WHERE ID_ZONE IN ("+zoneList+")")
				.addEntity("Z", CatZone.class)
				.addEntity("GZ", CatGZone.class)
				.list();
	}

}
