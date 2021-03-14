package com.adinfi.seven.persistence.dto;

import java.io.Serializable;

import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblPrensa;

public class PreciosDTO implements Serializable {

	private static final long serialVersionUID = 7717724723629951737L;
	private String precioId;
	private int medioId;//Impreso
	private int tipoMedioId;//Folleto, Prensa
	private CampanaDTO campana;//Regreso a Clases, Navidad
	private TblFolleto folleto;
	private TblPrensa prensa;
	
	public String getPrecioId() {
		return precioId;
	}
	public void setPrecioId(String precioId) {
		this.precioId = precioId;
	}
	public int getMedioId() {
		return medioId;
	}
	public void setMedioId(int medioId) {
		this.medioId = medioId;
	}
	public int getTipoMedioId() {
		return tipoMedioId;
	}
	public void setTipoMedioId(int tipoMedioId) {
		this.tipoMedioId = tipoMedioId;
	}
	public CampanaDTO getCampana() {
		return campana;
	}
	public void setCampana(CampanaDTO campana) {
		this.campana = campana;
	}
	public TblFolleto getFolleto() {
		return folleto;
	}
	public void setFolleto(TblFolleto folleto) {
		this.folleto = folleto;
	}
	public TblPrensa getPrensa() {
		return prensa;
	}
	public void setPrensa(TblPrensa prensa) {
		this.prensa = prensa;
	}
	
}
