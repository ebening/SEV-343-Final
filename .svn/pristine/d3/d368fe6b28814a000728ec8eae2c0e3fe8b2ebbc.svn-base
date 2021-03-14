package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

public class ColumnModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String header;
	private String property;

	public ColumnModel(String header, String property) {
		super();
		this.header = header;
		this.property = property;
	}

	public String getHeader() {
		return header;
	}

	public String getProperty() {
		return property;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ColumnModel)) {
			return false;
		}
		ColumnModel other = (ColumnModel) object;
		if (header.compareTo(other.getHeader()) != 0) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (header != null ? header.hashCode() : 0);
		return hash;
	}
}
