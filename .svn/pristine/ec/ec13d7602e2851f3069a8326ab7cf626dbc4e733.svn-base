package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.UserConnection;

public interface ServiceUsuarios {

	public List<CatUsuarios> getUsuariosAll();
	
	public CatUsuarios getUsuarioById(int id);
	
	public CatUsuarios getUsuarioForLogin(String login, String password);
	
	public CatUsuarios getUsuarioByLogin(String login);

	public CatUsuarios getUsuarioByRoleAndCategory(int idRole, int idCategory);

	public List<CatUsuarios> getUsuariosByRoleAndCategory(int idRole, int idCategory);
	
	public boolean saveUsuario(CatUsuarios catUsuario);
	
	public boolean updateUsuario(CatUsuarios catUsuario);
	
	public boolean deleteUsuario(CatUsuarios catUsuarios);
    
    /*metodos para la gestion de sesiones*/
    UserConnection getUserConnectionByUserId(int userId);
    
    int updateUserConnection(UserConnection uc);
    
    int insertUserConnection(UserConnection uc);
    
}
