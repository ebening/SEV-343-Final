package com.adinfi.seven.persistence.daos;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import java.util.List;

public interface DAOCatProveedor extends AbstractDao<CatProveedor> {
    
    CatProveedor getCatProveedor(Integer idProveedor);
    
    List<CatProveedor> getCatProveedorList() throws GeneralException;
    
    List<CatProveedor> getCatProveedorList(Integer idCategoria) throws GeneralException;
    
    List<CatCategory> getCatCategoryList() throws GeneralException;
    
}
