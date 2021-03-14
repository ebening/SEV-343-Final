package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adinfi.seven.business.domain.TblCampana;

public class ActivityDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private TblCampana tblCampana;
	private Integer perfil;
	private String perfilName;
	private Integer usuarioByUsuarioCreacion;
	private Integer usuarioByUsuarioModificacion;
	private UsuarioDTO usuarioByIdUsuarioResp;
	private String actividad;
	private String nombreActividad;
	private String actividadDescripcion;
	private int esFlujo;
	private String estatus;
	private Date fechaCierre;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private int porcentajeCompletado;
	private Date fechaInicioReal;
	private Date fechaFinReal;
	private String duracion;
	private String semaforo;
	private String codigoColor;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the tblCampana
	 */
	public TblCampana getTblCampana() {
		return tblCampana;
	}

	/**
	 * @param tblCampana
	 *            the tblCampana to set
	 */
	public void setTblCampana(TblCampana tblCampana) {
		this.tblCampana = tblCampana;
	}

	/**
	 * @return the perfil
	 */
	public Integer getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil
	 *            the perfil to set
	 */
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	/**
	 * @return the usuarioByUsuarioCreacion
	 */
	public Integer getUsuarioByUsuarioCreacion() {
		return usuarioByUsuarioCreacion;
	}

	/**
	 * @param usuarioByUsuarioCreacion
	 *            the usuarioByUsuarioCreacion to set
	 */
	public void setUsuarioByUsuarioCreacion(Integer usuarioByUsuarioCreacion) {
		this.usuarioByUsuarioCreacion = usuarioByUsuarioCreacion;
	}

	/**
	 * @return the usuarioByUsuarioModificacion
	 */
	public Integer getUsuarioByUsuarioModificacion() {
		return usuarioByUsuarioModificacion;
	}

	/**
	 * @param usuarioByUsuarioModificacion
	 *            the usuarioByUsuarioModificacion to set
	 */
	public void setUsuarioByUsuarioModificacion(
			Integer usuarioByUsuarioModificacion) {
		this.usuarioByUsuarioModificacion = usuarioByUsuarioModificacion;
	}

	/**
	 * @return the usuarioByIdUsuarioResp
	 */
	public UsuarioDTO getUsuarioByIdUsuarioResp() {
		return usuarioByIdUsuarioResp;
	}

	/**
	 * @param usuarioByIdUsuarioResp
	 *            the usuarioByIdUsuarioResp to set
	 */
	public void setUsuarioByIdUsuarioResp(UsuarioDTO usuarioByIdUsuarioResp) {
		this.usuarioByIdUsuarioResp = usuarioByIdUsuarioResp;
	}

	/**
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * @param actividad
	 *            the actividad to set
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * @return the nombreActividad
	 */
	public String getNombreActividad() {
		return nombreActividad;
	}

	/**
	 * @param nombreActividad
	 *            the nombreActividad to set
	 */
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	/**
	 * @return the actividadDescripcion
	 */
	public String getActividadDescripcion() {
		return actividadDescripcion;
	}

	/**
	 * @param actividadDescripcion
	 *            the actividadDescripcion to set
	 */
	public void setActividadDescripcion(String actividadDescripcion) {
		this.actividadDescripcion = actividadDescripcion;
	}

	/**
	 * @return the esFlujo
	 */
	public int getEsFlujo() {
		return esFlujo;
	}

	/**
	 * @param esFlujo
	 *            the esFlujo to set
	 */
	public void setEsFlujo(int esFlujo) {
		this.esFlujo = esFlujo;
	}

	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus
	 *            the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the fechaCierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * @param fechaCierre
	 *            the fechaCierre to set
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public String getFechaInicioStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fechaInicio);
	}

	public String getFechaFinStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fechaFin);
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion
	 *            the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the porcentajeCompletado
	 */
	public int getPorcentajeCompletado() {
		return porcentajeCompletado;
	}

	/**
	 * @param porcentajeCompletado
	 *            the porcentajeCompletado to set
	 */
	public void setPorcentajeCompletado(int porcentajeCompletado) {
		this.porcentajeCompletado = porcentajeCompletado;
	}

	/**
	 * @return the fechaInicioReal
	 */
	public Date getFechaInicioReal() {
		return fechaInicioReal;
	}

	/**
	 * @param fechaInicioReal
	 *            the fechaInicioReal to set
	 */
	public void setFechaInicioReal(Date fechaInicioReal) {
		this.fechaInicioReal = fechaInicioReal;
	}

	/**
	 * @return the fechaFinReal
	 */
	public Date getFechaFinReal() {
		return fechaFinReal;
	}

	/**
	 * @param fechaFinReal
	 *            the fechaFinReal to set
	 */
	public void setFechaFinReal(Date fechaFinReal) {
		this.fechaFinReal = fechaFinReal;
	}

	/**
	 * @return the duracion
	 */
	public String getDuracion() {
		return duracion;
	}

	/**
	 * @param duracion
	 *            the duracion to set
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	/**
	 * @return the perfilName
	 */
	public String getPerfilName() {
		return perfilName;
	}

	/**
	 * @param perfilName
	 *            the perfilName to set
	 */
	public void setPerfilName(String perfilName) {
		this.perfilName = perfilName;
	}

	/**
	 * @return the semaforo
	 */
	public String getSemaforo() {
		return semaforo;
	}

	/**
	 * @param semaforo
	 *            the semaforo to set
	 */
	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}

	public String getCodigoColor() {
		return codigoColor;
	}

	public void setCodigoColor(String codigoColor) {
		this.codigoColor = codigoColor;
	}
}