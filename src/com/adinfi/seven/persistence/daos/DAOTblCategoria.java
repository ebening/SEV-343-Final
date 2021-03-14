package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblCategoria;

public interface DAOTblCategoria extends AbstractDao<TblCategoria> {

	void deleteByMecanicaId(int mecanicaId);
	public void save(List<TblCategoria> list);
}
