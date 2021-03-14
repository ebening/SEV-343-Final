package com.adinfi.seven.persistence.daos;

import java.util.List;
import java.util.Map;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateUser;

public interface DAOArqTemplate extends AbstractDao<TblTemplate> {

	TblTemplate saveTemplate(TblTemplate template);

	Map<String, String> getTemplates() throws GeneralException;

	List<TblTemplateUser> getTemplatesByUser(Object[] restricciones);

}
