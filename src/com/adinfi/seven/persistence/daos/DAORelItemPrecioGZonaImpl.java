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
public class DAORelItemPrecioGZonaImpl extends AbstractDaoImpl<RelPrecioItemGzona> implements DAORelItemPrecioGZona {

    @Override
    public List<RelPrecioItemGzona> getAllRelPrecioGZoneByItem(CatItem item) {
        String query = "FROM RelPrecioItemGzona rel "
                + "INNER JOIN FETCH rel.catItem "
                + "INNER JOIN FETCH rel.catGZone "
                + "WHERE rel.catItem.code = ?";
        List<RelPrecioItemGzona> list = getHibernateTemplate().find(query, item.getCode());
        return list;
    }

    @Override
    public List<RelPrecioItemGzona> getAllRelPrecioGZoneByZone(CatZone zone) {
        String query = "FROM RelPrecioItemGzona rel "
                + "INNER JOIN FETCH rel.catItem "
                + "INNER JOIN FETCH rel.catGZone "
                + "WHERE rel.catGZone.code = ?";
        List<RelPrecioItemGzona> list = getHibernateTemplate().find(query, zone.getCode());
        return list;
    }

    @Override
    public List<RelPrecioItemGzona> getAllRelPrecioGZoneByItemCode(String code) {
        String query = "FROM RelPrecioItemGzona rel "
                + "INNER JOIN FETCH rel.catItem "
                + "INNER JOIN FETCH rel.catGZone "
                + "WHERE rel.catItem.code = ?";
        List<RelPrecioItemGzona> list = getHibernateTemplate().find(query, code);
        return list;
    }
    
}
