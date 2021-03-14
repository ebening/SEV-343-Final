package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblSolicitudAutorizacion;

public class DAOSolicitudAutorizacionImpl extends AbstractDaoImpl<TblSolicitudAutorizacion> implements DAOSolicitudAutorizacion {

	@SuppressWarnings("unchecked")
	@Override	
	public List<TblSolicitudAutorizacion> getAllCampanasAutorizacionByEstatus(int idEstatus) throws Exception {		
			Iterator<TblSolicitudAutorizacion> tblSolicitudAutorizacionList = getHibernateTemplate().iterate
					("from TBL_SOLICITUD_AUTORIZACION sol where " +
							" sol.campanasautorizacion.id_estatus_autorizacion = ?",idEstatus);
			return toInitializedList(tblSolicitudAutorizacionList);
	}
	

	@SuppressWarnings("unchecked")
	@Override	
	public List<TblSolicitudAutorizacion> getAllCampanasAutorizacionByUser(Integer idUsuario) throws Exception {		
			Iterator<Object> tblSolicitudAutorizacionList = (Iterator<Object>) getHibernateTemplate().iterate
					("from TblSolicitudAutorizacion as sol, " + 
					" TblCampanaAutorizacion aut where sol.idAutorizacion = aut.id.idAutorizacion " +
					"and aut.idEstatusAutorizacion != 1 " +
					"and  aut.idUsuario = ?",idUsuario);
		
			List<TblSolicitudAutorizacion> list = new ArrayList<TblSolicitudAutorizacion>();
			
		while ( tblSolicitudAutorizacionList.hasNext() ) {
			Object[] tuple = (Object[]) tblSolicitudAutorizacionList.next();
		   TblSolicitudAutorizacion tblSolicitudAutorizacion = (TblSolicitudAutorizacion) tuple[0];
		    
		    list.add(tblSolicitudAutorizacion); 
		    
		}

	Iterator<TblSolicitudAutorizacion> itr = list.iterator();
				
				return toInitializedList(itr);
	}
	
		
	@Override
	public List<TblSolicitudAutorizacion> getSolicitudAutorizacionById(long idAutorizacion) throws Exception {		
		Iterator tblSolicitudAutorizacionList = getHibernateTemplate().iterate
				("from TblSolicitudAutorizacion as sol, " + 
				" TblCampanaAutorizacion aut where sol.idAutorizacion = aut.id.idAutorizacion and  sol.idAutorizacion = ?",idAutorizacion);
	
		List<TblSolicitudAutorizacion> list = new ArrayList<TblSolicitudAutorizacion>();
		
	while ( tblSolicitudAutorizacionList.hasNext() ) {
		Object[] tuple = (Object[]) tblSolicitudAutorizacionList.next();
	   TblSolicitudAutorizacion tblSolicitudAutorizacion = (TblSolicitudAutorizacion) tuple[0];
	    
	    list.add(tblSolicitudAutorizacion); 
	    
	}

		Iterator<TblSolicitudAutorizacion> itr = list.iterator();
			
			return toInitializedList(itr);
	}
	
}