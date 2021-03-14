/**
 * 
 */
package com.adinfi.seven.persistence.daos;

import java.util.Date;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblReporteVentas;

/**
 * @author OMAR
 *
 */
public interface DAOReporteVentas  extends AbstractDao<TblReporteVentas> {
	
	
	 List<TblReporteVentas> getAllByIdsTienda(List<Integer> listIdTienda) throws GeneralException;
	 List<TblReporteVentas> getAllBySKU(List<String> listSku) throws GeneralException;
	 List<TblReporteVentas> getAllByFechaInicioAndFechaFin(Date fechaInicio,Date fechaFin)throws GeneralException;
	List<TblReporteVentas> getSkuByFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin, String sku) throws GeneralException;

}
