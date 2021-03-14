package com.adinfi.seven.presentation.views.convertidores;

import com.adinfi.seven.business.domain.CatFlujoAct;
import com.adinfi.seven.presentation.views.MBConverter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

/**
 * Created by jdominguez on 8/20/16.
 */
@FacesConverter(forClass = CatFlujoAct.class)
public class CatFlujoConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext fcontext, UIComponent uiComponent, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        MBConverter controller = (MBConverter) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "MBConverter");
        return controller.getFlujoById(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if(object == null){
            return null;
        }
        if (object instanceof CatFlujoAct){
            CatFlujoAct flujo = (CatFlujoAct) object;
            return String.valueOf(flujo.getId());
        }
        return "";
    }
}
