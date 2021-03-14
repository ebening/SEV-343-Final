package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatEtiquetas;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdominguez on 2/24/16.
 */
public class DAOCatEtiquetasImpl extends AbstractDaoImpl<CatEtiquetas> implements DAOCatEtiquetas {

    private Session getOpenSession(){
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    @Override
    public List<CatEtiquetas> getEtiquetasAll() {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(CatEtiquetas.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<CatEtiquetas> list = criteria.list();
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
    public CatEtiquetas getEtiquetaById(int id) {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(CatEtiquetas.class,"etiqueta");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("etiqueta.idetiqueta", id));
            CatEtiquetas estatus = (CatEtiquetas) criteria.uniqueResult();
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
