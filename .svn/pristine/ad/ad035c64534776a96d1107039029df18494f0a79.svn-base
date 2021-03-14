package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.RelUsuariosCategorias;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.persistence.dto.UsuarioDTO;

public interface DAOCampanaProgramasCategorias extends AbstractDao<TblCampanaProgramasCategorias> {
	List<TblCampanaProgramasCategorias> getCategoriasByCampanaIdAndPrograma(long idCampana, int idPrograma);
	List<TblCampanaProgramasCategorias> getProgramasListByUser(UsuarioDTO usuario);
	List<TblCampanaProgramasCategorias> getProgramasListByUser(CatUsuarios usuario, List<RelUsuariosCategorias> usuariosCategorias);
	public void saveCampanaProgramasCategorias(List<TblCampanaProgramasCategorias> list);
	public List<Integer> getCategoriasIdsByCampanaIdAndPrograma(long idCampana, int idPrograma);
}