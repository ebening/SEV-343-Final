package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.adinfi.seven.business.domain.CatUsuarios;

public class DAOUsuariosImpl extends AbstractDaoImpl<CatUsuarios>  implements DAOUsuarios{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Session getOpenSession(){
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	@Override
	public List<CatUsuarios> getUsuarioByRoleAndCategory(int idRole, int idCategory) {
		Session s = getOpenSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(CatUsuarios.class,"u");
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.createAlias("u.catRole", "role", Criteria.LEFT_JOIN);
			cr.createAlias("u.relUsuariosCategoriases", "reluc", Criteria.LEFT_JOIN);
			cr.createAlias("reluc.catCategory", "categos", Criteria.LEFT_JOIN);
			cr.add(Restrictions.eq("u.catRole.idrole", idRole));
			if (idCategory > 0){
				cr.add(Restrictions.eq("reluc.catCategory.idCategory", idCategory));
			}
			List<CatUsuarios> list = cr.list();
			return list;
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
	public List<CatUsuarios> getUsuariosAll() {
		Session s = getOpenSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(CatUsuarios.class,"u");
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.createAlias("u.catRole", "role", Criteria.LEFT_JOIN);
			cr.createAlias("u.relUsuariosCategoriases", "reluc", Criteria.LEFT_JOIN);
			cr.createAlias("reluc.catCategory", "categos", Criteria.LEFT_JOIN);
			List<CatUsuarios> list = cr.list();
			tx.commit();
			return list;
		}catch(HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return new ArrayList<>();
		}
	}

	@Override
	public CatUsuarios getUsuarioById(int id) {
		Session s = getOpenSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(CatUsuarios.class,"u");
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.createAlias("u.catRole", "role", Criteria.LEFT_JOIN);
			cr.createAlias("u.relUsuariosCategoriases", "reluc", Criteria.LEFT_JOIN);
			cr.createAlias("reluc.catCategory", "categos", Criteria.LEFT_JOIN);
			cr.add(Restrictions.eq("u.idusuarios", id));
			CatUsuarios user = (CatUsuarios) cr.uniqueResult();
			tx.commit();
			return user;
		}catch(HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		}
	}

	@Override
	public CatUsuarios getUsuarioForLogin(String login, String password) {
		Session s = getOpenSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(CatUsuarios.class,"u");
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.createAlias("u.catRole", "role", Criteria.LEFT_JOIN);
			cr.createAlias("u.relUsuariosCategoriases", "reluc", Criteria.LEFT_JOIN);
			cr.createAlias("reluc.catCategory", "categos", Criteria.LEFT_JOIN);
			cr.add(Restrictions.eq("u.login", login));
			cr.add(Restrictions.eq("u.password", password));
			CatUsuarios user = (CatUsuarios) cr.uniqueResult();
			tx.commit();
			return user;
		}catch(HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		}
	}

	@Override
	public CatUsuarios getUsuarioByLogin(String login) {
		Session s = getOpenSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(CatUsuarios.class,"u");
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.createAlias("u.catRole", "role", Criteria.LEFT_JOIN);
			cr.createAlias("u.relUsuariosCategoriases", "reluc", Criteria.LEFT_JOIN);
			cr.createAlias("reluc.catCategory", "categos", Criteria.LEFT_JOIN);
			cr.add(Restrictions.eq("u.login", login));
			CatUsuarios user = (CatUsuarios) cr.uniqueResult();
			tx.commit();
			return user;
		}catch(HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		}
	}

}
