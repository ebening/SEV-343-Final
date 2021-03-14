package com.adinfi.seven.presentation.views;

import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.business.services.ServiceCatDepto;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

public class MBCatDepto {
    
    private Logger LOG = Logger.getLogger(MBCatDepto.class);
    private String catalogNametitle = "DEPARTAMENTOS";
    private ServiceCatDepto serviceCatDepto;
    private List<CatDepto> catDeptoList;
    private CatDepto catDepto = null;
    private String errorMsg = "";

    @PostConstruct	
    public void init(){
        getAllCatDepto();
        catDepto = new CatDepto();
    }

    public void reset(){
        catDepto = new CatDepto();
    }

    public void setCatDeptoInfo(CatDepto catDepto){
        
        this.catDepto = catDepto;
        LOG.info("catDepto");
    }

    private void getAllCatDepto(){
        try {
            catDeptoList = this.serviceCatDepto.getCatDeptoList();
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public void addCatDepto(){
        try {
            if (catDepto != null  && catDepto.getIdDepto() != 0) {
                //catGZone.setIdGrupoZona(idGrupoZona);
            }

            if (catDepto.getCode().isEmpty()) {
                    errorMsg = "El nombre de la zona no debe estar vacío";
            } else {
                boolean inserted = this.serviceCatDepto.crearCatDepto(catDepto);

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
        getAllCatDepto();
    }
    
    public void removeCatDepto(){

        boolean deleted = this.serviceCatDepto.eliminarCatDepto(catDepto);

        if (deleted) {
            init();
            errorMsg = "Registro eliminado con exito";
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

    public ServiceCatDepto getServiceCatDepto() {
        return serviceCatDepto;
    }

    public void setServiceCatDepto(ServiceCatDepto serviceCatDepto) {
        this.serviceCatDepto = serviceCatDepto;
    }

    public List<CatDepto> getCatDeptoList() {
        return catDeptoList;
    }

    public void setCatDeptoList(List<CatDepto> catDeptoList) {
        this.catDeptoList = catDeptoList;
    }

    public CatDepto getCatDepto() {
        return catDepto;
    }

    public void setCatDepto(CatDepto catDepto) {
        this.catDepto = catDepto;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
        
        
        
}