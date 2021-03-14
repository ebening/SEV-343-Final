package com.adinfi.seven.business.services;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCadenaAutorizacion;
import com.adinfi.seven.business.domain.TblCadenaAutorizacionDet;
import com.adinfi.seven.persistence.daos.DAOCadenaAutorizacion;
import com.adinfi.seven.persistence.daos.DAOCadenaAutorizacionDet;

public class ServiceCadenaAutorizacionImpl implements ServiceCadenaAutorizacion {
	private Logger LOG = Logger.getLogger(ServiceCadenaAutorizacionImpl.class);
	private DAOCadenaAutorizacion daoCadenaAutorizacion;
	private DAOCadenaAutorizacionDet daoCadenaAutorizacionDet;

	public DAOCadenaAutorizacionDet getDaoCadenaAutorizacionDet() {
		return daoCadenaAutorizacionDet;
	}

	public void setDaoCadenaAutorizacionDet(
			DAOCadenaAutorizacionDet daoCadenaAutorizacionDet) {
		this.daoCadenaAutorizacionDet = daoCadenaAutorizacionDet;
	}

	public DAOCadenaAutorizacion getDaoCadenaAutorizacion() {
		return daoCadenaAutorizacion;
	}

	public void setDaoCadenaAutorizacion(
			DAOCadenaAutorizacion daoCadenaAutorizacion) {
		this.daoCadenaAutorizacion = daoCadenaAutorizacion;
	}

	@Override
	public void crearCadenaAutorizacion(
			TblCadenaAutorizacion tblCadenaAutorizacion) {
		try {
			daoCadenaAutorizacion.saveOrUpdate(tblCadenaAutorizacion);
		} catch (Exception e) {
			LOG.error(e);
		}

	}

	@Override
	public List<TblCadenaAutorizacion> getCadenaAutorizacionList()
			throws Exception {
		return daoCadenaAutorizacion.getAll();
	}

	@Override
	public void eliminarCadenaAutorizacion(
			TblCadenaAutorizacion tblCadenaAutorizacion) {
		try {
			daoCadenaAutorizacion.delete(tblCadenaAutorizacion);
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	@Override
	public TblCadenaAutorizacion getCadenaAutorizacionById(TblCadenaAutorizacion tblCadenaAutorizacion) {
		TblCadenaAutorizacion tblCadAut = new TblCadenaAutorizacion();
		try {
			 tblCadAut = daoCadenaAutorizacion.getCadenaAutorizacion(tblCadenaAutorizacion.getIdCadenaAutorizacion());
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return tblCadAut;
	}

	@Override
	public List<TblCadenaAutorizacion> getListCadenaAutorizacionDet(
			TblCadenaAutorizacion tblCadenaAutorizacion)
			throws GeneralException {
		return daoCadenaAutorizacion
				.getCadenaAutorizacionList(tblCadenaAutorizacion);
	}

	@Override
	public void guardarAutorizacionDet(
			TblCadenaAutorizacion tblCadenaAutorizacion) {

		for (Iterator<TblCadenaAutorizacionDet> iterator = tblCadenaAutorizacion
				.getTblCadenaAutorizacionDets().iterator(); iterator.hasNext();) {
			TblCadenaAutorizacionDet tblCadenaAutorizacionDet = (TblCadenaAutorizacionDet) iterator
					.next();
			try {
				daoCadenaAutorizacionDet.saveOrUpdate(tblCadenaAutorizacionDet);
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}

	@Override
	public void eliminarAutorizacionDet(
			TblCadenaAutorizacion tblCadenaAutorizacion) {

		for (Iterator<?> iterator = tblCadenaAutorizacion
				.getTblCadenaAutorizacionDets().iterator(); iterator.hasNext();) {
			TblCadenaAutorizacionDet tblCadenaAutorizacionDet = (TblCadenaAutorizacionDet) iterator
					.next();
			try {
				daoCadenaAutorizacionDet.delete(tblCadenaAutorizacionDet);
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}

	@Override
	public void eliminarAutorizacionDetById(
			TblCadenaAutorizacionDet tblCadenaAutorizacionDet) {
		try {
			daoCadenaAutorizacionDet.delete(tblCadenaAutorizacionDet);
		} catch (Exception e) {
			LOG.error(e);
		}

	}

	@Override
	public TblCadenaAutorizacionDet getIdOrdenByIdCadenaAutorizacion(
			int idCadenaAutorizacion) {
		try {
			return daoCadenaAutorizacionDet
					.getIdOrdenCadenaAutorizacionDet(idCadenaAutorizacion);
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	@Override
	public void crearCadenaAutorizacionDet(
			TblCadenaAutorizacionDet tblCadenaAutorizacionDet) {
		try {
			daoCadenaAutorizacionDet.saveOrUpdate(tblCadenaAutorizacionDet);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	@Override
	public List<TblCadenaAutorizacionDet> obtenerCadenaAutorizacionDetList(
			TblCadenaAutorizacion tblCadenaAutorizacion) {
		try {
			return daoCadenaAutorizacionDet
					.getCadenaAutorizacionDetList(tblCadenaAutorizacion);
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}
}
