package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblJuntaArchivos;
import com.adinfi.seven.business.domain.TblJuntaParticipantes;
import com.adinfi.seven.business.domain.TblJuntaPlaneacion;
import com.adinfi.seven.persistence.daos.DAOJuntaArchivos;
import com.adinfi.seven.persistence.daos.DAOJuntaParticipantes;
import com.adinfi.seven.persistence.daos.DAOJuntaPlaneacion;

public class ServiceJuntaPlaneacionImpl implements ServiceJuntaPlaneacion {
	private Logger LOG = Logger.getLogger(ServiceJuntaPlaneacionImpl.class);
	public DAOJuntaPlaneacion daoJuntaPlaneacion;
	public DAOJuntaArchivos daoJuntaArchivos;
	public DAOJuntaParticipantes daoJuntaParticipantes;

	/* (non-Javadoc)
	 * @see com.adinfi.seven.business.services.ServiceJuntaPlaneacion#getDaoJuntaPlaneacion()
	 */
	@Override
	public DAOJuntaPlaneacion getDaoJuntaPlaneacion() {
		return daoJuntaPlaneacion;
	}

	/* (non-Javadoc)
	 * @see com.adinfi.seven.business.services.ServiceJuntaPlaneacion#setDaoJuntaPlaneacion(com.adinfi.seven.persistence.daos.DAOJuntaPlaneacion)
	 */
	@Override
	public void setDaoJuntaPlaneacion(DAOJuntaPlaneacion daoJuntaPlaneacion) {
		this.daoJuntaPlaneacion = daoJuntaPlaneacion;
	}

	/* (non-Javadoc)
	 * @see com.adinfi.seven.business.services.ServiceJuntaPlaneacion#saveJuntaPlaneacion(com.adinfi.seven.business.domain.TblJuntaPlaneacion)
	 */
	@Override
	public void saveJuntaPlaneacion(TblJuntaPlaneacion juntaPlaneacion) throws GeneralException{
		try {
			daoJuntaPlaneacion.save(juntaPlaneacion);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.adinfi.seven.business.services.ServiceJuntaPlaneacion#getAllJuntaPlaneacion()
	 */
	@Override
	public List<TblJuntaPlaneacion> getAllJuntaPlaneacion() throws GeneralException{
		try {
			return daoJuntaPlaneacion.getAll();
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public DAOJuntaArchivos getDaoJuntaArchivos() {
		return daoJuntaArchivos;
	}
	
	@Override
	public void setDaoJuntaArchivos(DAOJuntaArchivos daoJuntaArchivos) {
		this.daoJuntaArchivos = daoJuntaArchivos;
	}
	
	@Override
	public DAOJuntaParticipantes getDaoJuntaParticipantes() {
		return daoJuntaParticipantes;
	}
	
	@Override
	public void setDaoJuntaParticipantes(DAOJuntaParticipantes daoJuntaParticipantes) {
		this.daoJuntaParticipantes = daoJuntaParticipantes;
	}
	
	@Override
	public void updateJuntaPlaneacion(TblJuntaPlaneacion juntaPlaneacion) throws GeneralException{
		try{
			this.daoJuntaPlaneacion.saveOrUpdate(juntaPlaneacion);
		} catch (Exception e){
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void deleteJuntaPlaneacion(TblJuntaPlaneacion juntaPlaneacion) throws GeneralException{
		try{
			this.daoJuntaPlaneacion.delete(juntaPlaneacion);
		} catch (Exception e){
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public List<TblJuntaArchivos> getAllJuntaArchivos() throws GeneralException{
		try {
			return this.daoJuntaArchivos.getAll();
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void saveJuntaArchivos(TblJuntaArchivos tblJuntaArchivos) throws GeneralException{
		try {
			this.daoJuntaArchivos.save(tblJuntaArchivos);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void updateJuntaArchivos(TblJuntaArchivos tblJuntaArchivos) throws GeneralException{
		try {
			this.daoJuntaArchivos.saveOrUpdate(tblJuntaArchivos);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void deleteJuntaArchivos(TblJuntaArchivos tblJuntaArchivos) throws GeneralException{
		try {
			this.daoJuntaArchivos.delete(tblJuntaArchivos);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void deleteJuntaArchivosByIdJunta(int idJunta) throws GeneralException{
		try {
			this.daoJuntaArchivos.deleteTblJuntaArchivosByIdJunta(idJunta);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public List<TblJuntaParticipantes> getAllJuntaParticipantes() throws GeneralException{
		try {
			return this.daoJuntaParticipantes.getAll();
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void saveJuntaParticipantes(TblJuntaParticipantes tblJuntaParticipantes) throws GeneralException{
		try {
			this.daoJuntaParticipantes.save(tblJuntaParticipantes);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void updateJuntaParticipantes(TblJuntaParticipantes tblJuntaParticipantes) throws GeneralException{
		try {
			this.daoJuntaParticipantes.saveOrUpdate(tblJuntaParticipantes);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void deleteJuntaParticipantes(TblJuntaParticipantes tblJuntaParticipantes) throws GeneralException{
		try {
			this.daoJuntaParticipantes.delete(tblJuntaParticipantes);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void deleteJuntaParticipantesByIdJunta(int idJunta) throws GeneralException{
		try {
			this.daoJuntaParticipantes.deleteTblJuntaParticipantesByIdJunta(idJunta);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
}
