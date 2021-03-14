package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblCampanaProgramas;

public interface DAOCampanaProgramas extends AbstractDao<TblCampanaProgramas>{
	TblCampanaProgramas getProgramaById(long idCampana, int programaId);
	List<TblCampanaProgramas> getProgramasByCampanaId(long idCampana);
	void deleteProgramasByCampanaIdProgramaID(long idCampana, int idPrograma);
	boolean changeEtapaPrograma(int idCampana, int idPrograma, String etapa);
}