package com.adinfi.seven.business.domain;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author christian
 */
public class TblCampana implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = -3108106609233756263L;
	private long idCampana;
	private Integer idResponsable;
	private Integer idTipoCampana;
	private CatEtiquetas catEtiquetas;
	private CatFlujoAct catFlujoAct;
	private CatEstatus catEstatus;
	private String nombre;
	private Integer idPeriodo;
	private Integer idTipoEvento;
	private Date fechaInicio;
	private Date fechaFin;
	private int idUsuarioCreacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer idUsuarioModificacion;
	private String comentarios;
	private int totalMec;
	private Set tblCampanaActividadeses = new HashSet(0);
	private Set tblPrensas = new HashSet(0);
	private Set tblCampanaCategoriases = new HashSet(0);
	private Set tblFolletos = new HashSet(0);
	private Set tblSolicitudAutorizacions = new HashSet(0);
	private Set tblCampanaMedios = new HashSet(0);
	private Set<TblCampanaProgramas> tblCampanaProgramas = new HashSet<TblCampanaProgramas>(0);
	private Set<TblMecanica> mecanicas = new HashSet<TblMecanica>(0);
	
	public TblCampana clone() throws CloneNotSupportedException {
        return (TblCampana) super.clone();
	}

	public TblCampana() {
	}
	
	public TblCampana(long idCampana, String nombre) {
		this.idCampana = idCampana;
		this.nombre = nombre;
	}
	
	public TblCampana(long idCampana, Integer idResponsable,
			Integer idTipoCampana, CatEtiquetas catEtiquetas, String nombre,
			Integer idTipoEvento, Date fechaInicio, Date fechaFin,
			int idUsuarioCreacion, Date fechaCreacion) {
		this.idCampana = idCampana;
		this.idResponsable = idResponsable;
		this.idTipoCampana = idTipoCampana;
		this.catEtiquetas = catEtiquetas;
		this.nombre = nombre;
		this.idTipoEvento = idTipoEvento;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idUsuarioCreacion = idUsuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public TblCampana(long idCampana, Integer idResponsable,
			Integer idTipoCampana, CatEtiquetas catEtiquetas, String nombre,
			Integer idPeriodo, Integer idTipoEvento, Date fechaInicio,
			Date fechaFin, int idUsuarioCreacion, Date fechaCreacion,
			Date fechaModificacion, Integer idUsuarioModificacion,
			String comentarios, Set tblCampanaActividadeses, Set tblPrensas,
			Set tblCampanaCategoriases, Set tblFolletos,
			Set tblSolicitudAutorizacions, Set tblCampanaMedios) {
		this.idCampana = idCampana;
		this.idResponsable = idResponsable;
		this.idTipoCampana = idTipoCampana;
		this.catEtiquetas = catEtiquetas;
		this.nombre = nombre;
		this.idPeriodo = idPeriodo;
		this.idTipoEvento = idTipoEvento;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idUsuarioCreacion = idUsuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.idUsuarioModificacion = idUsuarioModificacion;
		this.comentarios = comentarios;
		this.tblCampanaActividadeses = tblCampanaActividadeses;
		this.tblPrensas = tblPrensas;
		this.tblCampanaCategoriases = tblCampanaCategoriases;
		this.tblFolletos = tblFolletos;
		this.tblSolicitudAutorizacions = tblSolicitudAutorizacions;
		this.tblCampanaMedios = tblCampanaMedios;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		TblCampana campana = (TblCampana) object;
		return idCampana == campana.idCampana;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCampana);
	}

	public long getIdCampana() {
		return this.idCampana;
	}

	public void setIdCampana(long idCampana) {
		this.idCampana = idCampana;
	}

	public Integer getIdResponsable() {
		return this.idResponsable;
	}

	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}

	public int getIdTipoCampana() {
		return this.idTipoCampana;
	}

	public void setIdTipoCampana(Integer idTipoCampana) {
		this.idTipoCampana = idTipoCampana;
	}

	public CatEtiquetas getCatEtiquetas() {
		return catEtiquetas;
	}

	public void setCatEtiquetas(CatEtiquetas catEtiquetas) {
		this.catEtiquetas = catEtiquetas;
	}

	public CatFlujoAct getCatFlujoAct() {
		return catFlujoAct;
	}

	public void setCatFlujoAct(CatFlujoAct catFlujoAct) {
		this.catFlujoAct = catFlujoAct;
	}

	public CatEstatus getCatEstatus() {
		return catEstatus;
	}

	public void setCatEstatus(CatEstatus catEstatus) {
		this.catEstatus = catEstatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdPeriodo() {
		return this.idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public Integer getIdTipoEvento() {
		return this.idTipoEvento;
	}

	public void setIdTipoEvento(Integer idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getIdUsuarioCreacion() {
		return this.idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(int idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getIdUsuarioModificacion() {
		return this.idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Set getTblCampanaActividadeses() {
		return this.tblCampanaActividadeses;
	}

	public void setTblCampanaActividadeses(Set tblCampanaActividadeses) {
		this.tblCampanaActividadeses = tblCampanaActividadeses;
	}

	public Set getTblPrensas() {
		return this.tblPrensas;
	}

	public void setTblPrensas(Set tblPrensas) {
		this.tblPrensas = tblPrensas;
	}

	public Set getTblCampanaCategoriases() {
		return this.tblCampanaCategoriases;
	}

	public void setTblCampanaCategoriases(Set tblCampanaCategoriases) {
		this.tblCampanaCategoriases = tblCampanaCategoriases;
	}

	public Set getTblFolletos() {
		return this.tblFolletos;
	}

	public void setTblFolletos(Set tblFolletos) {
		this.tblFolletos = tblFolletos;
	}

	public Set getTblSolicitudAutorizacions() {
		return this.tblSolicitudAutorizacions;
	}

	public void setTblSolicitudAutorizacions(Set tblSolicitudAutorizacions) {
		this.tblSolicitudAutorizacions = tblSolicitudAutorizacions;
	}

	public Set getTblCampanaMedios() {
		return this.tblCampanaMedios;
	}

	public void setTblCampanaMedios(Set tblCampanaMedios) {
		this.tblCampanaMedios = tblCampanaMedios;
	}

	@Override
	public String toString() {

		return nombre;
	}

	public Set<TblCampanaProgramas> getTblCampanaProgramas() {
		return tblCampanaProgramas;
	}

	public void setTblCampanaProgramas(Set<TblCampanaProgramas> tblCampanaProgramas) {
		this.tblCampanaProgramas = tblCampanaProgramas;
	}

	public Set<TblMecanica> getMecanicas() {
		return mecanicas;
	}

	public void setMecanicas(Set<TblMecanica> mecanicas) {
		this.mecanicas = mecanicas;
	}

	public int getTotalMec() {
		return totalMec;
	}

	public void setTotalMec(int totalMec) {
		this.totalMec = totalMec;
	}
}