package com.adinfi.seven.business.domain;

public class CatViewControls implements Comparable<CatViewControls>,
		java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int catViewControlId;
	private Catalogs catalogsByCatId;
	private Attribs attribs;
	private Catalogs catalogsByCatIdSrc;
	private char controlType;
	private Character visible;
	private int attribId;
	private Integer catIdSrcAttrText;
	private String label;
	private Short orderId;
	private Character readOnly;
	private String format;

	public CatViewControls() {
	}

	public CatViewControls(int catViewControlId, Catalogs catalogsByCatId,
			char controlType, int attribId) {
		this.catViewControlId = catViewControlId;
		this.catalogsByCatId = catalogsByCatId;
		this.controlType = controlType;
		this.attribId = attribId;
	}

	public CatViewControls(int catViewControlId, Catalogs catalogsByCatId,
			Attribs attribs, Catalogs catalogsByCatIdSrc, char controlType,
			Character visible, int attribId, Integer catIdSrcAttrText,
			String label, Short orderId, Character readOnly, String format) {
		this.catViewControlId = catViewControlId;
		this.catalogsByCatId = catalogsByCatId;
		this.attribs = attribs;
		this.catalogsByCatIdSrc = catalogsByCatIdSrc;
		this.controlType = controlType;
		this.visible = visible;
		this.attribId = attribId;
		this.catIdSrcAttrText = catIdSrcAttrText;
		this.label = label;
		this.orderId = orderId;
		this.readOnly = readOnly;
		this.format = format;
	}

	public int getCatViewControlId() {
		return this.catViewControlId;
	}

	public void setCatViewControlId(int catViewControlId) {
		this.catViewControlId = catViewControlId;
	}

	public Catalogs getCatalogsByCatId() {
		return this.catalogsByCatId;
	}

	public void setCatalogsByCatId(Catalogs catalogsByCatId) {
		this.catalogsByCatId = catalogsByCatId;
	}

	public Attribs getAttribs() {
		return this.attribs;
	}

	public void setAttribs(Attribs attribs) {
		this.attribs = attribs;
	}

	public Catalogs getCatalogsByCatIdSrc() {
		return this.catalogsByCatIdSrc;
	}

	public void setCatalogsByCatIdSrc(Catalogs catalogsByCatIdSrc) {
		this.catalogsByCatIdSrc = catalogsByCatIdSrc;
	}

	public char getControlType() {
		return this.controlType;
	}

	public void setControlType(char controlType) {
		this.controlType = controlType;
	}

	public Character getVisible() {
		return this.visible;
	}

	public void setVisible(Character visible) {
		this.visible = visible;
	}

	public int getAttribId() {
		return this.attribId;
	}

	public void setAttribId(int attribId) {
		this.attribId = attribId;
	}

	public Integer getCatIdSrcAttrText() {
		return this.catIdSrcAttrText;
	}

	public void setCatIdSrcAttrText(Integer catIdSrcAttrText) {
		this.catIdSrcAttrText = catIdSrcAttrText;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Short getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Short orderId) {
		this.orderId = orderId;
	}

	public Character getReadOnly() {
		return this.readOnly;
	}

	public void setReadOnly(Character readOnly) {
		this.readOnly = readOnly;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CatViewControls)) {
			return false;
		}
		CatViewControls other = (CatViewControls) object;
		if (orderId.compareTo(other.getOrderId()) != 0) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (orderId != null ? orderId.hashCode() : 0);
		return hash;
	}

	@Override
	public int compareTo(final CatViewControls other) {
		return nullSafeStringComparator(this.orderId, other.orderId);
	}

	public static int nullSafeStringComparator(final Short one, final Short two) {
		if (one == null && two == null) {
			return 0;
		}

		if (one == null || two == null) {
			return (one == null) ? -1 : 1;
		}

		return one.compareTo(two);
	}

	@Override
	public String toString() {
		return "CVCID=" + this.getCatViewControlId() + " AttribId="
				+ this.getAttribId() + " Label=" + this.label + " Visible="
				+ this.getVisible() + " ReadOnly" + this.getReadOnly();
	}
}