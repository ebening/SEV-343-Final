/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.RelItemStore;
import com.adinfi.seven.business.domain.TblComponenteZonaPrecio;
import com.adinfi.seven.persistence.daos.DAORelItemStore;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class ServiceRelItemStoreImpl implements ServiceRelItemStore{
    
    private DAORelItemStore daoRelItemStore;

    @Override
    public List<RelItemStore> getAllItemStore() {
        try {
            return daoRelItemStore.getAll();
        } catch (Exception ex) {
            Logger.getLogger(ServiceRelItemStoreImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<RelItemStore> getItemStoreByStoreId(int storeId) {
        return daoRelItemStore.getItemStoreByStoreId(storeId);
    }

    @Override
    public List<RelItemStore> getItemStoreByItemId(String itemId) {
        return daoRelItemStore.getItemStoreByItemId(itemId);
    }

    @Override
    public List<RelItemStore> getItemStoreByItemId(List<String> itemId) {
        return daoRelItemStore.getItemStoreByItemId(itemId);
    }

    @Override
    public List<RelItemStore> getItemStoreByItemIdAndStoreId(String itemId, int storeId) {
        return daoRelItemStore.getItemStoreByItemIdAndStoreId(itemId, storeId);
    }

    public DAORelItemStore getDaoRelItemStore() {
        return daoRelItemStore;
    }

    public void setDaoRelItemStore(DAORelItemStore daoRelItemStore) {
        this.daoRelItemStore = daoRelItemStore;
    }
    
    public TblComponenteZonaPrecio getPromotionPriceByZone(TblComponenteZonaPrecio e, String sku){
        return this.daoRelItemStore.getPromotionPriceByZone(e, sku);
    }
}
