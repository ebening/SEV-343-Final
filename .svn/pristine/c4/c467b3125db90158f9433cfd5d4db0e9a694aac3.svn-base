/**
 * 
 */
package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblReporteVentas;



/**
 * @author OMAR
 *
 */
public class DAOReporteVentasImpl extends AbstractDaoImpl<TblReporteVentas> implements DAOReporteVentas {

	@Override
	public List<TblReporteVentas> getAllByIdsTienda(List<Integer> listIdTienda) throws GeneralException {
		int tamanioLista 						= listIdTienda.size()-1;
		StringBuilder query 					= new StringBuilder("from TblReporteVentas trv where trv.idSucursal in( ");
		
		if(listIdTienda.size() > 0 ){
			for(int iterator=0;tamanioLista>iterator;iterator++){
				query.append(listIdTienda.get(iterator)+",");
			}
	
			query.append(listIdTienda.get(tamanioLista)+")");
			Iterator<TblReporteVentas> listaResultado = getHibernateTemplate().iterate(query.toString());
			return toInitializedList(listaResultado);
		}else{
			return new ArrayList<TblReporteVentas>();
		}
	}

	
	
	@Override
	public List<TblReporteVentas> getAllBySKU(List<String> listSku){
		int tamanioLista 						= listSku.size()-1;
		StringBuilder query 					= new StringBuilder("from TblReporteVentas trv where trv.codeArt in( ");
		
		if(listSku.size() >0){
			for(int iterator=0;tamanioLista>iterator;iterator++){
				query.append(listSku.get(iterator)+",");
			}
		
			query.append(listSku.get(tamanioLista)+")");
			Iterator<TblReporteVentas> listaResultado = getHibernateTemplate().iterate(query.toString());
			return toInitializedList(listaResultado);
			
		}else {
			return new ArrayList<TblReporteVentas>();
		}
		
		
		
	}
	
	

	@Override
	public List<TblReporteVentas> getAllByFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin) throws GeneralException {
		Iterator<TblReporteVentas> itTiendasId = null ;
		itTiendasId =	getHibernateTemplate().iterate(" FROM  TblReporteVentas tft WHERE tft.fechaVenta BETWEEN ? and ?", new Object[] {fechaInicio,fechaFin});
		
		return toInitializedList(itTiendasId);
	}
	
	@Override
	public List<TblReporteVentas> getSkuByFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin, String sku) throws GeneralException {
		Iterator<TblReporteVentas> itTiendasId = null ;
		itTiendasId =	getHibernateTemplate().iterate(" FROM  TblReporteVentas tft WHERE tft.fechaVenta BETWEEN ? and ? and tft.codeArt = ? ", new Object[] {fechaInicio, fechaFin, sku});
		
		return toInitializedList(itTiendasId);
	}

}
