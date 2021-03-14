package com.adinfi.seven.persistence.daos;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaMedio;
import com.adinfi.seven.persistence.dto.CampanaResultDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;

public interface DAOCampana extends AbstractDao<TblCampana> {
	public boolean deleteAllCampanaProgramas (long idCampana);
	public TblCampana getCampana(long idCampana, Map<String, String> filters);
	List<TblCampanaMedio> getMedioByCampanaId(long idCampana);
	public CampanaResultDTO getCampanas(Integer firstRecord, Integer rowsPerPage, Map<String, String> filters);
	List<TblCampana> getAllCampanasByYearAsc(int year, List<PeriodoDTO> periodos);
	List<TblCampana> getListCampanaDTOByYearAndTypeCampana(int year,int typeCampana, List<PeriodoDTO> periodos);
	List<TblCampana> getAllCampanaByTypeCampana(int typeCampana);
	List<TblCampana> getCampanaByName(String name);
	public List<TblCampana> getAllCampanaByDashboardFilters(String periodo, int tipo, int estatus, Date fechaInicio, Date fechaFin, int idPeriodo, int ano);
	public Boolean campanaExists(Integer year, Integer tipoCampana, String nombre);
}