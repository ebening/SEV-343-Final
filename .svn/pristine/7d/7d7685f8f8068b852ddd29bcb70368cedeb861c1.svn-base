package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.RelUsuariosCategorias;

public class DAORelUsuariosCategoriasImpl extends AbstractDaoImpl<RelUsuariosCategorias> implements DAORelUsuariosCategorias {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<RelUsuariosCategorias> getRelUsuariosCategoriasList()
			throws GeneralException {
		return null;
	}

	@Override
	public boolean borrarRelUsuarioCategoria(Integer idUsuario) {
		Query query = this.getSession().createQuery(
						"delete from RelUsuariosCategorias relUsuariosCat where relUsuariosCat.catUsuarios.idusuarios =  :idUsuario");
		query.setParameter("idUsuario", idUsuario);
		int deleted = query.executeUpdate();
		return true;
	}

	@Override
	public List<RelUsuariosCategorias> getRelUsuariosCategoriasByIdUsuario(
			Integer id) throws Exception {
		@SuppressWarnings("unchecked")
		Iterator<RelUsuariosCategorias> usrsCatgs = getHibernateTemplate().iterate("from RelUsuariosCategorias obj where obj.idUsuario = ?", new Object[] {id});
		return toInitializedList(usrsCatgs);
	}
	
	
	

}
