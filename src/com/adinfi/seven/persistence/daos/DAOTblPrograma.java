package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblPrograma;

public interface DAOTblPrograma extends AbstractDao<TblPrograma> {

	void deleteByMecanicaId(int mecanicaId);
	public void save(List<TblPrograma> list);
}
