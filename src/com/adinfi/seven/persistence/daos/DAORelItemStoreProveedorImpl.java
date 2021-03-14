/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.RelItemStoreProveedor;
import java.util.List;

/**
 *
 * @author jdominguez
 */
public class DAORelItemStoreProveedorImpl extends AbstractDaoImpl<RelItemStoreProveedor> implements DAORelItemStoreProveedor{

    @Override
    public List<RelItemStoreProveedor> getItemStoreProvByItemId(String itemId) {
        String sentence = "FROM RelItemStoreProveedor table WHERE table.catItem.idItem = ?";
        return getHibernateTemplate().find(sentence, itemId);
    }

    @Override
    public List<RelItemStoreProveedor> getItemStoreProvByStoreId(int storeId) {
        String sentence = "FROM RelItemStoreProveedor table WHERE table.catStore.idStore = ?";
        return getHibernateTemplate().find(sentence, storeId);
    }

    @Override
    public List<RelItemStoreProveedor> getItemStoreProvByProvId(int provId) {
        String sentence = "FROM RelItemStoreProveedor table WHERE table.catProveedor.idProveedor = ?";
        return getHibernateTemplate().find(sentence, provId);
    }

    @Override
    public List<RelItemStoreProveedor> getItemStoreProvByItemAndStoreAndProv(String itemId, int storeId, int provId) {
        String sentence = "FROM RelItemStoreProveedor table WHERE table.catItem.idItem = ? AND table.catStore.idStore = ? AND table.catProveedor.idProveedor = ?";
        Object[] values = new Object[3];
        values[0] = itemId;
        values[1] = storeId;
        values[2] = provId;
        return getHibernateTemplate().find(sentence, values);
    }
    
}
