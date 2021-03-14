package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblFolletoTienda;

public interface DAOFolletoTienda extends AbstractDao<TblFolletoTienda> {
	
 List<TblFolletoTienda> getTiendasIdByFolletoId(int idFolleto) throws Exception;

void delete(int idFolleto) throws Exception;

}
