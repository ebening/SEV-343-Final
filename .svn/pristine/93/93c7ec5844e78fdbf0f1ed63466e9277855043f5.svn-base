/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.DoubleType;

import com.adinfi.seven.business.domain.RelItemStore;
import com.adinfi.seven.business.domain.TblComponenteZonaPrecio;
import com.google.common.base.Joiner;

/**
 *
 * @author jdominguez
 */
public class DAORelItemStoreImpl extends AbstractDaoImpl<RelItemStore> implements DAORelItemStore{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
    public List<RelItemStore> getAllItemStore() {
        String sentence = "FROM RelItemStore";
        return getHibernateTemplate().find(sentence);
    }

	@SuppressWarnings("unchecked")
    @Override
    public List<RelItemStore> getItemStoreByStoreId(int storeId) {
        String sentence = "FROM RelItemStore table WHERE table.catStore.idStore = ?";
        return getHibernateTemplate().find(sentence, storeId);
    }

	@SuppressWarnings("unchecked")
    @Override
    public List<RelItemStore> getItemStoreByItemId(String itemId) {
        String sentence = "FROM RelItemStore table WHERE table.catItem.idItem = ?";
        return getHibernateTemplate().find(sentence, itemId);
    }

	@SuppressWarnings("unchecked")
    @Override
    public List<RelItemStore> getItemStoreByItemId(List<String> itemId) {
		String in = Joiner.on("','").join(itemId);
		if(!in.isEmpty()){
			in = "'"+in+"'";
	        String sentence = "FROM RelItemStore table WHERE table.catItem.idItem in ("+in+")";
	        return getHibernateTemplate().find(sentence);
		}
		return new ArrayList<>();
    }

	@SuppressWarnings("unchecked")
    @Override
    public List<RelItemStore> getItemStoreByItemIdAndStoreId(String itemId, int storeId) {
        String sentece = "FROM RelItemStore table WHERE table.catItem.idItem = ? AND table.catStore.idStore = ?";
        Object[] values = new Object [2];
        values[0] = itemId;
        values[1] = storeId;
        return getHibernateTemplate().find(sentece, values);
    }
 
	@SuppressWarnings("unchecked")
    @Override
    public TblComponenteZonaPrecio getPromotionPriceByZone(TblComponenteZonaPrecio e, String sku){
        
        System.out.println("Buscando precio actual de elemento: " + sku +", en zona: " + e.getZonaId());
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT T.PRECIO_ATUAL PRECIO_ATUAL, T.MARGEN MARGEN, T.PORC_MARGEN PORC_MARGEN, T.IMPUESTO IMPUESTO FROM (");
        sb.append("SELECT DISTINCT PRECIO_ATUAL, MARGEN, PORC_MARGEN, IMPUESTO,  ROW_NUMBER() OVER () NO ");
        sb.append("FROM SEV343DEV.REL_ZONE_STORE RZS, SEV343DEV.REL_ITEM_STORE RIS ");
        sb.append("WHERE RZS.ID_ZONE = :idZone AND RZS.ID_STORE = RIS.ID_STORE AND RIS.ID_ITEM = :idItem ) T WHERE NO = 1");
        
        System.out.println("consulta: " + sb.toString());
        
        SQLQuery query = this.getSession().createSQLQuery(sb.toString());
        query.addScalar("PRECIO_ATUAL", new DoubleType());
        query.addScalar("MARGEN", new DoubleType());
        query.addScalar("PORC_MARGEN", new DoubleType());
        query.addScalar("IMPUESTO", new DoubleType());
        
        query.setParameter("idZone", e.getZonaId());
        query.setParameter("idItem", sku);
        
        List<Object[]> rows = query.list();
        
        if(rows == null || rows.size() == 0){
            System.out.println("no se encontro el precio actual de venta del elemento");
            e.setPrecioProducto(0D);
            e.setMargenRegularProducto(0D);
            e.setPorcentajeMargenRegularProducto(0D);
            e.setImpuesto(0D);
        }else{
            System.out.println("obteniendo resultados:");
            for(Object[] row : rows){
                e.setPrecioProducto(Double.parseDouble(row[0].toString()));
                e.setMargenRegularProducto(Double.parseDouble(row[1].toString()));
                e.setPorcentajeMargenRegularProducto(Double.parseDouble(row[2].toString()));
                e.setImpuesto(Double.parseDouble(row[3].toString()));
            }
        }
        
        return e;
    }
    
    
}
