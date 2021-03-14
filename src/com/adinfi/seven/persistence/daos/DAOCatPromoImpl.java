package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.adinfi.seven.business.domain.CatPromo;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DAOCatPromoImpl extends AbstractDaoImpl<CatPromo> implements DAOCatPromo {

	@Override
	@SuppressWarnings("unchecked")
	public CatPromo getCatPromo(int idCatPromo) {
		Iterator<CatPromo> tblCatPromoIterator 
		= getHibernateTemplate().
		iterate("from CatPromo catPromo where catPromo.idPromo = ? ", new Object[]{idCatPromo});
		return toInitializedInstance(tblCatPromoIterator);
	}


    @Override
    public List<CatPromo> getCatPromosByTipoPromo(int idTipoPromo) {
        Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            Criteria criteria = getDefaultCriteria(s);
            criteria.add(Restrictions.eq("tipo.idTipoPromo", idTipoPromo));
            List<CatPromo> list = criteria.list();
            tx.commit();
            return list;
        }catch (HibernateException e){
            if (tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }
    }

    @Override
	public List<CatPromo> getCatPromos() {
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            Criteria criteria = getDefaultCriteria(s);
            List<CatPromo> list = criteria.list();
            tx.commit();
            return list;
        }catch (HibernateException e){
            if (tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }
    }

    private Criteria getDefaultCriteria(Session s){
        Criteria criteria = s.createCriteria(CatPromo.class, "cp");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createAlias("cp.catTipoPromo", "tipo");
        return criteria;
    }
}
