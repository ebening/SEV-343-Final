package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.business.domain.RelDisenoSenal;
import com.adinfi.seven.business.domain.TblDisenos;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdominguez on 3/22/16.
 */
public class DAORelDisenoSenalImpl extends AbstractDaoImpl<RelDisenoSenal> implements DAORelDisenoSenal {

    private Session getOpenSession(){
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public List<RelDisenoSenal> getSenalByDiseno(TblDisenos disenos) {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = getDefaultCriteria(session);
            criteria.add(Restrictions.eq("diseno", disenos));
            List<RelDisenoSenal> list = criteria.list();
            tx.commit();
            return list;
        }catch (HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }
    }

    @Override
    public List<RelDisenoSenal> getDisenoBySenal(CatSenal catSenal) {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = getDefaultCriteria(session);
            criteria.add(Restrictions.eq("senal", catSenal));
            List<RelDisenoSenal> list = criteria.list();
            tx.commit();
            return list;
        }catch (HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }
    }

    private Criteria getDefaultCriteria(Session session){
        Criteria criteria = session.createCriteria(RelDisenoSenal.class, "rel");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createAlias("rel.catSenal", "senal", Criteria.LEFT_JOIN);
        criteria.createAlias("rel.tblDisenos", "diseno", Criteria.LEFT_JOIN);
        return criteria;
    }
}
