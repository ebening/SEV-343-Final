package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblPreciosPrensa;

public interface DAOPrecioPrensa extends AbstractDao<TblPreciosPrensa> {

	List<TblPreciosPrensa> getByPrensaId(int idPrensa) throws Exception;

}
