package com.adinfi.seven.business.domain;

import com.adinfi.seven.presentation.views.util.Constants;

public class TblCampanaProgramasId implements java.io.Serializable {
	private static final long serialVersionUID = 2356770379804692715L;
	private long idCampana;
	private int idPrograma;
	
	public TblCampanaProgramasId(){
	}
	
	public TblCampanaProgramasId(long idCampana, int idPrograma) {
		this.idCampana = idCampana;
		this.idPrograma = idPrograma;
	}
	
	public long getIdCampana() {
		return idCampana;
	}
	public void setIdCampana(long idCampana) {
		this.idCampana = idCampana;
	}
	public int getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TblCampanaProgramasId))
			return false;
		TblCampanaProgramasId castOther = (TblCampanaProgramasId) other;

		return (this.getIdCampana() == castOther.getIdCampana())
				&& (this.getIdPrograma() == castOther.getIdPrograma());
	}

	public int hashCode() {
		int result = Constants.DIECISIETE;
        
        result = Constants.TREINTAYSIETE * result + Long.valueOf(getIdCampana()).intValue();
        result = Constants.TREINTAYSIETE * result + this.getIdPrograma();
        return result;
	}
}
