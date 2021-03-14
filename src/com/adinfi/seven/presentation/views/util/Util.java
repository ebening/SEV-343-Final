package com.adinfi.seven.presentation.views.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.services.ServiceUsuarios;
import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.hibernate.mapping.Array;

public abstract class Util {
	
   
	private Util(){}

    public static String getCoordinadorEmail(ServiceUsuarios serviceUsuarios){
        CatUsuarios coordinador = serviceUsuarios.getUsuarioByRoleAndCategory(1, 0);
        return coordinador.getEmail();
    }

	public static EtapaDashboard etapaDashboardBefore(EtapaDashboard etapa){
		switch (etapa){
			case CAPTURA: return EtapaDashboard.PENDIENTE;
			case REV_PRECIOS: return EtapaDashboard.CAPTURA;
			case DISENO: return EtapaDashboard.REV_PRECIOS;
			case REV_DISENO: return EtapaDashboard.DISENO;
			case ENVIO_DISENO: return EtapaDashboard.REV_DISENO;
			default: return null;
		}
	}

	public static EtapaDashboard etapaDashboardAfter(EtapaDashboard etapa){
		switch (etapa){
			case PENDIENTE: return EtapaDashboard.CAPTURA;
			case CAPTURA: return EtapaDashboard.REV_PRECIOS;
			case REV_PRECIOS: return EtapaDashboard.DISENO;
			case DISENO: return EtapaDashboard.REV_DISENO;
			case REV_DISENO: return EtapaDashboard.ENVIO_DISENO;
			default: return null;
		}
	}

	public static int etapaDashboardToInt(EtapaDashboard etapa){
		switch (etapa){
			case PENDIENTE: return 0;
			case CAPTURA: return 1;
			case REV_PRECIOS: return 2;
			case DISENO: return 3;
			case REV_DISENO: return 4;
			case ENVIO_DISENO: return 5;
			case VENCIDA: return 6;
            default: return 0;
		}
	}

	public static EtapaDashboard intToEtapaDashboard(int i){
		switch (i){
			case 0: return EtapaDashboard.PENDIENTE;
			case 1: return EtapaDashboard.CAPTURA;
			case 2: return EtapaDashboard.REV_PRECIOS;
			case 3: return EtapaDashboard.DISENO;
			case 4: return EtapaDashboard.REV_DISENO;
			case 5: return EtapaDashboard.ENVIO_DISENO;
            case 6: return EtapaDashboard.VENCIDA;
			default: return null;
		}
	}

	public static EtapaDashboard convertProgramaEtapaAfter(String progEtapa){
        if (progEtapa.equals(Constants.ETAPA_CAPTURA_PROMOCION)){
            return EtapaDashboard.REV_PRECIOS;
        }else if (progEtapa.equals(Constants.ETAPA_PRECIOS)){
            return EtapaDashboard.DISENO;
        }else if (progEtapa.equals(Constants.ETAPA_ELABORACION_DISENO)){
            return EtapaDashboard.REV_DISENO;
        }else if (progEtapa.equals(Constants.ETAPA_REVISION_DISENO)){
            return EtapaDashboard.ENVIO_DISENO;
        }else {
            return null;
        }
    }

	public static EtapaDashboard convertProgramaEtapaBefore(String progEtapa){
		if (progEtapa.equals(Constants.ETAPA_PRECIOS)){
			return EtapaDashboard.CAPTURA;
		}else if (progEtapa.equals(Constants.ETAPA_ELABORACION_DISENO)){
			return EtapaDashboard.REV_PRECIOS;
		}else if (progEtapa.equals(Constants.ETAPA_REVISION_DISENO)){
			return EtapaDashboard.DISENO;
		}else if (progEtapa.equals(Constants.ETAPA_FINALIZADO)){
			return EtapaDashboard.REV_DISENO;
		}else {
			return null;
		}
	}

	public static EtapaDashboard convertProgramaEtapa (String progEtapa){
		if (progEtapa.equals(Constants.ETAPA_PENDIENTE)){
			return EtapaDashboard.PENDIENTE;
		}
		else if (progEtapa.equals(Constants.ETAPA_CAPTURA_PROMOCION)){
			return EtapaDashboard.CAPTURA;
		}else if (progEtapa.equals(Constants.ETAPA_PRECIOS)){
			return EtapaDashboard.REV_PRECIOS;
		}else if (progEtapa.equals(Constants.ETAPA_ELABORACION_DISENO)){
			return EtapaDashboard.DISENO;
		}else if (progEtapa.equals(Constants.ETAPA_REVISION_DISENO)){
			return EtapaDashboard.REV_DISENO;
		}else if (progEtapa.equals(Constants.ETAPA_FINALIZADO)){
			return EtapaDashboard.ENVIO_DISENO;
		}else if (progEtapa.equals(Constants.ETAPA_VENCIDA)){
            return EtapaDashboard.VENCIDA;
        }else {
			return null;
		}
	}

	public static String convertProgramaEtapa (EtapaDashboard etapaDashboard){
		switch (etapaDashboard){
			case PENDIENTE: return Constants.ETAPA_PENDIENTE;
			case CAPTURA:  return Constants.ETAPA_CAPTURA_PROMOCION;
			case REV_PRECIOS: return Constants.ETAPA_PRECIOS;
			case DISENO: return Constants.ETAPA_ELABORACION_DISENO;
			case REV_DISENO: return Constants.ETAPA_REVISION_DISENO;
			case ENVIO_DISENO: return Constants.ETAPA_FINALIZADO;
            case VENCIDA: return Constants.ETAPA_VENCIDA;
			default: return "";
		}
	}

	public static Date sumarRestarDiasFecha (Date fecha, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}
	
	public static List<Integer> castListStringToInteger (List<String> source){
		List<Integer> returnList = new ArrayList<>();
		try{
			for (String st : source){
				returnList.add(Integer.valueOf(st));
			}
			return returnList;
		}catch(NumberFormatException ex){
			System.out.println(ex);
			return new ArrayList<>();
		}
	}
	
	/**
	 * Busca un componente en la vista según si id.
	 * 
	 * @param id
	 * @return
	 */
	public static UIComponent findComponent(String id) {
		UIViewRoot iViewRoot = getFacesContext().getViewRoot();
		return iViewRoot.findComponent(id);
	}

	/**
	 * Refresca un componente visual por su id.
	 * 
	 * @param id
	 */
	public static void refreshComponent(String id) {
		PartialViewContext partialViewContext = getFacesContext()
				.getPartialViewContext();
		Collection<String> renderIds = partialViewContext.getRenderIds();
		renderIds.add(id);
	}

	/**
	 * Obtiene el ManagedBean del contexto.
	 * 
	 * @param <T>
	 * @param managedBeanName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "el-syntax" })
	public static <T> T getManagedBean(String managedBeanName) {
		return (T) getFacesContext().getApplication().evaluateExpressionGet(
				getFacesContext(), "#{" + managedBeanName + "}", Object.class);
	}

	public static FacesContext getFacesContext() {
		setFacesLocaleMXN();
		return FacesContext.getCurrentInstance();
	}

	public static void setFacesLocaleMXN() {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es_MX"));
	}

	/**
	 * Obtiene el domimio del server
	 * 
	 * @return
	 */
	public static String getLocalName() {
		return getRequest().getLocalName();
	}

	/**
	 * Obtiene la IP del server.
	 * 
	 * @return
	 */
	public static String getLocalAddress() {
		return getRequest().getLocalAddr();
	}

	/**
	 * Obtiene el request del contexto del servlet.
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}

	/**
	 * Obtiene un parametro del request.
	 * 
	 * @return
	 */
	public static String getRequestParameter(String name) {
		return getRequest().getParameter(name);
	}

	public static void redirect(String uri) {
		try {

			getFacesContext().responseComplete();
			getFacesContext().getExternalContext().dispatch(uri);
		} catch (IOException e) {
		 logger(Util.class).error(e);
		}
	}

	/**
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) getFacesContext().getExternalContext()
				.getResponse();
	}

	/**
	 * Muestra mensaje de jsf.
	 */
	public static void sendMessage(String message) {
		getFacesContext().addMessage(null, new FacesMessage(message));
	}

	/**
	 * Muestra mensaje de jsf.
	 * 
	 * @param id
	 * @param severity
	 * @param summary
	 * @param detail
	 */
	public static void sendMessage(String id, FacesMessage.Severity severity,
			String summary, String detail) {
		getFacesContext().addMessage(id,
				new FacesMessage(severity, summary, detail));
	}

	/**
	 * Pone un attributo en session.
	 * 
	 * @param name
	 * @param value
	 */
	public static void setSessionAttribute(String name, Object value) {
		getHttpSession().setAttribute(name, value);
	}

	/**
	 * Obtiene un attributo de session.
	 * 
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String name) {
		return (T) getHttpSession().getAttribute(name);
	}

	private static HttpSession getHttpSession() {
		return getRequest().getSession();
	}

	/**
	 * Redirecciona a un navigation case.
	 * 
	 * @param outcome
	 */
	public static void handleNavigation(String outcome) {

		ConfigurableNavigationHandler cnh = (ConfigurableNavigationHandler) getFacesContext()
				.getApplication().getNavigationHandler();
		cnh.performNavigation(outcome);
	}

	/**
	 * Obtiene el contextpath de la app
	 * 
	 * @return
	 */
	public static String getContextPath() {
		return getRequest().getContextPath();
	}

	/**
	 * Hace un redirect mediante el ExternalContext de JSF.
	 * 
	 * @param uri
	 */
	public static void externalRedirect(String uri) {
		try {

			getFacesContext().responseComplete();
			getFacesContext().getExternalContext().redirect(uri);
		} catch (IOException e) {
			 logger(Util.class).error(e);
		}
	}

	public static  String fechaFormat1(Date fecha) {
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
		return formatDate.format(fecha);
	}
	
	public static  String fechaFormat(Date fecha) {
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"MM'-'yyyy");
		return formatDate.format(fecha);
	}
	
	public static String fechaFormatComplete(Date fecha) {
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"dd-MM-yyyy");
		return formatDate.format(fecha);
	}
	
	public static Logger logger(Class<?> clase){	
		return Logger.getLogger(clase);
	}

}