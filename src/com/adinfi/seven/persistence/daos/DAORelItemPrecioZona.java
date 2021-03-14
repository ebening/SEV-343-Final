/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.RelPrecioItemZona;
import java.util.List;

/**
 *
 * @author jdominguez
 */
public interface DAORelItemPrecioZona extends AbstractDao<RelPrecioItemZona>{
    
    public List<RelPrecioItemZona> getAllRelPrecioZonaByItem(CatItem item);
    public List<RelPrecioItemZona> getAllRelPrecioZonaByZona(CatZone zona);
    public List<RelPrecioItemZona> getAllRelByItemCode(String code);
    public List<RelPrecioItemZona> getRelByItemAndZone(String itemcode, String zonecode);
}
