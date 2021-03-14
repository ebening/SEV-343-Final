package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblMecanica;

public interface DAOTblMecanica extends AbstractDao<TblMecanica> {
        boolean campanaHasMecanica(int campanaId);
	List<TblMecanica> getAllMecanica(int campanaId);
	List<TblMecanica> getAllMecanica(int campanaId, int programaId);
	List<TblMecanica> getAllMecanica(Integer campanaId, Integer programaId, Integer grupoZona, Integer zona);
	List<TblMecanica> getMecanicasByPrograma (long campanaId, int programaId);
	List<TblMecanica> getMecanicasByCampana (long campanaId);
	TblMecanica getMecanicaById(int id);
	public List<TblMecanica> getMecanicaWithChildrens(List<Object[]> list);
	public TblMecanica getMecanicaByIdWithChildren(int id);
}
