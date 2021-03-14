/**
 * 
 */
package com.adinfi.seven.persistence.daos;



import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblListaSeleccionSku;

/**
 * @author OMAR
 *
 */
public class DAOListaSeleccionSKUImpl extends AbstractDaoImpl<TblListaSeleccionSku> implements DAOListaSeleccionSKU {

	@Override
	public void saveListaSeleccionSKU(List<TblListaSeleccionSku> listaItems)
			throws Exception {
		
		for(TblListaSeleccionSku item : listaItems){
			getHibernateTemplate().save(item);
		}
		
		
	}

	
	@Override
	public List<TblListaSeleccionSku> getListaSeleccionByIdLista(Integer idLista)
			throws Exception {
		Iterator<TblListaSeleccionSku> itListaSeleccion = null ;
		itListaSeleccion =	getHibernateTemplate().iterate(" FROM  TblListaSeleccionSku tlsSKU WHERE tlsSKU.idLista = ? ORDER BY id  ASC ", new Object[] {idLista});
		return toInitializedList(itListaSeleccion);
	}


	@Override
	public int deleteListaSeleccionByIdLista(Integer idLista) throws Exception {
		
		Query query = this.getSession().createQuery("delete from TblListaSeleccionSku tlsSKU where tlsSKU.idLista =  :idLista");
		query.setParameter("idLista", idLista);
		int deleted = query.executeUpdate();
		
		
		return deleted;
		
	}

	@Override
	public void deleteSeleccionSKUByIds(Integer idLista, String idItem)
			throws Exception {
		
		Query query = this.getSession().createQuery("delete from TblListaSeleccionSku tlsSKU where tlsSKU.idLista = :idLista  and tlsSKU.idItem =:idItem");
		query.setParameter("idLista", idLista);
		query.setParameter("idItem", idItem);
		query.executeUpdate();
		
	}


	

}
