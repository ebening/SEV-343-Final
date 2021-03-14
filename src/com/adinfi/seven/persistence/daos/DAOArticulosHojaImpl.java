package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblArticulosHoja;

public class DAOArticulosHojaImpl extends AbstractDaoImpl<TblArticulosHoja>
		implements DAOArticulosHoja {
	private Logger LOG = Logger.getLogger(DAOArticulosHojaImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<TblArticulosHoja> getArticulosByIdFolleto(int idFolleto)
			throws Exception {
		Iterator<TblArticulosHoja> itListaArticulos = null ;
		itListaArticulos =	getHibernateTemplate().iterate(" FROM  TblArticulosHoja tah WHERE tah.idFolleto = ?", new Object[] {idFolleto});
		
		return toInitializedList(itListaArticulos);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<TblArticulosHoja> getArticulosByHoja(int idHoja) {
		Iterator<TblArticulosHoja> itListaArticulos = null ;
		itListaArticulos =	getHibernateTemplate().iterate(" FROM  TblArticulosHoja tah WHERE tah.hoja.idHoja = ? ORDER BY segmentName ", new Object[] {Integer.valueOf(idHoja)});
		
		return toInitializedList(itListaArticulos);
	}
	
	@Override
	public TblArticulosHoja saveArticulosHoja(TblArticulosHoja articulosHoja){
		try{
			saveOrUpdate(articulosHoja);
		}catch(Exception e){
			LOG.error(e);
		}
		return articulosHoja;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblArticulosHoja> getArticulosPrincipales(int idHoja){
		Iterator<TblArticulosHoja> itListaArticulos = null ;
		itListaArticulos =	getHibernateTemplate().iterate(" FROM  TblArticulosHoja tah WHERE tah.hoja.idHoja = ? and principal = 'Y' ORDER BY segmentName ", new Object[] {Integer.valueOf(idHoja)});
		
		return toInitializedList(itListaArticulos);
	}
	
	@Override
	public boolean deleteArticulosHoja(TblArticulosHoja articulosHoja){
		boolean retVal = true;
		try{
			delete(articulosHoja);
		}catch(Exception e){
			LOG.error(e);
			retVal = false;
		}
		
		return retVal;
	}
}
