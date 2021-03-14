package com.adinfi.seven.persistence.daos;

import java.util.Map;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblMarcaLogo;

public interface DAOArqMarcaLogo extends AbstractDao<TblMarcaLogo> {

	Map<String, String> getMarcas() throws GeneralException;

	TblMarcaLogo saveMarcas(TblMarcaLogo marca) throws GeneralException;

	TblMarcaLogo getMarcaLogo(int IdMarcaLogo) throws Exception;

}
