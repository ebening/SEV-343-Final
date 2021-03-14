package com.adinfi.seven.business.domain;

/**
 * @author christian
 */
public class TblPrensaTienda implements java.io.Serializable {

	private static final long serialVersionUID = -8115306981969610590L;
	private TblPrensaTiendaId id;
	private TblPrensa tblPrensa;

	public TblPrensaTienda() {
	}

	public TblPrensaTienda(TblPrensaTiendaId id, TblPrensa tblPrensa) {
		this.id = id;
		this.tblPrensa = tblPrensa;
	}

	public TblPrensaTiendaId getId() {
		return this.id;
	}

	public void setId(TblPrensaTiendaId id) {
		this.id = id;
	}

	public TblPrensa getTblPrensa() {
		return this.tblPrensa;
	}

	public void setTblPrensa(TblPrensa tblPrensa) {
		this.tblPrensa = tblPrensa;
	}

}
