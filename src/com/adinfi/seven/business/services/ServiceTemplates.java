package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateUser;

public interface ServiceTemplates {
    TblTemplate getTemplate(int templateId)  throws Exception;
    List<TblTemplateUser> getByUserAndCategory(int user, int category);
}
