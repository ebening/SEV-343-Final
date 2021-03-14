package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblJuntaArchivos;
import com.adinfi.seven.business.domain.TblJuntaParticipantes;
import com.adinfi.seven.business.domain.TblJuntaPlaneacion;
import com.adinfi.seven.persistence.daos.DAOJuntaArchivos;
import com.adinfi.seven.persistence.daos.DAOJuntaParticipantes;
import com.adinfi.seven.persistence.daos.DAOJuntaPlaneacion;

public interface ServiceJuntaPlaneacion {

	DAOJuntaPlaneacion getDaoJuntaPlaneacion();

	void setDaoJuntaPlaneacion(DAOJuntaPlaneacion daoJuntaPlaneacion);

	void saveJuntaPlaneacion(TblJuntaPlaneacion juntaPlaneacion) throws GeneralException;

	List<TblJuntaPlaneacion> getAllJuntaPlaneacion() throws GeneralException;
	
	DAOJuntaArchivos getDaoJuntaArchivos();

	void setDaoJuntaArchivos(DAOJuntaArchivos daoJuntaArchivos);

	DAOJuntaParticipantes getDaoJuntaParticipantes();

	void setDaoJuntaParticipantes(DAOJuntaParticipantes daoJuntaParticipantes);
	
	void updateJuntaPlaneacion(TblJuntaPlaneacion juntaPlaneacion) throws GeneralException;
	
	void deleteJuntaPlaneacion(TblJuntaPlaneacion juntaPlaneacion) throws GeneralException;
	
	List<TblJuntaArchivos> getAllJuntaArchivos() throws GeneralException;
	
	void saveJuntaArchivos(TblJuntaArchivos tblJuntaArchivos) throws GeneralException;
	
	void updateJuntaArchivos(TblJuntaArchivos tblJuntaArchivos) throws GeneralException;
	
	void deleteJuntaArchivos(TblJuntaArchivos tblJuntaArchivos) throws GeneralException;
	
	List<TblJuntaParticipantes> getAllJuntaParticipantes() throws GeneralException;
	
	void saveJuntaParticipantes(TblJuntaParticipantes tblJuntaParticipantes) throws GeneralException;
	
	void updateJuntaParticipantes(TblJuntaParticipantes tblJuntaParticipantes) throws GeneralException;
	
	void deleteJuntaParticipantes(TblJuntaParticipantes tblJuntaParticipantes) throws GeneralException;
	
	void deleteJuntaArchivosByIdJunta(int idJunta) throws GeneralException;
	
	void deleteJuntaParticipantesByIdJunta(int idJunta) throws GeneralException;
}