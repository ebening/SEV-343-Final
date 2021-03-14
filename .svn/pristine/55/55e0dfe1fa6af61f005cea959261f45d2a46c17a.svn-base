package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateUser;
import com.adinfi.seven.persistence.daos.DAOArqTemplateUser;
import com.adinfi.seven.persistence.daos.DAOTemplate;

public class ServiceTemplatesImpl implements ServiceTemplates {
       private DAOTemplate daoTemplate;
       private DAOArqTemplateUser daoArqTemplateUser;

    /**
     *    
     * @return
     */
	public DAOTemplate getDaoTemplate() {
		return daoTemplate;
	}

	/**
	 * 
	 * @param daoTemplate
	 */
	public void setDaoTemplate(DAOTemplate daoTemplate) {
		this.daoTemplate = daoTemplate;
	}
	
	public TblTemplate getTemplate(int templateId) throws Exception{
		return this.daoTemplate.getById(templateId);
	}
	
	public List<TblTemplateUser> getByUserAndCategory(int user, int category){
		return this.daoArqTemplateUser.getByUserAndCategory(user, category);
	}

	public DAOArqTemplateUser getDaoArqTemplateUser() {
		return daoArqTemplateUser;
	}

	public void setDaoArqTemplateUser(DAOArqTemplateUser daoArqTemplateUser) {
		this.daoArqTemplateUser = daoArqTemplateUser;
	}

 
       
}
