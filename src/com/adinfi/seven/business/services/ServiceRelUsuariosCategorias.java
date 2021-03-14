package com.adinfi.seven.business.services;
import java.util.List;

import com.adinfi.seven.business.domain.RelUsuariosCategorias;


public interface ServiceRelUsuariosCategorias {

	List<RelUsuariosCategorias> listaRelUsuariosCategorias() throws Exception; 
	
	boolean crearRelUsuarioCategoria(List<RelUsuariosCategorias> usuariosCategorias);
	
	boolean borrarRelUsuarioCategoria(Integer idUsuario);
	
	List<RelUsuariosCategorias> listarRelUsuariosCategoriasByUsuario(Integer idUsr) throws Exception;
		
	
}
