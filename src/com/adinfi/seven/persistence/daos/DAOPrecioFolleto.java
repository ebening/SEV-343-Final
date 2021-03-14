package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblPreciosFolleto;

public interface DAOPrecioFolleto extends AbstractDao<TblPreciosFolleto> {

	List<TblPreciosFolleto> getPreciosFolletoByFolleto(int idFolleto);

}
