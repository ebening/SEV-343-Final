package com.adinfi.seven.persistence.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.adinfi.seven.business.domain.RelCompSku;
import com.google.common.base.Joiner;

public class DAORelCompSkuImpl extends AbstractDaoImpl<RelCompSku> implements DAORelCompSku {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void deleteByComponenteId(int componenteId) {
		this.getSession().createQuery("delete from RelCompSku a where a.tblComponente.componenteId= "+ componenteId).executeUpdate();
	}
	
	@Override
	public void deleteByComponenteId(List<Integer> ids) {
		String in = Joiner.on(",").join(ids);
		if(!in.isEmpty()){
			this.getSession().createQuery("delete from RelCompSku a where a.tblComponente.componenteId in ("+in+")").executeUpdate();
		}
	}
	
	@Override
	public void save(List<RelCompSku> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (RelCompSku o : list) {
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
