package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblPreciosPromocion;
import com.adinfi.seven.business.domain.TblPreciosPromocionId;

public interface DAOTblPreciosPromocion extends AbstractDao<TblPreciosPromocion> {
	void deleteByMecanicaId(int mecanicaId);
	TblPreciosPromocion getPreciosPromocionById(TblPreciosPromocionId id);
	List<TblPreciosPromocion> getByMecanicaId(int mecanicaId, Integer estatusRevisionId, Integer estatusCapturaId);
	Double getAhorroMaximoByMecanicaId(int mecanicaId) throws Exception;
	public void save(List<TblPreciosPromocion> list);
}