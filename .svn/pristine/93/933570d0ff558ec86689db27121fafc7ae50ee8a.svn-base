package com.adinfi.seven.presentation.views;

import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.services.ServiceCatProveedor;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

public class MBCatProveedor {
    
    private Logger LOG = Logger.getLogger(MBCatProveedor.class);
    private String catalogNametitle = "PROVEEDORES";
    private ServiceCatProveedor serviceCatProveedor;
    private List<CatProveedor> catProveedorList;
    private List<CatCategory> catCategoryList;
    private CatProveedor catProveedor = null;
    private String errorMsg = "";
    
    @PostConstruct
    public void init(){
        getAllCatProveedor();
        this.getAllCatCategoryList();
        catProveedor = new CatProveedor();
    }

    public void reset(){
        catProveedor = new CatProveedor();
    }

    public void setCatProveedorInfo(CatProveedor catProveedor){
        
        this.catProveedor = catProveedor;
        LOG.info("catProveedor");
    }

    private void getAllCatProveedor(){
        try {
            catProveedorList = this.serviceCatProveedor.getCatProveedorList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }
    
    private void getAllCatCategoryList(){
        try {
            catCategoryList = this.serviceCatProveedor.getCatCategoryList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public void addCatProveedor(){
        try {
            if (catProveedor != null  && catProveedor.getIdProveedor() != 0) {
                //catGZone.setIdGrupoZona(idGrupoZona);
            }

            if (catProveedor.getCode().isEmpty()) {
                    errorMsg = "El nombre del proveedor no debe estar vacío";
            } else {
                boolean inserted = this.serviceCatProveedor.crearCatProveedor(catProveedor);
                
                if (inserted) {
                    this.init();
                    errorMsg = "Registro guardado con exito";
                    
                } else {
                    errorMsg = "El registro no pudo ser guardado";
                }
            }
        } catch (Exception e) {
                LOG.error(e);
        }
    }
    
    public void removeCatProveedor(){

        boolean deleted = this.serviceCatProveedor.eliminarCatProveedor(catProveedor);

        if (deleted) {
            errorMsg = "Registro eliminado con exito";
            init();
        } else {
            errorMsg = "El registro no pudo ser eliminado";
        }

    }

    public String getCatalogNametitle() {
        return catalogNametitle;
    }

    public void setCatalogNametitle(String catalogNametitle) {
        this.catalogNametitle = catalogNametitle;
    }

    public ServiceCatProveedor getServiceCatProveedor() {
        return serviceCatProveedor;
    }

    public void setServiceCatProveedor(ServiceCatProveedor serviceCatProveedor) {
        this.serviceCatProveedor = serviceCatProveedor;
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

    public CatProveedor getCatProveedor() {
        return catProveedor;
    }

    public void setCatProveedor(CatProveedor catProveedor) {
        this.catProveedor = catProveedor;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    
    
        
}