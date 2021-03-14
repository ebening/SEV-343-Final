package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.adinfi.seven.business.domain.TblPreciosPromocion;
import com.adinfi.seven.business.domain.TblPreciosPromocionId;

public class DAOTblPreciosPromocionImpl extends AbstractDaoImpl<TblPreciosPromocion> implements DAOTblPreciosPromocion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public TblPreciosPromocion getPreciosPromocionById(TblPreciosPromocionId id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(TblPreciosPromocion.class, "tbl");
			criteria.add(Restrictions.eq("tbl.id", id));
			TblPreciosPromocion result = (TblPreciosPromocion) criteria.uniqueResult();
			tx.commit();
			return result;
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblPreciosPromocion> getByMecanicaId(int mecanicaId, Integer estatusRevisionId,
			Integer estatusCapturaId) {
		StringBuilder from= new StringBuilder(" FROM TblPreciosPromocion a ");
		StringBuilder where= new StringBuilder();
		armarQuery(" a.id.mecanicaId = ", mecanicaId, where);
		armarQuery(" a.estatusRevision = ", estatusRevisionId, where);
		armarQuery(" a.estatusCaptura = ", estatusCapturaId, where);
		from.append(where);
        System.out.println("Query: " + from.toString());
		return getHibernateTemplate().find(from.toString());
		//return toInitializedList(list);
	}
	@Override
	public void deleteByMecanicaId(int mecanicaId) {
		Query query = this
				.getSession()
				.createQuery(
						"DELETE FROM TblPreciosPromocion a WHERE a.id.mecanicaId= "
								+ mecanicaId);
		query.executeUpdate();
	}
	@Override
	public Double getAhorroMaximoByMecanicaId(int mecanicaId) throws Exception {
		String query = "SELECT MAX(a.ahorroFijo) FROM TblPreciosPromocion a where a.id.mecanicaId= "
				+ mecanicaId;
		Query q = this.getSession().createQuery(query);
		Double maxAhorro = new Double(0);
		if (q.uniqueResult() != null) {
			maxAhorro = (Double)q.uniqueResult();
		}
		
		return maxAhorro;
	}
	private void armarQuery(String condicion, Integer valor, StringBuilder query){
		if(valor!=null&&valor.intValue()>0){
			if(query.indexOf("WHERE")>0){
				query.append(" AND ");
			}else{
				query.append(" WHERE ");
			}
			query.append(condicion);
			query.append(valor);
		}
	}
	
	@Override
	public void save(List<TblPreciosPromocion> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (TblPreciosPromocion o : list) {
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