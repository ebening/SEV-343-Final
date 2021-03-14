package com.adinfi.seven.persistence.daos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateUser;

public class DAOArqTemplateImpl extends AbstractDaoImpl<TblTemplate> implements
		DAOArqTemplate {
	private Logger LOG = Logger.getLogger(DAOArqTemplateImpl.class);
	
	@Override
	public TblTemplate saveTemplate(TblTemplate template){
		try {
			saveOrUpdate(template);
			this.flush();
			//this.refresh(template);
		} catch (Exception e) {
			LOG.error(e);
		}
		return template;
	}
	
	@Override
	public Map<String, String> getTemplates() throws GeneralException {
		Map<String, String> retValue = null;
		try {
			List<TblTemplate> items = getAll();
			Map<String, String> map = new HashMap<String, String>();
			for (TblTemplate item : items) {
				map.put(item.getTemplateName(),
						String.valueOf(item.getTemplateId()));
			}
			
			retValue = new TreeMap<String, String>(map);
			
		} catch (Exception e) {
			retValue = null;
			LOG.error(e);
		}
		return retValue;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblTemplateUser> getTemplatesByUser(Object[] restricciones){
		Iterator<TblTemplateUser> tempUser = getHibernateTemplate()
				.iterate(
						" FROM TblTemplateUser tu WHERE tu.idUser = ? and tu.tblTemplate.templateId = ? and tu.idCategory = ? and tu.idTemplateUser <> ? ",
						restricciones.clone());
		return toInitializedList(tempUser);
	}

}
