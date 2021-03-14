package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

public class GenericItemString implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String value;
	private Object obj;

	public GenericItemString() {
		super();
	}

	public GenericItemString(String id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GenericItemString)) {
			return false;
		}
		GenericItemString other = (GenericItemString) object;
		if (id.equals(other.getId()) == false) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * @param obj
	 *            the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
}