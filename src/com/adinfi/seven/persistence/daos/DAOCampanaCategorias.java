package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblCampanaCategorias;

public interface DAOCampanaCategorias extends AbstractDao<TblCampanaCategorias> {

	List<TblCampanaCategorias> getCategoriasByCampanaId(long idCampana);
}
