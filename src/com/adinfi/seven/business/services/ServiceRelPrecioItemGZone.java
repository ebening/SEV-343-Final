/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.RelPrecioItemGzona;
import java.util.List;

/**
 *
 * @author jdominguez
 */
public interface ServiceRelPrecioItemGZone {
    public List<RelPrecioItemGzona> getAllRelItemGZoneByItem(CatItem item);
    public List<RelPrecioItemGzona> getAllRelItemGZoneByZone(CatZone zone);
    public List<RelPrecioItemGzona> getAllRelITemGZoneByItemCode(String code);
    public void saveRelItemGZone (RelPrecioItemGzona r);
}
