/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.RelPrecioItemGzona;
import com.adinfi.seven.persistence.daos.DAORelItemPrecioGZona;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdominguez
 */
public class ServiceRelPrecioItemGZoneImpl implements ServiceRelPrecioItemGZone{

    private DAORelItemPrecioGZona daoPrecioItemGZone;
    
    @Override
    public List<RelPrecioItemGzona> getAllRelItemGZoneByItem(CatItem item) {
        return daoPrecioItemGZone.getAllRelPrecioGZoneByItem(item);
    }

    @Override
    public List<RelPrecioItemGzona> getAllRelItemGZoneByZone(CatZone zone) {
        return daoPrecioItemGZone.getAllRelPrecioGZoneByZone(zone);
    }

    public DAORelItemPrecioGZona getDaoPrecioItemGZone() {
        return daoPrecioItemGZone;
    }

    public void setDaoPrecioItemGZone(DAORelItemPrecioGZona daoPrecioItemGZone) {
        this.daoPrecioItemGZone = daoPrecioItemGZone;
    }

    @Override
    public void saveRelItemGZone(RelPrecioItemGzona r) {
        try {
            daoPrecioItemGZone.save(r);
        } catch (Exception ex) {
            Logger.getLogger(ServiceRelPrecioItemGZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<RelPrecioItemGzona> getAllRelITemGZoneByItemCode(String code) {
        return daoPrecioItemGZone.getAllRelPrecioGZoneByItemCode(code);
    }
}
