package com.adinfi.seven.persistence.dto;

import com.adinfi.seven.business.domain.TblCampana;

public class CampanaMedioDTO {
	private TblCampana campana;
	private CatMedioDTO medio;
	private CatTipoMedioDTO tipoMedio;
	private Integer folletoId= null;
	private Integer prensaId= null;
	private EtiquetaDTO etiqueta;
	private int idCampanaMedio;
	private String titulo;
	private int idResponsable;

	public CampanaMedioDTO() {
		campana = new TblCampana();
	}

	/**
	 * @return the campana
	 */
	public TblCampana getCampana() {
		return campana;
	}

	/**
	 * @param campana
	 *            the campana to set
	 */
	public void setCampana(TblCampana campana) {
		this.campana = campana;
	}

	/**
	 * @return the medio
	 */
	public CatMedioDTO getMedio() {
		return medio;
	}

	/**
	 * @param medio
	 *            the medio to set
	 */
	public void setMedio(CatMedioDTO medio) {
		this.medio = medio;
	}

	/**
	 * @return the tipoMedio
	 */
	public CatTipoMedioDTO getTipoMedio() {
		return tipoMedio;
	}

	/**
	 * @param tipoMedio
	 *            the tipoMedio to set
	 */
	public void setTipoMedio(CatTipoMedioDTO tipoMedio) {
		this.tipoMedio = tipoMedio;
	}

	public Integer getPrensaId() {
		return prensaId;
	}

	public void setPrensaId(Integer prensaId) {
		this.prensaId = prensaId;
	}

	public Integer getFolletoId() {
		return folletoId;
	}

	public void setFolletoId(Integer folletoId) {
		this.folletoId = folletoId;
	}

	public void setEtiqueta(EtiquetaDTO etiqueta) {
		this.etiqueta= etiqueta;
	}

	public EtiquetaDTO getEtiqueta() {
		return etiqueta;
	}

	public int getIdCampanaMedio() {
		return idCampanaMedio;
	}

	public void setIdCampanaMedio(int idCampanaMedio) {
		this.idCampanaMedio = idCampanaMedio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}
}
