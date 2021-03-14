package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

public class TblCampanaDetalleId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long idCampana;
	private int idPrograma;

	public TblCampanaDetalleId() {
	}

	public TblCampanaDetalleId(long idCampana, int idPrograma) {
		this.idCampana = idCampana;
		this.idPrograma = idPrograma;
	}

	public long getIdCampana() {
		return this.idCampana;
	}

	public void setIdCampana(long idCampana) {
		this.idCampana = idCampana;
	}

	public int getIdPrograma() {
		return this.idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblCampanaDetalleId))
			return false;
		TblCampanaDetalleId castOther = (TblCampanaDetalleId) other;

		return (this.getIdCampana() == castOther.getIdCampana())
				&& (this.getIdPrograma() == castOther.getIdPrograma());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;

		result = Constants.TREINTAYSIETE * result + (int) this.getIdCampana();
		result = Constants.TREINTAYSIETE * result + this.getIdPrograma();
		return result;
	}

}
