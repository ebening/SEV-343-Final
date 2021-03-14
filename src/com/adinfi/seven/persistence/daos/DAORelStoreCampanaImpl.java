package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.engine.SessionFactoryImplementor;

import com.adinfi.seven.business.domain.RelStoreCampana;
import com.google.common.base.Joiner;

public class DAORelStoreCampanaImpl extends AbstractDaoImpl<RelStoreCampana>
		implements DAORelStoreCampana {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void deleteById(long campanaId, int idPrograma) {
		Query query = this.getSession().createQuery(
				"delete from RelStoreCampana a where a.tblCampanaProgramas.id.idCampana= "
						+ campanaId + " and a.tblCampanaProgramas.id.idPrograma= "+idPrograma);
		query.executeUpdate();
	}
	@Override
	public void deleteById(List<Long> idCampana, List<Integer> idPrograma) {
		String cids = Joiner.on(",").join(idCampana);
		String pids = Joiner.on(",").join(idPrograma);
		String deleteQuery = "delete from RelStoreCampana a where a.tblCampanaProgramas.id.idCampana in ("+cids+")"
				+ " and a.tblCampanaProgramas.id.idPrograma in ("+pids+")";
		Query query = this.getSession().createQuery(deleteQuery);
		int r = query.executeUpdate();
		System.out.println("Deleted RelStoreCampana by list: "+r);
	}
	
	@Override
	public void save(List<RelStoreCampana> list){
		String query = "";
		String schema = ((SessionFactoryImplementor)getHibernateTemplate().getSessionFactory()).getSettings().getDefaultSchemaName();
		List<String> queries = new ArrayList<String>();
		if(!list.isEmpty()){
			Integer index = 0;
			for(RelStoreCampana o: list){
				if(!query.isEmpty()){
					query+=" UNION ";
				}
				query+="SELECT "+o.getStoreId()+","+o.getTblCampanaProgramas().getId().getIdCampana()+","+o.getTblCampanaProgramas().getId().getIdPrograma()+" FROM  SYSIBM.SYSDUMMY1";
				index++;
				if(index>=999){
					queries.add("INSERT INTO "+schema+".REL_STORE_CAMPANA(STORE_ID, ID_CAMPANA, ID_PROGRAMA) "+query);
					query="";
					index=0;
				}
			}
			if(!query.isEmpty()){
				queries.add("INSERT INTO "+schema+".REL_STORE_CAMPANA(STORE_ID, ID_CAMPANA, ID_PROGRAMA) "+query);
			}
		}
		if(!queries.isEmpty()){
			for(String q: queries){
				int r = this.getSession().createSQLQuery(q).executeUpdate();
				System.out.println("Inserted RelStoreCampana: "+r);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RelStoreCampana> getByCampanaPrograma(long idCampana, int idPrograma) {
		return (List<RelStoreCampana>) getSession().createQuery("from RelStoreCampana where tblCampanaProgramas.tblCampana.idCampana=? and tblCampanaProgramas.programa.idPrograma=?")
			.setLong(0, idCampana)
			.setInteger(1, idPrograma)
			.list();
	}
}
