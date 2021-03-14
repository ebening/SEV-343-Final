/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.persistence.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.adinfi.seven.business.domain.TblComponenteZonaPrecio;

/**
 *
 * @author joseg
 */
public class DAOTblComponenteZonaPrecioImpl extends AbstractDaoImpl<TblComponenteZonaPrecio> implements DAOTblComponenteZonaPrecio{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(int componentId, int zoneId) {
        String sentence = "FROM TblComponenteZonaPrecio table WHERE table.componenteId = ? and table.zonaId = ?";
        return getHibernateTemplate().find(sentence, new Integer[]{componentId, zoneId});
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(List<Object[]> list) {
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            Criteria cr = s.createCriteria(TblComponenteZonaPrecio.class,"o");
            Disjunction d = Restrictions.disjunction();
			for(Object[] o: list){
				d.add(Restrictions.and(Restrictions.eq("o.componenteId", o[0]), Restrictions.eq("o.zonaId", o[1])));
			}
			cr.add(d);
            List<TblComponenteZonaPrecio> l = (List<TblComponenteZonaPrecio>) cr.list();
            tx.commit();
            return l;
        }catch(HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return null;
        }
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(String componentList, String zoneList) {
        return getHibernateTemplate().find("FROM TblComponenteZonaPrecio table WHERE table.componenteId in ("+componentList+") and table.zonaId in ("+zoneList+")");
    }
    
    public void deleteComponenteZonaByComponenteId(int componenteId){
        String hql = "delete from TblComponenteZonaPrecio where componenteId= :componenteId";
        getSession().createQuery(hql).setString("componenteId", String.valueOf(componenteId)).executeUpdate();
    }
    
	@Override
	public void save(List<TblComponenteZonaPrecio> list){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Integer i = 0;
		for (TblComponenteZonaPrecio o : list) {
			session.saveOrUpdate(o);
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
