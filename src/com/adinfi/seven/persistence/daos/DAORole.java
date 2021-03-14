package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.CatRole;

public interface DAORole extends AbstractDao<CatRole>{

	public List<CatRole> getRoleAll();
	public CatRole getCatRoleById(int id);
	
}
