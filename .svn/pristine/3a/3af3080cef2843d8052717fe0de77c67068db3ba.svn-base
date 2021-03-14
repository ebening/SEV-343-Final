package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.CatItem;

public interface ServiceCatItem {

	public boolean crearCatItem(CatItem catItem);

	public boolean eliminarCatItem(CatItem catItem);

	public List<CatItem> getCatItemList() throws Exception;
        
	public List<CatItem> getCatItemListBySubCatID(int idSubCat, int idCat);

	public CatItem getCatItemById(CatItem catItem);

	public CatItem getCatItemById(String id);
        
	public List<CatItem> getCatItemByCategory(int idCat);

	public List<CatItem> getCatItemByProveedor(int idProveedor, List<Integer> stores);
}
