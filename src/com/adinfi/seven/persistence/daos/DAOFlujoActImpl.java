package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatFlujoAct;
import com.adinfi.seven.business.domain.RelFlujoAct;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdominguez on 8/19/16.
 */
public class DAOFlujoActImpl extends AbstractDaoImpl<CatFlujoAct> implements DAOFlujoAct {


    @Override
    public int getDiasTotalesFlujo(CatFlujoAct catFlujoAct) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT SUM (rel.dias) FROM RelFlujoAct AS rel WHERE rel.catFlujoAct.id = " + catFlujoAct.getId());
            Long r = (Long) query.uniqueResult();
            tx.commit();
            return r.intValue();
        }catch (HibernateException ex){
            if(tx != null){
                tx.rollback();
            }
            return 0;
        }
    }

    @Override
    public CatFlujoAct getFlujoById(int id) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(CatFlujoAct.class, "flujo");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("flujo.relFlujoActs", "rels", Criteria.LEFT_JOIN);
            criteria.createAlias("rels.catActPred", "act", Criteria.LEFT_JOIN);
            criteria.createAlias("rels.catActPred.role", "rol", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("flujo.id", id));
            CatFlujoAct catFlujoAct = (CatFlujoAct) criteria.uniqueResult();
            tx.commit();
            return catFlujoAct;
        }catch (HibernateException ex){
            if(tx != null){
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public List<CatFlujoAct> getAllFull() {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx =  null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(CatFlujoAct.class, "flujo");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("flujo.relFlujoActs", "rels", Criteria.LEFT_JOIN);
            criteria.createAlias("rels.catActPred", "act", Criteria.LEFT_JOIN);
            criteria.createAlias("rels.catActPred.role", "rol", Criteria.LEFT_JOIN);
            List<CatFlujoAct> list = criteria.list();
            tx.commit();
            return list;
        }catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }
    }

    @Override
    public List<RelFlujoAct> getRelFlujoByFlujo(CatFlujoAct flujoAct) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(RelFlujoAct.class, "rel");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("rel.catActPred", "act", Criteria.LEFT_JOIN);
            criteria.createAlias("rel.catFlujoAct", "flujo", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("rel.catFlujoAct", flujoAct));
            List<RelFlujoAct> list = criteria.list();
            tx.commit();
            return list;
        }catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }

    }

    @Override
    public boolean updateRelFlujoAct(RelFlujoAct relFlujoAct) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(relFlujoAct);
            tx.commit();
            return true;
        }catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
            }
            return false;
        }
    }
}
