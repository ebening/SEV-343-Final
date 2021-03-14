package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.List;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.seven.business.domain.CatRegs;

public class DAOCatRegsImpl extends AbstractDaoImpl<CatRegs> implements	DAOCatRegs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "DAOCatRegsImpl";
	}

	@SuppressWarnings("unchecked")
        @Override
	public List<CatRegs> getRegs(String catName, List<List<AttrSearch>> l) throws Exception {
		StringBuilder query = new StringBuilder("from CatRegs cr where ");
		Boolean started = Boolean.FALSE;
		if (l != null && !l.isEmpty()) {
			for(List<AttrSearch> lstSearchAttrs: l){
				if(started){
					query.append(" or ");
				}
				query.append("(cr.catalogs.catName = '").append(catName).append("' ");
				if(lstSearchAttrs!=null){
					for (AttrSearch attrSearch : lstSearchAttrs) {
						if (attrSearch.getAttrName() == null
								|| attrSearch.getAttrName().isEmpty()) {
							continue;
						}
						if (attrSearch.getValue() == null
								|| attrSearch.getValue().isEmpty()) {
							continue;
						}
						if (attrSearch.getSearchType() == AttrSearch.SEARCH_TYPE_EQUAL) {
							query.append(" AND EXISTS ( ");
							query.append(" FROM CatRegValues crv ");
							query.append(" WHERE ");
							query.append(" crv.catAttrs.attribs.attrName='");
							query.append(attrSearch.getAttrName());
							query.append("' ");
							query.append(" AND  crv.value='");
							query.append(attrSearch.getValue());
							query.append("' ");
							query.append(" AND  crv.catRegId=cr.catRegId  ");
							query.append(" ) ");
						}
						 else if (attrSearch.getSearchType() == AttrSearch.SEARCH_TYPE_EQUAL_OR) {
							query.append(" OR EXISTS ( ");
							query.append(" FROM CatRegValues crv ");
							query.append(" WHERE ");
							query.append(" crv.catAttrs.attribs.attrName='");
							query.append(attrSearch.getAttrName());
							query.append("'      ");
							query.append(" AND  crv.value='");
							query.append(attrSearch.getValue());
							query.append("'      ");
							query.append(" AND  crv.catRegId=cr.catRegId  ");
							query.append(" ) ");
						}
					}
				}
				query.append(")");
				started = Boolean.TRUE;
			}
			query.append("  order by cr.catRegId");
	      	return getHibernateTemplate().find(query.toString());
		}
		return new ArrayList<>();
	}

}
