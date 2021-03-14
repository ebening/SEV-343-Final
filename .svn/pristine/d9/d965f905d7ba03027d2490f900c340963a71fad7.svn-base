package com.adinfi.seven.business.services;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.business.domain.CatSubCategory;
import com.adinfi.seven.persistence.daos.DAOCatSubCategory;

public class ServiceCatSubCategoryImpl implements ServiceCatSubCategory {
    private final Logger LOG = Logger.getLogger(ServiceCatProveedor.class);
    private DAOCatSubCategory daoCatSubCategory;
    
    
    @Override
    public boolean crearCatSubCategory(CatSubCategory catSubCategory){
        try {
            daoCatSubCategory.saveOrUpdate(catSubCategory);
            return true;
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

    @Override
    public List<CatSubCategory> getCatSubCategoryList() throws Exception {
        return daoCatSubCategory.getCatSubCategoryList();
    }
    

    @Override
    public boolean eliminarCatSubCategory(CatSubCategory catSubCategory){
        try {
            daoCatSubCategory.delete(catSubCategory);
            return true;
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

//    @Override
//    public CatSubCategory getCatSubCategoryById(Integer catSubCategoryId){
//        try {
//            return daoCatSubCategory.getCatSubCategory(catSubCategoryId);
//        } catch (Exception e) {
//            LOG.error(e);
//        }
//        return null;
//    }
    
    @Override
    public CatSubCategory getCatSubCategoryById(Integer catSubCategoryId, Integer categoryId){
        try {
            return daoCatSubCategory.getCatSubCategory(catSubCategoryId,categoryId);
        } catch (Exception e) {
            LOG.error(e);
        }
        return null;
    }
    
    @Override
    public List<CatProveedor> getCatProveedorList() throws Exception {
        return daoCatSubCategory.getCatProveedorList();
    }
    
    @Override
    public List<CatCategory> getCatCategoryList() throws GeneralException{
        return daoCatSubCategory.getCatCategoryList();
    }

    public DAOCatSubCategory getDaoCatSubCategory() {
        return daoCatSubCategory;
    }

    public void setDaoCatSubCategory(DAOCatSubCategory daoCatSubCategory) {
        this.daoCatSubCategory = daoCatSubCategory;
    }

    @Override
    public List<CatSubCategory> getCatSubCategoryByIdProveedor(Integer id) {
            try {
                    return daoCatSubCategory.getCatSubCategoryByProveedor(id);
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return Collections.emptyList();
    }

    @Override
    public List<CatSubCategory> getCatSubCategoryByCategoryID(int id) {
        return daoCatSubCategory.getCatSubCategoryByCategoryID(id);
    }
    
    public CatSubCategory getCatSubCategoryByCatId(int subCatId, CatCategory catId){
        return daoCatSubCategory.getCatSubCategoryByCatId(subCatId, catId);
    }

    
}
