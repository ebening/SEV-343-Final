package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.RelStoreCampana;

public interface DAORelStoreCampana extends AbstractDao<RelStoreCampana> {
	void deleteById(long campanaId, int idPrograma);
	public void deleteById(List<Long> idCampana, List<Integer> idPrograma);
	public void save(List<RelStoreCampana> list);
	public List<RelStoreCampana> getByCampanaPrograma(long idCampana, int idPrograma);
}