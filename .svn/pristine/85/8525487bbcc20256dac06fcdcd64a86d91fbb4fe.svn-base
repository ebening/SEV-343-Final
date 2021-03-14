package com.adinfi.seven.presentation.views.convertidores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.adinfi.seven.persistence.dto.SucursalDTO;
import com.adinfi.seven.presentation.views.MBAuxConverter;

@FacesConverter(forClass = SucursalDTO.class)
public class SucursalConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4290566771591501147L;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		MBAuxConverter controller = (MBAuxConverter) facesContext
				.getApplication().getELResolver()
				.getValue(facesContext.getELContext(), null, "auxConverter");
		return controller.getSucursal(getKey(value));
	}

	java.lang.Integer getKey(String value) {
		java.lang.Integer key;
		key = Integer.valueOf(value);
		return key;
	}

	String getStringKey(java.lang.Integer value) {
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		return sb.toString();
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object object) {
		if (object == null) {
			return null;
		}
		if (object instanceof SucursalDTO) {
			SucursalDTO o = (SucursalDTO) object;
			return getStringKey(o.getId());
		} else {
			throw new IllegalArgumentException("object " + object
					+ " is of type " + object.getClass().getName()
					+ "; expected type: " + SucursalDTO.class.getName());
		}
	}
}
