package com.adinfi.seven.business.services;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.adinfi.seven.business.domain.RelUsuariosCategorias;
import com.adinfi.seven.business.domain.TblTemplateUser;
import com.adinfi.seven.persistence.daos.DAORelUsuariosCategorias;
import com.adinfi.seven.presentation.views.MBRelUsuariosCategorias;

import org.hibernate.SessionFactory;

public class ServiceRelUsuariosCategoriasImpl implements ServiceRelUsuariosCategorias {
	
	DAORelUsuariosCategorias daoRelUsuariosCategorias;
	private Logger LOG = Logger.getLogger(MBRelUsuariosCategorias.class);
	
	@Override
	public List<RelUsuariosCategorias> listaRelUsuariosCategorias()
			throws Exception {
		return  daoRelUsuariosCategorias.getAll();
	}
	
	@Override
	public boolean borrarRelUsuarioCategoria(Integer idUsuario) {
		return daoRelUsuariosCategorias.borrarRelUsuarioCategoria(idUsuario);
	}
	
	
	@Override
	public boolean crearRelUsuarioCategoria(
			List<RelUsuariosCategorias> usuariosCategorias) {
		
		if(usuariosCategorias.isEmpty() == false){
			for(RelUsuariosCategorias rel : usuariosCategorias){
				try {
					daoRelUsuariosCategorias.saveOrUpdate(rel);
				} catch (Exception e) {
					LOG.error(e);
				}
			}
		}
		
		return false;
	}
	
	@Override
	public List<RelUsuariosCategorias> listarRelUsuariosCategoriasByUsuario(Integer id)
			throws Exception {
		return daoRelUsuariosCategorias.getRelUsuariosCategoriasByIdUsuario(id);
	
	}
	
	

	public DAORelUsuariosCategorias getDaoRelUsuariosCategorias() {
		return daoRelUsuariosCategorias;
	}

	public void setDaoRelUsuariosCategorias(
			DAORelUsuariosCategorias daoRelUsuariosCategorias) {
		this.daoRelUsuariosCategorias = daoRelUsuariosCategorias;
	}


	public Logger getLOG() {
		return LOG;
	}


	public void setLOG(Logger lOG) {
		LOG = lOG;
	}

	


	
	

	
	

}
