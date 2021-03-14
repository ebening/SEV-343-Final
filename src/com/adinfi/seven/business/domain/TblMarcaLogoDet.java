package com.adinfi.seven.business.domain;

/**
 * @author christian
 */
public class TblMarcaLogoDet implements java.io.Serializable {

	private static final long serialVersionUID = -4074400736878872536L;
	private int idMarcaDet;
	private TblMarcaLogo tblMarcaLogo;
	private String pathLogo;

	public TblMarcaLogoDet() {
	}

	public TblMarcaLogoDet(TblMarcaLogo tblMarcaLogo, String pathLogo) {
		this.tblMarcaLogo = tblMarcaLogo;
		this.pathLogo = pathLogo;
	}

	public TblMarcaLogoDet(int idMarcaDet, TblMarcaLogo tblMarcaLogo,
			String pathLogo) {
		this.idMarcaDet = idMarcaDet;
		this.tblMarcaLogo = tblMarcaLogo;
		this.pathLogo = pathLogo;
	}

	public int getIdMarcaDet() {
		return this.idMarcaDet;
	}

	public void setIdMarcaDet(int idMarcaDet) {
		this.idMarcaDet = idMarcaDet;
	}

	public TblMarcaLogo getTblMarcaLogo() {
		return this.tblMarcaLogo;
	}

	public void setTblMarcaLogo(TblMarcaLogo tblMarcaLogo) {
		this.tblMarcaLogo = tblMarcaLogo;
	}

	public String getPathLogo() {
		return this.pathLogo;
	}

	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}

}
