package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampanaAutorizacion;
import com.adinfi.seven.business.domain.TblSolicitudAutorizacion;
import com.adinfi.seven.persistence.daos.DAOCampanaAutorizacion;
import com.adinfi.seven.persistence.daos.DAOSolicitudAutorizacion;

public class ServiceSolicitudAutorizacionImpl implements
		ServiceSolicitudAutorizacion {

	private Logger LOG = Logger
			.getLogger(ServiceSolicitudAutorizacionImpl.class);
	private DAOSolicitudAutorizacion daoSolicitudAutorizacion;
	private DAOCampanaAutorizacion daoCampanaAutorizacion;
	private List<TblSolicitudAutorizacion> listTblSolicitudAutorizacion = null;

	@Override
	public TblSolicitudAutorizacion getSolicitudAutorizacion(
			long idSolicitudAutorizacion) {
		return null;
	}

	@Override
	public List<TblSolicitudAutorizacion> getAllSolicitudAutorizacion() {
		try {
			this.setListTblSolicitudAutorizacion(daoSolicitudAutorizacion
					.getAll());
			LOG.info("Lista Size="
					+ this.getListTblSolicitudAutorizacion().size());

			return this.getListTblSolicitudAutorizacion();
		} catch (Exception e) {
			LOG.info(e);
		}
		return null;
	}

	@Override
	public List<TblSolicitudAutorizacion> getAllSolicitudAutorizacionByUser(
			Integer idUsuario) {
		try {
			this.setListTblSolicitudAutorizacion(daoSolicitudAutorizacion
					.getAllCampanasAutorizacionByUser(idUsuario));

			return this.getListTblSolicitudAutorizacion();
		} catch (Exception e) {
			LOG.info(e);
		}
		return null;
	}

	public DAOSolicitudAutorizacion getDaoSolicitudAutorizacion() {
		return daoSolicitudAutorizacion;
	}

	public void setDaoSolicitudAutorizacion(
			DAOSolicitudAutorizacion daoSolicitudAutorizacion) {
		this.daoSolicitudAutorizacion = daoSolicitudAutorizacion;
	}

	@Override
	public TblSolicitudAutorizacion saveSolicitudAutorizacion(
			TblSolicitudAutorizacion tblSolicitudAutorizacion)
			throws GeneralException {
		try {
			daoSolicitudAutorizacion.saveOrUpdate(tblSolicitudAutorizacion);
		} catch (Exception e) {
			LOG.info(e);
		}
		return tblSolicitudAutorizacion;
	}

	@Override
	public TblSolicitudAutorizacion deleteSolicitudAutorizacion(
			TblSolicitudAutorizacion tblSolicitudAutorizacion) {
		return null;
	}

	@Override
	public TblSolicitudAutorizacion updateSolicitudAutorizacion(
			TblSolicitudAutorizacion tblSolicitudAutorizacion)
			throws GeneralException {
		return null;
	}

	public List<TblSolicitudAutorizacion> getListTblSolicitudAutorizacion() {
		return listTblSolicitudAutorizacion;
	}

	public void setListTblSolicitudAutorizacion(
			List<TblSolicitudAutorizacion> listTblSolicitudAutorizacion) {
		this.listTblSolicitudAutorizacion = listTblSolicitudAutorizacion;
	}

	@Override
	public TblCampanaAutorizacion saveCampanaAutorizacion(
			TblCampanaAutorizacion tblCampanaAutorizacion)
			throws GeneralException {
		try {
			daoCampanaAutorizacion.saveOrUpdate(tblCampanaAutorizacion);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(
					"Error al guardar en CampanaAutorizacion", e);
		}
		return tblCampanaAutorizacion;
	}

	public DAOCampanaAutorizacion getDaoCampanaAutorizacion() {
		return daoCampanaAutorizacion;
	}

	public void setDaoCampanaAutorizacion(
			DAOCampanaAutorizacion daoCampanaAutorizacion) {
		this.daoCampanaAutorizacion = daoCampanaAutorizacion;
	}

	public void getCadenaAutorizacion() {

	}

	@Override
	public List<TblSolicitudAutorizacion> getSolicitudAutorizacionActual(
			long idAutorizacion) throws Exception {
		return daoSolicitudAutorizacion
				.getSolicitudAutorizacionById(idAutorizacion);
	}
}
