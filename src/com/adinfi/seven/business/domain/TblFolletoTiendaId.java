package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author christian
 */
public class TblFolletoTiendaId implements java.io.Serializable {

	private static final long serialVersionUID = -2886515410718942050L;
	private int idFolleto;
	private int idTienda;

	public TblFolletoTiendaId() {
	}

	public TblFolletoTiendaId(int idFolleto, int idTienda) {
		this.idFolleto = idFolleto;
		this.idTienda = idTienda;
	}

	public int getIdFolleto() {
		return this.idFolleto;
	}

	public void setIdFolleto(int idFolleto) {
		this.idFolleto = idFolleto;
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
		if (!(other instanceof TblFolletoTiendaId))
			return false;
		TblFolletoTiendaId castOther = (TblFolletoTiendaId) other;

		return (this.getIdFolleto() == castOther.getIdFolleto())
				&& (this.getIdTienda() == castOther.getIdTienda());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;

		result = Constants.TREINTAYSIETE * result + this.getIdFolleto();
		result = Constants.TREINTAYSIETE * result + this.getIdTienda();
		return result;
	}

}
