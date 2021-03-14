package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author Carlos Félix
 */
public class TblFolletoZonaId implements java.io.Serializable {

	private static final long serialVersionUID = -2886515410718942050L;
	private int idFolleto;
	private int idZona;

	public TblFolletoZonaId() {
	}

	public TblFolletoZonaId(int idFolleto, int idZona) {
		this.idFolleto = idFolleto;
		this.idZona = idZona;
	}

	public int getIdFolleto() {
		return this.idFolleto;
	}

	public void setIdFolleto(int idFolleto) {
		this.idFolleto = idFolleto;
	}

	public int getIdZona() {
		return this.idZona;
	}

	public void setIdZona(int idTienda) {
		this.idZona = idTienda;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblFolletoZonaId))
			return false;
		TblFolletoZonaId castOther = (TblFolletoZonaId) other;

		return (this.getIdFolleto() == castOther.getIdFolleto())
				&& (this.getIdZona() == castOther.getIdZona());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;

		result = Constants.TREINTAYSIETE * result + this.getIdFolleto();
		result = Constants.TREINTAYSIETE * result + this.getIdZona();
		return result;
	}

}
