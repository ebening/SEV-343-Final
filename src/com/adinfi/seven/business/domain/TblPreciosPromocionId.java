package com.adinfi.seven.business.domain;

import java.util.Objects;

public class TblPreciosPromocionId implements java.io.Serializable {
	private static final long serialVersionUID = 2356770379804692715L;
	private int mecanicaId;
	private int componenteId;
	private int zonaId;

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		TblPreciosPromocionId that = (TblPreciosPromocionId) object;
		return mecanicaId == that.mecanicaId &&
				componenteId == that.componenteId &&
				zonaId == that.zonaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mecanicaId, componenteId, zonaId);
	}

	public int getMecanicaId() {
		return mecanicaId;
	}

	public void setMecanicaId(int mecanicaId) {
		this.mecanicaId = mecanicaId;
	}

	public int getComponenteId() {
		return componenteId;
	}

	public void setComponenteId(int componenteId) {
		this.componenteId = componenteId;
	}

	public int getZonaId() {
		return zonaId;
	}

	public void setZonaId(int zonaId) {
		this.zonaId = zonaId;
	}
}