/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.RelPrecioItemGzona;
import java.util.List;

/**
 *
 * @author jdominguez
 */
public interface DAORelItemPrecioGZona extends AbstractDao<RelPrecioItemGzona>{
    
    public List<RelPrecioItemGzona> getAllRelPrecioGZoneByItem(CatItem item);
    public List<RelPrecioItemGzona> getAllRelPrecioGZoneByZone(CatZone zone);
    public List<RelPrecioItemGzona> getAllRelPrecioGZoneByItemCode(String code);
}
