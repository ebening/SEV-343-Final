package com.adinfi.seven.presentation.views;

import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatSubCategory;
import com.adinfi.seven.business.services.ServiceCatSubCategory;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

public class MBCatSubCategory {
    
    private Logger LOG = Logger.getLogger(MBCatSubCategory.class);
    private String catalogNametitle = "SUBCATEGORÍAS";
    private ServiceCatSubCategory serviceCatSubCategory;
    private List<CatSubCategory> catSubCategoryList;
    private List<CatProveedor> catProveedorList;
    private List<CatCategory> catCategoryList;
    private CatSubCategory catSubCategory = null;
    private String errorMsg = "";
    
    @PostConstruct
    public void init(){
        getAllCatSubCategory();
        getAllCatProveedor();
        getAllCatCategoryList();
        catSubCategory = new CatSubCategory();
    }

    public void reset(){
        catSubCategory = new CatSubCategory();
    }

    public void setCatSubCategoryInfo(CatSubCategory catSubCategoryId){
        this.catSubCategory = catSubCategoryId;//this.serviceCatSubCategory.getCatSubCategoryById(CatSubCategory);
        LOG.info("CatSubCategory");
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
            catProveedorList = this.serviceCatSubCategory.getCatProveedorList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }
    
    private void getAllCatCategoryList(){
        try {
            catCategoryList = this.serviceCatSubCategory.getCatCategoryList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public void addCatSubCategory(){
        try {
            if (catSubCategory != null  && catSubCategory.getIdSubcategory() != null && catSubCategory.getIdSubcategory() != 0) {
                //catGZone.setIdGrupoZona(idGrupoZona);
            }

            if (catSubCategory.getCode().isEmpty()) {
                    errorMsg = "El nombre de la subcategoria no debe estar vacío";
            } else {
                boolean inserted = this.serviceCatSubCategory.crearCatSubCategory(catSubCategory);
                
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
    
    public void removeCatSubCategory(){

        boolean deleted = this.serviceCatSubCategory.eliminarCatSubCategory(catSubCategory);

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

    public ServiceCatSubCategory getServiceCatSubCategory() {
        return serviceCatSubCategory;
    }

    public void setServiceCatSubCategory(ServiceCatSubCategory serviceCatSubCategory) {
        this.serviceCatSubCategory = serviceCatSubCategory;
    }

    public List<CatSubCategory> getCatSubCategoryList() {
        return catSubCategoryList;
    }

    public void setCatSubCategoryList(List<CatSubCategory> catSubCategoryList) {
        this.catSubCategoryList = catSubCategoryList;
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

    public CatSubCategory getCatSubCategory() {
        return catSubCategory;
    }

    public void setCatSubCategory(CatSubCategory catSubCategory) {
        this.catSubCategory = catSubCategory;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    
    
    
}