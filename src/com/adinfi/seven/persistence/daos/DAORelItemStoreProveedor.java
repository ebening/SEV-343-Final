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
public interface DAORelItemStoreProveedor extends AbstractDao<RelItemStoreProveedor>{
    public List<RelItemStoreProveedor> getItemStoreProvByItemId(String itemId);
    public List<RelItemStoreProveedor> getItemStoreProvByStoreId(int storeId);
    public List<RelItemStoreProveedor> getItemStoreProvByProvId(int provId);
    public List<RelItemStoreProveedor> getItemStoreProvByItemAndStoreAndProv(String itemId, int storeId, int provId);
}
