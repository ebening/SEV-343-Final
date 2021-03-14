/**
 * 
 */
package com.adinfi.seven.persistence.daos;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.adinfi.seven.business.domain.TblListaSeleccion;

/**
 * @author OMAR
 *
 */
public class DAOListaSeleccionImpl extends AbstractDaoImpl<TblListaSeleccion> implements DAOListaSeleccion {

	
	@Override
	public List<TblListaSeleccion> getListaByUserId(int userId)
			throws Exception {
		
		Iterator<TblListaSeleccion> itListaSeleccion = null ;
		itListaSeleccion =	getHibernateTemplate().iterate(" FROM  TblListaSeleccion tblListaSelec WHERE tblListaSelec.idUsr = ? ORDER BY NOMBRE  ASC ", new Object[] {userId});
		return toInitializedList(itListaSeleccion);
	}

	@Override
	public TblListaSeleccion getListaByUserAndName(int userId, String name)
			throws Exception {
		Iterator<TblListaSeleccion> itListaSeleccion 	= null ;
		List<TblListaSeleccion> resultList 				= null;
		TblListaSeleccion result 						= null;
		
		itListaSeleccion =	getHibernateTemplate().iterate(" FROM  TblListaSeleccion tblListaSelec WHERE tblListaSelec.idUsr = ? and tblListaSelec.nombre = ? ", new Object[] {userId , name});
		resultList = toInitializedList(itListaSeleccion);
		
		if(resultList.size() >0){
			result = resultList.get(0);
		}
		
		return result;
	}

	@Override
	public int saveListaSeleccion(TblListaSeleccion tblListaSeleccion)
			throws Exception {
		
		int idLista = 0;
		
		tblListaSeleccion.setFechaCreacion( new Date() );
		tblListaSeleccion.setFechaModificacion(new Date() );
		getHibernateTemplate().save(tblListaSeleccion);
		
		idLista = tblListaSeleccion.getIdLista();
		
		return idLista;
		
	}

	@Override
	public TblListaSeleccion getListaById(int idLista) throws Exception {
		
		Iterator<TblListaSeleccion> itListaSeleccion 	= null;
		List<TblListaSeleccion> resultList 				= null;
		TblListaSeleccion result 						= null;
		
		itListaSeleccion =	getHibernateTemplate().iterate(" FROM  TblListaSeleccion tblListaSelec WHERE tblListaSelec.idLista = ? ", new Object[] {idLista});
		resultList = toInitializedList(itListaSeleccion);
		
		if(resultList.size() >0){
			result = resultList.get(0);
		}
		
		return result;
	}

	@Override
	public int deleteListaSeleccion(int idLista) throws Exception {
		
		Query query = this.getSession().createQuery("delete from TblListaSeleccion tls where tls.idLista = :idLista");
		query.setParameter("idLista", idLista);
		int result = query.executeUpdate();
		
		return result;
	}
	
	
	

	@Override
	public void updateFechaModificacion(int idLista) throws Exception {
		
		Query query = this.getSession().createQuery("UPDATE  TblListaSeleccion tls set tls.fechaModificacion = :fecha  where tls.idLista = :idLista");
		query.setParameter("fecha", new Date());
		query.setParameter("idLista", idLista);
		query.executeUpdate();
		
	}

	
	
	
}
