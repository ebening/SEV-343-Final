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
public class DAORelItemPrecioZonaImpl extends AbstractDaoImpl<RelPrecioItemZona> implements DAORelItemPrecioZona  {

    @Override
    public List<RelPrecioItemZona> getAllRelPrecioZonaByItem(CatItem item) {
        String query = "FROM RelPrecioItemZona rel "
                + "INNER JOIN FETCH rel.catItem "
                + "INNER JOIN FETCH rel.catZone "
                + "WHERE rel.catItem.idItem = ?";
        List<RelPrecioItemZona> list = getHibernateTemplate().find(query, item.getIdItem());
        return list;
    }

    @Override
    public List<RelPrecioItemZona> getAllRelPrecioZonaByZona(CatZone zona) {
        String query = "FROM RelPrecioItemZona rel "
                + "INNER JOIN FETCH rel.catItem "
                + "INNER JOIN FETCH rel.catZone "
                + "WHERE rel.catZone.idGrupoZona = ?";
        List<RelPrecioItemZona> list = getHibernateTemplate().find(query, zona.getIdZone());
        return list;
    }

    @Override
    public List<RelPrecioItemZona> getAllRelByItemCode(String code) {
        String query = "FROM RelPrecioItemZona rel "
                + "INNER JOIN FETCH rel.catItem "
                + "INNER JOIN FETCH rel.catZone "
                + "WHERE rel.catItem.code = ?";
        List<RelPrecioItemZona> list = getHibernateTemplate().find(query, code);
        return list;
    }

    @Override
    public List<RelPrecioItemZona> getRelByItemAndZone(String itemcode, String zonecode) {
        String query = "FROM RelPrecioItemZona rel "
                + "INNER JOIN FETCH rel.catItem "
                + "INNER JOIN FETCH rel.catZone "
                + "WHERE rel.catItem.code = ? "
                + "AND rel.catZone.idZone = ?";
        Object[] params = new Object[]{itemcode, Integer.valueOf(zonecode)};
        List<RelPrecioItemZona> list = getHibernateTemplate().find(query, params );
        return list;
    }
}
