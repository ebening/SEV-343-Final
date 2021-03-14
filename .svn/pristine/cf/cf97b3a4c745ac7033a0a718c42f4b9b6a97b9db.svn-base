package com.adinfi.seven.presentation.views;

import java.io.Serializable;

import com.adinfi.seven.business.domain.Opcion;
import com.adinfi.seven.presentation.views.util.Utileria;

import javax.faces.context.FacesContext;

public class MBNavigator implements Serializable {
	private static final long serialVersionUID = 1L;
	private transient MBActivity activity;
	private String currentPage = null;
	private String activitiesPage;
	private MBUsuarios mbUsuarios;

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public void save(String page) {
		this.currentPage = page;
	}

	public void saveActivityPage(String page) {
		this.activitiesPage = page;
	}

	public String goToURL(String outcome) {
		return outcome;
	}

	public void filterActivities(String page, Long idCampana) {
		//activity = getActivityBean();
		this.activitiesPage = page;
		activity.setIdCampana(idCampana);
		activity.init();
	}

	public void detailActivities(String page, Long idActividad) {
		this.activitiesPage = page;
		//activity = getActivityBean();
		activity.setIdCampana(null);
		activity.init();
		activity.editActivity(idActividad);
	}
	
	public String firstPageActivities() {
		this.activitiesPage = "activitiesSchedule.xhtml";
		activity = getActivityBean();
		activity.setIdCampana(-1l);
		activity.init();
		return "activitiesDashboard.xhtml";
	}

	private MBActivity getActivityBean (){
		FacesContext context = FacesContext.getCurrentInstance();
		return (MBActivity) context.getApplication().getELResolver().getValue(context.getELContext(), null, "MBActivity");
	}

	/**
	 * @return the activity
	 */
	public MBActivity getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(MBActivity activity) {
		this.activity = activity;
	}

	/**
	 * @return the activitiesPage
	 */
	public String getActivitiesPage() {
		return activitiesPage;
	}

	/**
	 * @param activitiesPage
	 *            the activitiesPage to set
	 */
	public void setActivitiesPage(String activitiesPage) {
		this.activitiesPage = activitiesPage;
	}

	public MBUsuarios getMbUsuarios() {
		return mbUsuarios;
	}

	public void setMbUsuarios(MBUsuarios mbUsuarios) {
		this.mbUsuarios = mbUsuarios;
	}
	
	public void initDashboard(Opcion currentOpcion){
		FacesContext context = FacesContext.getCurrentInstance();
		mbUsuarios = context.getApplication().evaluateExpressionGet(context, "#{MBUsuarios}", MBUsuarios.class);
		setCurrentPage("");
		mbUsuarios.setCurrentOpcionesView(currentOpcion);
	}

	public String url(String url){
		return Utileria.url(url);
	}
}