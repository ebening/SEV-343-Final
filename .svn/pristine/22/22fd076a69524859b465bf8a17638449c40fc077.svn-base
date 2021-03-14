package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.TblActividad;
import com.adinfi.seven.business.domain.TblCampanaActividades;
import com.adinfi.seven.presentation.views.util.Util;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jdominguez on 2/15/16.
 */
public class DAOTblActividadImpl extends AbstractDaoImpl<TblActividad> implements DAOTblActividad {


    private Session getOpenSession(){
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    @Override
    public List<TblActividad> getTblActividadToOpen() {
        Session session = getOpenSession();
        Transaction tx = null;
        Date hoy = new Date();
        try {
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            cr.add(Restrictions.eq("tbl.vigenciaInicio", hoy));
            cr.add(Restrictions.eq("tbl.catEstatus.idestatus", 21));
            List<TblActividad> list = cr.list();
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
    public List<TblActividad> getTblActividadVencidas() {
        Session session = getOpenSession();
        Transaction tx = null;
        Date ayer = Util.sumarRestarDiasFecha(new Date(), -1);
        try {
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            cr.add(Restrictions.eq("tbl.vigenciaFinal", ayer));
            cr.add(Restrictions.eq("tbl.catEstatus.idestatus", 1));
            List<TblActividad> list = cr.list();
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
    public boolean deleteActividadesByIdMecanica(int idmecanica) {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM TblActividad WHERE tblMecanica.mecanicaId = " + idmecanica);
            int r = query.executeUpdate();
            tx.commit();
            return r  >= 0;
        }catch (HibernateException ex){
            if (tx != null){
                tx.rollback();
            }
            return false;
        }
    }

    @Override
    public TblActividad getTblActividadByMecanicaAndOrden(int idMecanica, int orden) {
        Session session = getOpenSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            cr.add(Restrictions.eq("tbl.tblMecanica.mecanicaId", idMecanica));
            cr.add(Restrictions.eq("tbl.orden", orden));
            TblActividad actividad = (TblActividad) cr.uniqueResult();
            tx.commit();
            return actividad;
        }catch (HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public List<TblActividad> getTblActividadAll() {
        Session session = getOpenSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            List<TblActividad> list = cr.list();
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
    public List<TblActividad> getTblActividadByMecanica(int idMecanica) {
        Session session = getOpenSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            cr.add(Restrictions.eq("mecanica.mecanicaId", idMecanica));
            List<TblActividad> list = cr.list();
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
    public List<TblActividad> getTblActividadByResponsable(CatUsuarios responsable) {
        Session session = getOpenSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            cr.add(Restrictions.eq("res.idusuarios", responsable.getIdusuarios()));
            List<TblActividad> list = cr.list();
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
    public List<TblActividad> getTblActividadByCreador(CatUsuarios creador) {
        Session session = getOpenSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            cr.add(Restrictions.eq("creador.idusuarios", creador.getIdusuarios()));
            List<TblActividad> list = cr.list();
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
    public List<TblActividad> getTblActividadByCampanaAct(TblCampanaActividades camapanaAct) {
        Session session = getOpenSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            cr.add(Restrictions.eq("idtblcampact", camapanaAct.getIdActividad()));
            List<TblActividad> list = cr.list();
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
    public TblActividad getTblActividadById(int idActividad) {
        Session session = getOpenSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria cr = getCriteriaStandard(session);
            cr.add(Restrictions.eq("idactividad", idActividad));
            TblActividad list = (TblActividad) cr.uniqueResult();
            tx.commit();
            return list;
        }catch (HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public List<TblActividad> getTblActividadForTodayAlert() {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Criteria criteria = getCriteriaStandard(session);
            criteria.add(Restrictions.eq("tbl.alerta", new Date()));
            List<TblActividad> list = criteria.list();
            tx.commit();
            return list;
        }catch (HibernateException ex){
           if (tx != null){
               tx.rollback();
           }
            return new ArrayList<>();
        }
    }

    private Criteria getCriteriaStandard (Session session){
        Criteria cr = session.createCriteria(TblActividad.class, "tbl");
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        cr.createAlias("tbl.catUsuariosByIdresponsable", "res", Criteria.LEFT_JOIN );
        cr.createAlias("tbl.catUsuariosByIdcreador", "creador", Criteria.LEFT_JOIN);
        cr.createAlias("creador.relUsuariosCategoriases", "categs", Criteria.FULL_JOIN);
        cr.createAlias("res.relUsuariosCategoriases", "categs2", Criteria.LEFT_JOIN);
        cr.createAlias("categs.catCategory", "cate", Criteria.LEFT_JOIN);
        cr.createAlias("categs2.catCategory", "cate2", Criteria.LEFT_JOIN);
        cr.createAlias("tbl.tblMecanica", "mecanica", Criteria.LEFT_JOIN);
        cr.createAlias("tbl.catEstatus", "estatus", Criteria.LEFT_JOIN);
        cr.createAlias("tbl.catRole", "role", Criteria.LEFT_JOIN);
        return cr;
    }
}
