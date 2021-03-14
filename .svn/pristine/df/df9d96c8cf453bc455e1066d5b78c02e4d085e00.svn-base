package com.adinfi.seven.business.services.admin;

import java.util.List;

import com.adinfi.seven.business.domain.RolOpcion;
import com.adinfi.seven.persistence.daos.admin.DAORolOpcion;

public class ServiceMenuAndRolesImpl implements ServiceMenuAndRoles {
	DAORolOpcion daoRolOpcion = null;

	public DAORolOpcion getDaoRolOpcion() {
		return daoRolOpcion;
	}

	public void setDaoRolOpcion(DAORolOpcion daoRolOpcion) {
		this.daoRolOpcion = daoRolOpcion;
	}

	public List<RolOpcion> getOpcionesPorRol(String role) throws Exception {
		return this.daoRolOpcion.getOpcionesPorRol(role);

	}
	
	public List<Object[]> getMenuByUsuario(int idUsuario) {
    	return daoRolOpcion.getMenuByUsuario(idUsuario);
    }
}
