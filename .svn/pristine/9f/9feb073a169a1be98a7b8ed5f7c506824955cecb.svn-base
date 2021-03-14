package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.adinfi.seven.business.domain.CatRole;

public class DAORoleImpl extends AbstractDaoImpl<CatRole> implements DAORole {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Session openSession(){
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatRole> getRoleAll() {
		Session s = openSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(CatRole.class, "role");
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.createAlias("role.catUsuarioses", "u", Criteria.LEFT_JOIN);
			List<CatRole> roles = cr.list();
			tx.commit();
			return roles;
		}catch(HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return new ArrayList<>();
		}
	}

	@Override
	public CatRole getCatRoleById(int id) {
		Session s = openSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(CatRole.class, "role");
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.createAlias("role.catUsuarioses", "u", Criteria.LEFT_JOIN);
			cr.add(Restrictions.eq("role.idrole", id));
			CatRole roles = (CatRole) cr.uniqueResult();
			tx.commit();
			return roles;
		}catch(HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		}
	}



}
