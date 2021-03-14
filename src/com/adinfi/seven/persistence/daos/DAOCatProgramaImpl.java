 package com.adinfi.seven.persistence.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.TblCampanaProgramas;

@SuppressWarnings("serial")
public class DAOCatProgramaImpl extends AbstractDaoImpl<CatPrograma> implements DAOCatPrograma {

    @Override
    public TblCampanaProgramas getTblCampanaProgramasById(long idCampana, int idPrograma) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TblCampanaProgramas.class,"tbl");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("tbl.tblCampana","campana", Criteria.LEFT_JOIN);
            criteria.createAlias("tbl.grupoZonas","gzonas", Criteria.LEFT_JOIN);
            criteria.createAlias("tbl.zonas","zonas", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("tbl.id.idCampana", idCampana));
            criteria.add(Restrictions.eq("tbl.id.idPrograma", idPrograma));
            TblCampanaProgramas campanaProgramas = (TblCampanaProgramas) criteria.uniqueResult();
            tx.commit();
            return campanaProgramas;
        }catch (HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return null;
        }
    }

    @Override
	public CatPrograma getCatPrograma(int idCat) {
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            Criteria cr = s.createCriteria(CatPrograma.class,"catPrograma");
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            cr.createAlias("catPrograma.catSenals", "senals", Criteria.LEFT_JOIN);
            cr.add(Restrictions.eq("catPrograma.idPrograma", idCat));
            CatPrograma list = (CatPrograma) cr.uniqueResult();
            tx.commit();
            return list;
        }catch(HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return null;
        }
	}

    @SuppressWarnings("unchecked")
	@Override
	public List<CatPrograma> getCatPrograma(List<Integer> idsCat) {
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            Criteria cr = s.createCriteria(CatPrograma.class,"catPrograma");
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            cr.createAlias("catPrograma.catSenals", "senals", Criteria.LEFT_JOIN);
            cr.add(Restrictions.in("catPrograma.idPrograma", idsCat));
            List<CatPrograma> list = (List<CatPrograma>) cr.list();
            tx.commit();
            return list;
        }catch(HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return null;
        }
	}

	@Override
	public List<CatPrograma> getCatProgramas() {
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = s.beginTransaction();
            Criteria cr = s.createCriteria(CatPrograma.class,"catPrograma");
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            cr.createAlias("catPrograma.catSenals", "senals", Criteria.LEFT_JOIN);
            List<CatPrograma> list = cr.list();
            tx.commit();
            return list;
        }catch(HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return null;
        }
	}
	

}
