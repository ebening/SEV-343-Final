package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblArticulosEspacios;

public interface DAOArticulosEspacio extends AbstractDao<TblArticulosEspacios> {
	
	 List<TblArticulosEspacios> getArticulosByIdPrensa(int idPrensa)   throws Exception;

}
