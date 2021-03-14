package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.RelZonaCampana;

import java.util.List;

public interface DAORelZonaCampana extends AbstractDao<RelZonaCampana> {

	void deleteById(long campanaId, int idPrograma);
	List<RelZonaCampana> getRelZonaCamapana (long campanaId, int idPrograma);
	public void deleteById(List<Long> idCampana, List<Integer> idPrograma);
	public void save(List<RelZonaCampana> list);
	public List<RelZonaCampana> getByCampanaPrograma(long idCampana, int idPrograma);
}
