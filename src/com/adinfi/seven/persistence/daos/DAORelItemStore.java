/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.RelItemStore;
import com.adinfi.seven.business.domain.TblComponenteZonaPrecio;
import java.util.List;

/**
 *
 * @author jdominguez
 */
public interface DAORelItemStore extends AbstractDao<RelItemStore>{
    public List<RelItemStore> getAllItemStore();
    public List<RelItemStore> getItemStoreByStoreId(int storeId);
    public List<RelItemStore> getItemStoreByItemId(String itemId);
    public List<RelItemStore> getItemStoreByItemIdAndStoreId(String itemId, int storeId);
    public TblComponenteZonaPrecio getPromotionPriceByZone(TblComponenteZonaPrecio e, String sku);
    public List<RelItemStore> getItemStoreByItemId(List<String> itemId);
}
