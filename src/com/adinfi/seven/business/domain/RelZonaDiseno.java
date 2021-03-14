package com.adinfi.seven.business.domain;

public class RelZonaDiseno  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    private int id;
	private int zonaId;
	private TblDisenos disenos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZonaId() {
		return zonaId;
	}
	public void setZonaId(int zonaId) {
		this.zonaId = zonaId;
	}
	public TblDisenos getDisenos() {
		return disenos;
	}
	public void setDisenos(TblDisenos disenos) {
		this.disenos = disenos;
	}
}