package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

import com.adinfi.seven.business.domain.TblCadenaAutorizacion;
import com.adinfi.seven.business.domain.TblCampanaAutorizacion;
import com.adinfi.seven.business.domain.TblSolicitudAutorizacion;

public class SolicitudAutorizacionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	UsuarioDTO usuarioDTO;
	UsuarioDTO usuarioDTOSolicitud;

	TblSolicitudAutorizacion tblSolicitudAutorizacion;
	PeriodoDTO periodoDTO;
	String nombreCompleto;
	TblCampanaAutorizacion tblCampanaAutorizacion;
	TblCadenaAutorizacion tblCadenaAutorizacion;
	CampanaDTO campanaDTO;

	public CampanaDTO getCampanaDTO() {
		return campanaDTO;
	}

	public void setCampanaDTO(CampanaDTO campanaDTO) {
		this.campanaDTO = campanaDTO;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public TblSolicitudAutorizacion getTblSolicitudAutorizacion() {
		return tblSolicitudAutorizacion;
	}

	public void setTblSolicitudAutorizacion(
			TblSolicitudAutorizacion tblSolicitudAutorizacion) {
		this.tblSolicitudAutorizacion = tblSolicitudAutorizacion;
	}

	public PeriodoDTO getPeriodoDTO() {
		return periodoDTO;
	}

	public void setPeriodoDTO(PeriodoDTO periodoDTO) {
		this.periodoDTO = periodoDTO;
	}

	public String getNombreCompleto() {
		return usuarioDTOSolicitud.getName() + " "
				+ usuarioDTOSolicitud.getPlastName();
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public TblCampanaAutorizacion getTblCampanaAutorizacion() {
		return tblCampanaAutorizacion;
	}

	public void setTblCampanaAutorizacion(
			TblCampanaAutorizacion tblCampanaAutorizacion) {
		this.tblCampanaAutorizacion = tblCampanaAutorizacion;
	}

	public TblCadenaAutorizacion getTblCadenaAutorizacion() {
		return tblCadenaAutorizacion;
	}

	public void setTblCadenaAutorizacion(
			TblCadenaAutorizacion tblCadenaAutorizacion) {
		this.tblCadenaAutorizacion = tblCadenaAutorizacion;
	}

	public UsuarioDTO getUsuarioDTOSolicitud() {
		return usuarioDTOSolicitud;
	}

	public void setUsuarioDTOSolicitud(UsuarioDTO usuarioDTOSolicitud) {
		this.usuarioDTOSolicitud = usuarioDTOSolicitud;
	}
}
