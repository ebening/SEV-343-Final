package com.adinfi.catalogs.bos;

public class CatAttrib {

	public int getCatAttribId() {
		return catAttribId;
	}

	public void setCatAttribId(int catAttribId) {
		this.catAttribId = catAttribId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getAttribId() {
		return attribId;
	}

	public void setAttribId(int attribId) {
		this.attribId = attribId;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	private int catAttribId = 0;

	private int catId = 0;

	public String getAttribName() {
		return attribName;
	}

	public void setAttribName(String attribName) {
		this.attribName = attribName;
	}

	private int attribId = 0;
	private String estatus = null;
	private boolean required = false;
	private boolean unique = false;
	private String attribName = null;

}
