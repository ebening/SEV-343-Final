package com.adinfi.seven.persistence.daos;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatSubCategory;

import java.util.List;

public interface DAOCatSubCategory extends AbstractDao<CatSubCategory> {
    
    //CatSubCategory getCatSubCategory(Integer subCategoryId);
    
    CatSubCategory getCatSubCategory(Integer subCategoryId, Integer categoryId);

    
    List<CatSubCategory> getCatSubCategoryList() throws GeneralException;
    
    List<CatProveedor> getCatProveedorList() throws GeneralException;
    
    List<CatCategory> getCatCategoryList() throws GeneralException;

    List<CatSubCategory> getCatSubCategoryByProveedor(Integer id);
   
    List<CatSubCategory> getCatSubCategoryByCategoryID(int id);
    
    CatSubCategory getCatSubCategoryByCatId(int subCatId, CatCategory catId);
    
}
