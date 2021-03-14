package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;

public interface DAOFolletoSistemaVenta extends
		AbstractDao<TblFolletoSistemaVenta> {
	TblFolletoSistemaVenta getFolletoSistemaVentaByFolio(int idFolleto);
	List<TblFolletoSistemaVenta> getSistemaVentaByFolleto(int idFolleto);
	void delete(int idFolleto) throws Exception;
}