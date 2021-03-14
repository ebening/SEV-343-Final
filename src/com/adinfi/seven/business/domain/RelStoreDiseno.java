package com.adinfi.seven.business.domain;

public class RelStoreDiseno  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int storeId;
	private TblDisenos disenos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public TblDisenos getDisenos() {
		return disenos;
	}
	public void setDisenos(TblDisenos disenos) {
		this.disenos = disenos;
	}
}