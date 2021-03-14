package com.adinfi.seven.presentation.views.convertidores;

import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.presentation.views.MBConverter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by jdominguez on 9/26/16.
 */
@FacesConverter(forClass = TblCampana.class)
public class TblCampanaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fcontext, UIComponent uiComponent, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        MBConverter controller = (MBConverter) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "MBConverter");
        try{
            TblCampana tblCampana = controller.getTblCampanaById(Long.valueOf(value));
            return tblCampana;
        }catch (NumberFormatException ex){
            System.out.println("Cannot Convert TblCampana, Value: " + value);
            return null;
        }catch (Exception e){
            System.out.println("Cannot Convert TblCampana, Value: " + value);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if(object == null){
            return null;
        }
        if (object instanceof TblCampana){
            TblCampana campana = (TblCampana) object;
            return String.valueOf(campana.getIdCampana());
        }
        return "";
    }
}
