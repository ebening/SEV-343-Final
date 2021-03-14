package com.adinfi.seven.persistence.daos;

import java.util.Iterator;

import com.adinfi.seven.business.domain.Catalogs;

public class DAOCatalogsImpl extends AbstractDaoImpl<Catalogs> implements
		DAOCatalogs {

	@SuppressWarnings("unchecked")
	@Override
	public Catalogs getByCatName(String catalogName) {
		Iterator<Catalogs> catalogIterator = getHibernateTemplate().iterate(
				"from Catalogs cat " + "where cat.catName = ? ",
				new Object[] { catalogName });
		return toInitializedInstance(catalogIterator);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Catalogs getByCatNameAndDescription(String catalogName,
			String description) {
		Iterator<Catalogs> catalogIterator = getHibernateTemplate().iterate("from Catalogs cat " + 
				"where cat.catName = ? and cat.descrip = ?",new Object[]{catalogName,description});
		return toInitializedInstance(catalogIterator);	
	}

}
