/**
 * 
 */
package com.adinfi.seven.business.services;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblDisenoPromoCm;
import com.adinfi.seven.business.domain.TblFolletoHoja;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.persistence.daos.DAOArticulosHoja;
import com.adinfi.seven.persistence.daos.DAODisenoPromoCm;
import com.adinfi.seven.persistence.daos.DAOFolletoHojas;

/**
 * @author OMAR
 *
 */
public class ServiceEjecucionImpl implements ServiceEjecucion {
	
	private DAOArticulosHoja  daoArticulosHoja;
	private DAODisenoPromoCm daoDisenoPromoCm;
	private DAOFolletoHojas  daoFolletoHojas;
	private Logger LOG = Logger.getLogger(ServiceEjecucionImpl.class);
	
	@Override
	public List<TblArticulosHoja> getArticulosByHojaId(int idHoja) {
		// TODO Auto-generated method stub
		
		return daoArticulosHoja.getArticulosByHoja(idHoja);
	}
	
	
	@Override
	public TblFolletoHojas getHojaById(int idHoja) {
		// TODO Auto-generated method stub
		try{
		  return daoFolletoHojas.getById(idHoja);
		  
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	
	
	
	@Override
	public void saveTblFolletoHojas(TblFolletoHojas hoja) {
		try {
			daoFolletoHojas.saveFolletoHojas(hoja);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public TblDisenoPromoCm getDiseno(long idCampana, int idPrograma) {
		return daoDisenoPromoCm.getDiseno(idCampana, idPrograma);
	}
	
	@Override
	public TblDisenoPromoCm saveDiseno(TblDisenoPromoCm tblDisenoPromoCm) throws GeneralException {
		LOG.info("saveCampana " + tblDisenoPromoCm.getIdCampana());
		try {
			daoDisenoPromoCm.saveOrUpdate(tblDisenoPromoCm);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
		return tblDisenoPromoCm;
	}
	

	/**
	 * @return the daoArticulosHoja
	 */
	public DAOArticulosHoja getDaoArticulosHoja() {
		return daoArticulosHoja;
	}

	/**
	 * @param daoArticulosHoja the daoArticulosHoja to set
	 */
	public void setDaoArticulosHoja(DAOArticulosHoja daoArticulosHoja) {
		this.daoArticulosHoja = daoArticulosHoja;
	}
	
	public DAODisenoPromoCm getDaoDisenoPromoCm() {
		return daoDisenoPromoCm;
	}

	public void setDaoDisenoPromoCm(DAODisenoPromoCm daoDisenoPromoCm) {
		this.daoDisenoPromoCm = daoDisenoPromoCm;
	}

	public DAOFolletoHojas getDaoFolletoHojas() {
		return daoFolletoHojas;
	}

	public void setDaoFolletoHojas(DAOFolletoHojas daoFolletoHojas) {
		this.daoFolletoHojas = daoFolletoHojas;
	}
	
	
}
