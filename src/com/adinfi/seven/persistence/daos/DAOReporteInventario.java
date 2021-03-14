/**
 * 
 */
package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblReporteInventario;

/**
 * @author OMAR
 *
 */
public interface DAOReporteInventario extends AbstractDao<TblReporteInventario>{
	
	 List<TblReporteInventario> getAllByIdsTienda(List<Integer> listIdTienda) throws GeneralException;
	 List<TblReporteInventario> getAllBySKU(List<String> listSku) throws GeneralException;
	


}
