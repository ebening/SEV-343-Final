package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblComentarioArticulo;

public interface DAOComentarioArticulo extends AbstractDao<TblComentarioArticulo>{

	List<TblComentarioArticulo> getComentariosHojaSegmento(
			Object[] restricciones);

	TblComentarioArticulo saveComentarioArticulo(TblComentarioArticulo coment);
	
}
