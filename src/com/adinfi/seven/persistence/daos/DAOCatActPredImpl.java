package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatActPred;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdominguez on 2/15/16.
 */
public class DAOCatActPredImpl extends AbstractDaoImpl<CatActPred> implements DAOCatActPred {

    private Session getOpenSession(){
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    @Override
    public CatActPred getActById(int id) {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria  = session.createCriteria(CatActPred.class, "act");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("act.relFlujoActs", "rels", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("act.id", id));
            CatActPred catActPred = (CatActPred) criteria.uniqueResult();
            tx.commit();
            return catActPred;
        }catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public List<CatActPred> getCatalogoActividades() {
        Session session = getOpenSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(CatActPred.class, "act");
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            cr.createAlias("act.relFlujoActs", "rel", Criteria.LEFT_JOIN);
            List<CatActPred> list = cr.list();
            tx.commit();
            return list;
        }catch (HibernateException ex){
            System.out.println(ex);
            if (tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }
    }
}
