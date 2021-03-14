package com.adinfi.seven.persistence.daos;

import java.util.Iterator;
import java.util.List;

import com.adinfi.seven.business.domain.TblImageArticulo;

public class DAOImageArticuloImpl extends AbstractDaoImpl<TblImageArticulo>
		implements DAOImageArticulo {
	@SuppressWarnings("unchecked")
	@Override
	public TblImageArticulo getImageArticulo(int idImageArt) {
		Iterator<TblImageArticulo> tblCampanaIterator = getHibernateTemplate()
				.iterate(
						"from TblImageArticulo obj where obj.idImageArt = ?",
						new Object[] { idImageArt });
		return toInitializedInstance(tblCampanaIterator);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<TblImageArticulo> getImagesByArt(String idArt){
		
		Iterator<TblImageArticulo> catRegValuesList = getHibernateTemplate()
				.iterate(
						"from TblImageArticulo ia where ia.idArticulo=? ",
						idArt);
		return toInitializedList(catRegValuesList);
		
	}
	
	 
	
	
	@SuppressWarnings("unchecked")
	@Override
	public TblImageArticulo getImageArtByImgId(int idImg) throws Exception{
		Iterator<TblImageArticulo> tblCampanaIterator = getHibernateTemplate()
				.iterate(
						"from TblImageArticulo obj where obj.tblImagenes.idImage=? ",
						new Object[] { (long)idImg });
		return toInitializedInstance(tblCampanaIterator);
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblImageArticulo> buscaSKUById(String idArticulo) {
		Iterator<TblImageArticulo> tblImageIterator = getHibernateTemplate()
				.iterate("from TblImageArticulo obj where obj.idArticulo = ?",
						new Object[] { idArticulo });
		return toInitializedList(tblImageIterator);
	}
	
	
	
}
