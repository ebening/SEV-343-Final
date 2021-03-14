package com.adinfi.seven.business.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author christian
 */
public class TblPrensa implements java.io.Serializable {

	private static final long serialVersionUID = 7001662493984117864L;
	private int idPrensa;
	private TblCampana tblCampana;
	private Date fehcaInicio;
	private Date fechaFin;
	private Integer espacios;
	private Set tblArticulosEspacioses = new HashSet(0);
	private Set tblPrensaTiendas = new HashSet(0);
	private Set tblPrensaEspacioses = new HashSet(0);
	private Set tblPreciosPrensas = new HashSet(0);

	public TblPrensa() {
	}

	public TblPrensa(int idPrensa, TblCampana tblCampana) {
		this.idPrensa = idPrensa;
		this.tblCampana = tblCampana;
	}

	public TblPrensa(int idPrensa, TblCampana tblCampana, Date fehcaInicio,
			Date fechaFin, Integer espacios, Set tblArticulosEspacioses,
			Set tblPrensaTiendas, Set tblPrensaEspacioses, Set tblPreciosPrensas) {
		this.idPrensa = idPrensa;
		this.tblCampana = tblCampana;
		this.fehcaInicio = fehcaInicio;
		this.fechaFin = fechaFin;
		this.espacios = espacios;
		this.tblArticulosEspacioses = tblArticulosEspacioses;
		this.tblPrensaTiendas = tblPrensaTiendas;
		this.tblPrensaEspacioses = tblPrensaEspacioses;
		this.tblPreciosPrensas = tblPreciosPrensas;
	}

	public int getIdPrensa() {
		return this.idPrensa;
	}

	public void setIdPrensa(int idPrensa) {
		this.idPrensa = idPrensa;
	}

	public TblCampana getTblCampana() {
		return this.tblCampana;
	}

	public void setTblCampana(TblCampana tblCampana) {
		this.tblCampana = tblCampana;
	}

	public Date getFehcaInicio() {
		return this.fehcaInicio;
	}

	public void setFehcaInicio(Date fehcaInicio) {
		this.fehcaInicio = fehcaInicio;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getEspacios() {
		return this.espacios;
	}

	public void setEspacios(Integer espacios) {
		this.espacios = espacios;
	}

	public Set getTblArticulosEspacioses() {
		return this.tblArticulosEspacioses;
	}

	public void setTblArticulosEspacioses(Set tblArticulosEspacioses) {
		this.tblArticulosEspacioses = tblArticulosEspacioses;
	}

	public Set getTblPrensaTiendas() {
		return this.tblPrensaTiendas;
	}

	public void setTblPrensaTiendas(Set tblPrensaTiendas) {
		this.tblPrensaTiendas = tblPrensaTiendas;
	}

	public Set getTblPrensaEspacioses() {
		return this.tblPrensaEspacioses;
	}

	public void setTblPrensaEspacioses(Set tblPrensaEspacioses) {
		this.tblPrensaEspacioses = tblPrensaEspacioses;
	}

    public Set getTblPreciosPrensas() {
        return this.tblPreciosPrensas;
    }
    
    public void setTblPreciosPrensas(Set tblPreciosPrensas) {
        this.tblPreciosPrensas = tblPreciosPrensas;
    }
}
