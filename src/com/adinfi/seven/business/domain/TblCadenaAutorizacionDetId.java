package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

public class TblCadenaAutorizacionDetId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int idCadenaAutorizacion;
	private int idOrden;

	public TblCadenaAutorizacionDetId() {
	}

	public TblCadenaAutorizacionDetId(int idCadenaAutorizacion, int idOrden) {
		this.idCadenaAutorizacion = idCadenaAutorizacion;
		this.idOrden = idOrden;
	}

	public int getIdCadenaAutorizacion() {
		return this.idCadenaAutorizacion;
	}

	public void setIdCadenaAutorizacion(int idCadenaAutorizacion) {
		this.idCadenaAutorizacion = idCadenaAutorizacion;
	}

	public int getIdOrden() {
		return this.idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblCadenaAutorizacionDetId))
			return false;
		TblCadenaAutorizacionDetId castOther = (TblCadenaAutorizacionDetId) other;

		return (this.getIdCadenaAutorizacion() == castOther
				.getIdCadenaAutorizacion())
				&& (this.getIdOrden() == castOther.getIdOrden());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;

		result = Constants.TREINTAYSIETE * result + this.getIdCadenaAutorizacion();
		result = Constants.TREINTAYSIETE * result + this.getIdOrden();
		return result;
	}
}