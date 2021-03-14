package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.persistence.daos.DAOCatDepto;
import java.util.List;
import org.apache.log4j.Logger;

public class ServiceCatDeptoImpl implements ServiceCatDepto {
    private final Logger LOG = Logger.getLogger(ServiceCatDeptoImpl.class);
    private DAOCatDepto daoCatDepto;
    
    
    @Override
    public boolean crearCatDepto(CatDepto catDepto){
        try {
            daoCatDepto.saveOrUpdate(catDepto);
            return true;
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

    @Override
    public List<CatDepto> getCatDeptoList() throws Exception {
        return daoCatDepto.getAll();
    }
    

    @Override
    public boolean eliminarCatDepto(CatDepto catDepto){
        try {
            daoCatDepto.delete(catDepto);
            return true;
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

    @Override
    public CatDepto getCatDeptoById(CatDepto catDepto){
        try {
            return daoCatDepto.getCatDepto(catDepto.getIdDepto());
        } catch (Exception e) {
            LOG.error(e);
        }
        return null;
    }
    
    
    public DAOCatDepto getDaoCatDepto() {
        return daoCatDepto;
    }

    public void setDaoCatDepto(DAOCatDepto daoCatDepto) {
        this.daoCatDepto = daoCatDepto;
    }
    
}
