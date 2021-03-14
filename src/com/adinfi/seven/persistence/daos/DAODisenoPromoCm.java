package com.adinfi.seven.persistence.daos;

import java.util.List;




import com.adinfi.seven.business.domain.TblDisenoPromoCm;
import com.adinfi.seven.business.domain.TblFolletoHojas;

public interface DAODisenoPromoCm extends
		AbstractDao<TblDisenoPromoCm> {

	List<TblFolletoHojas> getHojasByFolletoId(int idFolleto);

	TblDisenoPromoCm saveDisenoPromoCM(TblDisenoPromoCm tblDisenoPromoCm);

	TblFolletoHojas getHojaByIdFolletoIdHoja(int idFolleto,int idHoja);

	Number getTotalNumHojasFolleto(int idHoja);

	List<TblFolletoHojas> getCopias(TblFolletoHojas hoja);
	Number getCategoriaHoja(int idHoja);

	TblDisenoPromoCm getDiseno(long idCampana, int idPrograma);
}
