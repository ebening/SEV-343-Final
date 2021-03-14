/**
 * 
 */
package com.adinfi.seven.persistence.daos;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;


import com.adinfi.seven.business.domain.TblVentasItem;

/**
 * @author OMAR
 *
 */
public class DAOVentasItemImpl extends AbstractDaoImpl<TblVentasItem> implements DAOVentasItem {

	
	@Override
	public List<TblVentasItem> getVentasItemByTiendaAndItemsId(Integer idTienda,List<String> listIdItems) throws Exception {
		
		StringBuilder query = new StringBuilder("FROM TblVentasItem tvi WHERE tvi.id.idTienda= ? AND tvi.id.idItem  IN ( ");

		if (listIdItems != null) {
			int tamanoLista =listIdItems.size();
			int siguiente = 0;
			for(int iterador=0;iterador<tamanoLista;iterador++){
				String idItem = listIdItems.get(iterador);
				query.append( "'"+idItem +"'");
				siguiente = iterador + 1;
				if( siguiente < tamanoLista){
					query.append( ",");
				}
			}
			query.append("       ) ");
		}
		Iterator<TblVentasItem> listVentasItem = getHibernateTemplate().iterate(query.toString(), new Object[] {  idTienda });
		return toInitializedList(listVentasItem);
	}

	
	@Override
	public List<TblVentasItem> getVentasItemByTiendaId(Integer idTienda)
			throws Exception {
		
		Iterator<TblVentasItem> itListaVentasItem = null ;
		itListaVentasItem =	getHibernateTemplate().iterate(" FROM TblVentasItem tvi WHERE tvi.id.idTienda= ?  ", new Object[] { idTienda });
		return toInitializedList(itListaVentasItem);
	}

	
	@Override
	public BigDecimal getSumaVMontoAnioActualByIdItem(String idItem,
			int mes, int anio) throws Exception {
		String query = "SELECT SUM(tvi.vmonto)  FROM TblVentasItem tvi WHERE tvi.id.idItem = ? AND tvi.id.anio = ? AND tvi.id.mes <= ?";
		
		Iterator<BigDecimal> sumavmonto = getHibernateTemplate().iterate(query, new Object[] { idItem,anio,mes });
		List<BigDecimal> listaResult= toInitializedList(sumavmonto);
		
		if(listaResult.size()>0){
			return listaResult.get(0);
		}else{
			return BigDecimal.ZERO;
		}
	}

	
	@Override
	public BigDecimal getSumaVMontoAnioAnteriorByIdItem(String idItem,int mes, int anio) throws Exception {
		String query = "SELECT SUM(tvi.vmonto)  FROM TblVentasItem tvi WHERE tvi.id.idItem = ?  AND tvi.id.anio = ? AND tvi.id.mes > ?";
		
		Iterator<BigDecimal> sumavmonto = getHibernateTemplate().iterate(query, new Object[] { idItem,anio,mes });
		List<BigDecimal> listaResult= toInitializedList(sumavmonto);
		
		if(listaResult.size()>0){
			return listaResult.get(0);
		}else{
			return  BigDecimal.ZERO;
		}
	}

	
	@Override
	public BigDecimal getSumaVMontoTotal(String idItem, int idTienda)throws Exception {
		String query = "SELECT SUM(tvi.vmonto)  FROM TblVentasItem tvi WHERE tvi.id.idItem = ? AND tvi.id.idTienda = ?";
		
		Iterator<BigDecimal> sumavmonto = getHibernateTemplate().iterate(query, new Object[] { idItem,idTienda });
		List<BigDecimal> listaResult= toInitializedList(sumavmonto);
		
		if(listaResult.size()>0){
			return listaResult.get(0);
		}else{
			return BigDecimal.ZERO;
		}
	}
	
	
	


	

}
