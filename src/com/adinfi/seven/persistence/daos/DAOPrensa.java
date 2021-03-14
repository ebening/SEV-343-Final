package com.adinfi.seven.persistence.daos;


import java.util.List;

import com.adinfi.seven.business.domain.TblPrensa;

public interface DAOPrensa extends AbstractDao<TblPrensa> {
	boolean validarPrensa(int idFolleto);
	List<TblPrensa> getTblPrensaLstByIdCampana(long l) throws Exception;
}
