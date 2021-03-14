package com.adinfi.seven.persistence.daos;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblJuntaParticipantes;

public interface DAOJuntaParticipantes extends AbstractDao<TblJuntaParticipantes>{
	void deleteTblJuntaParticipantesByIdJunta(int idJunta) throws GeneralException;
}