/**
 * 
 */
package com.adinfi.seven.presentation.views.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.adinfi.seven.business.domain.TblReporteVentas;
import com.adinfi.seven.persistence.dto.ReporteVentaFiltroDTO;
import com.adinfi.seven.persistence.dto.ReporteVentaSkuDTO;

/**
 * @author OMAR
 *
 */
public final class ReporteVentasUtil {
	
	
	/**
	 * Funcion que agrupa el reporte de acuerdo a los distritos que tiene.
	 */
	public static List<ReporteVentaFiltroDTO>  loadInfoDistrito( List<ReporteVentaSkuDTO> infoReporteSku ){
		HashMap<String,ReporteVentaFiltroDTO> reporteFiltrado 	= new HashMap<String,ReporteVentaFiltroDTO>();
		
		for(ReporteVentaSkuDTO detalleReporte:infoReporteSku){
			String filtro = detalleReporte.getDistrito();
			analisisInfoFiltro(filtro,detalleReporte, reporteFiltrado);
		}
		
		return setInfoReporteFiltro(reporteFiltrado);
	}
	
	/**
	 * Funcion que agrupa el reporte de acuerdo a las Zonas que tiene.
	 */
	public static List<ReporteVentaFiltroDTO> loadInfoZona( List<ReporteVentaSkuDTO> infoReporteSku ){
		
		HashMap<String,ReporteVentaFiltroDTO> reporteFiltrado 	= new HashMap<String,ReporteVentaFiltroDTO>();
		
		for(ReporteVentaSkuDTO detalleReporte:infoReporteSku){
			String filtro = detalleReporte.getZonaNombre();
			analisisInfoFiltro(filtro,detalleReporte, reporteFiltrado);
		}
				
		
		return setInfoReporteFiltro(reporteFiltrado);
	}
	
	
	/**
	 * Funcion que agrupa el reporte de acuerdo a las Sucursal que tiene.
	 */
	public static List<ReporteVentaFiltroDTO> loadInfoSucursal( List<ReporteVentaSkuDTO> infoReporteSku ){
		
		HashMap<String,ReporteVentaFiltroDTO> reporteFiltrado 	= new HashMap<String,ReporteVentaFiltroDTO>();
		
		for(ReporteVentaSkuDTO detalleReporte:infoReporteSku){
			String filtro = detalleReporte.getSucursal();
			analisisInfoFiltro(filtro,detalleReporte, reporteFiltrado);
		}
		
		return setInfoReporteFiltro(reporteFiltrado);
	}
	
	
	
	
	/**
	 * Analisa la informacion , la filtra de acuerdo al valor del filtro que se le pasa y se agrega al HAsh-Map
	 * @param filtro
	 * @param detalleReporte
	 * @param reporteFiltrado
	 */
	private static void analisisInfoFiltro(String filtro ,ReporteVentaSkuDTO detalleReporte,HashMap<String,ReporteVentaFiltroDTO> reporteFiltrado){
			
			ReporteVentaFiltroDTO detalleFiltroNuevo		= new ReporteVentaFiltroDTO();
			ReporteVentaFiltroDTO detalleFiltroTemp			= null;
			

			detalleFiltroNuevo.setFiltro(filtro);
			detalleFiltroNuevo.setVentaUnidades( detalleReporte.getVentaUnidades());
			detalleFiltroNuevo.setVentaPesos(detalleReporte.getVentaPesos());
			
			if(reporteFiltrado.containsKey(filtro)){
				
				detalleFiltroTemp = reporteFiltrado.get(detalleFiltroNuevo.getFiltro());
				detalleFiltroTemp.addValorVentaUnidades( detalleFiltroNuevo.getVentaUnidades());
				detalleFiltroTemp.addValorVentaPesos( detalleFiltroNuevo.getVentaPesos());
				reporteFiltrado.put(filtro, detalleFiltroTemp);
				
			}else{
				reporteFiltrado.put(filtro, detalleFiltroNuevo);
			}
		}
	
	
	/**
	 * Agrega la Informacion de HashMAp a la Lista de detalle del Reporte Filtrado
	 * @param reporteFiltrado
	 */
	private static   List<ReporteVentaFiltroDTO> setInfoReporteFiltro(HashMap<String,ReporteVentaFiltroDTO> reporteFiltrado){
		
		List<ReporteVentaFiltroDTO> infoReporteFiltro			= new ArrayList<ReporteVentaFiltroDTO>();
		Iterator<Entry<String,ReporteVentaFiltroDTO>> iterator 	= reporteFiltrado.entrySet().iterator();
		  
		  while(iterator.hasNext()){
		   Entry<String,ReporteVentaFiltroDTO> detalle=(Entry<String,ReporteVentaFiltroDTO>)iterator.next();
		   infoReporteFiltro.add( detalle.getValue());
		  }
		  
		  return  infoReporteFiltro;
	}
	
	
	/**
	 * Devuelve una lista donde no tiene elementos repetidos de tipo TblReporteVentas
	 * @param resultadoParcial
	 * @return
	 */
	public static List<TblReporteVentas>  addElementosUnicosVenta(List<TblReporteVentas> resultadoParcial	){
		List<TblReporteVentas> resultado  = new ArrayList<TblReporteVentas>();
		
		for(TblReporteVentas detalle:resultadoParcial){
			if(!resultado.contains(detalle)){
				resultado.add(detalle);
			}
		}
		
		return resultado;

	}
	

}
