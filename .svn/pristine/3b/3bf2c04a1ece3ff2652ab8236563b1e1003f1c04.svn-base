package com.adinfi.seven.business.services;

import java.io.Serializable;
import java.util.List;

import com.adinfi.seven.business.domain.CatRole;
import com.adinfi.seven.persistence.daos.DAORole;

public class ServiceCatRoleImpl implements ServiceCatRole, Serializable{
	
	private DAORole daoRole;

	@Override
	public List<CatRole> getRoleAll() throws Exception {
		return getDaoRole().getAll();
	}

	@Override
	public CatRole getRoleById(int id) throws Exception {
		return getDaoRole().getById(id);
	}

	@Override
	public boolean saveRole(CatRole role) {
		try {
			getDaoRole().save(role);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateRole(CatRole role) {
		try {
			daoRole.update(role);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean deleteRole(CatRole role) {
		try {
			daoRole.delete(role);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public DAORole getDaoRole() {
		return daoRole;
	}

	public void setDaoRole(DAORole daoRole) {
		this.daoRole = daoRole;
	}

	

	
}
