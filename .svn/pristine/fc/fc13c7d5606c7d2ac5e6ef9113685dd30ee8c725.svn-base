package com.adinfi.seven.presentation.views.convertidores;

import com.adinfi.seven.business.domain.TblMecanica;
import com.adinfi.seven.presentation.views.MBConverter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by jdominguez on 7/12/16.
 */
@FacesConverter(forClass = TblMecanica.class, value = "tblMecanicaConverter")
public class TblMecanicaConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fcontext, UIComponent uiComponent, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        MBConverter controller = (MBConverter) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "MBConverter");
        try{
            TblMecanica tblMecanica = controller.tblMecanicaById(Integer.valueOf(value));
            return tblMecanica;
        }catch (NumberFormatException ex){
            System.out.println("Cannot Convert TblMecanica, Value: " + value);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if(object == null){
            return null;
        }
        if (object instanceof TblMecanica){
            TblMecanica mecanica = (TblMecanica) object;
            return String.valueOf(mecanica.getMecanicaId());
        }
        return "";
    }
}
