package com.adinfi.seven.persistence.dto;

public class ParametrosFolletoAndPrensaDTO {
	private CategoriaDTO[] categoria;
	private UsuarioDTO[] responsable;
	private SubCategoriasDTO[] subcategoria;
	private UsuarioDTO[] diseñador;
	private String[] compradorInvitado;

	public ParametrosFolletoAndPrensaDTO(int size) {
		categoria = new CategoriaDTO[size];
		responsable = new UsuarioDTO[size];
		subcategoria = new SubCategoriasDTO[size];
		diseñador = new UsuarioDTO[size];
		compradorInvitado = new String[size];
	}

	/**
	 * @return the categoria
	 */
	public CategoriaDTO[] getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(CategoriaDTO[] categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the responsable
	 */
	public UsuarioDTO[] getResponsable() {
		return responsable;
	}

	/**
	 * @param responsable
	 *            the responsable to set
	 */
	public void setResponsable(UsuarioDTO[] responsable) {
		this.responsable = responsable;
	}

	/**
	 * @return the subcategoria
	 */
	public SubCategoriasDTO[] getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria
	 *            the subcategoria to set
	 */
	public void setSubcategoria(SubCategoriasDTO[] subcategoria) {
		this.subcategoria = subcategoria;
	}

	/**
	 * @return the diseñador
	 */
	public UsuarioDTO[] getDiseñador() {
		return diseñador;
	}

	/**
	 * @param diseñador
	 *            the diseñador to set
	 */
	public void setDiseñador(UsuarioDTO[] diseñador) {
		this.diseñador = diseñador;
	}

	/**
	 * @return the compradorInvitado
	 */
	public String[] getCompradorInvitado() {
		return compradorInvitado;
	}

	/**
	 * @param compradorInvitado
	 *            the compradorInvitado to set
	 */
	public void setCompradorInvitado(String[] compradorInvitado) {
		this.compradorInvitado = compradorInvitado;
	}

}
