package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblCampanaCategorias;

public class DAOCampanaCategoriasImpl extends AbstractDaoImpl<TblCampanaCategorias>
		implements DAOCampanaCategorias {

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampanaCategorias> getCategoriasByCampanaId(long idCampana) {
		Iterator<TblCampanaCategorias> tblCampanaIterator = getHibernateTemplate()
				.iterate(
						"from TblCampanaCategorias TblCampanaCategorias  where TblCampanaCategorias.tblCampana.idCampana = ? ",
						new Object[] { idCampana });
		return toInitializedList(tblCampanaIterator);
	}
	
	

}
