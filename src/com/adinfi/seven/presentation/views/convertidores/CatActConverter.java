package com.adinfi.seven.presentation.views.convertidores;

import com.adinfi.seven.business.domain.CatActPred;
import com.adinfi.seven.presentation.views.MBConverter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

/**
 * Created by jdominguez on 8/20/16.
 */
@FacesConverter(forClass = CatActPred.class)
public class CatActConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext fcontext, UIComponent uiComponent, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        MBConverter controller = (MBConverter) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "MBConverter");
        return controller.getActById(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if(object == null){
            return null;
        }
        if (object instanceof CatActPred){
            CatActPred act = (CatActPred) object;
            return String.valueOf(act.getId());
        }
        return "";
    }
}
