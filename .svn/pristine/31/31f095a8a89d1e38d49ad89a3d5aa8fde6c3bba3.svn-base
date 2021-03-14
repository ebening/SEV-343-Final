package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblCampanaCategoriasPlaza;

public class DAOCampanaCategoriasPlazaImpl extends AbstractDaoImpl<TblCampanaCategoriasPlaza> implements DAOCampanaCategoriasPlaza{

	@Override
	public List<TblCampanaCategoriasPlaza> getPlazasByCampanaIdAndCategory(long idCampana, int idCategoria) {
		@SuppressWarnings("unchecked")
		Iterator<TblCampanaCategoriasPlaza> tblCamCatPlazaIterator = getHibernateTemplate()
				.iterate(
						"from TblCampanaCategoriasPlaza TCCP  where TCCP.tblCampanaCategorias.id.idCampana = ? and TCCP.tblCampanaCategorias.id.idCategoria = ?",
						new Object[] { idCampana, idCategoria });
		return toInitializedList(tblCamCatPlazaIterator);
	}
	
	@Override
	public void removePlazaByCampanaIdAndCategory(long idCampana, int idCategoria){
		Query query = this
				.getSession()
				.createQuery(
						"delete from TblCampanaCategoriasPlaza TCCP  where TCCP.tblCampanaCategorias.id.idCampana = " + idCampana + " and TCCP.tblCampanaCategorias.id.idCategoria = "
								+ idCategoria);
		query.executeUpdate();
	}
}
