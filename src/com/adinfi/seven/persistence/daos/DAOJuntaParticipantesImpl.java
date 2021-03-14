package com.adinfi.seven.persistence.daos;

import org.hibernate.Query;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblJuntaParticipantes;

public class DAOJuntaParticipantesImpl extends AbstractDaoImpl<TblJuntaParticipantes> implements DAOJuntaParticipantes{
	public void deleteTblJuntaParticipantesByIdJunta(int idJunta) throws GeneralException{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblJuntaParticipantes juntaPart where juntaPart.tblJuntaPlaneacion.idJunta= "
								+ idJunta);
		query.executeUpdate();
	}
}
