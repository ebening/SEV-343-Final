package com.adinfi.seven.persistence.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.adinfi.seven.business.domain.RelStore;

public class DAORelStoreImpl extends AbstractDaoImpl<RelStore> implements DAORelStore {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void deleteByMecanicaId(int mecanicaId) {
		this.getSession().createQuery("delete from RelStore a where a.tblMecanica.mecanicaId= "+ mecanicaId).executeUpdate();
	}
	
	@Override
	public void save(List<RelStore> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (RelStore o : list) {
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
