package com.adinfi.seven.presentation.views.convertidores;

import com.adinfi.seven.business.domain.CatEstatus;
import com.adinfi.seven.presentation.views.MBConverter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

/**
 * Created by jdominguez on 9/13/16.
 */
@FacesConverter(forClass = CatEstatus.class)
public class CatEstatusConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext fcontext, UIComponent uiComponent, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        MBConverter controller = (MBConverter) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "MBConverter");
        return controller.getCatEstatusById(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if(object == null){
            return null;
        }
        if (object instanceof CatEstatus){
            CatEstatus act = (CatEstatus) object;
            return String.valueOf(act.getIdestatus());
        }
        return "";
    }
}
