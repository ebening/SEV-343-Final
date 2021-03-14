package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.RelZonaCampana;
import com.adinfi.seven.business.domain.TblCampanaProgramas;

public interface ServiceCatPrograma {
	
	public boolean crearCatPrograma(CatPrograma catPrograma);
	
	public boolean eliminarCatPrograma(CatPrograma catPrograma);
	
	public List<CatPrograma> getCatProgramas() throws Exception;
	
	public CatPrograma getCatPrograma(CatPrograma catPrograma);
        
	public CatPrograma getCatPrograma(int id);
	
	public List<CatPrograma> getCatProgramaByMecanica(int idmecanica);

	public List<RelZonaCampana> getRelZonaCampanaByProg(long idcampana, int idprograma);

	public TblCampanaProgramas getTblCampanaProgramasById(long idCampana, int idPrograma);
	
	public List<CatPrograma> getCatPrograma(List<Integer> ids);

}
