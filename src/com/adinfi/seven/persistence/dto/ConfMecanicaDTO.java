package com.adinfi.seven.persistence.dto;

public class ConfMecanicaDTO {
	private int categoriaId;
	private Double ahorro;
	private Double ahorroPorcentaje;
	private int pkMec;
	public int getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	public Double getAhorro() {
		return ahorro;
	}
	public void setAhorro(Double ahorro) {
		this.ahorro = ahorro;
	}
	public Double getAhorroPorcentaje() {
		return ahorroPorcentaje;
	}
	public void setAhorroPorcentaje(Double ahorroPorcentaje) {
		this.ahorroPorcentaje = ahorroPorcentaje;
	}
	public int getPkMec() {
		return pkMec;
	}
	public void setPkMec(int pkMec) {
		this.pkMec = pkMec;
	}
}