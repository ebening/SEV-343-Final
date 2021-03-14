package com.adinfi.catalogs.bos;

import java.sql.Date;

public class RegValue {
	private int catRegValId = 0;

	public int getCatRegValId() {
		return catRegValId;
	}

	public void setCatRegValId(int catRegValId) {
		this.catRegValId = catRegValId;
	}

	public int getCatRegId() {
		return catRegId;
	}

	public void setCatRegId(int catRegId) {
		this.catRegId = catRegId;
	}

	public int getCatAttribId() {
		return catAttribId;
	}

	public void setCatAttribId(int catAttribId) {
		this.catAttribId = catAttribId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private int catRegId = 0;
	private int catAttribId = 0;
	private Date updateDate = null;
	private String value = null;

}
