package com.adinfi.seven.persistence.daos;

import java.util.List;




import com.adinfi.seven.business.domain.TblFolletoHojas;

public interface DAOFolletoHojas extends
		AbstractDao<TblFolletoHojas> {

	List<TblFolletoHojas> getHojasByFolletoId(int idFolleto);

	TblFolletoHojas saveFolletoHojas(TblFolletoHojas folletoHojas);

	TblFolletoHojas getHojaByIdFolletoIdHoja(int idFolleto,int idHoja);

	Number getTotalNumHojasFolleto(int idHoja);

	List<TblFolletoHojas> getCopias(TblFolletoHojas hoja);
	Number getCategoriaHoja(int idHoja);

	void delete(int idFolleto) throws Exception;

	void deleteHojas(int folletoId, int ultimaHoja) throws Exception;

	void deleteHojas(int folletoId, short numHoja, String espaciosValidos);
}
