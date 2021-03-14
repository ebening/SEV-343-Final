package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.CatPromo;
import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.business.services.ServiceCatPrograma;
import com.adinfi.seven.business.services.ServiceCatSenal;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.Utileria;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;

public class MBCatSenal implements Serializable{
	
	private Logger LOG = Logger.getLogger(MBCatSenal.class);
	private String catalogNametitle = "SEÑALAMIENTO";
	
	private int idSenal;
	private int idPrograma;
	private String nombre;
	
	private ServiceCatSenal serviceCatSenal;
	private ServiceCatPrograma serviceCatPrograma;
	private List<CatSenal> listCatSenal;
	private List<CatPrograma> listCatPrograma;
	private CatSenal catSenal = null;
	private CatPrograma catPrograma;
    private String errorMsg =  null;
    
    
    @PostConstruct
    public void init(){
        System.out.println("Ejecutando init");
    	getAllSenalamientos();
    	getAllPrograms();
		catPrograma = new CatPrograma();
        this.idSenal = 0;
        this.idPrograma = 0;
        this.nombre = null;
    }
    
    private int getNextSenalId(){
        try {
            int max =0;
            for(CatSenal s :this.serviceCatSenal.getCatSenales()){
                if(s.getIdSenal() > max){
                    max = s.getIdSenal();
                }
            }
            return max + 1;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MBCatSenal.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public void setSenalInfo(CatSenal catSenal){
        System.out.println("ejecutando setSenalInfo");
        this.catSenal = catSenal;
    	this.idSenal = catSenal.getIdSenal();
    	this.nombre = catSenal.getNombre();
    	this.idPrograma = catSenal.getCatPrograma().getIdPrograma();
    	this.catPrograma = catSenal.getCatPrograma();
    }
    
    public void reset(){
        System.out.println("ejecutando reset");
    	this.idSenal = 0;
    	this.nombre = null;
    	this.idPrograma = 0;
		catPrograma = new CatPrograma();
        //this.idSenal = this.getNextSenalId();
    }
    
    public void addCatSenal(){
        System.out.println("Ejecutando addCatSenal");
    	if(this.nombre.isEmpty() == false){
    	catSenal = new CatSenal();
    		
    		if (this.idSenal != 0) {
				catSenal.setIdSenal(idSenal);
			}
    		
    		
    		catPrograma = new CatPrograma();
    		catPrograma.setIdPrograma(idPrograma);
    		catPrograma = this.serviceCatPrograma.getCatPrograma(catPrograma);
    		
    		catSenal.setCatPrograma(catPrograma);
    		
    		catSenal.setNombre(this.nombre);
    		
    		boolean insertado = getServiceCatSenal().crearCatSenal(catSenal);
    		
    		if(insertado){
    			LOG.info("Insertó Registro Señalamiento");
    			Utileria.mensajeSatisfactorio("Guardado correctamente","Guardado correctamente");
    		}else{
    			LOG.error("Falló Registro de Señalamiento");
    			Utileria.mensajeErroneo("Error al guardar","Error al guardar");
    		}
    	}else{
    		Messages.mensajeAlerta("Complete los campos obligatorios");
    		Utileria.mensajeAlerta("Complete los campos obligatoriosx","Complete los campos obligatoriosx");
    	}
    	
    	reset();
    	getAllSenalamientos();
    }
    
    public void deleteSenal(){
    	
    	//catSenal = new CatSenal();
    	//catSenal.setIdSenal(idSenal);
    	//catSenal.setNombre(nombre);
//    	catPrograma = new CatPrograma();
//		catPrograma.setIdPrograma(3);
//		catPrograma.setNombre("Programa3");
    	
		//catSenal.setCatPrograma(catPrograma);
    	
    	if(catSenal != null){
    	  boolean b = this.serviceCatSenal.eliminarCatSenal(catSenal);
    	  if(b){
              Utileria.mensajeSatisfactorio("Eliminado correctamente","Eliminado correctamente");
              LOG.info("Se eliminó");
              reset();
              getAllSenalamientos(); 
    	  }else{
                Utileria.mensajeErroneo("No se elimino el registro", "No se elimino el registro");
    		  LOG.error("No se eliminó");
    	  }
    	}else{
    		LOG.error("Elemento para eliminar nulo");
    	}
        
        this.getAllSenalamientos();
    }
    
    public void getAllSenalamientos(){
    	try {
			listCatSenal = getServiceCatSenal().getCatSenales();
		} catch (Exception e) {
			LOG.error(e);
		}
    }
    
    public void getAllPrograms(){
    	try {
    		
			listCatPrograma = serviceCatPrograma.getCatProgramas();
			
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
	public int getIdSenal() {
        System.out.println("regresando idSenal: " +  this.idSenal);
		return idSenal;
	}
	public void setIdSenal(int idSenal) {
		this.idSenal = idSenal;
	}
	public CatPrograma getCatPrograma() {
		return catPrograma;
	}
	public void setCatPrograma(CatPrograma catPrograma) {
		this.catPrograma = catPrograma;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ServiceCatSenal getServiceCatSenal() {
		return serviceCatSenal;
	}
	public void setServiceCatSenal(ServiceCatSenal serviceCatSenal) {
		this.serviceCatSenal = serviceCatSenal;
	}
	public List<CatSenal> getListCatSenal() {
        Collections.sort(listCatSenal, new Comparator<CatSenal>() {
            @Override
            public int compare(CatSenal o1, CatSenal o2) {
                return (o1.getIdSenal() > o2.getIdSenal()) ? 1 : (o1.getIdSenal() == o2.getIdSenal()) ? 0 : -1;
            }
        });
		return listCatSenal;
	}
	public void setListCatSenal(List<CatSenal> listCatSenal) {
		this.listCatSenal = listCatSenal;
	}
	public CatSenal getCatSenal() {
		return catSenal;
	}
	public void setCatSenal(CatSenal catSenal) {
		this.catSenal = catSenal;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getIdPrograma() {
        System.out.println("Regresando idPrograma: " + this.idPrograma);
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public List<CatPrograma> getListCatPrograma() {
        getAllPrograms();
		return listCatPrograma;
	}

	public void setListCatPrograma(List<CatPrograma> listCatPrograma) {
		this.listCatPrograma = listCatPrograma;
	}

	public ServiceCatPrograma getServiceCatPrograma() {
		return serviceCatPrograma;
	}

	public void setServiceCatPrograma(ServiceCatPrograma serviceCatPrograma) {
		this.serviceCatPrograma = serviceCatPrograma;
	}

	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}
    
	

}
