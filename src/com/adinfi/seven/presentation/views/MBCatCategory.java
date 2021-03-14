package com.adinfi.seven.presentation.views;

import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.business.services.ServiceCatCategory;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

public class MBCatCategory {
    
    private Logger LOG = Logger.getLogger(MBCatCategory.class);
    private String catalogNametitle = "CATEGORÍAS";
    private ServiceCatCategory serviceCatCategory;
    private List<CatCategory> catCategoryList;
    private List<CatDepto> catDeptoList;
    private CatCategory catCategory = null;
    private String errorMsg = "";
    
    @PostConstruct
    public void init(){
        getAllCatCategory();
        this.getAllCatDeptoList();
        catCategory = new CatCategory();
    }

    public void reset(){
        catCategory = new CatCategory();
    }

    public void setCatCategoryInfo(CatCategory catCategory){
        
        this.catCategory = catCategory;
        LOG.info("catCategory");
    }

    private void getAllCatCategory(){
        try {
            catCategoryList = this.serviceCatCategory.getCatCategoryList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }
    
    private void getAllCatDeptoList(){
        try {
            catDeptoList = this.serviceCatCategory.getDeptoList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public void addCatCategory(){
        try {
            if (catCategory != null  && catCategory.getIdCategory() != 0) {
                //catGZone.setIdGrupoZona(idGrupoZona);
            }

            if (catCategory.getCode().isEmpty()) {
                    errorMsg = "El nombre de la categoria no debe estar vacío";
            } else {
                boolean inserted = this.serviceCatCategory.crearCatCategory(catCategory);
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
    
    public void removeCatCategory(){

        boolean deleted = this.serviceCatCategory.eliminarCatCategory(catCategory);

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

    public ServiceCatCategory getServiceCatCategory() {
        return serviceCatCategory;
    }

    public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
        this.serviceCatCategory = serviceCatCategory;
    }

    public List<CatCategory> getCatCategoryList() {
        return catCategoryList;
    }

    public void setCatCategoryList(List<CatCategory> catCategoryList) {
        this.catCategoryList = catCategoryList;
    }

    public CatCategory getCatCategory() {
        return catCategory;
    }

    public void setCatCategory(CatCategory catCategory) {
        this.catCategory = catCategory;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<CatDepto> getCatDeptoList() {
        return catDeptoList;
    }

    public void setCatDeptoList(List<CatDepto> catDeptoList) {
        this.catDeptoList = catDeptoList;
    }
    
        
}