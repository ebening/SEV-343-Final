package com.adinfi.seven.presentation.views;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.services.ServiceCatPrograma;
import com.adinfi.seven.presentation.views.util.Utileria;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

public class MBCatPrograma implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger LOG = Logger.getLogger(CatPrograma.class);
    private String catalogNametitle = "PROGRAMAS";
    private ServiceCatPrograma serviceCatPrograma; 
    private List<CatPrograma> catProgramasList;
    private Integer idPrograma = null;
    private String  nombre;
    private CatPrograma catPrograma = null;
    private String errorMsg =  null;
	
    @PostConstruct
    public void init(){
            getAllCatPrograms();
            catPrograma =  new CatPrograma();
    }


    public void reset(){
            catPrograma =  new CatPrograma();
            nombre = "";
            idPrograma = null;
    }

    public void getAllCatPrograms(){
            try {
                catProgramasList = serviceCatPrograma.getCatProgramas();
            } catch (Exception e) {
                LOG.error(e);
            }
    }


    public void setCatGProgramaInfo(CatPrograma catPrograma){
            this.idPrograma = catPrograma.getIdPrograma();
            this.nombre = catPrograma.getNombre();
    }

    public void addCatPrograma(){
        System.out.println("Guardando programa");
        try {
            catPrograma = new CatPrograma();
            if (idPrograma != null && idPrograma != 0) {
                System.out.println("asignando id a programa");
                catPrograma.setIdPrograma(idPrograma);
            }else{
                System.out.println("id programa nulo o cero");
            }

            if(nombre != null && nombre.length() > 0){
                catPrograma.setNombre(nombre);
            }
            
            catPrograma.setNombre(nombre);
            if (catPrograma.getNombre().isEmpty()) {
                    errorMsg = "El nombre no debe estar vacío";
                    Utileria.mensajeErroneo("Ingrese por favor el nombre del programa.", "Ingrese por favor el nombre del programa.");
            } else {
                if (serviceCatPrograma.crearCatPrograma(catPrograma)) {
                    Utileria.mensajeSatisfactorio("Registro guardado con exito", "Registro guardado con exito");
                } else {
                    Utileria.mensajeErroneo("El registro no pudo ser guardado", "El registro no pudo ser guardado");
                }
            }
            
        } catch (Exception e) {
                LOG.error(e);
        }

        reset();
        getAllCatPrograms();
    }

    public void removePrograma(){
        
    /*    for(CatPrograma cp : catProgramasList){
            if(cp.getIdPrograma() == idPrograma){
                catPrograma = cp;
                break;
            }
        } */
//            catPrograma.setIdPrograma(idPrograma);
//            catPrograma.setNombre(nombre);
        catPrograma = serviceCatPrograma.getCatPrograma(idPrograma);
        if (serviceCatPrograma.eliminarCatPrograma(catPrograma)) {
                errorMsg = "Registro eliminado con exito";
                Utileria.mensajeSatisfactorio("Registro eliminado con exito", "Registro eliminado con exito");
        } else {
                errorMsg = "El registro no pudo ser eliminado";
                Utileria.mensajeErroneo("El registro no pudo ser eliminado", "El registro no pudo ser eliminado");
        }

        reset();
        getAllCatPrograms();
    }
	
	
	
    public String getCatalogNametitle() {
            return catalogNametitle;
    }
    public void setCatalogNametitle(String catalogNametitle) {
            this.catalogNametitle = catalogNametitle;
    }
    public ServiceCatPrograma getServiceCatPrograma() {
            return serviceCatPrograma;
    }
    public void setServiceCatPrograma(ServiceCatPrograma serviceCatPrograma) {
            this.serviceCatPrograma = serviceCatPrograma;
    }
    public List<CatPrograma> getCatProgramasList() {
            Collections.sort(catProgramasList, new Comparator<CatPrograma>() {
                @Override
                public int compare(CatPrograma o1, CatPrograma o2) {
                    return (o1.getIdPrograma() > o2.getIdPrograma()) ? 1 : (o1.getIdPrograma() == o2.getIdPrograma()) ? 0 : -1;
                }
            });
            return catProgramasList;
    }
    public void setCatProgramasList(List<CatPrograma> catProgramasList) {
            this.catProgramasList = catProgramasList;
    }
    public Integer getIdPrograma() {
            return idPrograma;
    }
    public void setIdPrograma(Integer idPrograma) {
            this.idPrograma = idPrograma;
    }
    public String getNombre() {
            return nombre;
    }
    public void setNombre(String nombre) {
            this.nombre = nombre;
    }
    public CatPrograma getCatPrograma() {
            return catPrograma;
    }
    public void setCatPrograma(CatPrograma catPrograma) {
            this.catPrograma = catPrograma;
    }
    public Logger getLOG() {
            return LOG;
    }
    public void setLOG(Logger lOG) {
            LOG = lOG;
    }
    public String getErrorMsg() {
            return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
    }      
}
