/**
 * 
 */
package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblReporteInventario;


/**
 * @author OMAR
 *
 */
public class DAOReporteInventarioImpl extends AbstractDaoImpl<TblReporteInventario> implements DAOReporteInventario  {

	@Override
	public List<TblReporteInventario> getAllByIdsTienda(List<Integer> listIdTienda) throws GeneralException {
		int tamanioLista 						= listIdTienda.size()-1;
		StringBuilder query 					= new StringBuilder("from TblReporteInventario tri where tri.idSucursal in( ");
		
		for(int iterator=0;tamanioLista>iterator;iterator++){
			query.append(listIdTienda.get(iterator)+",");
		}

		query.append(listIdTienda.get(tamanioLista)+")");
		Iterator<TblReporteInventario> listaResultado = getHibernateTemplate().iterate(query.toString());
		return toInitializedList(listaResultado);
	}

	
	@Override
	public List<TblReporteInventario> getAllBySKU(List<String> listSku)throws GeneralException {
		int tamanioLista 						= listSku.size()-1;
		StringBuilder query 					= new StringBuilder("from TblReporteInventario tri where tri.codeArt in( ");
		
		for(int iterator=0;tamanioLista>iterator;iterator++){
			query.append(listSku.get(iterator)+",");
		}
	
		query.append(listSku.get(tamanioLista)+")");
		Iterator<TblReporteInventario> listaResultado = getHibernateTemplate().iterate(query.toString());
		return toInitializedList(listaResultado);
	}

}
