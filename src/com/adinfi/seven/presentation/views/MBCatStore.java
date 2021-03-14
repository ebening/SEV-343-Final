package com.adinfi.seven.presentation.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.services.ServiceCatStore;
import com.adinfi.seven.business.services.ServiceCatZone;
import com.adinfi.seven.presentation.views.util.Messages;

public class MBCatStore {
	private Logger LOG = Logger.getLogger(MBCatStore.class);
	private String catalogNametitle = "TIENDAS";
	private ServiceCatStore serviceCatStore;
	private ServiceCatZone serviceCatZone;
	private List<CatStore> catStoreList;	
	private List<CatZone> catZoneList;	
	private CatZone catZone = null;
	private CatStore catStore = null;
	private String errorMsg = "";
	
	@PostConstruct	
	public void init(){
		getAllCatStore();
		getAllCatZone();
		catStore = new CatStore();
		catZone = new CatZone();
	}
	
	public void reset(){
		catStore = new CatStore();
		catZone = new CatZone();
	
	}
	
	public void setCatStoreInfo(CatStore catStore){
		
		this.catStore = catStore;
//		this.catZone.setIdZone(catStore.getCatZone().getIdZone());
		
	}
	
	private void getAllCatZone(){
		catZoneList = null;
		try {
			catZoneList = serviceCatZone.getCatZoneList();
							
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	private void getAllCatStore(){
		try {
			catStoreList = serviceCatStore.getCatStoreList();
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void addCatStore(){
		try {
			if (catStore.getIdStore() == 0 || catStore.getIdStore() == 0) {
				Messages.mensajeError("El campo ID es requerido");
			} else if (catStore.getCode() == null || catStore.getCode().isEmpty()) {
				Messages.mensajeError("El campo CODIGO es requerido");
			} else if (catZone.getIdZone() == 0) {
				Messages.mensajeError("El campo ID_ZONA es requerido");
			} else {
					
//					catStore.setCatZone(serviceCatZone.getCatZoneById(catZone));
					
					boolean inserted = this.serviceCatStore.crearCatStore(catStore);
	
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
		getAllCatStore();
	}
	
	public void removeCatStore(){
		
		boolean deleted = this.serviceCatStore.eliminarCatStore(this.catStore);
		
		if (deleted) {
			errorMsg = "Registro eliminado con exito";
		} else {
			errorMsg = "El registro no pudo ser eliminado";
		}
		reset();
		getAllCatStore();
	}
			
	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}

	public ServiceCatStore getServiceCatStore() {
		return serviceCatStore;
	}

	public void setServiceCatStore(ServiceCatStore serviceCatStore) {
		this.serviceCatStore = serviceCatStore;
	}

	public List<CatStore> getCatStoreList() {
		return catStoreList;
	}

	public void setCatStoreList(List<CatStore> catStoreList) {
		this.catStoreList = catStoreList;
	}

	public CatStore getCatStore() {
		return catStore;
	}

	public void setCatStore(CatStore catStore) {
		this.catStore = catStore;
	}
	
	public List<CatZone> getcatZoneList() {
		return catZoneList;
	}

	public void setcatZoneList(List<CatZone> catZoneList) {
		this.catZoneList = catZoneList;
	}
	
	public ServiceCatZone getServiceCatZone() {
		return serviceCatZone;
	}

	public void setServiceCatZone(ServiceCatZone serviceCatZone) {
		this.serviceCatZone = serviceCatZone;
	}

	public CatZone getCatZone() {
		return catZone;
	}

	public void setCatZone(CatZone catZone) {
		this.catZone = catZone;
	}

}