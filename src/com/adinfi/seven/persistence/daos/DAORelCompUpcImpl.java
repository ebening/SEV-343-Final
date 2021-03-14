package com.adinfi.seven.persistence.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.adinfi.seven.business.domain.RelCompUpc;
import com.google.common.base.Joiner;

public class DAORelCompUpcImpl extends AbstractDaoImpl<RelCompUpc> implements DAORelCompUpc {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void deleteByComponenteId(int componenteId) {
		this.getSession().createQuery("delete from RelCompUpc a where a.tblComponente.componenteId= "+ componenteId).executeUpdate();
	}
	
	@Override
	public void deleteByComponenteId(List<Integer> ids) {
		String in = Joiner.on(",").join(ids);
		if(!in.isEmpty()){
			this.getSession().createQuery("delete from RelCompUpc a where a.tblComponente.componenteId in ("+in+")").executeUpdate();
		}
	}
	
	@Override
	public void save(List<RelCompUpc> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (RelCompUpc o : list) {
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
