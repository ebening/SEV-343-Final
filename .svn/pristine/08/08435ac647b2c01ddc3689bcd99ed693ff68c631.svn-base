package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblCampanaProgramasPlazas;

public class DAOCampanaProgramasPlazasImpl extends AbstractDaoImpl<TblCampanaProgramasPlazas> implements DAOCampanaProgramasPlazas{
	@Override
	public List<TblCampanaProgramasPlazas> getPlazasByCampanaIdAndPrograma(long idCampana, int idPrograma) {
		@SuppressWarnings("unchecked")
		Iterator<TblCampanaProgramasPlazas> tblCamProgPlazaIterator = getHibernateTemplate()
				.iterate(
						"from TblCampanaProgramasPlazas TCPP  where TCPP.tblCampanaProgramas.id.idCampana = ? and TCPP.tblCampanaProgramas.id.idPrograma = ?",
						new Object[] { idCampana, idPrograma });
		return toInitializedList(tblCamProgPlazaIterator);
	}
}
