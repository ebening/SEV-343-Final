package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author christian
 */
public class TblPrensaTiendaId implements java.io.Serializable {

	private static final long serialVersionUID = -6268551122255829939L;
	private int idPrensa;
	private int idTienda;

	public TblPrensaTiendaId() {
	}

	public TblPrensaTiendaId(int idPrensa, int idTienda) {
		this.idPrensa = idPrensa;
		this.idTienda = idTienda;
	}

	public int getIdPrensa() {
		return this.idPrensa;
	}

	public void setIdPrensa(int idPrensa) {
		this.idPrensa = idPrensa;
	}

	public int getIdTienda() {
		return this.idTienda;
	}

	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblPrensaTiendaId))
			return false;
		TblPrensaTiendaId castOther = (TblPrensaTiendaId) other;

		return (this.getIdPrensa() == castOther.getIdPrensa())
				&& (this.getIdTienda() == castOther.getIdTienda());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;

		result = Constants.TREINTAYSIETE * result + this.getIdPrensa();
		result = Constants.TREINTAYSIETE * result + this.getIdTienda();
		return result;
	}

}
