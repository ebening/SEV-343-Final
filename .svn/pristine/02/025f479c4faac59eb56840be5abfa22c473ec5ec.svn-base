package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampanaDetalle;

public interface DAOCampanaDetalles extends AbstractDao<TblCampanaDetalle> {
	TblCampanaDetalle getCampDetByIdCampana(long idCampana);

	int getIdCategoriaByIdCampana(long idCampana);

	List<TblCampanaDetalle> getCampanaDetallesListByidCampana(long idCampana)
			throws GeneralException;
}
