package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.TblImageArticulo;
import com.adinfi.seven.persistence.daos.DAOImageArticulo;

public class ServiceArticlesImpl implements ServiceArticles {
	private DAOImageArticulo daoImageArticulo;
	
	 public DAOImageArticulo getDaoImageArticulo() {
		return daoImageArticulo;
	}

	public void setDaoImageArticulo(DAOImageArticulo daoImageArticulo) {
		this.daoImageArticulo = daoImageArticulo;
	}

	public List<TblImageArticulo>  getImagesByArt(String idArt) throws Exception{
		return this.daoImageArticulo.getImagesByArt(idArt); 
	 }
	
	public TblImageArticulo getImageArticulo(int idImageArt) throws Exception{
		TblImageArticulo imgArt = daoImageArticulo.getImageArticulo(idImageArt);
		return imgArt;
	}
	
	public TblImageArticulo getImageArtByImgId(int idImg) throws Exception{
		TblImageArticulo imgArt = daoImageArticulo.getImageArtByImgId(idImg);
		return imgArt;
	}
	

}
