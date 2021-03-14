/**
 * 
 */
package com.adinfi.seven.presentation.views.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.adinfi.seven.business.domain.TblReporteInventario;
import com.adinfi.seven.persistence.dto.ReporteInventarioFiltroDTO;
import com.adinfi.seven.persistence.dto.ReporteInventarioSkuDTO;

/**
 * @author OMAR
 *
 */
public final class ReporteInventarioUtil {
	
	
	/**
	 * Funcion que agrupa el reporte de acuerdo a los distritos que tiene.
	 */
	public static List<ReporteInventarioFiltroDTO>  loadInfoDistrito( List<ReporteInventarioSkuDTO> infoReporteSku ){
		HashMap<String,ReporteInventarioFiltroDTO> reporteFiltrado 	= new HashMap<String,ReporteInventarioFiltroDTO>();
		
		for(ReporteInventarioSkuDTO detalleReporte:infoReporteSku){
			String filtro = detalleReporte.getDistrito();
			analisisInfoFiltro(filtro,detalleReporte, reporteFiltrado);
		}
		
		return setInfoReporteFiltro(reporteFiltrado);
	}
	
	/**
	 * Funcion que agrupa el reporte de acuerdo a las Zonas que tiene.
	 */
	public static List<ReporteInventarioFiltroDTO> loadInfoZona( List<ReporteInventarioSkuDTO> infoReporteSku ){
		
		HashMap<String,ReporteInventarioFiltroDTO> reporteFiltrado 	= new HashMap<String,ReporteInventarioFiltroDTO>();
		
		for(ReporteInventarioSkuDTO detalleReporte:infoReporteSku){
			String filtro = detalleReporte.getZonaNombre();
			analisisInfoFiltro(filtro,detalleReporte, reporteFiltrado);
		}
		
		return setInfoReporteFiltro(reporteFiltrado);
	}
	
	
	/**
	 * Funcion que agrupa el reporte de acuerdo a las Sucursal que tiene.
	 */
	public static List<ReporteInventarioFiltroDTO> loadInfoSucursal( List<ReporteInventarioSkuDTO> infoReporteSku ){
		
		HashMap<String,ReporteInventarioFiltroDTO> reporteFiltrado 	= new HashMap<String,ReporteInventarioFiltroDTO>();
		
		for(ReporteInventarioSkuDTO detalleReporte:infoReporteSku){
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
	private static void analisisInfoFiltro(String filtro ,ReporteInventarioSkuDTO detalleReporte,HashMap<String,ReporteInventarioFiltroDTO> reporteFiltrado){
			
			ReporteInventarioFiltroDTO detalleFiltroNuevo		= new ReporteInventarioFiltroDTO();
			ReporteInventarioFiltroDTO detalleFiltroTemp		= null;
			

			detalleFiltroNuevo.setFiltro(filtro);
			detalleFiltroNuevo.setInventarioTienda( detalleReporte.getInventarioTienda());
			detalleFiltroNuevo.setInventarioCedis( detalleReporte.getInventarioCedis());
			detalleFiltroNuevo.setInventarioTransito( detalleReporte.getInventarioTransito());
			
			if(reporteFiltrado.containsKey(filtro)){
				
				detalleFiltroTemp = reporteFiltrado.get(detalleFiltroNuevo.getFiltro());
				detalleFiltroTemp.addValorInventarioTienda( detalleFiltroNuevo.getInventarioTienda());
				detalleFiltroTemp.addValorInventarioCedis( detalleFiltroNuevo.getInventarioCedis());
				detalleFiltroTemp.addValorInventarioTransito( detalleFiltroNuevo.getInventarioTransito());
				reporteFiltrado.put(filtro, detalleFiltroTemp);
				
			}else{
				reporteFiltrado.put(filtro, detalleFiltroNuevo);
			}
		}
	
	
	/**
	 * Agrega la Informacion de HashMAp a la Lista de detalle del Reporte Filtrado
	 * @param reporteFiltrado
	 */
	private static   List<ReporteInventarioFiltroDTO> setInfoReporteFiltro(HashMap<String,ReporteInventarioFiltroDTO> reporteFiltrado){
		
		List<ReporteInventarioFiltroDTO> infoReporteFiltro			= new ArrayList<ReporteInventarioFiltroDTO>();
		Iterator<Entry<String,ReporteInventarioFiltroDTO>> iterator 	= reporteFiltrado.entrySet().iterator();
		  
		  while(iterator.hasNext()){
		   Entry<String,ReporteInventarioFiltroDTO> detalle=(Entry<String,ReporteInventarioFiltroDTO>)iterator.next();
		   infoReporteFiltro.add( detalle.getValue());
		  }
		  
		  return  infoReporteFiltro;
	}
	
	
	/**
	 * Devuelve una lista donde no tiene elementos repetidos de tipo TblReporteVentas
	 * @param resultadoParcial
	 * @return
	 */
	public static List<TblReporteInventario>  addElementosUnicosInventario(List<TblReporteInventario> resultadoParcial	){
		List<TblReporteInventario> resultado  = new ArrayList<TblReporteInventario>();
		
		for(TblReporteInventario detalle:resultadoParcial){
			if(!resultado.contains(detalle)){
				resultado.add(detalle);
			}
		}
		
		return resultado;

	}

}
