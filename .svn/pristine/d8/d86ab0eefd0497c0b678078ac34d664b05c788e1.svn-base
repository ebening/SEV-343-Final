package com.adinfi.seven.presentation.views.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class Messages {
    // property file is: package/name/messages.properties
    private static final String BUNDLE_NAME = "com.adinfi.seven.presentation.views.util.message";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public static String getString(String key, Object... params) {
        try {
                return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
                return '!' + key + '!';
        }
    }

    public static void mensajeSatisfactorio(Object msj) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",msj.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void mensajeAlerta(Object msj) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",msj.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void mensajeErroneo(Object msj) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", msj.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void mensajeError(Object msj) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,msj.toString(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public static void mensajeSatisfactorio(Object title, Object msj) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,title.toString(), msj.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void mensajeAlerta(Object title, Object msj) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,title.toString(), msj.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void mensajeErroneo(Object title, Object msj) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,title.toString(), msj.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }

}