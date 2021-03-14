package com.adinfi.seven.persistence.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.engine.SessionFactoryImplementor;

import com.adinfi.seven.business.domain.RelGrupoZonaCampana;
import com.google.common.base.Joiner;

public class DAORelGrupoZonaCampanaImpl extends AbstractDaoImpl<RelGrupoZonaCampana> implements
		DAORelGrupoZonaCampana {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void deleteById(long campanaId, int idPrograma){
		Query query = this
				.getSession()
				.createQuery(
						"delete from RelGrupoZonaCampana a where a.tblCampanaProgramas.id.idCampana= "
								+ campanaId + " and a.tblCampanaProgramas.id.idPrograma= "+idPrograma);
		query.executeUpdate();
	}
	
	@Override
	public void deleteById(List<Long> campanaId, List<Integer> idPrograma){
		String cids = Joiner.on(",").join(campanaId);
		String pids = Joiner.on(",").join(idPrograma);
		String deleteQuery = "delete from RelGrupoZonaCampana a where a.tblCampanaProgramas.id.idCampana in ("
				+ cids + ") and a.tblCampanaProgramas.id.idPrograma in ("+pids+")";
		Query query = this.getSession().createQuery(deleteQuery);
		int r = query.executeUpdate();
		System.out.println("Deleted DAORelGrupoZonaCampana by list: "+r);
	}
	
	@Override
	public void save(List<RelGrupoZonaCampana> list){
		String query = "";
		String schema = ((SessionFactoryImplementor)getHibernateTemplate().getSessionFactory()).getSettings().getDefaultSchemaName();
		if(!list.isEmpty()){
			for(RelGrupoZonaCampana o: list){
				if(!query.isEmpty()){
					query+=" UNION ";
				}
				query+="SELECT "+o.getGrupoId()+","+o.getTblCampanaProgramas().getId().getIdCampana()+","+o.getTblCampanaProgramas().getId().getIdPrograma()+" FROM  SYSIBM.SYSDUMMY1";
			}
			query = "INSERT INTO "+schema+".REL_GRUPO_ZONA_CAMPANA(GRUPO_ID, ID_CAMPANA, ID_PROGRAMA) "+query;
		}
		if(!query.isEmpty()){
			int r = this.getSession().createSQLQuery(query).executeUpdate();
			System.out.println("Inserted RelGrupoZonaCampana: "+r);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RelGrupoZonaCampana> getByCampanaPrograma(long idCampana, int idPrograma) {
		return (List<RelGrupoZonaCampana>) getSession().createQuery("from RelGrupoZonaCampana where tblCampanaProgramas.tblCampana.idCampana=? and tblCampanaProgramas.programa.idPrograma=?")
			.setLong(0, idCampana)
			.setInteger(1, idPrograma)
			.list();
	}
}
