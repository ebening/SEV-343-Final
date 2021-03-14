package com.adinfi.seven.presentation.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatTipoZona;
import com.adinfi.seven.business.services.ServiceCatTipoZona;
import com.adinfi.seven.presentation.views.util.Messages;

public class MBCatTipoZona {
	private Logger LOG = Logger.getLogger(MBCatTipoZona.class);
	private String catalogNametitle = "CATALOGO TIPO ZONA";
	private ServiceCatTipoZona serviceCatTipoZona;
	private List<CatTipoZona> catTipoZonaList;	
	private CatTipoZona catTipoZona = null;
	private String errorMsg = "";
	
	@PostConstruct	
	public void init(){
		getAllCatTipoZona();
		catTipoZona = new CatTipoZona();
	}
	
	public void reset(){
		catTipoZona = new CatTipoZona();
		
	}
	
	public void setCatTipoZonaInfo(CatTipoZona catTipoZona){
		this.catTipoZona = catTipoZona;
	}
	
	private void getAllCatTipoZona(){
		try {
			catTipoZonaList = serviceCatTipoZona.getCatTipoZonaList(); 
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void addCatTipoZona(){
		try {
			if (catTipoZona.getIdTipoZona() == 0) {
				Messages.mensajeError("El campo ID es requerido");
			} else if (catTipoZona.getCode() == null || catTipoZona.getCode().isEmpty()) {
				Messages.mensajeError("El campo CODIGO es requerido");
			} else {
				boolean inserted = this.serviceCatTipoZona.crearCatTipoZona(catTipoZona);

				if (inserted) {
					errorMsg = "Registro guardado con exito";
				} else {
					errorMsg = "El registro no pudo ser guardado";
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		
		reset();
		getAllCatTipoZona();
	}
	
	public void removeCatTipoZona(){
		
		boolean deleted = this.serviceCatTipoZona.eliminarCatTipoZona(this.catTipoZona);
		
		if (deleted) {
			errorMsg = "Registro eliminado con exito";
		} else {
			errorMsg = "El registro no pudo ser eliminado";
		}
		reset();
		getAllCatTipoZona();
	}
			
	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}

	public ServiceCatTipoZona getServiceCatTipoZona() {
		return serviceCatTipoZona;
	}

	public void setServiceCatTipoZona(ServiceCatTipoZona serviceCatTipoZona) {
		this.serviceCatTipoZona = serviceCatTipoZona;
	}

	public List<CatTipoZona> getCatTipoZonaList() {
		return catTipoZonaList;
	}

	public void setCatTipoZonaList(List<CatTipoZona> catTipoZonaList) {
		this.catTipoZonaList = catTipoZonaList;
	}

	public CatTipoZona getCatTipoZona() {
		return catTipoZona;
	}

	public void setCatTipoZona(CatTipoZona catTipoZona) {
		this.catTipoZona = catTipoZona;
	}

}