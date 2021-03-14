package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampanaAutorizacion;
import com.adinfi.seven.business.domain.TblSolicitudAutorizacion;

public interface ServiceSolicitudAutorizacion {

	TblSolicitudAutorizacion saveSolicitudAutorizacion(
			TblSolicitudAutorizacion tblSolicitudAutorizacion)
			throws GeneralException;

	TblSolicitudAutorizacion deleteSolicitudAutorizacion(
			TblSolicitudAutorizacion tblSolicitudAutorizacion);

	TblSolicitudAutorizacion updateSolicitudAutorizacion(
			TblSolicitudAutorizacion tblSolicitudAutorizacion)
			throws GeneralException;

	TblSolicitudAutorizacion getSolicitudAutorizacion(
			long idSolicitudAutorizacion);

	List<TblSolicitudAutorizacion> getAllSolicitudAutorizacion();

	TblCampanaAutorizacion saveCampanaAutorizacion(
			TblCampanaAutorizacion tblCampanaAutorizacion)
			throws GeneralException;

	List<TblSolicitudAutorizacion> getAllSolicitudAutorizacionByUser(
			Integer idUsuario);

	List<TblSolicitudAutorizacion> getSolicitudAutorizacionActual(
			long idAutorizacion) throws Exception;
}
