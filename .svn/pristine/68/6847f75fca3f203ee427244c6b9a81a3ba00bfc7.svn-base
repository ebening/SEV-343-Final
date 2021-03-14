/**
 * 
 */
package com.adinfi.seven.business.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adinfi.seven.business.domain.TblExistenciaItemTienda;
import com.adinfi.seven.business.domain.TblListaSeleccion;
import com.adinfi.seven.business.domain.TblListaSeleccionSku;
import com.adinfi.seven.business.domain.TblVentasItem;
import com.adinfi.seven.persistence.daos.DAOCatalogs;
import com.adinfi.seven.persistence.daos.DAOExistenciaItemTienda;
import com.adinfi.seven.persistence.daos.DAOListaSeleccion;
import com.adinfi.seven.persistence.daos.DAOListaSeleccionSKU;
import com.adinfi.seven.persistence.daos.DAOVentasItem;
import com.adinfi.seven.persistence.dto.UsuarioDTO;

/**
 * @author OMAR
 *
 */
public class ServiceAnalisisImpl implements ServiceAnalisis {
	
	
	private DAOListaSeleccion daoListaSeleccion;
	
	private DAOListaSeleccionSKU daoListaSeleccionSKU;
	
	private DAOVentasItem daoVentasItem;
	
	private DAOExistenciaItemTienda daoExistenciaItemTienda;
	
	private DAOCatalogs daoCatalogs;

	
	
	@Override
	public List<TblListaSeleccion> getListaSeleccionbyUser(UsuarioDTO usuario)
			throws Exception {
		
		List<TblListaSeleccion> lista 	= new ArrayList<TblListaSeleccion>(); 
		int userId 						= usuario.getUserId();
		
		lista = daoListaSeleccion.getListaByUserId(userId);
		
		return lista;
	}
	
	

	@Override
	public Map<String,TblVentasItem> getListaVentaItemByIdTienda(Integer idTienda) throws Exception {
		
		List<TblVentasItem> lista 					= new ArrayList<TblVentasItem>(); 
		Map<String,TblVentasItem> mapVentasItem 	= new HashMap<String,TblVentasItem>();
		
		lista = daoVentasItem.getVentasItemByTiendaId(idTienda);
		
		for(TblVentasItem ventaItem:lista){
			String idItem =  ventaItem.getId().getIdItem() ;
			mapVentasItem.put(idItem, ventaItem);
		}
		
		return mapVentasItem;
	}
	
	
	@Override
	public Map<String, TblExistenciaItemTienda> getListaExistenciaItemByIdTienda(Integer idTienda) throws Exception {
		
		List<TblExistenciaItemTienda> lista 					= new ArrayList<TblExistenciaItemTienda>(); 
		Map<String,TblExistenciaItemTienda> mapExistenciaItem 	= new HashMap<String,TblExistenciaItemTienda>();
		
		lista = daoExistenciaItemTienda.getExistenciaItemByTiendaId(idTienda);
		
		for(TblExistenciaItemTienda existenciaItem : lista){
			String idItem =  existenciaItem.getId().getIdItem();
			mapExistenciaItem.put(idItem, existenciaItem);
		}
		
		return mapExistenciaItem;
	}
	
	

	@Override
	public BigDecimal getVentasHistoricasTodasLasTiendas(String idItem) throws Exception {
		
		Date fechaActual 					= new Date();
		Calendar calendar 					= Calendar.getInstance();
		BigDecimal sumaAnioActual 			= new BigDecimal(0);
		BigDecimal sumaAnioAnterior 		= new BigDecimal(0);
		int mes 							= 0;
		int anio 							= 0 ;
		int anioAnterior					= 0 ;
		
        calendar.setTime(fechaActual);
        anio = calendar.get(Calendar.YEAR);
        mes  = calendar.get(Calendar.MONTH) + 1;
        anioAnterior = anio -1 ;
		
		sumaAnioActual = daoVentasItem.getSumaVMontoAnioActualByIdItem(idItem, mes, anio);
		sumaAnioAnterior = daoVentasItem.getSumaVMontoAnioAnteriorByIdItem(idItem, mes, anioAnterior);
		
		if(sumaAnioActual==null){
			sumaAnioActual 	= new BigDecimal(0);
		}
		if(sumaAnioAnterior==null){
			sumaAnioAnterior 	= new BigDecimal(0);
		}
		
		return sumaAnioActual.add(sumaAnioAnterior);
	}
	
	
	@Override
	public BigDecimal getVentasHistoricasbyTienda(String idItem, int idTienda)
			throws Exception {
		BigDecimal sumaAnioTotal	= null;
		
		sumaAnioTotal = daoVentasItem.getSumaVMontoTotal(idItem, idTienda);
		
		return sumaAnioTotal; 
	}
	

	@Override
	public BigDecimal getInventarioItemByIdTienda(String idItem, int idTienda)
			throws Exception {
		BigDecimal inventario		= null;
		
		inventario = daoExistenciaItemTienda.getSumaExistenciaByTiendaId(idItem, idTienda);
		
		return inventario;
	}
	
	
	
	@Override
	public boolean validateExistListaSeleccion(int idUser, String nombre)
			throws Exception {
		Boolean result 							= Boolean.FALSE;
		TblListaSeleccion tblListaSeleccion 	= null;
		
		tblListaSeleccion = daoListaSeleccion.getListaByUserAndName(idUser, nombre);
		if(tblListaSeleccion != null){
			result = Boolean.TRUE;
		}
		
		return result;
	}
	
	@Override
	public int saveListaSeleccion(TblListaSeleccion tblListaSeleccion)
			throws Exception {
		int idListaSeleccion = 0 ;
		
		idListaSeleccion = daoListaSeleccion.saveListaSeleccion(tblListaSeleccion);
		
		return idListaSeleccion;
		
	}
	
	
	@Override
	public void saveListaSeleccionSKU(int idListaSeleccion,List<TblListaSeleccionSku> listaItems) throws Exception {
		
		List<TblListaSeleccionSku> nuevalistaItems = new ArrayList<TblListaSeleccionSku>();
		for(TblListaSeleccionSku item:listaItems){
			item.setIdLista(idListaSeleccion);
			nuevalistaItems.add(item);
		}
		
		daoListaSeleccionSKU.saveListaSeleccionSKU(nuevalistaItems);
		
	}
	
	@Override
	public void saveProductoListaSeleccionSKU(int idListaSeleccion,TblListaSeleccionSku item) throws Exception {
		
		List<TblListaSeleccionSku> nuevalistaItems = new ArrayList<TblListaSeleccionSku>();
		item.setIdLista(idListaSeleccion);
		nuevalistaItems.add(item);
		
		
		daoListaSeleccionSKU.saveListaSeleccionSKU(nuevalistaItems);
		
	}
	
	
	@Override
	public void updateListaSeleccionSKU(int idListaSeleccion,List<TblListaSeleccionSku> listaItems) throws Exception {
			
		for(TblListaSeleccionSku item:listaItems){
			item.setIdLista(idListaSeleccion);
			daoListaSeleccionSKU.update(item);
		}
		
	}
	
	
	
	
	

	@Override
	public List<TblListaSeleccionSku> getListaITemsByIdLista(Integer idLista)
			throws Exception {
		List<TblListaSeleccionSku> result = null;
		result = daoListaSeleccionSKU.getListaSeleccionByIdLista(idLista);
		
		return result;
	}
	
	@Override
	public boolean deleteListaItemsByIdLista(Integer idLista) throws Exception {
		boolean resultado 	= Boolean.FALSE;
		int result 			= 0;
		
		result = daoListaSeleccionSKU.deleteListaSeleccionByIdLista(idLista);
		if(result >0){
			resultado = Boolean.TRUE;
		}
		
		return resultado;
	}
	

	@Override
	public boolean deleteListaSeleccion(Integer idLista) throws Exception {
		boolean resultado 	= Boolean.FALSE;
		int result 			= 0;
		
		result = daoListaSeleccion.deleteListaSeleccion(idLista);
		if(result ==1){
			resultado = Boolean.TRUE;
		}
		
		return resultado;
	}
	
	
	@Override
	public TblListaSeleccion getListaById(Integer idLista) throws Exception {
		TblListaSeleccion resultado = new TblListaSeleccion();
		
		resultado = daoListaSeleccion.getListaById(idLista);
		return resultado;
	}
	
	
	@Override
	public void updateFechaListaSeleccion(Integer idLista) throws Exception {
		daoListaSeleccion.updateFechaModificacion(idLista);
		
	}
	
	

	@Override
	public void deleteSeleccionListByIdAndSKU(Integer idLista, List<String> listIdSeleccionSku)
			throws Exception {
		
		
		for(String sku :listIdSeleccionSku){
			daoListaSeleccionSKU.deleteSeleccionSKUByIds(idLista, sku);
		}
		
		
	}


	
	/***************************************************************************************/
	/** *********************************** GET & SET *********************************** **/
	/***************************************************************************************/
	

	/**
	 * @return the daoListaSeleccion
	 */
	public DAOListaSeleccion getDaoListaSeleccion() {
		return daoListaSeleccion;
	}

	/**
	 * @param daoListaSeleccion the daoListaSeleccion to set
	 */
	public void setDaoListaSeleccion(DAOListaSeleccion daoListaSeleccion) {
		this.daoListaSeleccion = daoListaSeleccion;
	}

	/**
	 * @return the daoListaSeleccionSKU
	 */
	public DAOListaSeleccionSKU getDaoListaSeleccionSKU() {
		return daoListaSeleccionSKU;
	}

	/**
	 * @param daoListaSeleccionSKU the daoListaSeleccionSKU to set
	 */
	public void setDaoListaSeleccionSKU(DAOListaSeleccionSKU daoListaSeleccionSKU) {
		this.daoListaSeleccionSKU = daoListaSeleccionSKU;
	}

	/**
	 * @return the daoVentasItem
	 */
	public DAOVentasItem getDaoVentasItem() {
		return daoVentasItem;
	}

	/**
	 * @param daoVentasItem the daoVentasItem to set
	 */
	public void setDaoVentasItem(DAOVentasItem daoVentasItem) {
		this.daoVentasItem = daoVentasItem;
	}

	/**
	 * @return the daoExistenciaItemTienda
	 */
	public DAOExistenciaItemTienda getDaoExistenciaItemTienda() {
		return daoExistenciaItemTienda;
	}

	/**
	 * @param daoExistenciaItemTienda the daoExistenciaItemTienda to set
	 */
	public void setDaoExistenciaItemTienda(
			DAOExistenciaItemTienda daoExistenciaItemTienda) {
		this.daoExistenciaItemTienda = daoExistenciaItemTienda;
	}


	/**
	 * @return the daoCatalogs
	 */
	public DAOCatalogs getDaoCatalogs() {
		return daoCatalogs;
	}


	/**
	 * @param daoCatalogs the daoCatalogs to set
	 */
	public void setDaoCatalogs(DAOCatalogs daoCatalogs) {
		this.daoCatalogs = daoCatalogs;
	}






}
