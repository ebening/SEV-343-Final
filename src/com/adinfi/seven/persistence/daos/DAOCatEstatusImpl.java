package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatEstatus;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdominguez on 2/24/16.
 */
public class DAOCatEstatusImpl extends AbstractDaoImpl<CatEstatus> implements DAOCatEstatus {

    private Session getOpenSession(){
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    @Override
    public CatEstatus getEstatusByNameContains(String nombre) {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM CatEstatus WHERE NOMBRE LIKE '%" + nombre + "%'");
            List<CatEstatus> list = query.list();
            return list.size() > 0 ? list.get(0) : null;
        }catch(HibernateException ex){
            System.out.println(ex);
            if (tx != null){
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public List<CatEstatus> getEstatusAll() {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(CatEstatus.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<CatEstatus> list = criteria.list();
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
    public CatEstatus getEstatusById(int id) {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(CatEstatus.class,"estatus");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("estatus.idestatus", id));
            CatEstatus estatus = (CatEstatus) criteria.uniqueResult();
            tx.commit();
            return estatus;
        }catch(HibernateException ex){
            System.out.println(ex);
            if (tx != null){
                tx.rollback();
            }
            return null;
        }
    }
}
