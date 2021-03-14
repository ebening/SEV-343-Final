/**
 * 
 */
package com.adinfi.seven.business.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.adinfi.seven.business.domain.TblExistenciaItemTienda;
import com.adinfi.seven.business.domain.TblListaSeleccion;
import com.adinfi.seven.business.domain.TblListaSeleccionSku;
import com.adinfi.seven.business.domain.TblVentasItem;
import com.adinfi.seven.persistence.dto.UsuarioDTO;

/**
 * @author OMAR
 *
 */
public interface ServiceAnalisis {
	
	
	List<TblListaSeleccion> 				getListaSeleccionbyUser(UsuarioDTO usuario) throws Exception;
	Map<String,TblVentasItem> 				getListaVentaItemByIdTienda(Integer idTienda) throws Exception;
	Map<String,TblExistenciaItemTienda> 	getListaExistenciaItemByIdTienda(Integer idTienda) throws Exception;
	BigDecimal 								getVentasHistoricasTodasLasTiendas(String idItem)throws Exception;
	BigDecimal 								getVentasHistoricasbyTienda(String idItem, int idTienda)throws Exception;
	BigDecimal 								getInventarioItemByIdTienda(String idItem, int idTienda)throws Exception;
	TblListaSeleccion 						getListaById(Integer idLista)throws Exception;
	List<TblListaSeleccionSku> 				getListaITemsByIdLista(Integer idLista)throws Exception;
	
	
	boolean validateExistListaSeleccion(int idUser,String nombre)throws Exception;
	
	
	int 	saveListaSeleccion (TblListaSeleccion tblListaSeleccion) throws Exception;
	void 	saveListaSeleccionSKU(int idListaSeleccion, List<TblListaSeleccionSku> listaItems)throws Exception;
	void 	saveProductoListaSeleccionSKU(int idListaSeleccion, TblListaSeleccionSku item)throws Exception;
	
	
	void updateListaSeleccionSKU(int idListaSeleccion, List<TblListaSeleccionSku> listaItems)throws Exception;
	void updateFechaListaSeleccion(Integer idLista)throws Exception;
	
	
	boolean deleteListaItemsByIdLista(Integer idLista)throws Exception;
	boolean deleteListaSeleccion (Integer idLista)throws Exception;
	void 	deleteSeleccionListByIdAndSKU (Integer idLista, List<String> listSKU)throws Exception;

}
