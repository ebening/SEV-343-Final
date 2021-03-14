/**
 * 
 */
package com.adinfi.seven.business.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblArticulosEspacios;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;
import com.adinfi.seven.business.domain.TblFolletoTienda;
import com.adinfi.seven.business.domain.TblImageArticulo;
import com.adinfi.seven.business.domain.TblPrensa;
import com.adinfi.seven.business.domain.TblPrensaSistemaVenta;
import com.adinfi.seven.business.domain.TblPrensaTienda;
import com.adinfi.seven.business.domain.TblReporteInventario;
import com.adinfi.seven.business.domain.TblReporteVentas;
import com.adinfi.seven.persistence.daos.DAOArticulosEspacio;
import com.adinfi.seven.persistence.daos.DAOArticulosHoja;
import com.adinfi.seven.persistence.daos.DAOFolleto;
import com.adinfi.seven.persistence.daos.DAOFolletoSistemaVenta;
import com.adinfi.seven.persistence.daos.DAOFolletoTienda;
import com.adinfi.seven.persistence.daos.DAOImageArticulo;
import com.adinfi.seven.persistence.daos.DAOPrensa;
import com.adinfi.seven.persistence.daos.DAOPrensaSistemaVenta;
import com.adinfi.seven.persistence.daos.DAOPrensaTienda;
import com.adinfi.seven.persistence.daos.DAOReporteInventario;
import com.adinfi.seven.persistence.daos.DAOReporteVentas;
import com.adinfi.seven.persistence.dto.CabeceraReporteDTO;
import com.adinfi.seven.persistence.dto.ReporteInventarioSkuDTO;
import com.adinfi.seven.persistence.dto.ReporteVentaSkuDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.DTOUtil;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.ReporteInventarioUtil;
import com.adinfi.seven.presentation.views.util.ReporteVentasUtil;

/**
 * @author OMAR
 * 
 */
public class ServiceReporteImpl implements ServiceReporte {
	private Logger LOG = Logger.getLogger(ServiceReporteImpl.class);
	private DAOArticulosEspacio daoArticulosEspacio;

	private DAOArticulosHoja daoArticulosHoja;

	private DAOFolletoTienda daoFolletoTienda;

	private DAOPrensaTienda daoPrensaTienda;

	private DAOReporteVentas daoReporteVentas;

	private DAOReporteInventario daoReporteInventario;

	private DAOFolleto daoFolleto;

	private DAOPrensa daoPrensa;

	private DAOPrensaSistemaVenta daoPrensaSistemaVenta;

	private DAOFolletoSistemaVenta daoFolletoSistemaVenta;

	private DAOImageArticulo daoImageArticulo;

	/**
	 * @return the daoPrensaSistemaVenta
	 */
	public DAOPrensaSistemaVenta getDaoPrensaSistemaVenta() {
		return daoPrensaSistemaVenta;
	}

	/**
	 * @param daoPrensaSistemaVenta
	 *            the daoPrensaSistemaVenta to set
	 */
	public void setDaoPrensaSistemaVenta(
			DAOPrensaSistemaVenta daoPrensaSistemaVenta) {
		this.daoPrensaSistemaVenta = daoPrensaSistemaVenta;
	}

	/**
	 * @return the daoFolletoSistemaVenta
	 */
	public DAOFolletoSistemaVenta getDaoFolletoSistemaVenta() {
		return daoFolletoSistemaVenta;
	}

	/**
	 * @param daoFolletoSistemaVenta
	 *            the daoFolletoSistemaVenta to set
	 */
	public void setDaoFolletoSistemaVenta(
			DAOFolletoSistemaVenta daoFolletoSistemaVenta) {
		this.daoFolletoSistemaVenta = daoFolletoSistemaVenta;
	}

	/**
	 * @return the daoImageArticulo
	 */
	public DAOImageArticulo getDaoImageArticulo() {
		return daoImageArticulo;
	}

	/**
	 * @param daoImageArticulo
	 *            the daoImageArticulo to set
	 */
	public void setDaoImageArticulo(DAOImageArticulo daoImageArticulo) {
		this.daoImageArticulo = daoImageArticulo;
	}

	/**
	 * @return the daoArticulosEspacio
	 */
	public DAOArticulosEspacio getDaoArticulosEspacio() {
		return daoArticulosEspacio;
	}

	/**
	 * @param daoArticulosEspacio
	 *            the daoArticulosEspacio to set
	 */
	public void setDaoArticulosEspacio(DAOArticulosEspacio daoArticulosEspacio) {
		this.daoArticulosEspacio = daoArticulosEspacio;
	}

	/**
	 * @return the daoArticulosHoja
	 */
	public DAOArticulosHoja getDaoArticulosHoja() {
		return daoArticulosHoja;
	}

	/**
	 * @param daoArticulosHoja
	 *            the daoArticulosHoja to set
	 */
	public void setDaoArticulosHoja(DAOArticulosHoja daoArticulosHoja) {
		this.daoArticulosHoja = daoArticulosHoja;
	}

	/**
	 * @return the daoReporteVentas
	 */
	public DAOReporteVentas getDaoReporteVentas() {
		return daoReporteVentas;
	}

	/**
	 * @param daoReporteVentas
	 *            the daoReporteVentas to set
	 */
	public void setDaoReporteVentas(DAOReporteVentas daoReporteVentas) {
		this.daoReporteVentas = daoReporteVentas;
	}

	/**
	 * @return the daoFolletoTienda
	 */
	public DAOFolletoTienda getDaoFolletoTienda() {
		return daoFolletoTienda;
	}

	/**
	 * @param daoFolletoTienda
	 *            the daoFolletoTienda to set
	 */
	public void setDaoFolletoTienda(DAOFolletoTienda daoFolletoTienda) {
		this.daoFolletoTienda = daoFolletoTienda;
	}

	/**
	 * @return the daoPrensaTienda
	 */
	public DAOPrensaTienda getDaoPrensaTienda() {
		return daoPrensaTienda;
	}

	/**
	 * @param daoPrensaTienda
	 *            the daoPrensaTienda to set
	 */
	public void setDaoPrensaTienda(DAOPrensaTienda daoPrensaTienda) {
		this.daoPrensaTienda = daoPrensaTienda;
	}

	/**
	 * @return the daoFolleto
	 */
	public DAOFolleto getDaoFolleto() {
		return daoFolleto;
	}

	/**
	 * @param daoFolleto
	 *            the daoFolleto to set
	 */
	public void setDaoFolleto(DAOFolleto daoFolleto) {
		this.daoFolleto = daoFolleto;
	}

	/**
	 * @return the daoPrensa
	 */
	public DAOPrensa getDaoPrensa() {
		return daoPrensa;
	}

	/**
	 * @param daoPrensa
	 *            the daoPrensa to set
	 */
	public void setDaoPrensa(DAOPrensa daoPrensa) {
		this.daoPrensa = daoPrensa;
	}

	/**
	 * @return the daoReporteInventario
	 */
	public DAOReporteInventario getDaoReporteInventario() {
		return daoReporteInventario;
	}

	/**
	 * @param daoReporteInventario
	 *            the daoReporteInventario to set
	 */
	public void setDaoReporteInventario(
			DAOReporteInventario daoReporteInventario) {
		this.daoReporteInventario = daoReporteInventario;
	}

	/**
	 * FUNCIONALIDAD
	 */

	/**
	 * Calcula el Reporte de Venta para un Folleto
	 */
	@Override
	public List<ReporteVentaSkuDTO> calcularReporteVentaSKUFolleto(String folio)
			throws GeneralException {

		List<TblReporteVentas> reporteVentasTienda 				= new ArrayList<TblReporteVentas>();
		List<TblReporteVentas> reporteVentasArticulos 			= new ArrayList<TblReporteVentas>();
		List<TblReporteVentas> reporteTotal 					= new ArrayList<TblReporteVentas>();
		List<ReporteVentaSkuDTO> resultadoReporte 				= new ArrayList<ReporteVentaSkuDTO>();
		List<TblArticulosHoja> listaArticulos		 			= new ArrayList<TblArticulosHoja>();
		List<TblFolletoTienda> listaTiendas 					= new ArrayList<TblFolletoTienda>();
		TblFolleto folleto 										= null;
		int idFolleto 											= Integer.parseInt(folio);

		try {
			folleto = validacionFolleto(folio);
			listaArticulos = daoArticulosHoja.getArticulosByIdFolleto(idFolleto);
			listaTiendas = daoFolletoTienda.getTiendasIdByFolletoId(idFolleto);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}

		if (listaArticulos.size() > 0) {
			reporteVentasArticulos = getReporteVentasBySKUFolleto(listaArticulos);
		}

		if (listaTiendas.size() > 0) {
			reporteVentasTienda = getReporteVentasByTiendasFolleto(listaTiendas);
		}		

		reporteTotal.addAll(reporteVentasArticulos);
		reporteTotal.addAll(reporteVentasTienda);
		reporteTotal.addAll( getReporteVentasByDate(folleto.getFechaInicio(),folleto.getFechaFin()) );

		reporteTotal = ReporteVentasUtil.addElementosUnicosVenta(reporteTotal);

		resultadoReporte.addAll(DTOUtil
				.tblReporteVentasToReporteVentaSkuDTO(reporteTotal));

		return resultadoReporte;
	}

	/**
	 * Calcula el Reporte de Venta para una Prensa
	 */
	@Override

	public List<ReporteVentaSkuDTO> calcularReporteVentaSKUPrensa(String folio) throws GeneralException {
		
		List<TblReporteVentas> reporteVentasTienda	 	= new ArrayList<TblReporteVentas>();
		List<TblReporteVentas> reporteVentasArticulos 	= new ArrayList<TblReporteVentas>();
		List<TblReporteVentas> reporteTotal 			= new ArrayList<TblReporteVentas>();
		List<ReporteVentaSkuDTO> resultadoReporte 		= new ArrayList<ReporteVentaSkuDTO>();
		List<TblArticulosEspacios> listaArticulos 		= new ArrayList<TblArticulosEspacios>();
		List<TblPrensaTienda> listaTiendas				= new ArrayList<TblPrensaTienda>();
		TblPrensa prensa 								= null;
		int idprensa 									= Integer.parseInt(folio);
			
		try{
			prensa = validacionPrensa(folio);
			listaArticulos = daoArticulosEspacio.getArticulosByIdPrensa(idprensa);
			listaTiendas = daoPrensaTienda.getTiendasIdByPrensaId(idprensa);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}

		if(listaArticulos.size()> Constants.ZERO){
			reporteVentasArticulos = getReporteVentasBySKUPrensa(listaArticulos);
		}
		
		if(listaTiendas.size() > Constants.ZERO){
			reporteVentasTienda = getReporteVentasByTiendasPrensa(listaTiendas);
		}
		

		reporteTotal.addAll(reporteVentasArticulos);
		reporteTotal.addAll(reporteVentasTienda);
		reporteTotal.addAll( getReporteVentasByDate(prensa.getFehcaInicio(),prensa.getFechaFin()) );

		reporteTotal = ReporteVentasUtil.addElementosUnicosVenta(reporteTotal);

		resultadoReporte.addAll(DTOUtil
				.tblReporteVentasToReporteVentaSkuDTO(reporteTotal));

		return resultadoReporte;

	}

	@Override
	public CabeceraReporteDTO getCabeceraFolleto(String folio)throws GeneralException {
		TblFolleto folleto 		= new TblFolleto();
		int idFolleto 			= Integer.parseInt(folio);

		try {
			folleto = daoFolleto.getById(idFolleto);
		} catch (Exception e) {
			LOG.error(e);
		}
		return DTOUtil.tblFolletoToCabeceraReporteDTO(folleto);
	}

	@Override
	public CabeceraReporteDTO getCabeceraPrensa(String folio)throws GeneralException {
		TblPrensa prensa = new TblPrensa();
		int idPrensa = Integer.parseInt(folio);

		try {
			prensa = daoPrensa.getById(idPrensa);
		} catch (Exception e) {
			LOG.error(e);
		}
		return DTOUtil.tblFolletoToCabeceraReporteDTO(prensa);
	}

	private List<TblReporteVentas> getReporteVentasByTiendasFolleto(List<TblFolletoTienda> listaTiendas) throws GeneralException {

		List<Integer> listIdTienda = new ArrayList<Integer>();

		for (TblFolletoTienda tienda : listaTiendas) {
			listIdTienda.add(tienda.getId().getIdTienda());
		}

		return daoReporteVentas.getAllByIdsTienda(listIdTienda);
	}

	private List<TblReporteVentas> getReporteVentasBySKUFolleto(List<TblArticulosHoja> listaArticulos) throws GeneralException {

		List<String> listArticuloSku = new ArrayList<String>();

		for (TblArticulosHoja articulo : listaArticulos) {
			listArticuloSku.add(articulo.getIdArticulo());
		} 

		return daoReporteVentas.getAllBySKU(listArticuloSku);
	}

	private List<TblReporteVentas> getReporteVentasByTiendasPrensa(List<TblPrensaTienda> listaTiendas) throws GeneralException {
		List<Integer> listIdTienda = new ArrayList<Integer>();

		for (TblPrensaTienda tienda : listaTiendas) {
			listIdTienda.add(tienda.getId().getIdTienda());
		}

		return daoReporteVentas.getAllByIdsTienda(listIdTienda);
	}

	private List<TblReporteVentas> getReporteVentasBySKUPrensa(List<TblArticulosEspacios> listaArticulos) throws GeneralException {
		List<String> listArticuloSku 			= new ArrayList<String>();

		for (TblArticulosEspacios articulo : listaArticulos) {
			listArticuloSku.add(articulo.getId().getIdArticulo());
		}

		return  daoReporteVentas.getAllBySKU(listArticuloSku);
	}
	
	private List<TblReporteVentas> getReporteVentasByDate(Date fechaInicio,Date fechaFin) throws GeneralException {

		return daoReporteVentas.getAllByFechaInicioAndFechaFin(fechaInicio, fechaFin);
	}
	
	@Override
	public List<TblReporteVentas> getReporteVentasByDateSku(Date fechaInicio,Date fechaFin, String sku) throws GeneralException {

		return daoReporteVentas.getSkuByFechaInicioAndFechaFin(fechaInicio, fechaFin, sku);
	}

	/**
	 * Calcula la informaion del Reporte de Inventario para un Folleto
	 */
	@Override

	public List<ReporteInventarioSkuDTO> calcularReporteInventarioSKUFolleto(String folio) throws GeneralException {
		
		List<TblReporteInventario> reporteInventarioTienda	 	= new ArrayList<TblReporteInventario>();
		List<TblReporteInventario> reporteInventarioArticulos 		= new ArrayList<TblReporteInventario>();
		List<TblReporteInventario> reporteTotal 				= new ArrayList<TblReporteInventario>();
		List<ReporteInventarioSkuDTO> resultadoReporte 			= new ArrayList<ReporteInventarioSkuDTO>();
		List<TblArticulosHoja> listaArticulos 					= new ArrayList<TblArticulosHoja>();
		List<TblFolletoTienda> listaTiendas						= new ArrayList<TblFolletoTienda>();
		int idFolleto 											= Integer.parseInt(folio);
		
		try {	
			validacionFolleto(folio);
			listaArticulos = daoArticulosHoja.getArticulosByIdFolleto(idFolleto);
			listaTiendas = daoFolletoTienda.getTiendasIdByFolletoId(idFolleto);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}

		if(listaArticulos.size() > Constants.ZERO){
			reporteInventarioArticulos = getReporteInventarioBySKUFolleto(listaArticulos);
		}

		
		if(listaTiendas.size() > Constants.ZERO){
			reporteInventarioTienda = getReporteInventarioByTiendasFolleto(listaTiendas);
		}
		
		reporteTotal.addAll(reporteInventarioArticulos);
		reporteTotal.addAll(reporteInventarioTienda);

		reporteTotal = ReporteInventarioUtil
				.addElementosUnicosInventario(reporteTotal);

		resultadoReporte.addAll(DTOUtil
				.tblReporteInventarioToReporteInventarioSkuDTO(reporteTotal));

		return resultadoReporte;
	}

	/**
	 * Calcula la informaion del Reporte de Inventario para una Prensa
	 */
	@Override

	public List<ReporteInventarioSkuDTO> calcularReporteInventarioSKUPrensa(String folio) throws GeneralException {
		
		List<TblReporteInventario> reporteInventarioTienda	 	= new ArrayList<TblReporteInventario>();
		List<TblReporteInventario> reporteInventarioArticulos 		= new ArrayList<TblReporteInventario>();
		List<TblReporteInventario> reporteTotal 				= new ArrayList<TblReporteInventario>();
		List<ReporteInventarioSkuDTO> resultadoReporte 			= new ArrayList<ReporteInventarioSkuDTO>();
		List<TblArticulosEspacios> listaArticulos 				= new ArrayList<TblArticulosEspacios>();
		List<TblPrensaTienda> listaTiendas						= new ArrayList<TblPrensaTienda>();
		int idPrensa 											= Integer.parseInt(folio);
		
		try {	
			validacionPrensa(folio);
			listaArticulos = daoArticulosEspacio.getArticulosByIdPrensa(idPrensa);
			listaTiendas = daoPrensaTienda.getTiendasIdByPrensaId(idPrensa);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}

		if( listaArticulos.size() > Constants.ZERO){
			reporteInventarioArticulos = getReporteInventarioBySKUPrensa(listaArticulos);
		}

		
		if( listaTiendas.size() > Constants.ZERO){
			reporteInventarioTienda = getReporteInventarioByTiendasPrensa(listaTiendas);
		}
		
		reporteTotal.addAll(reporteInventarioArticulos);
		reporteTotal.addAll(reporteInventarioTienda);

		reporteTotal = ReporteInventarioUtil
				.addElementosUnicosInventario(reporteTotal);

		resultadoReporte.addAll(DTOUtil
				.tblReporteInventarioToReporteInventarioSkuDTO(reporteTotal));

		return resultadoReporte;
	}

	private List<TblReporteInventario> getReporteInventarioBySKUFolleto(List<TblArticulosHoja> listaArticulos) throws GeneralException {
		List<String> listArticuloSku = new ArrayList<String>();

		for (TblArticulosHoja articulo : listaArticulos) {
			listArticuloSku.add(articulo.getIdArticulo());
		}

		return daoReporteInventario.getAllBySKU(listArticuloSku);
	}

	private List<TblReporteInventario> getReporteInventarioByTiendasFolleto(List<TblFolletoTienda> listaTiendas) throws GeneralException {
		
		List<Integer> listIdTienda = new ArrayList<Integer>();

		for (TblFolletoTienda tienda : listaTiendas) {
			listIdTienda.add(tienda.getId().getIdTienda());
		}


		return daoReporteInventario.getAllByIdsTienda(listIdTienda);
	}

	private List<TblReporteInventario> getReporteInventarioBySKUPrensa(List<TblArticulosEspacios> listaArticulos) throws GeneralException {

		List<String> listArticuloSku = new ArrayList<String>();

		for (TblArticulosEspacios articulo : listaArticulos) {
			listArticuloSku.add(articulo.getId().getIdArticulo());
		}

		return daoReporteInventario.getAllBySKU(listArticuloSku);
	}

	private List<TblReporteInventario> getReporteInventarioByTiendasPrensa(List<TblPrensaTienda> listaTiendas) throws GeneralException {

		List<Integer> listIdTienda = new ArrayList<Integer>();

		for (TblPrensaTienda tienda : listaTiendas) {
			listIdTienda.add(tienda.getId().getIdTienda());
		}

		return daoReporteInventario.getAllByIdsTienda(listIdTienda);
	}

	private TblFolleto validacionFolleto(String folio) throws GeneralException {
		TblFolleto folleto = null;
		try {
			int idFolleto = Integer.parseInt(folio);
			folleto = daoFolleto.getById(idFolleto);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}

		if(folleto==null){
			throw new GeneralException(Constants.MENSAJE_VALIDACION_FOLIO_FOLLETO + folio);
		}

		return folleto;
	}

	private TblPrensa validacionPrensa(String folio) throws GeneralException {
		TblPrensa prensa = null;
		try {
			int idPrensa = Integer.parseInt(folio);
			prensa = daoPrensa.getById(idPrensa);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage());
		}

		if(prensa==null){
			throw new GeneralException(Constants.MENSAJE_VALIDACION_FOLIO_PRENSA + folio);
		}

		return prensa;
	}

	public List<TblArticulosEspacios> getArticulosByFolioPrensa(String folio)
			throws GeneralException {
		List<TblArticulosEspacios> list = null;
		try {
			int idFolleto = Integer.parseInt(folio);
			if(validarPrensa(idFolleto)){
				list = daoArticulosEspacio.getArticulosByIdPrensa(idFolleto);	
				}else{
					Messages.mensajeErroneo("Error", Constants.MENSAJE_VALIDACION_FOLIO_PRENSA + folio);
				}	
		} catch (Exception e) {
			LOG.error(e);
			Messages.mensajeErroneo("Error", e.getMessage());
		}
		return list;
	}

	public List<TblArticulosHoja> getArticulosByFolioFolleto(String folio) {
		List<TblArticulosHoja> list = null;
		try {	
			int idFolleto = Integer.parseInt(folio);
			if(validarFolleto(idFolleto)){
				list = daoArticulosHoja.getArticulosByIdFolleto(idFolleto);
				}else{
					Messages.mensajeErroneo("Error", Constants.MENSAJE_VALIDACION_FOLIO_FOLLETO + folio);
				}		
			
		} catch (Exception e) {
			LOG.error(e);
		}
		return list;
	}

	@Override
	public TblFolletoSistemaVenta getFolletoSistemaVentaByFolio(String folio) {
		TblFolletoSistemaVenta object = new TblFolletoSistemaVenta();
		try {
			int idFolleto = Integer.parseInt(folio);
			object = daoFolletoSistemaVenta
					.getFolletoSistemaVentaByFolio(idFolleto);
		} catch (Exception e) {
		}
		return object;
	}

	@Override
	public TblPrensaSistemaVenta getPrensaSistemaVentaByFolio(String folio)
			throws GeneralException {
		TblPrensaSistemaVenta obj = null;
		try {
			int idPrensa = Integer.parseInt(folio);	
			obj = daoPrensaSistemaVenta.getPrensaSistemaVentaByFolio(idPrensa);
		} catch (Exception e) {

		}
		return obj;
	}

	@Override
	public String getTipoDeFaltante(int idImageArt) throws GeneralException {
		String tipoDeFaltante = "Esta null";
		TblImageArticulo obj = new TblImageArticulo();
		obj = daoImageArticulo.getImageArticulo(idImageArt);
		if (obj == null) {
			tipoDeFaltante = "Foto/Descripcion";
		} else if (obj.getPathDesc() == null || obj.getPathDesc().equals("")) {
			tipoDeFaltante = "Descripción";
		} else if (obj != null) {
			tipoDeFaltante = "---";
		}
		return tipoDeFaltante;
	}

	private boolean validarFolleto(int idFolleto) {
		return daoFolleto.validarFolleto(idFolleto);
	}

	private boolean validarPrensa(int idPrensa) {

		return daoPrensa.validarPrensa(idPrensa);
	}

}
