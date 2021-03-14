package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TblCampanaCategorias implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private TblCampanaCategoriasId id;
	private TblCampana tblCampana;
	private Set<TblCampanaCategoriasPlaza> tblCategoriasPlazas = new HashSet<TblCampanaCategoriasPlaza>(0);
	private int idPrograma;
	private double ingreso;
	private int esSencillo;
	private List<String> plazaSelect;
	private String descripcion;
	private boolean selected;

	public TblCampanaCategorias() {
	}

	public TblCampanaCategorias(TblCampanaCategoriasId id, TblCampana tblCampana) {
		this.id = id;
		this.tblCampana = tblCampana;
	}

	public TblCampanaCategorias(TblCampanaCategoriasId id) {
		this.id = id;
	}

	public TblCampanaCategoriasId getId() {
		return this.id;
	}

	public void setId(TblCampanaCategoriasId id) {
		this.id = id;
	}

	public TblCampana getTblCampana() {
		return this.tblCampana;
	}

	public void setTblCampana(TblCampana tblCampana) {
		this.tblCampana = tblCampana;
	}

	public Set<TblCampanaCategoriasPlaza> getTblCategoriasPlazas() {
		return tblCategoriasPlazas;
	}

	public void setTblCategoriasPlazas(Set<TblCampanaCategoriasPlaza> tblCategoriasPlazas) {
		this.tblCategoriasPlazas = tblCategoriasPlazas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public double getIngreso() {
		return ingreso;
	}

	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}

	public int getEsSencillo() {
		return esSencillo;
	}

	public void setEsSencillo(int esSencillo) {
		this.esSencillo = esSencillo;
	}

	public List<String> getPlazaSelect() {
		return plazaSelect;
	}

	public void setPlazaSelect(List<String> plazaSelect) {
		this.plazaSelect = plazaSelect;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}