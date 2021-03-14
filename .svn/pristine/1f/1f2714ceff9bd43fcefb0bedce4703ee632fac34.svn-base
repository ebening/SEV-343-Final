package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.engine.SessionFactoryImplementor;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.Attribs;
import com.adinfi.seven.business.domain.CatAttrs;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.google.common.base.Joiner;

public class DAOCatRegValuesImpl extends AbstractDaoImpl<CatRegValues>
		implements DAOCatRegValues {

	
	private Logger LOG = Logger.getLogger(DAOCatRegValuesImpl.class);
	public void deleteByCatAttrId(int catAttrId) throws Exception {
		Query query = this.getSession().createQuery(
				"delete from CatRegValues crv where crv.catAttrs.catAttribId= "
						+ catAttrId);
		int deleted = query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<CatRegValues> getByCatAttrId(int catAttrId) throws Exception {
		Iterator<CatRegValues> catRegValuesList = getHibernateTemplate()
				.iterate(
						"from CatRegValues crv where crv.catAttrs.catAttribId=? ",
						catAttrId);
		return toInitializedList(catRegValuesList);

	}

	@SuppressWarnings("unchecked")
	public List<CatRegValues> getByCatAttrIdRefAndValue(int catAttrId,
			String value) throws Exception {
		Iterator<CatRegValues> catRegValuesList = null;
		try {
			catRegValuesList = getHibernateTemplate()
					.iterate(
							"from CatRegValues crv where crv.catAttrs.catAttribIdRef=? and crv.value=?  ",
							new Object[] { catAttrId, value });

		} catch (Exception e) {
			LOG.error(e);
		}

		return toInitializedList(catRegValuesList);

	}

	@SuppressWarnings("unchecked")
	public List<CatRegValues> getByCatAttrIdAndValue(int catAttrId, String value)
			throws Exception {
		Iterator<CatRegValues> catRegValuesList = getHibernateTemplate()
				.iterate(
						"from CatRegValues crv where crv.catAttrs.catAttribId=? and crv.value=?  ",
						new Object[] { catAttrId, value });

		return toInitializedList(catRegValuesList);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatRegValues> getByCatRegId(List<Integer> catRegIds) throws GeneralException {
		String ids = Joiner.on(",").join(catRegIds);
		String schema = ((SessionFactoryImplementor)getHibernateTemplate().getSessionFactory()).getSettings().getDefaultSchemaName();
		String query = "SELECT CRV.CAT_REG_VAL_ID, CRV.CAT_REG_ID, CRV.VALUE, CA.CAT_ATTRIB_ID, A.ATTRIB_ID, A.ATTR_NAME "+
					"FROM "+schema+".CAT_REG_VALUES CRV, "+schema+".CAT_ATTRS CA, "+schema+".ATTRIBS A "+
					"WHERE CRV.CAT_ATTRIB_ID = CA.CAT_ATTRIB_ID "+
					"AND CA.ATTRIB_ID = A.ATTRIB_ID "+ 
					"AND CRV.CAT_REG_ID IN ("+ids+")";
		List<CatRegValues> list = new ArrayList<>();
		if(!ids.isEmpty()){
			List<Object[]> results = this.getSession().createSQLQuery(query).list();
			for(Object[] o: results){
				CatRegValues v = new CatRegValues();
				v.setCatRegValId(Integer.valueOf(o[0].toString()));
				v.setCatRegId(new CatRegs());
				v.getCatRegId().setCatRegId(Integer.valueOf(o[1].toString()));
				v.setValue((String)o[2]);
				v.setCatAttrs(new CatAttrs());
				v.getCatAttrs().setCatAttribId(Integer.valueOf(o[3].toString()));
				v.getCatAttrs().setAttribs(new Attribs());
				v.getCatAttrs().getAttribs().setAttribId(Integer.valueOf(o[4].toString()));
				v.getCatAttrs().getAttribs().setAttrName((String)o[5]);
				list.add(v);
			}
		}
		return list;
	}

}
