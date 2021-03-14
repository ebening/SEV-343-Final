package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author christian
 */
public class TblMarcaLogo implements java.io.Serializable {

	private static final long serialVersionUID = -4167995233387429111L;
	private int idMarca;
	private String nombreMarca;
	private Set<TblMarcaLogoDet> tblMarcaLogoDets = new HashSet<TblMarcaLogoDet>(
			0);

	public TblMarcaLogo() {
	}

	public TblMarcaLogo(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public TblMarcaLogo(int idMarca, String nombreMarca) {
		this.idMarca = idMarca;
		this.nombreMarca = nombreMarca;
	}

	public TblMarcaLogo(String nombreMarca,
			Set<TblMarcaLogoDet> tblMarcaLogoDets) {
		this.nombreMarca = nombreMarca;
		this.tblMarcaLogoDets = tblMarcaLogoDets;
	}

	public TblMarcaLogo(int idMarca, String nombreMarca,
			Set<TblMarcaLogoDet> tblMarcaLogoDets) {
		this.idMarca = idMarca;
		this.nombreMarca = nombreMarca;
		this.tblMarcaLogoDets = tblMarcaLogoDets;
	}

	public int getIdMarca() {
		return this.idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getNombreMarca() {
		return this.nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public Set<TblMarcaLogoDet> getTblMarcaLogoDets() {
		return this.tblMarcaLogoDets;
	}

	public void setTblMarcaLogoDets(Set<TblMarcaLogoDet> tblMarcaLogoDets) {
		this.tblMarcaLogoDets = tblMarcaLogoDets;
	}

}
