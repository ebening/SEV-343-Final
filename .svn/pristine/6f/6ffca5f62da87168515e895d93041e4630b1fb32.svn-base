package com.adinfi.catalogs.bos;

/**
 * Objeto que encapsula un parámetro de búsqueda en los catálogos dinámicos.
 *
 */
public class AttrSearch {

	public static final int SEARCH_TYPE_EQUAL = 0;
	public static final int SEARCH_TYPE_LIKE = 1;
	public static final int SEARCH_TYPE_NOTEQUAL = 2;
	public static final int SEARCH_TYPE_EQUAL_OR = 3;
	private int searchType = 0;
	private String attrName = null;
	private String value = null;
	private boolean exact = true;

	public AttrSearch() {
		super();
	}

	public AttrSearch(String attrName, String value) {
		super();
		this.attrName = attrName;
		this.value = value;
	}

	@Override
	public String toString() {
		return "searchType:" + searchType + " attrName:" + attrName + " value:"
				+ value + " exact:" + exact;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isExact() {
		return exact;
	}

	public void setExact(boolean exact) {
		this.exact = exact;
	}
}
