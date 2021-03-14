package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.adinfi.seven.business.domain.TblConfMecanica;

public class DAOTblConfMecanicaImpl extends AbstractDaoImpl<TblConfMecanica> implements DAOTblConfMecanica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<TblConfMecanica> getByMecanicaId(int mecanicaId) {
		Iterator<TblConfMecanica> confMecanicaLst = null;
		confMecanicaLst = getHibernateTemplate().iterate(" FROM TblConfMecanica a WHERE a.tblMecanica.mecanicaId = ?", new Object[] { mecanicaId });
		return toInitializedList(confMecanicaLst);
	}

	@Override
	public void deleteByMecanicaId(int mecanicaId) {
		this.getSession().createQuery("DELETE FROM TblConfMecanica a WHERE a.tblMecanica.mecanicaId= "+ mecanicaId).executeUpdate();
	}
	
	@Override
	public void save(List<TblConfMecanica> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (TblConfMecanica o : list) {
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