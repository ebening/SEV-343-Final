package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.RelZonaCampana;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.persistence.daos.DAOCatPrograma;
import com.adinfi.seven.persistence.daos.DAORelZonaCampana;

public class ServiceCatProgramaImpl implements ServiceCatPrograma {
	
	private Logger LOG = Logger.getLogger(ServiceCatProgramaImpl.class);
	private DAOCatPrograma daoCatPrograma;
	private DAORelZonaCampana daoRelZonaCampana;

	@Override
	public TblCampanaProgramas getTblCampanaProgramasById(long idCampana, int idPrograma) {
		return daoCatPrograma.getTblCampanaProgramasById(idCampana, idPrograma);
	}

	@Override
	public List<RelZonaCampana> getRelZonaCampanaByProg(long idcampana, int idprograma) {
		return daoRelZonaCampana.getRelZonaCamapana(idcampana, idprograma);
	}

	@Override
	public boolean crearCatPrograma(CatPrograma catPrograma) {
		try {
			daoCatPrograma.saveOrUpdate(catPrograma);
			return true;
		} catch (Exception e) {
			LOG.error(e);
			return false;
		}
	}

	@Override
	public boolean eliminarCatPrograma(CatPrograma catPrograma) {
		try {
			daoCatPrograma.delete(catPrograma);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatPrograma> getCatProgramas() throws Exception {
		return daoCatPrograma.getCatProgramas();
	}

	@Override
	public CatPrograma getCatPrograma(CatPrograma catPrograma) {
		return daoCatPrograma.getCatPrograma(catPrograma.getIdPrograma());
	}

	public DAOCatPrograma getDaoCatPrograma() {
		return daoCatPrograma;
	}

	public void setDaoCatPrograma(DAOCatPrograma daoCatPrograma) {
		this.daoCatPrograma = daoCatPrograma;
	}

    @Override
    public CatPrograma getCatPrograma(int id) {
        return daoCatPrograma.getCatPrograma(id);
    }

    @Override
    public List<CatPrograma> getCatPrograma(List<Integer> ids) {
        return daoCatPrograma.getCatPrograma(ids);
    }

	@Override
	public List<CatPrograma> getCatProgramaByMecanica(int idmecanica) {
		return null;
	}

	public DAORelZonaCampana getDaoRelZonaCampana() {
		return daoRelZonaCampana;
	}

	public void setDaoRelZonaCampana(DAORelZonaCampana daoRelZonaCampana) {
		this.daoRelZonaCampana = daoRelZonaCampana;
	}
}
