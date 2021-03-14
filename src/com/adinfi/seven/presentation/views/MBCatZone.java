package com.adinfi.seven.presentation.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatTipoZona;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.services.ServiceCatGZone;
import com.adinfi.seven.business.services.ServiceCatTipoZona;
import com.adinfi.seven.business.services.ServiceCatZone;
import com.adinfi.seven.presentation.views.util.Messages;

public class MBCatZone {
	private Logger LOG = Logger.getLogger(MBCatZone.class);
	private String catalogNametitle = "ZONAS";
	private ServiceCatZone serviceCatZone;
	private ServiceCatGZone serviceCatGZone;
	private ServiceCatTipoZona serviceCatTipoZona;
	private List<CatZone> catZoneList;	
	private List<CatGZone> catGZoneList;
	private CatGZone catGZone = null;
	private CatTipoZona catTipoZona = null;
	private List<CatTipoZona> catTipoZonaList;
	private CatZone catZone = null;
	private String errorMsg = "";
	
	@PostConstruct	
	public void init(){
		getAllCatZone();
		getAllCatGZone();
		getAllCatTipoZona();
		catZone = new CatZone();
		catGZone = new CatGZone();
		catTipoZona = new CatTipoZona();
	}
	
	private void getAllCatGZone(){
		try {
			catGZoneList = serviceCatGZone.getCatGZoneList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	private void getAllCatTipoZona(){
		try {			
			catTipoZonaList = serviceCatTipoZona.getCatTipoZonaList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void reset(){
		catZone = new CatZone();
		catGZone = new CatGZone();
		catTipoZona = new CatTipoZona();		
	}
	
	public void setCatZoneInfo(CatZone catZone){
		this.catZone = catZone;
		this.catGZone.setIdGrupoZona(catZone.getCatGZone().getIdGrupoZona());
		this.catTipoZona.setIdTipoZona(catZone.getCatTipoZona().getIdTipoZona());
	}
	
	private void getAllCatZone(){
		catZoneList = null;
		try {
			catZoneList = serviceCatZone.getCatZoneList();
							
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void addCatZone(){
		try {
			if (catZone.getIdZone() == 0) {
				Messages.mensajeError("El campo ID es requerido");
			} else if (catZone.getCode() == null || catZone.getCode().isEmpty()) {
				Messages.mensajeError("El campo CODIGO es requerido");
			} else if (catGZone.getIdGrupoZona() == 0
					|| catGZone.getIdGrupoZona() == 0) {
				Messages.mensajeError("El campo ID_GRUPO es requerido");
			} else if (catTipoZona.getIdTipoZona() == 0
					|| catTipoZona.getIdTipoZona() == 0) {
				Messages.mensajeError("El campo TIPO es requerido");

			} else {

				catZone.setCatGZone(serviceCatGZone.getCatGZoneById(catGZone.getIdGrupoZona()));
				catZone.setCatTipoZona(serviceCatTipoZona.getCatTipoZonaById(catTipoZona));

				boolean inserted = this.serviceCatZone.crearCatZone(catZone);

				if (inserted) {
					errorMsg = "Registro guardado con exito";
					reset();
				} else {
					errorMsg = "El registro no se guardo";
					reset();
				}
			}
			
		} catch (Exception e) {
			LOG.error(e);
		}		
		
		getAllCatZone();
	}
	
	public void removeCatZone(){
		
		boolean deleted = this.serviceCatZone.eliminarCatZone(this.catZone);
		
		if (deleted) {
			errorMsg = "Registro eliminado con exito";
		} else {
			errorMsg = "El registro no pudo ser eliminado";
		}
		reset();
		getAllCatZone();
	}
			
	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}

	public ServiceCatZone getServiceCatZone() {
		return serviceCatZone;
	}

	public void setServiceCatZone(ServiceCatZone serviceCatZone) {
		this.serviceCatZone = serviceCatZone;
	}

	public List<CatZone> getcatZoneList() {
		return catZoneList;
	}

	public void setcatZoneList(List<CatZone> catZoneList) {
		this.catZoneList = catZoneList;
	}

	public CatZone getcatZone() {
		return catZone;
	}

	public void setcatZone(CatZone catZone) {
		this.catZone = catZone;
	}

	public ServiceCatGZone getServiceCatGZone() {
		return serviceCatGZone;
	}

	public void setServiceCatGZone(ServiceCatGZone serviceCatGZone) {
		this.serviceCatGZone = serviceCatGZone;
	}

	public ServiceCatTipoZona getServiceCatTipoZona() {
		return serviceCatTipoZona;
	}

	public void setServiceCatTipoZona(ServiceCatTipoZona serviceCatTipoZona) {
		this.serviceCatTipoZona = serviceCatTipoZona;
	}

	public List<CatGZone> getCatGZoneList() {
		return catGZoneList;
	}

	public void setCatGZoneList(List<CatGZone> catGZoneList) {
		this.catGZoneList = catGZoneList;
	}

	public List<CatTipoZona> getCatTipoZonaList() {
		return catTipoZonaList;
	}

	public void setCatTipoZonaList(List<CatTipoZona> catTipoZonaList) {
		this.catTipoZonaList = catTipoZonaList;
	}

	public CatGZone getCatGZone() {
		return catGZone;
	}

	public void setCatGZone(CatGZone catGZone) {
		this.catGZone = catGZone;
	}

	public CatTipoZona getCatTipoZona() {
		return catTipoZona;
	}

	public void setCatTipoZona(CatTipoZona catTipoZona) {
		this.catTipoZona = catTipoZona;
	}

}