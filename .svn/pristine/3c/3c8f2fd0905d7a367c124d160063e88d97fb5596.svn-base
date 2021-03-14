package com.adinfi.seven.business.services;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatSubCategory;

import java.util.List;

public interface ServiceCatSubCategory {
    
    boolean crearCatSubCategory(CatSubCategory catSubCategory);
    
    boolean eliminarCatSubCategory(CatSubCategory catSubCategory);
    
    List<CatSubCategory> getCatSubCategoryList() throws Exception;
    
//  CatSubCategory getCatSubCategoryById(Integer catSubCategoryId);
    CatSubCategory getCatSubCategoryById(Integer catSubCategoryId, Integer categoryId);
    
    List<CatProveedor> getCatProveedorList() throws Exception;
    
    List<CatCategory> getCatCategoryList() throws GeneralException;

    List<CatSubCategory> getCatSubCategoryByIdProveedor(Integer id);
    
    List<CatSubCategory> getCatSubCategoryByCategoryID(int id);
    
    CatSubCategory getCatSubCategoryByCatId(int subCatId, CatCategory catId);
    
}
