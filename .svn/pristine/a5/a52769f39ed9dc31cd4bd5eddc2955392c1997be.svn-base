package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.business.domain.RelStoreDiseno;
import com.adinfi.seven.business.domain.RelZonaDiseno;
import com.adinfi.seven.business.domain.TblDisenos;

public class DAOTblDisenosImpl extends AbstractDaoImpl<TblDisenos> implements DAOTblDisenos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean deleteByMecanicaId(int idmecanica) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM TblDisenos WHERE tblMecanica.mecanicaId = " + idmecanica);
            int r = query.executeUpdate();
            tx.commit();
            return r >= 0;
        }catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
            }
            System.out.println(ex);
            return false;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<TblDisenos> getDisenos(Integer programaId, Integer mecanicaId, List<String> grupoZonaId,
									   List<String> zonaId, List<CatSenal> senals, List<String> storeId, Integer estatusPrecio,
									   Integer estatusDiseno) throws Exception {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = getCriteriaSearch(session);
            criteria.add(Restrictions.eq("a.programaId", programaId));
            criteria.add(Restrictions.eq("a.tblMecanica.mecanicaId", mecanicaId));

            Disjunction zonaRestrictions = Restrictions.disjunction();
            Conjunction senalConjunction = Restrictions.conjunction();

            if (!grupoZonaId.isEmpty()) {
                zonaRestrictions.add(Restrictions.in("gz.grupoId", convertListStringtoInt(grupoZonaId)));
            }

            if (!zonaId.isEmpty()) {
                DetachedCriteria storeSubquery = DetachedCriteria.forClass(RelZonaDiseno.class, "rzd");
            	storeSubquery.add(Restrictions.in("rzd.zonaId", convertListStringtoInt(zonaId)));
            	storeSubquery.setProjection(Projections.property("disenos.disenoId"));
            	zonaRestrictions.add(Subqueries.propertyIn("a.disenoId",storeSubquery));
            }

            if (!storeId.isEmpty()) {
            	DetachedCriteria storeSubquery = DetachedCriteria.forClass(RelStoreDiseno.class, "rsd");
            	storeSubquery.add(Restrictions.in("rsd.storeId", convertListStringtoInt(storeId)));
            	storeSubquery.setProjection(Projections.property("disenos.disenoId"));
            	zonaRestrictions.add(Subqueries.propertyIn("a.disenoId",storeSubquery));
            }

            if (senals != null && !senals.isEmpty()) {
                senalConjunction.add(Restrictions.in("senals.catSenal", senals));
            }


            Criterion complete = Restrictions.conjunction().add(zonaRestrictions).add(senalConjunction);

            criteria.add(complete);
            List<TblDisenos> designs = criteria.list();
            tx.commit();
            return designs;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex);
            return new ArrayList<>();
        }
    }

    private Criteria getCriteriaDefault(Session session){
        Criteria criteria = session.createCriteria(TblDisenos.class, "a");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createAlias("a.grupoZonaLst", "gz", Criteria.LEFT_JOIN);
        criteria.createAlias("a.zonaLst", "z", Criteria.LEFT_JOIN);
        criteria.createAlias("a.storeLst", "s", Criteria.LEFT_JOIN);
        criteria.createAlias("a.relDisenoSenals", "senals", Criteria.LEFT_JOIN);
        criteria.createAlias("senals.catSenal","catSenals", Criteria.LEFT_JOIN);
        criteria.createAlias("a.tblMecanica", "mecanica", Criteria.LEFT_JOIN);
        return criteria;
	}

    private Criteria getCriteriaSearch(Session session){
        Criteria criteria = session.createCriteria(TblDisenos.class, "a");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createAlias("a.grupoZonaLst", "gz", Criteria.LEFT_JOIN);
        criteria.createAlias("a.relDisenoSenals", "senals", Criteria.LEFT_JOIN);
        criteria.createAlias("senals.catSenal","catSenals", Criteria.LEFT_JOIN);
        criteria.createAlias("a.tblMecanica", "mecanica", Criteria.LEFT_JOIN);
        return criteria;
	}

	private List<Integer> convertListStringtoInt(List<String> stringList){
		List<Integer> integerList = new ArrayList<>();
		for (String s : stringList){
			integerList.add(Integer.valueOf(s));
		}
		return integerList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblDisenos> getDiseno(Integer mecanicaID) throws Exception {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = getCriteriaDefault(session);
            criteria.add(Restrictions.eq("a.tblMecanica.mecanicaId", mecanicaID));
            List<TblDisenos> designs = criteria.list();
            tx.commit();
            return designs;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex);
            return new ArrayList<>();
        }
	}

	@Override
	public boolean updateStatusDisenoByMecanica(Integer idMecanica, Integer idStatus){
		Query query = this.getSession().createQuery("UPDATE  TblDisenos tls set tls.preciosAuth = :idStatus  where tls.tblMecanica.mecanicaId = :idMecanica");
		query.setParameter("idStatus", idStatus);
		query.setParameter("idMecanica", idMecanica);
		query.executeUpdate();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<RelStoreDiseno> getStores(List<Integer> disenoIds){
		Criteria stores = this.getSession().createCriteria(RelStoreDiseno.class, "rsd");
		stores.add(Restrictions.in("rsd.disenos.disenoId", disenoIds));
		return stores.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RelZonaDiseno> getZonas(List<Integer> disenoIds){
		Criteria zonas = this.getSession().createCriteria(RelZonaDiseno.class, "rzd");
		zonas.add(Restrictions.in("rzd.disenos.disenoId", disenoIds));
		return zonas.list();
	}

}
