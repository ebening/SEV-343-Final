package com.adinfi.seven.presentation.views;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.CatPromo;
import com.adinfi.seven.business.domain.CatTipoPromo;
import com.adinfi.seven.business.services.ServiceCatPromo;
import com.adinfi.seven.business.services.ServiceCatTipoPromo;
import com.adinfi.seven.presentation.views.util.Utileria;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

public class MBCatPromo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String catalogNametitle = "PROMOCION";
	private Logger LOG = Logger.getLogger(CatPrograma.class);
	private CatPromo catPromo = null;
	private List<CatPromo> catPromosList;
	private List<CatTipoPromo> catTipoPromosList;
	private ServiceCatPromo serviceCatPromo;
	private int idPromo;
	private int idTipoPromo;
	private CatTipoPromo catTipoPromo;
	private ServiceCatTipoPromo serviceCatTipoPromo;
	private String nombre;
	private String errorMsg = null;

	@PostConstruct
	public void init() {
		getAllPromos();
		getAllTipoPromos();
		catPromo = new CatPromo();
		catTipoPromo = new CatTipoPromo();
	}

	public void getAllPromos() {
		try {
			catPromosList = serviceCatPromo.getCatPromos();
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void getAllTipoPromos(){
		try {
			catTipoPromosList = this.serviceCatTipoPromo.getCatTipoPromos();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public void reset() {
		this.idPromo = 0;
		this.nombre = null;
		catPromo = new CatPromo();
	}

	public void setInfoPromo(CatPromo catPromo) {
		this.nombre = catPromo.getNombre();
		this.idPromo = catPromo.getIdPromo();
		this.idTipoPromo = catPromo.getCatTipoPromo().getIdTipoPromo();
		this.catTipoPromo = catPromo.getCatTipoPromo();
	}

	public void addPromo() {

		try {
			catPromo = new CatPromo();
			if (this.idPromo > 0) {
				catPromo.setIdPromo(idPromo);
			}
			
			/*Tipo de promo*/
			catTipoPromo = new CatTipoPromo();
			catTipoPromo.setIdTipoPromo(this.idTipoPromo);
			catTipoPromo = this.serviceCatTipoPromo.getCatTipoPromo(catTipoPromo);
			
			catPromo.setNombre(this.nombre);
			catPromo.setCatTipoPromo(catTipoPromo);


			if (catPromo.getNombre() != null && catPromo.getNombre().isEmpty() == false) {

				boolean record = this.serviceCatPromo.crearCatPromo(catPromo);

				if (record) {
					LOG.info("se inserto");
				} else {
					LOG.info("no se inserto");
				}

			} else {
				LOG.error("Nombre vacio");
                Utileria.mensajeErroneo("Ingrese por favor el nombre de la promoción.", "Ingrese por favor el nombre de la promoción.");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		reset();
		getAllPromos();

	}
	
	
	public void removePromo(){
		try {
			catPromo.setIdPromo(this.idPromo);
			catPromo.setNombre(this.nombre);
			boolean removed = this.serviceCatPromo.eliminarCatPromo(catPromo);
			
			if(removed){
				LOG.info("Eliminado");
			}else{
				LOG.error("no eliminado");
			}
			
			reset();
			getAllPromos();
			
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public Logger getLOG() {
		return LOG;
	}

	public void setLOG(Logger lOG) {
		LOG = lOG;
	}

	public CatPromo getCatPromo() {
		return catPromo;
	}

	public void setCatPromo(CatPromo catPromo) {
		this.catPromo = catPromo;
	}

	public List<CatPromo> getCatPromosList() {
        Collections.sort(catPromosList, new Comparator<CatPromo>() {
            @Override
            public int compare(CatPromo o1, CatPromo o2) {
                return (o1.getIdPromo() > o2.getIdPromo()) ? 1 : (o1.getIdPromo() == o2.getIdPromo()) ? 0 : -1;
            }
        });
		return catPromosList;
	}

	public void setCatPromosList(List<CatPromo> catPromosList) {
		this.catPromosList = catPromosList;
	}

	public ServiceCatPromo getServiceCatPromo() {
		return serviceCatPromo;
	}

	public void setServiceCatPromo(ServiceCatPromo serviceCatPromo) {
		this.serviceCatPromo = serviceCatPromo;
	}

	public int getIdPromo() {
		return idPromo;
	}

	public void setIdPromo(int idPromo) {
		this.idPromo = idPromo;
	}

	public CatTipoPromo getCatTipoPromo() {
		return catTipoPromo;
	}

	public void setCatTipoPromo(CatTipoPromo catTipoPromo) {
		this.catTipoPromo = catTipoPromo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}

	public ServiceCatTipoPromo getServiceCatTipoPromo() {
		return serviceCatTipoPromo;
	}

	public void setServiceCatTipoPromo(ServiceCatTipoPromo serviceCatTipoPromo) {
		this.serviceCatTipoPromo = serviceCatTipoPromo;
	}

	public List<CatTipoPromo> getCatTipoPromosList() {
		return catTipoPromosList;
	}

	public void setCatTipoPromosList(List<CatTipoPromo> catTipoPromosList) {
		this.catTipoPromosList = catTipoPromosList;
	}

	public int getIdTipoPromo() {
		return idTipoPromo;
	}

	public void setIdTipoPromo(int idTipoPromo) {
		this.idTipoPromo = idTipoPromo;
	}
	

}
