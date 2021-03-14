package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.util.List;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String login;
	private String password;
	private Character changePassword;
	private String name="";
	private String email;
	private String plastName="";
	private String mlastName="";
	private String role;
	private Character isNivel1;
	private Character isActivo;
	private String code;
	private List<CategoriaDTO> categorias;
	

	public String getMlastName() {
		return mlastName;
	}

	public void setMlastName(String mlastName) {
		this.mlastName = mlastName;
	}

	@Override
	public String toString() {
		return name +" "+ plastName+" "+mlastName ;
	}
	
	public String getFullName(){
		return name +" "+ plastName+" "+mlastName ;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pLastName
	 */
	public String getPlastName() {
		return plastName;
	}

	/**
	 * @param pLastName
	 *            the pLastName to set
	 */
	public void setPlastName(String pLastName) {
		this.plastName = pLastName;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the changePassword
	 */
	public Character getChangePassword() {
		return changePassword;
	}

	/**
	 * @param changePassword
	 *            the changePassword to set
	 */
	public void setChangePassword(Character changePassword) {
		this.changePassword = changePassword;
	}

	/**
	 * @return the isActivo
	 */
	public Character getIsActivo() {
		return isActivo;
	}

	/**
	 * @param isActivo
	 *            the isActivo to set
	 */
	public void setIsActivo(Character isActivo) {
		this.isActivo = isActivo;
	}

	/**
	 * @return the isNivel1
	 */
	public Character getIsNivel1() {
		return isNivel1;
	}

	/**
	 * @param isNivel1
	 *            the isNivel1 to set
	 */
	public void setIsNivel1(Character isNivel1) {
		this.isNivel1 = isNivel1;
	}

	/**
	 * @return the isNivel1
	 */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userId != null ? userId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof UsuarioDTO)) {
			return false;
		}
		UsuarioDTO other = (UsuarioDTO) object;
		if ((this.userId == null && other.userId != null)
				|| (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the categorias
	 */
	public List<CategoriaDTO> getCategorias() {
		return categorias;
	}

	/**
	 * @param categorias the categorias to set
	 */
	public void setCategorias(List<CategoriaDTO> categorias) {
		this.categorias = categorias;
	}

}