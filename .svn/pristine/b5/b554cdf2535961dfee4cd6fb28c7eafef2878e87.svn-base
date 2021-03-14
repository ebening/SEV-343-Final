package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblComentarioArticulo;

public class DAOComentarioArticuloImpl extends AbstractDaoImpl<TblComentarioArticulo> 
				implements DAOComentarioArticulo {
	private Logger LOG = Logger.getLogger(DAOComentarioArticuloImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblComentarioArticulo> getComentariosHojaSegmento(Object[] restricciones){
		Iterator<TblComentarioArticulo> coment = getHibernateTemplate()
				.iterate(
						" FROM TblComentarioArticulo tu WHERE tu.segmentId = ? and tu.idHoja = ? ORDER BY tu.fechaAlta desc ",
						restricciones.clone());
		return toInitializedList(coment);
	}
	
	@Override
	public TblComentarioArticulo saveComentarioArticulo(TblComentarioArticulo coment){
		try {
			saveOrUpdate(coment);
		} catch (Exception e) {
			LOG.error(e);
		}
		return coment;
	}
}
