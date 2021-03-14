/**
 * 
 */
package com.adinfi.seven.persistence.daos;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblExistenciaItemTienda;

/**
 * @author OMAR
 *
 */
public class DAOExistenciaItemTiendaImpl extends AbstractDaoImpl<TblExistenciaItemTienda> implements DAOExistenciaItemTienda {

	

	@Override
	public List<TblExistenciaItemTienda> getExistenciaItemByTiendaId(Integer idTienda) throws Exception {
		Iterator<TblExistenciaItemTienda> itListaExistenciaItemTienda = null ;
		itListaExistenciaItemTienda =	getHibernateTemplate().iterate(" FROM TblExistenciaItemTienda teit WHERE teit.id.idTienda= ?  ", new Object[] { idTienda });
		return toInitializedList(itListaExistenciaItemTienda);
	}

	
	@Override
	public BigDecimal getSumaExistenciaByTiendaId(String idItem, int idTienda)
			throws Exception {
		String query = "SELECT SUM(teit.existencia)  FROM TblExistenciaItemTienda teit WHERE teit.id.idItem = ? AND teit.id.idTienda = ? ";
		
		Iterator<BigDecimal> sumaExistencia = getHibernateTemplate().iterate(query, new Object[] { idItem,idTienda });
		List<BigDecimal> listaResult= toInitializedList(sumaExistencia);
		
		if(listaResult.size()>0){
			return listaResult.get(0);
		}else{
		return BigDecimal.ZERO;
		}
	}

}
