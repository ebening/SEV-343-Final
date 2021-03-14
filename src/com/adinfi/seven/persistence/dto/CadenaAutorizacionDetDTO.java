package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

import com.adinfi.seven.business.domain.TblCadenaAutorizacion;
import com.adinfi.seven.business.domain.TblCadenaAutorizacionDet;

public class CadenaAutorizacionDetDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private TblCadenaAutorizacionDet tblCadenaAutorizacionDet;
	private TblCadenaAutorizacion tblCadenaAutorizacion;

	public TblCadenaAutorizacion getTblCadenaAutorizacion() {
		return tblCadenaAutorizacion;
	}

	public void setTblCadenaAutorizacion(
			TblCadenaAutorizacion tblCadenaAutorizacion) {
		this.tblCadenaAutorizacion = tblCadenaAutorizacion;
	}

	public TblCadenaAutorizacionDet getTblCadenaAutorizacionDet() {
		return tblCadenaAutorizacionDet;
	}

	public void setTblCadenaAutorizacionDet(
			TblCadenaAutorizacionDet tblCadenaAutorizacionDet) {
		this.tblCadenaAutorizacionDet = tblCadenaAutorizacionDet;
	}

	public String getCompositeKey() {
		return String
				.valueOf(
						this.tblCadenaAutorizacionDet.getId()
								.getIdCadenaAutorizacion()).concat(
						String.valueOf(this.tblCadenaAutorizacionDet.getId()
								.getIdOrden()));
	}

}
