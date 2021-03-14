package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

public class TblCampanaCategoriasId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private long idCampana;
	private int idCategoria;

	public TblCampanaCategoriasId() {
		
	}

	public TblCampanaCategoriasId(long idCampana, int idCategoria) {
		this.idCampana = idCampana;
		this.idCategoria = idCategoria;
	}

	public long getIdCampana() {
		return this.idCampana;
	}

	public void setIdCampana(long idCampana) {
		this.idCampana = idCampana;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblCampanaCategoriasId))
			return false;
		TblCampanaCategoriasId castOther = (TblCampanaCategoriasId) other;

		return (this.getIdCampana() == castOther.getIdCampana())
				&& (this.getIdCategoria() == castOther.getIdCategoria());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;

		result = Constants.TREINTAYSIETE * result + (int) this.getIdCampana();
		result = Constants.TREINTAYSIETE * result + this.getIdCategoria();
		return result;
	}
}