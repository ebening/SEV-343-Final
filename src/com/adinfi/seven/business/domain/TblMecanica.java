package com.adinfi.seven.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TblMecanica implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int mecanicaId;
	private TblCampana campana;
	private BigDecimal ingresoPopReal;
	private int promoId;
	private String nombrePromo;
	private Integer tipoPromocionId;
	private Integer esCompartido;
	private String acuerdo1;
	private String acuerdo2;
	private String acuerdo3;
	private String comentarios;
	private Set<TblSenalamientos> tblSenalamientoses = new HashSet<TblSenalamientos>(0);
	private Set<TblCategoria> tblCategorias = new HashSet<TblCategoria>(0);
	private Set<RelGrupoZona> relGrupoZonas = new HashSet<RelGrupoZona>(0);
	private Set<TblComponente> tblComponentes = new HashSet<TblComponente>(0);
	private Set<RelZona> relZonas = new HashSet<RelZona>(0);
	private Set<TblPrograma> tblProgramas = new HashSet<TblPrograma>(0);
	private Set<RelStore> relStores = new HashSet<RelStore>(0);
	private Set<TblConfMecanica> confMecanicaLst = new HashSet<TblConfMecanica>(0);
	private Set<TblPreciosPromocion> preciosLst = new HashSet<TblPreciosPromocion>(0);
	private Set<TblDisenos> disenoLst= new HashSet<TblDisenos>(0);
	private int programaId;
	private Date horaIni;
	private Date horaFin;
	private int lunes = 1;
	private int martes = 1;
	private int miercoles = 1;
	private int jueves = 1;
	private int viernes = 1;
	private int sabado = 1;
	private int domingo = 1;
	private String nombreMecanica;

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || !(object instanceof TblMecanica)){
			return false;
		}
		TblMecanica mecanica = (TblMecanica) object;
		return mecanicaId == mecanica.mecanicaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mecanicaId);
	}

	@Override
	public String toString() {
		return nombreMecanica;
	}

	public int getMecanicaId() {
		return this.mecanicaId;
	}

	public void setMecanicaId(int mecanicaId) {
		this.mecanicaId = mecanicaId;
	}

	public BigDecimal getIngresoPopReal() {
		return this.ingresoPopReal;
	}

	public void setIngresoPopReal(BigDecimal ingresoPopReal) {
		this.ingresoPopReal = ingresoPopReal;
	}

	public int getPromoId() {
		return this.promoId;
	}

	public void setPromoId(int promoId) {
		this.promoId = promoId;
	}

	public String getNombrePromo() {
		return this.nombrePromo;
	}

	public void setNombrePromo(String nombrePromo) {
		this.nombrePromo = nombrePromo;
	}

	public Integer getTipoPromocionId() {
		return this.tipoPromocionId;
	}

	public void setTipoPromocionId(Integer tipoPromocionId) {
		this.tipoPromocionId = tipoPromocionId;
	}

	public Integer getEsCompartido() {
		return this.esCompartido;
	}

	public void setEsCompartido(Integer esCompartido) {
		this.esCompartido = esCompartido;
	}

	public String getAcuerdo1() {
		return this.acuerdo1;
	}

	public void setAcuerdo1(String acuerdo1) {
		this.acuerdo1 = acuerdo1;
	}

	public String getAcuerdo2() {
		return this.acuerdo2;
	}

	public void setAcuerdo2(String acuerdo2) {
		this.acuerdo2 = acuerdo2;
	}

	public String getAcuerdo3() {
		return this.acuerdo3;
	}

	public void setAcuerdo3(String acuerdo3) {
		this.acuerdo3 = acuerdo3;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Set<TblSenalamientos> getTblSenalamientoses() {
		return this.tblSenalamientoses;
	}

	public void setTblSenalamientoses(Set<TblSenalamientos> tblSenalamientoses) {
		this.tblSenalamientoses = tblSenalamientoses;
	}

	public Set<TblCategoria> getTblCategorias() {
		return this.tblCategorias;
	}

	public void setTblCategorias(Set<TblCategoria> tblCategorias) {
		this.tblCategorias = tblCategorias;
	}

	public Set<RelGrupoZona> getRelGrupoZonas() {
		return this.relGrupoZonas;
	}

	public void setRelGrupoZonas(Set<RelGrupoZona> relGrupoZonas) {
		this.relGrupoZonas = relGrupoZonas;
	}

	public Set<TblComponente> getTblComponentes() {
		return this.tblComponentes;
	}

	public void setTblComponentes(Set<TblComponente> tblComponentes) {
		this.tblComponentes = tblComponentes;
	}

	public Set<RelZona> getRelZonas() {
		return this.relZonas;
	}

	public void setRelZonas(Set<RelZona> relZonas) {
		this.relZonas = relZonas;
	}

	public Set<TblPrograma> getTblProgramas() {
		return this.tblProgramas;
	}

	public void setTblProgramas(Set<TblPrograma> tblProgramas) {
		this.tblProgramas = tblProgramas;
	}

	public Set<RelStore> getRelStores() {
		return this.relStores;
	}

	public void setRelStores(Set<RelStore> relStores) {
		this.relStores = relStores;
	}

	public TblCampana getCampana() {
		return campana;
	}

	public void setCampana(TblCampana campana) {
		this.campana = campana;
	}

	public Date getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(Date horaIni) {
		this.horaIni = horaIni;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public int getLunes() {
		return lunes;
	}

	public void setLunes(int lunes) {
		this.lunes = lunes;
	}

	public int getMartes() {
		return martes;
	}

	public void setMartes(int martes) {
		this.martes = martes;
	}

	public int getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(int miercoles) {
		this.miercoles = miercoles;
	}

	public int getJueves() {
		return jueves;
	}

	public void setJueves(int jueves) {
		this.jueves = jueves;
	}

	public int getViernes() {
		return viernes;
	}

	public void setViernes(int viernes) {
		this.viernes = viernes;
	}

	public int getSabado() {
		return sabado;
	}

	public void setSabado(int sabado) {
		this.sabado = sabado;
	}

	public int getDomingo() {
		return domingo;
	}

	public void setDomingo(int domingo) {
		this.domingo = domingo;
	}

	public int getProgramaId() {
		return programaId;
	}

	public void setProgramaId(int programaId) {
		this.programaId = programaId;
	}

	public String getNombreMecanica() {
		return nombreMecanica;
	}

	public void setNombreMecanica(String nombreMecanica) {
		this.nombreMecanica = nombreMecanica;
	}

	public Set<TblConfMecanica> getConfMecanicaLst() {
		return confMecanicaLst;
	}

	public void setConfMecanicaLst(Set<TblConfMecanica> confMecanicaLst) {
		this.confMecanicaLst = confMecanicaLst;
	}

	public Set<TblPreciosPromocion> getPreciosLst() {
		return preciosLst;
	}

	public void setPreciosLst(Set<TblPreciosPromocion> preciosLst) {
		this.preciosLst = preciosLst;
	}

	public Set<TblDisenos> getDisenoLst() {
		return disenoLst;
	}

	public void setDisenoLst(Set<TblDisenos> disenoLst) {
		this.disenoLst = disenoLst;
	}

}
