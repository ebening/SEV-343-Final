/**
 * 
 */
package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblDisenoPromoCm;
import com.adinfi.seven.business.domain.TblFolletoHojas;

/**
 * @author OMAR
 *
 */
public interface ServiceEjecucion {
	
	
	List<TblArticulosHoja> getArticulosByHojaId(int idHoja) throws GeneralException;

	TblDisenoPromoCm getDiseno(long idCampana, int idPrograma);
	
	TblDisenoPromoCm saveDiseno(TblDisenoPromoCm tblDisenoPromoCm) throws GeneralException;
	
	TblFolletoHojas getHojaById(int idHoja) throws GeneralException;

	void saveTblFolletoHojas(TblFolletoHojas hoja);

}
