package com.adinfi.seven.presentation.views.convertidores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.presentation.views.MBConverter;

@FacesConverter(forClass=CatCategory.class)
public class CatCategoryConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext fcontext, UIComponent component, String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		MBConverter controller = (MBConverter) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "MBConverter");
		return controller.catCategoryById(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext fcontext, UIComponent component, Object object) {
		if(object == null){
			return null;
		}
		if (object instanceof CatCategory){
			CatCategory cat = (CatCategory) object;
			return String.valueOf(cat.getIdCategory());
		}
		return "";
	}

	
}
