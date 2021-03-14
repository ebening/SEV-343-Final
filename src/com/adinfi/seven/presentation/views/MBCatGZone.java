package com.adinfi.seven.presentation.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.services.ServiceCatGZone;
import com.adinfi.seven.presentation.views.util.Messages;

public class MBCatGZone {
	private Logger LOG = Logger.getLogger(MBCatGZone.class);
	private String catalogNametitle = "GRUPO ZONAS";
	private ServiceCatGZone serviceCatGZone;
	private List<CatGZone> catGZoneList;	

	private CatGZone catGZone = null;
	private String errorMsg = null;
	
	@PostConstruct	
	public void init(){
		getAllCatGZone();
		catGZone = new CatGZone();
	}
	
	public void reset(){
		catGZone = new CatGZone();		
	}
	
	public void setCatGZoneInfo(CatGZone catGZone){
		this.catGZone=catGZone;
		
		LOG.info("CatGZone");
	}
	
	private void getAllCatGZone(){
		try {
			catGZoneList = serviceCatGZone.getCatGZoneList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void addCatGZone(){
		try {
			if (catGZone.getIdGrupoZona() == 0) {
				Messages.mensajeError("El campo ID es requerido");
			} else if (catGZone.getCode().isEmpty()) {
				Messages.mensajeError("El campo CODIGO es requerido");
			} else {
				boolean inserted = this.serviceCatGZone.crearCatGZone(catGZone);

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
		getAllCatGZone();
	}
	
	public void removeCatGZone(){
		
		boolean deleted = this.serviceCatGZone.eliminarCatGZone(this.catGZone);
		
		if (deleted) {
			errorMsg = "Registro eliminado con exito";
		} else {
			errorMsg = "El registro no pudo ser eliminado";
		}
		
		reset();
		getAllCatGZone();
	}
			
	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}

	public ServiceCatGZone getServiceCatGZone() {
		return serviceCatGZone;
	}

	public void setServiceCatGZone(ServiceCatGZone serviceCatGZone) {
		this.serviceCatGZone = serviceCatGZone;
	}

	public List<CatGZone> getCatGZoneList() {
		return catGZoneList;
	}

	public void setCatGZoneList(List<CatGZone> catGZoneList) {
		this.catGZoneList = catGZoneList;
	}

	public CatGZone getCatGZone() {
		return catGZone;
	}

	public void setCatGZone(CatGZone catGZone) {
		this.catGZone = catGZone;
	}

}