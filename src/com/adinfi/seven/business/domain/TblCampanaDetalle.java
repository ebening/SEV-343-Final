package com.adinfi.seven.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TblCampanaDetalle implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private TblCampanaDetalleId id;
	private int idCategoria;
	private BigDecimal ingresoCategoria;
	private int idDivision;
	private int idPlaza;
	private int idFormato;
	private int idMercado;
	private int idCampo;
	private int idTienda;
	private short esCompartido;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModificacion;
	private Date fechaModificacion;

	public TblCampanaDetalle() {
	}

	public TblCampanaDetalle(TblCampanaDetalleId id, int idCategoria,
			BigDecimal ingresoCategoria, int idDivision, int idPlaza,
			int idFormato, int idMercado, int idCampo, int idTienda,
			short esCompartido, String usuarioCreacion, Date fechaCreacion) {
		this.id = id;
		this.idCategoria = idCategoria;
		this.ingresoCategoria = ingresoCategoria;
		this.idDivision = idDivision;
		this.idPlaza = idPlaza;
		this.idFormato = idFormato;
		this.idMercado = idMercado;
		this.idCampo = idCampo;
		this.idTienda = idTienda;
		this.esCompartido = esCompartido;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public TblCampanaDetalle(TblCampanaDetalleId id, int idCategoria,
			BigDecimal ingresoCategoria, int idDivision, int idPlaza,
			int idFormato, int idMercado, int idCampo, int idTienda,
			short esCompartido, String usuarioCreacion, Date fechaCreacion,
			String usuarioModificacion, Date fechaModificacion) {
		this.id = id;
		this.idCategoria = idCategoria;
		this.ingresoCategoria = ingresoCategoria;
		this.idDivision = idDivision;
		this.idPlaza = idPlaza;
		this.idFormato = idFormato;
		this.idMercado = idMercado;
		this.idCampo = idCampo;
		this.idTienda = idTienda;
		this.esCompartido = esCompartido;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
	}

	public TblCampanaDetalleId getId() {
		return this.id;
	}

	public void setId(TblCampanaDetalleId id) {
		this.id = id;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public BigDecimal getIngresoCategoria() {
		return this.ingresoCategoria;
	}

	public void setIngresoCategoria(BigDecimal ingresoCategoria) {
		this.ingresoCategoria = ingresoCategoria;
	}

	public int getIdDivision() {
		return this.idDivision;
	}

	public void setIdDivision(int idDivision) {
		this.idDivision = idDivision;
	}

	public int getIdPlaza() {
		return this.idPlaza;
	}

	public void setIdPlaza(int idPlaza) {
		this.idPlaza = idPlaza;
	}

	public int getIdFormato() {
		return this.idFormato;
	}

	public void setIdFormato(int idFormato) {
		this.idFormato = idFormato;
	}

	public int getIdMercado() {
		return this.idMercado;
	}

	public void setIdMercado(int idMercado) {
		this.idMercado = idMercado;
	}

	public int getIdCampo() {
		return this.idCampo;
	}

	public void setIdCampo(int idCampo) {
		this.idCampo = idCampo;
	}

	public int getIdTienda() {
		return this.idTienda;
	}

	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}

	public short getEsCompartido() {
		return this.esCompartido;
	}

	public void setEsCompartido(short esCompartido) {
		this.esCompartido = esCompartido;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

}
