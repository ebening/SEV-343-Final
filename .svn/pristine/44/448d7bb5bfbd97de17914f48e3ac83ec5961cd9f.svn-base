package com.adinfi.seven.business.domain;

import java.io.Serializable;

import com.adinfi.seven.presentation.views.util.Constants;

public class TblFolletoHojasId implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idFolleto;
	private int idHoja;
	public int getIdFolleto() {
		return idFolleto;
	}
	public void setIdFolleto(int idFolleto) {
		this.idFolleto = idFolleto;
	}
	public int getIdHoja() {
		return idHoja;
	}
	public void setIdHoja(int idHoja) {
		this.idHoja = idHoja;
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblFolletoHojasId))
			return false;
		TblFolletoHojasId castOther = (TblFolletoHojasId) other;

		return (this.getIdFolleto() == castOther.getIdFolleto())
				&& (this.getIdHoja() == castOther.getIdHoja());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;
		result = Constants.TREINTAYSIETE * result + this.getIdFolleto();
		result = Constants.TREINTAYSIETE * result + this.getIdHoja();
		return result;
	}
}
