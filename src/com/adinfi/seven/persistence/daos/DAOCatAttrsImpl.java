package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.CatAttrs;

public class DAOCatAttrsImpl extends AbstractDaoImpl<CatAttrs> implements
		DAOCatAttrs {
	@SuppressWarnings("unchecked")
	@Override
	public List<CatAttrs> getCatalogAttributes(Integer catId) throws Exception {
		Iterator<CatAttrs> catAttrsList = getHibernateTemplate()
				.iterate(
						"from CatAttrs c where c.catalogs.catId= ? ", catId);
		return toInitializedList(catAttrsList);
	}

	@SuppressWarnings("rawtypes")
	public List getRegExternalFK(String tableName, String fieldName,
			Object value) throws Exception {
		String query = "select  " + fieldName + "  from " + tableName + " "
				+ "where " + fieldName + " = ? ";
		return this.getSession().createQuery(query).setParameter(0, value)
				.list();
	}

}