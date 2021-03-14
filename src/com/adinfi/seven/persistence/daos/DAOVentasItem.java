/**
 * 
 */
package com.adinfi.seven.persistence.daos;


import java.math.BigDecimal;
import java.util.List;

import com.adinfi.seven.business.domain.TblVentasItem;

/**
 * @author OMAR
 *
 */
public interface DAOVentasItem extends AbstractDao<TblVentasItem>{
	
	List<TblVentasItem> getVentasItemByTiendaAndItemsId(Integer idTienda ,  List<String>  listIdItems ) throws Exception; 	
	List<TblVentasItem> getVentasItemByTiendaId(Integer idTienda)throws Exception; 	
	BigDecimal getSumaVMontoAnioActualByIdItem(String idItem, int mes , int anio) throws Exception;
	BigDecimal getSumaVMontoAnioAnteriorByIdItem(String idItem, int mes , int anio) throws Exception;
	BigDecimal getSumaVMontoTotal(String idItem,int idTienda)throws Exception;

}
