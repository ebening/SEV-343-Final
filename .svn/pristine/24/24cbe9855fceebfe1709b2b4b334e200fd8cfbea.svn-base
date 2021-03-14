package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblTemplateUser;;

public class DAOArqTemplateUserImpl extends AbstractDaoImpl<TblTemplateUser> 
		implements DAOArqTemplateUser {
	private Logger LOG = Logger.getLogger(DAOArqTemplateUserImpl.class);
	
	@Override
	public TblTemplateUser saveTemplateUser(TblTemplateUser templateUser){
		try {
			saveOrUpdate(templateUser);
		} catch (Exception e) {
			LOG.error(e);
		}
		return templateUser;
	}
	

    @Override
	public List<TblTemplateUser> getByUserAndCategory(int user, int category) {
		@SuppressWarnings("unchecked")
		Iterator<TblTemplateUser> templates = getHibernateTemplate()
				.iterate(
						"from TblTemplateUser obj where obj.idUser = ?  and obj.idCategory=? ",
						new Object[] {user,category});
		return toInitializedList(templates);
	}
	
	

}
