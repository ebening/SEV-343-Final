/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.RelPrecioItemZona;
import com.adinfi.seven.persistence.daos.DAORelItemPrecioZona;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class ServiceRelPrecioItemZoneImpl implements ServiceRelPrecioItemZone{
    
    private DAORelItemPrecioZona daoRelPrecioZone;

    @Override
    public List<RelPrecioItemZona> getAllRelPrecioZoneByItem(CatItem item) {
        return daoRelPrecioZone.getAllRelPrecioZonaByItem(item);
    }

    @Override
    public List<RelPrecioItemZona> getAllRelPrecioZoneByZone(CatZone zone) {
        return daoRelPrecioZone.getAllRelPrecioZonaByZona(zone);
    }

    public DAORelItemPrecioZona getDaoRelPrecioZone() {
        return daoRelPrecioZone;
    }

    public void setDaoRelPrecioZone(DAORelItemPrecioZona daoRelPrecioZone) {
        this.daoRelPrecioZone = daoRelPrecioZone;
    }

    @Override
    public List<RelPrecioItemZona> getAllRelPrecioByCode(String code) {
        return daoRelPrecioZone.getAllRelByItemCode(code);
    }

    @Override
    public void saveRelPrecioItemZona(RelPrecioItemZona r) {
        try {
            daoRelPrecioZone.save(r);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ServiceRelPrecioItemZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<RelPrecioItemZona> getRelByItemAndZone(String itemcode, String zonecode) {
        return daoRelPrecioZone.getRelByItemAndZone(itemcode, zonecode);
    }
    
    
}
