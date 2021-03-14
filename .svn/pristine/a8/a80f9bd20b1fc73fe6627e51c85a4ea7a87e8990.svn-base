package com.adinfi.seven.persistence.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblMarcaLogo;

public class DAOArqMarcaLogoImp extends AbstractDaoImpl<TblMarcaLogo> implements
		DAOArqMarcaLogo {
	private Logger LOG = Logger.getLogger(DAOArqMarcaLogoImp.class);

	@Override
	public Map<String, String> getMarcas() throws GeneralException {
		Map<String, String> retValue = new HashMap<String, String>();
		try {
			List<TblMarcaLogo> items = getAll();
			for (TblMarcaLogo item : items) {
				retValue.put(item.getNombreMarca(),
						String.valueOf(item.getIdMarca()));
			}
		} catch (Exception e) {
			retValue = null;
			LOG.error(e);
		}
		return retValue;
	}

	@Override
	public TblMarcaLogo saveMarcas(TblMarcaLogo marca) throws GeneralException {
		try {
			saveOrUpdate(marca);
		} catch (Exception e) {
			LOG.error(e);
		}
		return marca;
	}

	@Override
	public TblMarcaLogo getMarcaLogo(int IdMarcaLogo) throws Exception {
		return getById(IdMarcaLogo);
	}
}
