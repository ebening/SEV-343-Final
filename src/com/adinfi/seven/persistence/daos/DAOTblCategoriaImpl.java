package com.adinfi.seven.persistence.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.adinfi.seven.business.domain.TblCategoria;

public class DAOTblCategoriaImpl extends AbstractDaoImpl<TblCategoria> implements DAOTblCategoria {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void deleteByMecanicaId(int mecanicaId) {
		this.getSession().createQuery("delete from TblCategoria a where a.tblMecanica.mecanicaId= "+ mecanicaId).executeUpdate();
	}
	
	@Override
	public void save(List<TblCategoria> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (TblCategoria o : list) {
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
