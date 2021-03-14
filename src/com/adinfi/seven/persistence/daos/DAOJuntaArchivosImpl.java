package com.adinfi.seven.persistence.daos;

import org.hibernate.Query;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblJuntaArchivos;

public class DAOJuntaArchivosImpl extends AbstractDaoImpl<TblJuntaArchivos> implements DAOJuntaArchivos {
	public void deleteTblJuntaArchivosByIdJunta(int idJunta) throws GeneralException{
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblJuntaArchivos juntaArch where juntaArch.tblJuntaPlaneacion.idJunta= "
								+ idJunta);
		query.executeUpdate();
	}
}
