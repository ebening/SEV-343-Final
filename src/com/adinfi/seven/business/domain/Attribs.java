package com.adinfi.seven.business.domain;

// Generated 19/08/2013 10:38:14 AM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Attribs generated by hbm2java
 */
public class Attribs implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int attribId;
	private String attrName;
	private String descrip;
	private Character tipoDato;
	private Short length;
	private Set<CatAttrs> catAttrses = new HashSet<CatAttrs>(0);
	private Set<CatViewControls> catViewControlses = new HashSet<CatViewControls>(
			0);

	public Attribs() {
	}

	public Attribs(int attribId, String attrName) {
		this.attribId = attribId;
		this.attrName = attrName;
	}

	public Attribs(int attribId, String attrName, String descrip,
			Character tipoDato, Short length, Set<CatAttrs> catAttrses,
			Set<CatViewControls> catViewControlses) {
		this.attribId = attribId;
		this.attrName = attrName;
		this.descrip = descrip;
		this.tipoDato = tipoDato;
		this.length = length;
		this.catAttrses = catAttrses;
		this.catViewControlses = catViewControlses;
	}

	public int getAttribId() {
		return this.attribId;
	}

	public void setAttribId(int attribId) {
		this.attribId = attribId;
	}

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Character getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(Character tipoDato) {
		this.tipoDato = tipoDato;
	}

	public Short getLength() {
		return this.length;
	}

	public void setLength(Short length) {
		this.length = length;
	}

	public Set<CatAttrs> getCatAttrses() {
		return this.catAttrses;
	}

	public void setCatAttrses(Set<CatAttrs> catAttrses) {
		this.catAttrses = catAttrses;
	}

	public Set<CatViewControls> getCatViewControlses() {
		return this.catViewControlses;
	}

	public void setCatViewControlses(Set<CatViewControls> catViewControlses) {
		this.catViewControlses = catViewControlses;
	}

	@Override
	public boolean equals(Object o) {
		boolean bRet = false;
		if (o != null && o instanceof Attribs) {
			Attribs other = (Attribs) o;
			if (other.getAttribId() == this.getAttribId()) {
				bRet = true;
			}
		}
		return bRet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		Integer id = Integer.valueOf(attribId);
		hash += id.hashCode();
		return hash;
	}
}
