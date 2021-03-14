package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblSenalamientos;

public interface DAOTblSenalamientos extends AbstractDao<TblSenalamientos> {
	void deleteByMecanicaId(int mecanicaId);
	public void save(List<TblSenalamientos> list);
}
