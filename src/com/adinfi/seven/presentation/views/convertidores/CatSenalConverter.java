package com.adinfi.seven.presentation.views.convertidores;

import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.presentation.views.MBConverter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by jdominguez on 3/22/16.
 */
@FacesConverter(forClass = CatSenal.class, value = "catSenalConverter")
public class CatSenalConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fcontext, UIComponent uiComponent, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        MBConverter controller = (MBConverter) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "MBConverter");
        try{
            CatSenal catSenal = controller.catSenalById(Integer.valueOf(value));
            return catSenal;
        }catch (NumberFormatException ex){
            System.out.println("Cannot Convert CatSenal, Value: " + value);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if(object == null){
            return null;
        }
        if (object instanceof CatSenal){
            CatSenal cat = (CatSenal) object;
            return String.valueOf(cat.getIdSenal());
        }
        return "";

    }
}
