package com.adinfi.seven.business.services;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import java.util.List;

public interface ServiceCatProveedor {
    
    boolean crearCatProveedor(CatProveedor catProveedor);
    
    boolean eliminarCatProveedor(CatProveedor catProveedor);
    
    List<CatProveedor> getCatProveedorList() throws Exception;
    
    List<CatProveedor> getCatProveedorList(Integer idCategoria) throws Exception;
    
    CatProveedor getCatProveedorById(CatProveedor catProveedor);
    
    List<CatCategory> getCatCategoryList() throws GeneralException;
    
}
