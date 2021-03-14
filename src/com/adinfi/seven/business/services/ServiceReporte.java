/**
 * 
 */
package com.adinfi.seven.business.services;
import java.util.Date;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblArticulosEspacios;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;
import com.adinfi.seven.business.domain.TblPrensaSistemaVenta;
import com.adinfi.seven.business.domain.TblReporteVentas;
import com.adinfi.seven.persistence.dto.CabeceraReporteDTO;
import com.adinfi.seven.persistence.dto.ReporteInventarioSkuDTO;
import com.adinfi.seven.persistence.dto.ReporteVentaSkuDTO;

/**
 * @author OMAR
 *
 */
public interface ServiceReporte {
	
	/**
	 * 
	 * REPORTE VENTA
	 */
	List<ReporteVentaSkuDTO> calcularReporteVentaSKUFolleto(String folio) throws GeneralException;
	List<ReporteVentaSkuDTO> calcularReporteVentaSKUPrensa(String folio) throws GeneralException;
	CabeceraReporteDTO getCabeceraFolleto(String folio)throws GeneralException;
	CabeceraReporteDTO getCabeceraPrensa(String folio)throws GeneralException;
	
	/**
	 * REPORTE INVENTARIO
	 */
	List<ReporteInventarioSkuDTO> calcularReporteInventarioSKUFolleto(String folio) throws GeneralException;
	List<ReporteInventarioSkuDTO> calcularReporteInventarioSKUPrensa(String folio) throws GeneralException;
	List<TblArticulosEspacios> getArticulosByFolioPrensa(String folio) throws GeneralException;
	List<TblArticulosHoja> getArticulosByFolioFolleto(String folio) throws GeneralException;
	TblFolletoSistemaVenta getFolletoSistemaVentaByFolio(String folio) throws GeneralException;
	TblPrensaSistemaVenta getPrensaSistemaVentaByFolio(String folio) throws GeneralException;
	String getTipoDeFaltante(int idImageArt)throws GeneralException;
	List<TblReporteVentas> getReporteVentasByDateSku(Date fechaInicio, Date fechaFin, String sku) throws GeneralException;
}
