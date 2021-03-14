package com.adinfi.seven.business.domain;

public class RelGrupoZonaCampana implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int grupoId;
	private TblCampanaProgramas tblCampanaProgramas;

	public int getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(int grupoId) {
		this.grupoId = grupoId;
	}

	public TblCampanaProgramas getTblCampanaProgramas() {
		return tblCampanaProgramas;
	}

	public void setTblCampanaProgramas(TblCampanaProgramas tblCampanaProgramas) {
		this.tblCampanaProgramas = tblCampanaProgramas;
	}
}