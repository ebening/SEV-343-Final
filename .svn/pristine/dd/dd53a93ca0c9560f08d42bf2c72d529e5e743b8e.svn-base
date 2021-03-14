package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.business.domain.CatProveedor;

public interface ServiceCatCategory {
    
	public boolean crearCatCategory(CatCategory catCategory);
    
	public boolean eliminarCatCategory(CatCategory catCategory);
    
	public List<CatCategory> getCatCategoryList() throws Exception;
    
	public CatCategory getCatCategoryById(int idCategory);
    
    public CatCategory getCatCategoryByCode(String code);
    
    public List<CatDepto> getDeptoList() throws GeneralException;
    
    public List<CatProveedor> getProveedores(int categoriaId);
    
    public List<CatCategory> getCatCategoryByIds(List<Integer> ids);
    
}
