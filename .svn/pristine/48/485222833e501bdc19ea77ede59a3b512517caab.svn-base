package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

public class GenericItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String value;
	private Object obj;

	public GenericItem() {
		super();
	}

	public GenericItem(Integer id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof GenericItem)) {
			return false;
		}
		GenericItem other = (GenericItem) object;
		if (id.compareTo(other.getId()) != 0) {
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
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
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