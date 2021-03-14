package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.business.domain.CatProveedor;

public interface DAOCatCategory extends AbstractDao<CatCategory> {
    
    public CatCategory getCatCategory(Integer idCategory);
    
    public CatCategory getCatCategoryByCode(String code);
    
    public List<CatCategory> getCatCategoryList() throws GeneralException;
    
    public List<CatDepto> getDeptoList() throws GeneralException;
    
    public List<CatProveedor> getProveedoresDeCategoria(int categoriaId) throws GeneralException;
    
    public List<CatCategory> getCatCategories(List<Integer> ids);
}
