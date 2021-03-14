package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.TblImageArticulo;
 
 

public interface ServiceArticles {
	 List<TblImageArticulo>  getImagesByArt(String idArt) throws Exception; 
	 TblImageArticulo getImageArticulo(int idImageArt) throws Exception;
	 TblImageArticulo getImageArtByImgId(int idImg) throws Exception;
		
}
