package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.CatSenal;

@SuppressWarnings("serial")
public class DAOCatSenalImpl extends AbstractDaoImpl<CatSenal> implements DAOCatSenal {

	@Override
	@SuppressWarnings("unchecked")
	public CatSenal getCatSenal(Integer idSenal) {
		
		Iterator<CatSenal> tblCatSenalIterator = getHibernateTemplate().
		iterate("from CatSenal catSenal where catSenal.idSenal = ? ", new Object[]{idSenal});
		return toInitializedInstance(tblCatSenalIterator);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatSenal> getCatSenales() {
		return getSession().createQuery("select new CatSenal(idSenal, catPrograma.idPrograma, nombre) from CatSenal")
				.list();
	}
	

}
