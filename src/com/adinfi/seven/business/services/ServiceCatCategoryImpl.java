package com.adinfi.seven.business.services;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.persistence.daos.DAOCatCategory;

public class ServiceCatCategoryImpl implements ServiceCatCategory {
    private final Logger LOG = Logger.getLogger(ServiceCatCategoryImpl.class);
    private DAOCatCategory daoCatCategory;
    
    
    @Override
    public boolean crearCatCategory(CatCategory catCategory){
        try {
            daoCatCategory.saveOrUpdate(catCategory);
            return true;
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

    @Override
    public List<CatCategory> getCatCategoryList() throws Exception {
        return daoCatCategory.getCatCategoryList();
    }
    

    @Override
    public boolean eliminarCatCategory(CatCategory catCategory){
        try {
            daoCatCategory.delete(catCategory);
            return true;
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

    @Override
    public CatCategory getCatCategoryById(int idCategory){
        try {
            return daoCatCategory.getCatCategory(idCategory);
        } catch (Exception e) {
            LOG.error(e);
        }
        return null;
    }
    
    @Override
    public List<CatCategory> getCatCategoryByIds(List<Integer> ids){
        try {
            return daoCatCategory.getCatCategories(ids);
        } catch (Exception e) {
            LOG.error(e);
        }
        return null;
    }
    
    @Override
    public List<CatDepto> getDeptoList() throws GeneralException{
        return daoCatCategory.getDeptoList();
    }

    public DAOCatCategory getDaoCatCategory() {
        return daoCatCategory;
    }

    public void setDaoCatCategory(DAOCatCategory daoCatCategory) {
        this.daoCatCategory = daoCatCategory;
    }
    
    @Override
	public List<CatProveedor> getProveedores(int categoriaId) {
		try {
			return daoCatCategory.getProveedoresDeCategoria(categoriaId);
		} catch (GeneralException e) {
			LOG.error(e);
		}
		return Collections.emptyList();
	}

	@Override
	public CatCategory getCatCategoryByCode(String code) {
		return daoCatCategory.getCatCategoryByCode(code);
	}
    
}
