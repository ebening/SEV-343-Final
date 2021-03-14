package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.*;

import com.adinfi.seven.business.domain.TblCampanaProgramas;
import org.hibernate.criterion.Restrictions;

public class DAOCampanaProgramasImpl extends AbstractDaoImpl<TblCampanaProgramas> implements DAOCampanaProgramas{


    @Override
    public TblCampanaProgramas getProgramaById(long idCampana, int programaId) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TblCampanaProgramas.class, "p");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("p.tblCampana", "campana", Criteria.LEFT_JOIN);
            criteria.createAlias("p.programa", "prog", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("p.id.idCampana", idCampana));
            criteria.add(Restrictions.eq("p.id.idPrograma", programaId));
            TblCampanaProgramas programa = (TblCampanaProgramas) criteria.uniqueResult();
            tx.commit();
            return programa;
        }catch (HibernateException e){
            if (tx != null){
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public boolean changeEtapaPrograma(int idCampana, int idPrograma, String etapa) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = null;
        String sentence = "UPDATE TblCampanaProgramas SET etapa = '" + etapa +
                "' WHERE id.idCampana = " + idCampana + " AND id.idPrograma = " + idPrograma;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(sentence);
            query.executeUpdate();
            tx.commit();
            return true;
        }catch (HibernateException ex){
            System.out.println("Error Cambiar Etapa Programa: " + ex);
            if (tx != null){
                tx.rollback();
            }
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TblCampanaProgramas> getProgramasByCampanaId(long idCampana) {
        Iterator<TblCampanaProgramas> tblCampanaIterator = getHibernateTemplate()
                        .iterate(
                                        "from TblCampanaProgramas tblCampanaProgramas  where tblCampanaProgramas.tblCampana.idCampana = ? ",
                                        new Object[] { idCampana });
        return toInitializedList(tblCampanaIterator);
    }

    @Override
    public void deleteProgramasByCampanaIdProgramaID(long idCampana, int idPrograma) {
        String sentence = "delete from TblCampanaProgramas tblCampanaProgramas  where tblCampanaProgramas.id.idCampana = " + idCampana + 
                                            " and tblCampanaProgramas.id.idPrograma = " + idPrograma;
        Query query = getSession().createQuery(sentence);
        query.executeUpdate();
    }
}
