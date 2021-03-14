package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

/**
 * @author christian
 */
public class TblPrensaEspaciosId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5439682123183334253L;
	private int idEspacio;
	private int idPrensa;

	public TblPrensaEspaciosId() {
	}

	public TblPrensaEspaciosId(int idEspacio, int idPrensa) {
		this.idEspacio = idEspacio;
		this.idPrensa = idPrensa;
	}

	public int getIdEspacio() {
		return this.idEspacio;
	}

	public void setIdEspacio(int idEspacio) {
		this.idEspacio = idEspacio;
	}

	public int getIdPrensa() {
		return this.idPrensa;
	}

	public void setIdPrensa(int idPrensa) {
		this.idPrensa = idPrensa;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblPrensaEspaciosId))
			return false;
		TblPrensaEspaciosId castOther = (TblPrensaEspaciosId) other;

		return (this.getIdEspacio() == castOther.getIdEspacio())
				&& (this.getIdPrensa() == castOther.getIdPrensa());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;

		result = Constants.TREINTAYSIETE * result + this.getIdEspacio();
		result = Constants.TREINTAYSIETE * result + this.getIdPrensa();
		return result;
	}

}
