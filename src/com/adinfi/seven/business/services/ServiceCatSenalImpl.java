package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.persistence.daos.DAOCatSenal;

public class ServiceCatSenalImpl implements ServiceCatSenal{
	
	private DAOCatSenal daoCatSenal;
	private Logger LOG = Logger.getLogger(ServiceCatSenalImpl.class);


	@Override
	public boolean crearCatSenal(CatSenal catSenal) {
		try {
			 daoCatSenal.saveOrUpdate(catSenal);
			 return true;
		} catch (Exception e) {
			LOG.error(e);
			return false;
		}
	}

	@Override
	public boolean eliminarCatSenal(CatSenal catSenal) {
		try {
			daoCatSenal.delete(catSenal);
			return true;
		} catch (Exception e) {
            e.printStackTrace();
			LOG.error(e);
			return false;
		}
	}

	@Override
	public List<CatSenal> getCatSenales() throws Exception {
		return daoCatSenal.getAll();
        
	}
	
	@Override
	public List<CatSenal> getCatSenalesForCombo() throws Exception {
		return daoCatSenal.getCatSenales();
        
	}

	@Override
	public CatSenal getCatSenal(int id) {
		try {
			//return daoCatSenal.getById(id);
			return daoCatSenal.getCatSenal(id);
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}

	public DAOCatSenal getDaoCatSenal() {
		return daoCatSenal;
	}

	public void setDaoCatSenal(DAOCatSenal daoCatSenal) {
		this.daoCatSenal = daoCatSenal;
	}

	
	
	
	
}
