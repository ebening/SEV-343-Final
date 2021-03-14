package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

public class TblCampanaAutorizacionId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long idAutorizacion;
	private int idOrden;

	public TblCampanaAutorizacionId() {
	}

	public TblCampanaAutorizacionId(long idAutorizacion, int idOrden) {
		this.idAutorizacion = idAutorizacion;
		this.idOrden = idOrden;
	}

	public long getIdAutorizacion() {
		return this.idAutorizacion;
	}

	public void setIdAutorizacion(long idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
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
		if (!(other instanceof TblCampanaAutorizacionId))
			return false;
		TblCampanaAutorizacionId castOther = (TblCampanaAutorizacionId) other;

		return (this.getIdAutorizacion() == castOther.getIdAutorizacion())
				&& (this.getIdOrden() == castOther.getIdOrden());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;

		result = Constants.TREINTAYSIETE * result + (int) this.getIdAutorizacion();
		result = Constants.TREINTAYSIETE * result + this.getIdOrden();
		return result;
	}
}