package com.adinfi.seven.presentation.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatListDet;
import com.adinfi.seven.business.domain.CatLista;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatSubCategory;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceCatListDet;
import com.adinfi.seven.business.services.ServiceCatLista;
import com.adinfi.seven.business.services.ServiceCatProveedor;
import com.adinfi.seven.business.services.ServiceCatSubCategory;
import com.adinfi.seven.presentation.views.util.Messages;

public class MBCatListDet {
	
	private Logger LOG = Logger.getLogger(MBCatListDet.class);
	private String catalogNametitle = "CAT_LISTA_DET";
	
	private String idItem;
	
	private CatListDet catListDet;
	private CatProveedor catProveedor;
	private CatCategory catCategory;
	private CatSubCategory catSubCategory;
	private CatLista catLista;
	
	private List<CatProveedor> catProveedorList;
	private List<CatCategory> catCategoryList;
	private List<CatSubCategory> catSubCategoryList;
	private List<CatLista> catListas;
	private List<CatListDet> catListDets;
	
	private ServiceCatProveedor serviceCatProveedor;
	private ServiceCatCategory serviceCatCategory;
	private ServiceCatSubCategory serviceCatSubCategory;
	private ServiceCatLista serviceCatLista;
	private ServiceCatListDet serviceCatListDet;
	
	private String errorMsg = "";
	
	@PostConstruct	
	public void init(){ 
		getAllCatCategory();
		getAllCatListDet();
		getAllCatProveedor();
		getAllCatSubCategory();
		getAllCatLista();
		catListDet = new CatListDet();
		catProveedor = new CatProveedor();
		catCategory = new CatCategory();
		catSubCategory = new CatSubCategory();
		catLista = new CatLista();
		reset();
	}
	
	public void reset(){
		catListDet = new CatListDet();		
		catProveedor = new CatProveedor();
		catCategory = new CatCategory();
		catSubCategory = new CatSubCategory();
		catLista = new CatLista();
	}
	
	
	
	public void setCatListDetInfo(CatListDet catListDet){
		
		this.catListDet = catListDet;
		
//		this.catCategory.setCategoryId(catListDet.getCatCategory().getCategoryId());
//		this.catSubCategory.setSubCategoryId(catListDet.getCatSubCategory().getSubCategoryId());
//		this.catProveedor.setIdProveedor(catListDet.getCatProveedor().getIdProveedor());
//		this.catLista.setIdLista(catListDet.getCatLista().getIdLista());
		
	}
	
    private void getAllCatCategory(){
        try {
            catCategoryList = this.serviceCatCategory.getCatCategoryList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }
    
	private void getAllCatSubCategory(){
        try {
            catSubCategoryList = this.serviceCatSubCategory.getCatSubCategoryList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }
	
    private void getAllCatProveedor(){
        try {
            catProveedorList = this.serviceCatProveedor.getCatProveedorList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }
    
    private void getAllCatLista(){
    	
    	try {
    		catListas = serviceCatLista.getCatListaList();
		} catch (Exception e) {
			LOG.error(e);
		}
    }
    
	private void getAllCatListDet(){	    	
		catListDets = new ArrayList<CatListDet>();
		try {
	    		catListDets = serviceCatListDet.getCatListDetList();
			} catch (Exception e) {
				LOG.error(e);
			}
	    }
    
	
	public void addCatListDet(){
		
	/*	if(this.catCategory.getCategoryId() > 0){
			this.catListDet.setCatCategory(this.catCategory);
		}
		
		if(this.catSubCategory.getSubCategoryId() > 0){
			this.catListDet.setCatSubCategory(this.catSubCategory);
		}
		
		if(this.catProveedor!= null && this.catProveedor.getIdProveedor() > 0){
			this.catListDet.setCatProveedor(this.catProveedor);
		}
		
		if(this.catLista.getIdLista() > 0){
			this.catListDet.setCatLista(this.catLista);
		}
		
		if(this.catListDet.getIdItem() == 0 ||
			this.catListDet.getIdItem() == null){
			Messages.mensajeError("El campo ID_ITEM es requerido");
		} */
				
		
		if(this.catListDet.getCode().isEmpty()){
			Messages.mensajeError("El campo CODIGO es requerido");
		}else{
		   this.serviceCatListDet.crearCatListDet(this.catListDet);
		}
	    
		reset();
		getAllCatListDet();
	}
	
	public void removeCatListDet(){
		
		boolean deleted = this.serviceCatListDet.eliminarCatListDet(this.catListDet);
		
		if (deleted) {
			errorMsg = "Registro eliminado con exito";
		} else {
			errorMsg = "El registro no pudo ser eliminado";
		}
		reset();
		getAllCatListDet();
	}	
			
	public String getCatalogNametitle() {
		return catalogNametitle;
	}

	public void setCatalogNametitle(String catalogNametitle) {
		this.catalogNametitle = catalogNametitle;
	}

	public CatProveedor getCatProveedor() {
		return catProveedor;
	}

	public void setCatProveedor(CatProveedor catProveedor) {
		this.catProveedor = catProveedor;
	}

	public CatCategory getCatCategory() {
		return catCategory;
	}

	public void setCatCategory(CatCategory catCategory) {
		this.catCategory = catCategory;
	}

	public CatSubCategory getCatSubCategory() {
		return catSubCategory;
	}

	public void setCatSubCategory(CatSubCategory catSubCategory) {
		this.catSubCategory = catSubCategory;
	}

	public CatLista getCatLista() {
		return catLista;
	}

	public void setCatLista(CatLista catLista) {
		this.catLista = catLista;
	}

	public List<CatProveedor> getCatProveedorList() {
		return catProveedorList;
	}

	public void setCatProveedorList(List<CatProveedor> catProveedorList) {
		this.catProveedorList = catProveedorList;
	}

	public List<CatCategory> getCatCategoryList() {
		return catCategoryList;
	}

	public void setCatCategoryList(List<CatCategory> catCategoryList) {
		this.catCategoryList = catCategoryList;
	}

	public List<CatSubCategory> getCatSubCategoryList() {
		return catSubCategoryList;
	}

	public void setCatSubCategoryList(List<CatSubCategory> catSubCategoryList) {
		this.catSubCategoryList = catSubCategoryList;
	}

	public List<CatLista> getCatListas() {
		return catListas;
	}

	public void setCatListas(List<CatLista> catListas) {
		this.catListas = catListas;
	}

	public ServiceCatProveedor getServiceCatProveedor() {
		return serviceCatProveedor;
	}

	public void setServiceCatProveedor(ServiceCatProveedor serviceCatProveedor) {
		this.serviceCatProveedor = serviceCatProveedor;
	}

	public ServiceCatCategory getServiceCatCategory() {
		return serviceCatCategory;
	}

	public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
		this.serviceCatCategory = serviceCatCategory;
	}

	public ServiceCatSubCategory getServiceCatSubCategory() {
		return serviceCatSubCategory;
	}

	public void setServiceCatSubCategory(ServiceCatSubCategory serviceCatSubCategory) {
		this.serviceCatSubCategory = serviceCatSubCategory;
	}

	public ServiceCatLista getServiceCatLista() {
		return serviceCatLista;
	}

	public void setServiceCatLista(ServiceCatLista serviceCatLista) {
		this.serviceCatLista = serviceCatLista;
	}

	public String getIdItem() {
		return idItem;
	}

	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}

	public ServiceCatListDet getServiceCatListDet() {
		return serviceCatListDet;
	}

	public void setServiceCatListDet(ServiceCatListDet serviceCatListDet) {
		this.serviceCatListDet = serviceCatListDet;
	}

	public CatListDet getCatListDet() {
		return catListDet;
	}

	public void setCatListDet(CatListDet catListDet) {
		this.catListDet = catListDet;
	}

	public List<CatListDet> getCatListDets() {
		return catListDets;
	}

	public void setCatListDets(List<CatListDet> catListDets) {
		this.catListDets = catListDets;
	}


}