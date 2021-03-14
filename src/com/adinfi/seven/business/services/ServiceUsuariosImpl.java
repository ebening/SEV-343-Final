package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.UserConnection;
import com.adinfi.seven.persistence.daos.DAOUserConnection;
import com.adinfi.seven.persistence.daos.DAOUsuarios;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceUsuariosImpl implements ServiceUsuarios{

	private DAOUsuarios daoUsuarios;
    private DAOUserConnection daoUserConnection;

	@Override
	public List<CatUsuarios> getUsuariosAll() {
		return getDaoUsuarios().getUsuariosAll();
	}

	@Override
	public CatUsuarios getUsuarioById(int id) {
		return getDaoUsuarios().getUsuarioById(id);
	}

	@Override
	public CatUsuarios getUsuarioForLogin(String login, String password) {
		return getDaoUsuarios().getUsuarioForLogin(login, password);
	}

	@Override
	public CatUsuarios getUsuarioByRoleAndCategory(int idRole, int idCategory) {
		List<CatUsuarios> list = daoUsuarios.getUsuarioByRoleAndCategory(idRole, idCategory);
		return list.size() > 0 ? list.get(0) : null ;
	}

	@Override
	public List<CatUsuarios> getUsuariosByRoleAndCategory(int idRole, int idCategory) {
		return daoUsuarios.getUsuarioByRoleAndCategory(idRole, idCategory);
	}

	@Override
	public boolean saveUsuario(CatUsuarios catUsuario) {
		try{
			getDaoUsuarios().save(catUsuario);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateUsuario(CatUsuarios catUsuario) {
		try{
			getDaoUsuarios().update(catUsuario);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean deleteUsuario(CatUsuarios catUsuarios) {
		try{
			getDaoUsuarios().delete(catUsuarios);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	@Override
	public CatUsuarios getUsuarioByLogin(String login) {
		return getDaoUsuarios().getUsuarioByLogin(login);
	}
	
	public DAOUsuarios getDaoUsuarios() {
		return daoUsuarios;
	}

	public void setDaoUsuarios(DAOUsuarios daoUsuarios) {
		this.daoUsuarios = daoUsuarios;
	}

    @Override
    public UserConnection getUserConnectionByUserId(int userId) {
        return getDaoUserConnection().findUserConnectionByUserId(userId);
    }

    @Override
    public int updateUserConnection(UserConnection uc) {
        try {
            getDaoUserConnection().update(uc);
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(ServiceUsuariosImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int insertUserConnection(UserConnection uc) {
        int insertUser = getDaoUserConnection().insertUser(uc);
        return insertUser;
    }

    /**
     * @return the daoUserConnection
     */
    public DAOUserConnection getDaoUserConnection() {
        return daoUserConnection;
    }

    /**
     * @param daoUserConnection the daoUserConnection to set
     */
    public void setDaoUserConnection(DAOUserConnection daoUserConnection) {
        this.daoUserConnection = daoUserConnection;
    }

	
}
