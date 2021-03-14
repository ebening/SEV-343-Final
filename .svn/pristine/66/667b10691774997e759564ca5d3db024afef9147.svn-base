package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.RelGrupoZonaCampana;

public interface DAORelGrupoZonaCampana extends AbstractDao<RelGrupoZonaCampana> {
	void deleteById(long campanaId, int idPrograma);
	public void deleteById(List<Long> campanaId, List<Integer> idPrograma);
	public void save(List<RelGrupoZonaCampana> list);
	public List<RelGrupoZonaCampana> getByCampanaPrograma(long idCampana, int idPrograma);
}