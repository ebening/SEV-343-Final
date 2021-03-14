package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.Bitacora;
import com.adinfi.seven.business.domain.BitacoraParam;
import com.adinfi.seven.business.domain.BitacoraTipo;
import com.adinfi.seven.persistence.daos.DAOBitacora;
import com.adinfi.seven.persistence.daos.DAOBitacoraMetodo;
import com.adinfi.seven.persistence.daos.DAOBitacoraParam;
import com.adinfi.seven.persistence.daos.DAOBitacoraTipo;

public class ServiceBitacoraImpl implements ServiceBitacora {
	private Logger LOG = Logger.getLogger(ServiceBitacoraImpl.class);
	public DAOBitacora daoBitacora;
	public DAOBitacoraParam daoBitacoraParam;
	public DAOBitacoraTipo daoBitacoraTipo;
	public DAOBitacoraMetodo daoBitacoraMetodo;
	
	@Override
	public List<Bitacora> getBitacora() throws GeneralException{
		try {
			return daoBitacora.getAll();
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public List<BitacoraTipo> getTipoActivo() throws GeneralException{
		List<BitacoraTipo> tipos= null;
		try{
			tipos= daoBitacoraTipo.getTipoActivo();
		}catch(Exception e){
			LOG.error(e);
			throw new GeneralException(e);
		}
		return tipos;
	}
	
	@Override
	public void saveBitacora(Bitacora bitacora) throws GeneralException {
		try{
			daoBitacora.save(bitacora);
		}catch(Exception e){
			LOG.error(e);
			throw new GeneralException(e);
		}
	}

	@Override
	public void saveBitacoraParam(BitacoraParam param) throws GeneralException {
		try{
			daoBitacoraParam.save(param);
		}catch(Exception e){
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	/**
	 * @return the daoBitacora
	 */
	public DAOBitacora getDaoBitacora() {
		return daoBitacora;
	}
	/**
	 * @param daoBitacora the daoBitacora to set
	 */
	public void setDaoBitacora(DAOBitacora daoBitacora) {
		this.daoBitacora = daoBitacora;
	}
	/**
	 * @return the daoBitacoraParam
	 */
	public DAOBitacoraParam getDaoBitacoraParam() {
		return daoBitacoraParam;
	}
	/**
	 * @param daoBitacoraParam the daoBitacoraParam to set
	 */
	public void setDaoBitacoraParam(DAOBitacoraParam daoBitacoraParam) {
		this.daoBitacoraParam = daoBitacoraParam;
	}

	/**
	 * @return the daoBitacoraTipo
	 */
	public DAOBitacoraTipo getDaoBitacoraTipo() {
		return daoBitacoraTipo;
	}

	/**
	 * @param daoBitacoraTipo the daoBitacoraTipo to set
	 */
	public void setDaoBitacoraTipo(DAOBitacoraTipo daoBitacoraTipo) {
		this.daoBitacoraTipo = daoBitacoraTipo;
	}

	/**
	 * @return the daoBitacoraMetodo
	 */
	public DAOBitacoraMetodo getDaoBitacoraMetodo() {
		return daoBitacoraMetodo;
	}

	/**
	 * @param daoBitacoraMetodo the daoBitacoraMetodo to set
	 */
	public void setDaoBitacoraMetodo(DAOBitacoraMetodo daoBitacoraMetodo) {
		this.daoBitacoraMetodo = daoBitacoraMetodo;
	}

	@Override
	public List<BitacoraTipo> getNiveles() throws GeneralException {
		try{
			return daoBitacoraTipo.getAll();
		}catch(Exception e){
			LOG.error(e);
			throw new GeneralException(e);
		}
	}

	@Override
	public void saveNiveles(Integer id, int active) throws GeneralException {
		try{
			BitacoraTipo tipo= daoBitacoraTipo.getById(id);
			tipo.setActive(active);
			daoBitacoraTipo.saveOrUpdate(tipo);
		}catch(Exception e){
			LOG.error(e);
			throw new GeneralException(e);
		}
	}

	@Override
	public List<BitacoraParam> getParamsListByBitacoraId(int bitacora)
			throws GeneralException {
		return daoBitacoraParam.getParamsListByBitacoraId(bitacora);
	}
}
