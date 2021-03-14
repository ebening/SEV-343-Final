package com.adinfi.seven.persistence.daos;

import java.util.List;
import com.adinfi.seven.business.domain.TblFolletoZona;

public interface DAOFolletoZona extends AbstractDao<TblFolletoZona> {
	List<TblFolletoZona> getZonasById(int idFolleto) throws Exception;
	void delete(int idFolleto) throws Exception;
}
