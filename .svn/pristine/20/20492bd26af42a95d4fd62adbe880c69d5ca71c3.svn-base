package com.adinfi.seven.presentation.views;

import java.util.List;
import javax.annotation.PostConstruct;

import com.adinfi.seven.presentation.views.util.Messages;
import org.apache.log4j.Logger;
import com.adinfi.seven.business.domain.CatTipoPromo;
import com.adinfi.seven.business.domain.RelFlujoAct;
import com.adinfi.seven.business.services.ServiceCatTipoPromo;
import com.adinfi.seven.presentation.views.util.Utileria;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

public class MBCatTipoPromo implements Serializable{
	
    private Logger LOG = Logger.getLogger(CatTipoPromo.class);
    private String catalogNametitle = "TIPO PROMOCION";
    private int idTipoPromo;
    private String nombre;
    private ServiceCatTipoPromo serviceCatTipoPromo;
    private List<CatTipoPromo> listCatTipoPromo = null;
    private CatTipoPromo catTipoPromo = null;
    private String errorMsg =  null;
    
    @PostConstruct
	public void init(){
    	getAllCatTipoPromos();
    	catTipoPromo = new CatTipoPromo();
    }
    
    public void setCatTipoPromoInfo(CatTipoPromo catTipoPromo){
      this.nombre = catTipoPromo.getNombre();
      this.idTipoPromo = catTipoPromo.getIdTipoPromo();
    }
 
    public void reset(){
    	this.nombre =  null;
    	this.idTipoPromo = 0;
    	this.catTipoPromo = null;
    	catTipoPromo = new CatTipoPromo();

    }
    
    public void getAllCatTipoPromos(){
    	try {
			listCatTipoPromo = serviceCatTipoPromo.getCatTipoPromos();
		} catch (Exception e) {
			LOG.error(e);
		}
    }
    
    public void addCatTipoPromo(){
    	try {
            catTipoPromo = new CatTipoPromo();
            if(idTipoPromo > 0){
                    catTipoPromo.setIdTipoPromo(idTipoPromo);
            }

            catTipoPromo.setNombre(nombre);

                if (catTipoPromo.getNombre().isEmpty()) {
                        Messages.mensajeErroneo("El nombre no debe estar vacío","");
                } else {
                        boolean inserted = this.serviceCatTipoPromo.crearCatTipoPromo(catTipoPromo);

                        if (inserted) {
                            errorMsg = "Registro guardado con exito";
                            Utileria.mensajeSatisfactorio("Guardado correctamente","Guardado correctamente");
                        } else {
                            errorMsg = "El registro no pudo ser guardado";
                            Utileria.mensajeErroneo("El registro no pudo ser guardado", "El registro no pudo ser guardado");
                        }
                }
                reset();
                getAllCatTipoPromos();
        } catch (Exception e) {
                LOG.error(e);
        }
    }
    
   
    public void eliminarCatTipoPromo(){
    	try {
    		catTipoPromo.setNombre(nombre);
    		catTipoPromo.setIdTipoPromo(idTipoPromo);

			if (serviceCatTipoPromo.tipoHavePromos(idTipoPromo)){
                Messages.mensajeAlerta("Tipo tiene promociones asociadas. NO es posible eliminar", "");
            }else{
                if (serviceCatTipoPromo.eliminarCatTipoPromo(catTipoPromo)) {
                    Utileria.mensajeSatisfactorio("Registro eliminado con exito", "Registro eliminado con exito");
                    errorMsg = "Registro eliminado con exito";

                } else {
                    Utileria.mensajeErroneo("El registro no pudo ser eliminado", "El registro no pudo ser eliminado");
                    errorMsg = "El registro no pudo ser eliminado";
                }
            }
		} catch (Exception e) {
			LOG.error(e);
                        Utileria.mensajeErroneo("El registro no pudo ser eliminado", "El registro no pudo ser eliminado");
			errorMsg = "El registro no pudo ser eliminado";
		}
    	
    	getAllCatTipoPromos();
		reset();
    }
    
	public Logger getLOG() {
		return LOG;
	}
	public void setLOG(Logger lOG) {
		LOG = lOG;
	}
	public String getCatalogNametitle() {
		return catalogNametitle;
	}
	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}
	public int getIdTipoPromo() {
		return idTipoPromo;
	}
	public void setIdTipoPromo(int idTipoPromo) {
		this.idTipoPromo = idTipoPromo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ServiceCatTipoPromo getServiceCatTipoPromo() {
		return serviceCatTipoPromo;
	}
	public void setServiceCatTipoPromo(ServiceCatTipoPromo serviceCatTipoPromo) {
		this.serviceCatTipoPromo = serviceCatTipoPromo;
	}
	public List<CatTipoPromo> getListCatTipoPromo() {
        Collections.sort(listCatTipoPromo, new Comparator<CatTipoPromo>() {
            @Override
            public int compare(CatTipoPromo o1, CatTipoPromo o2) {
                return (o1.getIdTipoPromo() > o2.getIdTipoPromo()) ? 1 : (o1.getIdTipoPromo() == o2.getIdTipoPromo()) ? 0 : -1;
            }
        });
		return listCatTipoPromo;
	}
	public void setListCatTipoPromo(List<CatTipoPromo> listCatTipoPromo) {
		this.listCatTipoPromo = listCatTipoPromo;
	}
	public CatTipoPromo getCatTipoPromo() {
		return catTipoPromo;
	}
	public void setCatTipoPromo(CatTipoPromo catTipoPromo) {
		this.catTipoPromo = catTipoPromo;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	

}
