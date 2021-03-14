/**
 * 
 */
package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblListaSeleccion;

/**
 * @author OMAR
 *
 */
public interface DAOListaSeleccion extends AbstractDao<TblListaSeleccion>{

	List<TblListaSeleccion> getListaByUserId(int userId) throws Exception;
	TblListaSeleccion getListaByUserAndName(int userId , String name)throws Exception;
	TblListaSeleccion getListaById(int idLista )throws Exception;
	int saveListaSeleccion (TblListaSeleccion tblListaSeleccion)throws Exception;
	int deleteListaSeleccion (int idLista)throws Exception;
	void updateFechaModificacion(int idLista) throws Exception;
}
