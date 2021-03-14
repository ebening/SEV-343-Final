package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.TblPrensaSistemaVenta;

public interface DAOPrensaSistemaVenta extends
		AbstractDao<TblPrensaSistemaVenta> {

	TblPrensaSistemaVenta getPrensaSistemaVentaByFolio(int idFolleto);

	void delete(int idPrensa) throws Exception;
	

}
