/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.RelItemStoreProveedor;
import com.adinfi.seven.persistence.daos.DAORelItemStoreProveedor;
import java.util.List;

/**
 *
 * @author jdominguez
 */
public class ServiceRelItemStoreProveedorImpl implements ServiceRelItemStoreProveedor{
    
    private DAORelItemStoreProveedor daoRelItemStoreProveedor;

    @Override
    public List<RelItemStoreProveedor> getItemStoreProvByItemId(String itemId) {
        return daoRelItemStoreProveedor.getItemStoreProvByItemId(itemId);
    }

    @Override
    public List<RelItemStoreProveedor> getItemStoreProvByStoreId(int storeId) {
        return daoRelItemStoreProveedor.getItemStoreProvByStoreId(storeId);
    }

    @Override
    public List<RelItemStoreProveedor> getItemStoreProvByProvId(int provId) {
        return daoRelItemStoreProveedor.getItemStoreProvByProvId(provId);
    }

    @Override
    public List<RelItemStoreProveedor> getItemStoreProvByItemAndStoreAndProv(String itemId, int storeId, int provId) {
        return daoRelItemStoreProveedor.getItemStoreProvByItemAndStoreAndProv(itemId, storeId, provId);
    }

    public DAORelItemStoreProveedor getDaoRelItemStoreProveedor() {
        return daoRelItemStoreProveedor;
    }

    public void setDaoRelItemStoreProveedor(DAORelItemStoreProveedor daoRelItemStoreProveedor) {
        this.daoRelItemStoreProveedor = daoRelItemStoreProveedor;
    }
    
    
}
