package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatDepto;
import com.adinfi.seven.business.domain.CatProveedor;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOCatCategoryImpl extends AbstractDaoImpl<CatCategory> implements DAOCatCategory {
    

	private static final long serialVersionUID = 1L;

    @Override
    public CatCategory getCatCategory(Integer idCategory) {
    		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
    		Transaction tx = null;
    		try{
    			tx = s.beginTransaction();
    			Criteria cr = s.createCriteria(CatCategory.class, "cat");
    			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    			cr.createAlias("cat.catDepto", "depto", Criteria.LEFT_JOIN);
    			cr.add(Restrictions.eq("cat.idCategory", idCategory));
    			CatCategory c = (CatCategory) cr.uniqueResult();
    			tx.commit();
    			return c;
    		}catch(HibernateException e){
    			e.getStackTrace();
    			return null;
    		}
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<CatCategory> getCatCategories(List<Integer> ids) {
			Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Transaction tx = null;
			try{
				tx = s.beginTransaction();
				Criteria cr = s.createCriteria(CatCategory.class, "cat");
				cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				cr.createAlias("cat.catDepto", "depto", Criteria.LEFT_JOIN);
				cr.add(Restrictions.in("cat.idCategory", ids));
				List<CatCategory> c = cr.list();
				tx.commit();
				return c;
			}catch(HibernateException e){
				e.getStackTrace();
				return null;
			}
    }

    @SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
    @Override
    public List<CatCategory> getCatCategoryList(){
	    return getHibernateTemplate().find("from CatCategory catCategory");
	     //return toInitializedList(itActivity);
    }
    
    @SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
    @Override
    public List<CatDepto> getDeptoList()
            throws GeneralException {
        Iterator<CatDepto> itActivity = getHibernateTemplate()
                .iterate(
                        " from CatDepto catDepto order by deptoId asc ");
        return toInitializedList(itActivity);
    }
    
	@Override
    public List<CatProveedor> getProveedoresDeCategoria(int categoriaId) throws GeneralException {
//    	return getHibernateTemplate().findByNamedQuery("categoria.getProveedores", categoriaId);
        return new ArrayList<>();
    }

	@Override
	public CatCategory getCatCategoryByCode(String code) {
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = s.beginTransaction();
			Criteria cr = s.createCriteria(CatCategory.class, "cat");
			cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			cr.createAlias("cat.catDepto", "depto", Criteria.LEFT_JOIN);
			cr.add(Restrictions.eq("cat.code", code));
			CatCategory c = (CatCategory) cr.uniqueResult();
			tx.commit();
			return c;
		}catch(HibernateException e){
			e.getStackTrace();
			return null;
		}
	}
    
}
