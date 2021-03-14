package com.adinfi.seven.business.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TblCampanaProgramas implements java.io.Serializable, Cloneable {
	private static final long serialVersionUID = 279215768188723905L;
	private TblCampanaProgramasId id;
	private TblCampana tblCampana;
    private CatPrograma programa;
	private Set<TblCampanaProgramasPlazas> tblCampanaProgramasPlazas;
	private Set<TblCampanaProgramasCategorias> tblCampanaProgramasCategorias; 
	private double ingreso;
	private int esSencillo;
	private List<String> plazaSelect;
	private String descripcion;
	private String etapa;
	private boolean open;
	private Set<RelGrupoZonaCampana> grupoZonas = new HashSet<RelGrupoZonaCampana>(0);
	private Set<RelZonaCampana> zonas = new HashSet<RelZonaCampana>(0);
	private Set<RelStoreCampana> tiendas = new HashSet<RelStoreCampana>(0);
	
	public TblCampanaProgramas clone() throws CloneNotSupportedException {
		return (TblCampanaProgramas) super.clone();
	}
	public TblCampanaProgramas(){
		
	}

	@Override
	public String toString() {
		return programa.getNombre();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		TblCampanaProgramas that = (TblCampanaProgramas) object;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public TblCampanaProgramasId getId() {
		return id;
	}
	public void setId(TblCampanaProgramasId id) {
		this.id = id;
	}
	public TblCampana getTblCampana() {
		return tblCampana;
	}
	public void setTblCampana(TblCampana tblCampana) {
		this.tblCampana = tblCampana;
	}

    public CatPrograma getPrograma() {
        return programa;
    }

    public void setPrograma(CatPrograma programa) {
        this.programa = programa;
    }

    public Set<TblCampanaProgramasPlazas> getTblCampanaProgramasPlazas() {
		return tblCampanaProgramasPlazas;
	}
	public void setTblCampanaProgramasPlazas(
			Set<TblCampanaProgramasPlazas> tblCampanaProgramasPlazas) {
		this.tblCampanaProgramasPlazas = tblCampanaProgramasPlazas;
	}
	public Set<TblCampanaProgramasCategorias> getTblCampanaProgramasCategorias() {
		return tblCampanaProgramasCategorias;
	}
	public void setTblCampanaProgramasCategorias(
			Set<TblCampanaProgramasCategorias> tblCampanaProgramasCategorias) {
		this.tblCampanaProgramasCategorias = tblCampanaProgramasCategorias;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public Set<RelGrupoZonaCampana> getGrupoZonas() {
		return grupoZonas;
	}
	public void setGrupoZonas(Set<RelGrupoZonaCampana> grupoZonas) {
		this.grupoZonas = grupoZonas;
	}
	public Set<RelZonaCampana> getZonas() {
		return zonas;
	}
	public void setZonas(Set<RelZonaCampana> zonas) {
		this.zonas = zonas;
	}
	public Set<RelStoreCampana> getTiendas() {
		return tiendas;
	}
	public void setTiendas(Set<RelStoreCampana> tiendas) {
		this.tiendas = tiendas;
	}
}
