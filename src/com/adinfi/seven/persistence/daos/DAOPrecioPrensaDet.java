package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.TblPreciosPrensaDet;

public interface DAOPrecioPrensaDet extends AbstractDao<TblPreciosPrensaDet> {

	void delete(int idPrecPrensa) throws Exception;

}
