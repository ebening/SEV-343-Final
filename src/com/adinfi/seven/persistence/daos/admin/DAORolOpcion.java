package com.adinfi.seven.persistence.daos.admin;

import java.util.List;

import com.adinfi.seven.business.domain.RolOpcion;
import com.adinfi.seven.persistence.daos.AbstractDao;

public interface DAORolOpcion extends AbstractDao<RolOpcion> {
	List<RolOpcion> getOpcionesPorRol(String role) throws Exception;
	List<Object[]> getMenuByUsuario(int idUsuario);
}
