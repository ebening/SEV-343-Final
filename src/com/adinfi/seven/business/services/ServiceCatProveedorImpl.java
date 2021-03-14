package com.adinfi.seven.business.services;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.persistence.daos.DAOCatProveedor;

import java.util.List;

import org.apache.log4j.Logger;

public class ServiceCatProveedorImpl implements ServiceCatProveedor {
    private final Logger LOG = Logger.getLogger(ServiceCatProveedor.class);
    private DAOCatProveedor daoCatProveedor;
    
    
    @Override
    public boolean crearCatProveedor(CatProveedor catProveedor){
        try {
            daoCatProveedor.saveOrUpdate(catProveedor);
            return true;
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

    @Override
    public List<CatProveedor> getCatProveedorList() throws Exception {
        return daoCatProveedor.getAll();
    }
    
    @Override
    public List<CatProveedor> getCatProveedorList(Integer idCategoria) throws Exception {
    	return daoCatProveedor.getCatProveedorList(idCategoria);
    }
    

    @Override
    public boolean eliminarCatProveedor(CatProveedor catProveedor){
        try {
            daoCatProveedor.delete(catProveedor);
            return true;
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

    @Override
    public CatProveedor getCatProveedorById(CatProveedor catProveedor){
        try {
            return daoCatProveedor.getCatProveedor(catProveedor.getIdProveedor());
        } catch (Exception e) {
            LOG.error(e);
        }
        return null;
    }
    
    @Override
    public List<CatCategory> getCatCategoryList() throws GeneralException{
        return daoCatProveedor.getCatCategoryList();
    }
    
    public DAOCatProveedor getDaoCatProveedor() {
        return daoCatProveedor;
    }

    public void setDaoCatProveedor(DAOCatProveedor daoCatProveedor) {
        this.daoCatProveedor = daoCatProveedor;
    }
    
}
