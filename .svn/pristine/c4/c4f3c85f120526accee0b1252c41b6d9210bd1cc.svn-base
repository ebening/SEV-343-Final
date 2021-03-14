package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.CatUsuarios;

public interface DAOUsuarios extends AbstractDao<CatUsuarios>{

	public List<CatUsuarios> getUsuariosAll();
	
	public CatUsuarios getUsuarioById(int id);
	
	public CatUsuarios getUsuarioForLogin(String login, String password);
	
	public CatUsuarios getUsuarioByLogin(String login);

	public List<CatUsuarios> getUsuarioByRoleAndCategory(int idRole, int idCategory);
}
