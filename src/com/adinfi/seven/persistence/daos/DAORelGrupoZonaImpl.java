package com.adinfi.seven.persistence.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.adinfi.seven.business.domain.RelGrupoZona;

public class DAORelGrupoZonaImpl extends AbstractDaoImpl<RelGrupoZona> implements DAORelGrupoZona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void deleteByMecanicaId(int mecanicaId) {
		this.getSession().createQuery("delete from RelGrupoZona a where a.tblMecanica.mecanicaId= "+ mecanicaId);
	}
	
	@Override
	public void save(List<RelGrupoZona> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (RelGrupoZona o : list) {
			session.save(o);
			i++;
		    if ( i % 50 == 0 ) {
		    	this.getSession().flush();
		    	this.getSession().clear();
		    }
		}
		tx.commit();
		session.close();
	}
}
