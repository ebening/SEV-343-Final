package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.presentation.views.util.Messages;

public class MBCampanaFormRendered implements Serializable {

	/**
	 * 
	 */

	/**** Declaraciones *****/

	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceCampana serviceCampana;
	private List<CampanaDTO> listaCampana;
	private List<TblCampana> campanas;
	private static final long serialVersionUID = 899531966833850303L;
	private boolean alta = false;
	private boolean edicion = false;
	private boolean table = true;
	private boolean periodo = false;
	private boolean treeMenu = true;
	private boolean vigencia = false;
	private TipoCampanaDTO tipoCampana;

	/******** Constructor **********/
	@PostConstruct
	public void init() {
		camposAbilitado();
		cargarCampañas();

	}

	public void viewTreeMenu() {
		if (treeMenu) {
			treeMenu = false;
		} else if (!treeMenu) {
			treeMenu = true;
		}
	}

	/******** Metodos **********/
	public void viewAlta() {
		if (table || edicion) {
			table = false;
			edicion = false;
			Messages.mensajeSatisfactorio("Edicion " + edicion);
			alta = true;
			Messages.mensajeSatisfactorio("Alta " + alta);
		}
	}

	public void viewTabla() {
		if (edicion || alta) {
			edicion = false;
			alta = false;
			table = true;
		}
	}

	public boolean getVigencia() {
		return vigencia;
	}

	public void viewEdicion() {
		if (table || alta) {
			table = false;
			alta = false;
			Messages.mensajeSatisfactorio("Alta " + alta);
			edicion = true;
			Messages.mensajeSatisfactorio("Edicion " + edicion);
			table = false;
		}
	}

	public boolean getPeriodo() {
		return periodo;
	}

	public boolean getTreeMenu() {
		return treeMenu;
	}

	public boolean getTable() {
		return table;
	}

	public boolean getAlta() {
		return alta;
	}

	public boolean getEdicion() {
		return edicion;

	}

	private void cargarCampañas() {
		campanas = this.serviceCampana.getAllCampana();
		setTipoCampana(new TipoCampanaDTO());

	}

	private void camposAbilitado() {
		vigencia = true;
	}

	/****** Setter & Getters ******/
	public List<TblCampana> getCampanas() {
		return campanas;
	}

	public void setCampanas(List<TblCampana> campanas) {
		this.campanas = campanas;
	}

	public ServiceCampana getServiceCampana() {
		return serviceCampana;
	}

	public List<CampanaDTO> getListaCampana() {
		return listaCampana;
	}

	public void setListaCampana(List<CampanaDTO> listaCampana) {
		this.listaCampana = listaCampana;
	}

	public void setServiceCampana(ServiceCampana serviceCampana) {
		this.serviceCampana = serviceCampana;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public TipoCampanaDTO getTipoCampana() {
		return tipoCampana;
	}

	public void setTipoCampana(TipoCampanaDTO tipoCampana) {
		this.tipoCampana = tipoCampana;
	}
}
