package com.adinfi.catalogs.bos.exception.catalogs;

public class CatalogExceptionInfo {

	private int catId = 0;
	private int attrId = 0;
	private String attrName = null;
	private String catName = null;
	private int catIdSrc = 0;
	private int attrIdSrc = 0;
	private String extFkTable = null;
	private String extFkField = null;

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	private int regId = 0;
	private Object value = null;
	private String attrSrcName = null;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getAttrId() {
		return attrId;
	}

	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getCatIdSrc() {
		return catIdSrc;
	}

	public void setCatIdSrc(int catIdSrc) {
		this.catIdSrc = catIdSrc;
	}

	public int getAttrIdSrc() {
		return attrIdSrc;
	}

	public void setAttrIdSrc(int attrIdSrc) {
		this.attrIdSrc = attrIdSrc;
	}

	public String getAttrSrcName() {
		return attrSrcName;
	}

	public void setAttrSrcName(String attrSrcName) {
		this.attrSrcName = attrSrcName;
	}

	public String getExtFkTable() {
		return extFkTable;
	}

	public void setExtFkTable(String extFkTable) {
		this.extFkTable = extFkTable;
	}

	public String getExtFkField() {
		return extFkField;
	}

	public void setExtFkField(String extFkField) {
		this.extFkField = extFkField;
	}

}
