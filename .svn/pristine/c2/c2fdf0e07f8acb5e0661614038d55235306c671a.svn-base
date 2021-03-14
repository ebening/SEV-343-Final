package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.business.domain.RelStoreDiseno;
import com.adinfi.seven.business.domain.RelZonaDiseno;
import com.adinfi.seven.business.domain.TblDisenos;

public interface DAOTblDisenos extends AbstractDao<TblDisenos> {
	List<TblDisenos> getDisenos(Integer programaId, Integer mecanicaId, List<String> grupoZonaId,
								List<String> zonaId, List<CatSenal> senals, List<String> storeId, Integer estatusPrecio,
								Integer estatusDiseno) throws Exception;
	
	List<TblDisenos> getDiseno(Integer mecanicaID) throws Exception;
	
	boolean updateStatusDisenoByMecanica(Integer idMecanica, Integer idStatus) throws Exception;

	boolean deleteByMecanicaId(int idmecanica);
	public List<RelStoreDiseno> getStores(List<Integer> disenoIds);
	public List<RelZonaDiseno> getZonas(List<Integer> disenoIds);
	
}
