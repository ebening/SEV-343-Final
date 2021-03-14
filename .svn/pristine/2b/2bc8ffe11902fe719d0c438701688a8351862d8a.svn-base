package com.adinfi.seven.persistence.daos;

import java.util.Date;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblDelegacionActividades;

public interface DAODelegacionActividades extends
		AbstractDao<TblDelegacionActividades> {
	List<TblDelegacionActividades> getDelegateActivity(String idUsuario);

	List<TblDelegacionActividades> getDelegateActivity(String idUsuarioDelega,
			String idUsuarioDelegado, Date dateInit, Date dateEnd)
			throws GeneralException;
}
