package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatItem;

public interface DAOCatItem extends AbstractDao<CatItem> {

	public CatItem getCatItem(String idCatItem);

	public List<CatItem> getCatItemList(CatItem catItem) throws GeneralException;
        
    public List<CatItem> getCatItemListBySubCatID(int idSubCat, int idCat );
        
    public List<CatItem> getCatItemByCategory(int idCat);

    public List<CatItem> getCatItemByProveedor(int idProveedor, List<Integer> stores);
}
