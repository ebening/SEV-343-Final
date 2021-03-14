package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;

import com.adinfi.seven.business.domain.RelZonaCampana;
import com.google.common.base.Joiner;

public class DAORelZonaCampanaImpl extends AbstractDaoImpl<RelZonaCampana>
		implements DAORelZonaCampana {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void deleteById(long campanaId, int idPrograma) {
		Query query = this.getSession().createQuery(
				"delete from RelZonaCampana a where a.tblCampanaProgramas.id.idCampana= "
						+ campanaId + " and a.tblCampanaProgramas.id.idPrograma= "+idPrograma);
		query.executeUpdate();
	}

	@Override
	public void deleteById(List<Long> idCampana, List<Integer> idPrograma) {
		String cids = Joiner.on(",").join(idCampana);
		String pids = Joiner.on(",").join(idPrograma);
		String deleteQuery = "delete from RelZonaCampana a where a.tblCampanaProgramas.id.idCampana in ("+cids+")"+
				" and a.tblCampanaProgramas.id.idPrograma in ("+pids+")";
		Query query = this.getSession().createQuery(deleteQuery);
		int r = query.executeUpdate();
		System.out.println("Deleted RelZonaCampana by list: "+r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RelZonaCampana> getRelZonaCamapana(long campanaId, int idPrograma) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(RelZonaCampana.class, "root");
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.createAlias("root.tblCampanaProgramas", "progs", Criteria.LEFT_JOIN);
            criteria.createAlias("progs.zonas", "zonas", Criteria.LEFT_JOIN);
            criteria.createAlias("progs.tiendas", "tiendas", Criteria.LEFT_JOIN);
            criteria.add(Restrictions.eq("progs.id.idCampana", campanaId));
            criteria.add(Restrictions.eq("progs.id.idPrograma", idPrograma));
            List<RelZonaCampana> list = criteria.list();
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
	public void save(List<RelZonaCampana> list){
		String query = "";
		String schema = ((SessionFactoryImplementor)getHibernateTemplate().getSessionFactory()).getSettings().getDefaultSchemaName();
		if(!list.isEmpty()){
			for(RelZonaCampana o: list){
				if(!query.isEmpty()){
					query+=" UNION ";
				}
				query+="SELECT "+o.getZonaId()+","+o.getTblCampanaProgramas().getId().getIdCampana()+","+o.getTblCampanaProgramas().getId().getIdPrograma()+" FROM  SYSIBM.SYSDUMMY1";
			}
			query = "INSERT INTO "+schema+".REL_ZONA_CAMPANA(ZONA_ID, ID_CAMPANA, ID_PROGRAMA) "+query;
		}
		if(!query.isEmpty()){
			int r = this.getSession().createSQLQuery(query).executeUpdate();
			System.out.println("Inserted RelZonaCampana: "+r);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RelZonaCampana> getByCampanaPrograma(long idCampana, int idPrograma) {
		return (List<RelZonaCampana>) getSession().createQuery("from RelZonaCampana where tblCampanaProgramas.tblCampana.idCampana=? and tblCampanaProgramas.programa.idPrograma=?")
			.setLong(0, idCampana)
			.setInteger(1, idPrograma)
			.list();
	}
}
