package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.adinfi.seven.business.domain.TblMecanica;

public class DAOTblMecanicaImpl extends AbstractDaoImpl<TblMecanica> implements DAOTblMecanica {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
    @Override
    public List<TblMecanica> getAllMecanica(int campanaId) {
        String sentence = " from TblMecanica a where a.campana.idCampana= ?";
            Iterator<TblMecanica> itMecanica = getHibernateTemplate().iterate(sentence,new Object[]{(long) campanaId});
            return toInitializedList(itMecanica);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TblMecanica> getAllMecanica(int campanaId, int programaId) {
        String sentence = " from TblMecanica a where a.campana.idCampana= ? and a.programaId= ?";
        Iterator<TblMecanica> itMecanica = getHibernateTemplate().iterate(sentence,new Object[]{(long) campanaId, programaId});
        return toInitializedList(itMecanica);
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<TblMecanica> getMecanicaWithChildrens(List<Object[]> list) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(TblMecanica.class, "m");
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.createAlias("m.tblCategorias", "cats", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.tblComponentes", "camp", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.tblProgramas", "prog", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.tblSenalamientoses", "sen", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.relStores", "st", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.relGrupoZonas", "gz", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.relZonas", "z", Criteria.LEFT_JOIN);
			Disjunction d = Restrictions.disjunction();
			for(Object[] o: list){
				d.add(Restrictions.and(Restrictions.eq("m.campana.idCampana", o[0]), Restrictions.eq("m.programaId", o[1])));
			}
			criteria.add(d);
			List<TblMecanica> l = criteria.list();
			tx.commit();
			return l;
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TblMecanica> getAllMecanica(Integer campanaId,
			Integer programaId, Integer grupoZona, Integer zona) {
		StringBuilder whereStr= new StringBuilder();
		if(campanaId!=null&&campanaId>0){
			whereStr.append(" a.campana.idCampana= ");
			whereStr.append(campanaId.intValue());
		}
		if(programaId!=null&&programaId>0){
			if(whereStr.length()>0){
				whereStr.append(" AND ");
			}
			whereStr.append(" a.programaId= ");
			whereStr.append(programaId.intValue());
		}
		if(grupoZona!=null&&grupoZona>0){
			if(whereStr.length()>0){
				whereStr.append(" AND a.mecanicaId in (");
			}
			whereStr.append(" SELECT tblMecanica.mecanicaId FROM RelGrupoZona gz WHERE gz.grupoId= ");
			whereStr.append(grupoZona.intValue());
			whereStr.append(") ");
		}
		if(zona!=null&&zona>0){
			if(whereStr.length()>0){
				whereStr.append(" AND a.mecanicaId in (");
			}
			whereStr.append(" SELECT tblMecanica.mecanicaId FROM RelZona z WHERE z.zonaId= ");
			whereStr.append(zona.intValue());
			whereStr.append(") ");
		}
		if(whereStr.length()>0){
			return getHibernateTemplate().find("from TblMecanica a JOIN FETCH a.tblCategorias JOIN FETCH a.tblComponentes where "+whereStr+"");
		}
		return null;
	}

    @Override
    public boolean campanaHasMecanica(int campanaId) {
        String sentence = "FROM TblMecanica tm WHERE tm.campana.idCampana = ?";
        @SuppressWarnings("rawtypes")
		List cs = getHibernateTemplate().find(sentence,(long) campanaId);
        return cs.isEmpty();
    }

	@Override
	public List<TblMecanica> getMecanicasByCampana(long campanaId) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(TblMecanica.class, "mec");
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.createAlias("mec.campana", "camp", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("mec.campana.idCampana", campanaId));
			List<TblMecanica> list = criteria.list();
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

	@Override
    public List<TblMecanica> getMecanicasByPrograma(long campanaId, int programaId) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TblMecanica.class, "mec");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.createAlias("mec.campana", "camp", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("mec.campana.idCampana", campanaId));
            criteria.add(Restrictions.eq("mec.programaId", programaId));
            List<TblMecanica> list = criteria.list();
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

    @Override
	public TblMecanica getMecanicaById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(TblMecanica.class, "mec");
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("mec.tblCategorias", "cats", Criteria.LEFT_JOIN);
			criteria.createAlias("mec.campana", "camp", Criteria.LEFT_JOIN);
			criteria.createAlias("camp.catFlujoAct", "flujo", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("mec.mecanicaId", id));
			TblMecanica mecanica = (TblMecanica) criteria.uniqueResult();
			tx.commit();
			return mecanica;
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		}
	}
    
    @Override
	public TblMecanica getMecanicaByIdWithChildren(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(TblMecanica.class, "m");
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("m.tblCategorias", "cats", Criteria.LEFT_JOIN);
			criteria.createAlias("m.tblComponentes", "comp", Criteria.LEFT_JOIN);
			criteria.createAlias("comp.relCompUpcs", "compu", Criteria.LEFT_JOIN);
			criteria.createAlias("comp.relCompSkus", "comps", Criteria.LEFT_JOIN);
			criteria.createAlias("m.tblProgramas", "prog", Criteria.LEFT_JOIN);
			criteria.createAlias("m.tblSenalamientoses", "sen", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.relStores", "st", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.relGrupoZonas", "gz", Criteria.LEFT_JOIN);
//			criteria.createAlias("m.relZonas", "z", Criteria.LEFT_JOIN);
			criteria.createAlias("m.confMecanicaLst", "cm", Criteria.LEFT_JOIN);
			criteria.createAlias("m.preciosLst", "pl", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("m.mecanicaId", id));
			TblMecanica mecanica = (TblMecanica) criteria.uniqueResult();
			tx.commit();
			return mecanica;
		}catch (HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return null;
		}
	}
}