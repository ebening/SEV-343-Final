package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblArchivoArticulo;

public class DAOArchivoArticuloImpl extends AbstractDaoImpl<TblArchivoArticulo> 
				implements DAOArchivoArticulo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3112875150170917251L;
	private Logger LOG = Logger.getLogger(DAOArchivoArticuloImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblArchivoArticulo> getArchivoArticulo(Object[] restricciones){
		Iterator<TblArchivoArticulo> coment = getHibernateTemplate()
				.iterate(
						" FROM TblArchivoArticulo tu WHERE tu.segmentId = ? and tu.idHoja = ? ORDER BY tu.fechaAlta desc ",
						restricciones.clone());
		return toInitializedList(coment);
	}
	
	@Override
	public TblArchivoArticulo saveArchivoArticulo(TblArchivoArticulo archivo){
		try {
			saveOrUpdate(archivo);
		} catch (Exception e) {
			LOG.error(e);
		}
		return archivo;
	}
}
