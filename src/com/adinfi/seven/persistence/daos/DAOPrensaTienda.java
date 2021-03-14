package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblPrensaTienda;

public interface DAOPrensaTienda extends AbstractDao<TblPrensaTienda> {
	
	
	 List<TblPrensaTienda>  getTiendasIdByPrensaId(int idPrensa) throws Exception;

	void delete(int idPrensa) throws Exception;

}
