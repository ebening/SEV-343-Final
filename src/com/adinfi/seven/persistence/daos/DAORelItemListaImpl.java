package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.RelItemLista;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jdominguez on 5/12/16.
 */
public class DAORelItemListaImpl extends AbstractDaoImpl<RelItemLista> implements DAORelItemLista {

    @Override
    public List<RelItemLista> getRelItemListaByListaID(int idlista) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(RelItemLista.class, "rel");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("rel.catItem", "catITem", Criteria.LEFT_JOIN);
            criteria.createAlias("rel.catLista", "lista", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("rel.catLista.idLista", idlista));
            List<RelItemLista> listaList = criteria.list();
            return listaList;
        }catch (HibernateException ex){
            System.out.println(ex);
            if (tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
        }
    }
}
