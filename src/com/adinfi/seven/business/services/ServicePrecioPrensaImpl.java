package com.adinfi.seven.business.services;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblPreciosPrensa;
import com.adinfi.seven.business.domain.TblPrensa;
import com.adinfi.seven.persistence.daos.DAOPrecioPrensa;
import com.adinfi.seven.persistence.daos.DAOPrecioPrensaDet;
import com.adinfi.seven.persistence.daos.DAOPrensa;

public class ServicePrecioPrensaImpl implements ServicePrecioPrensa {

	private Logger LOG = Logger.getLogger(ServicePrecioFolletoImpl.class);
	private DAOPrensa daoPrensa;
	private DAOPrecioPrensa daoPrecioPrensa;
	private DAOPrecioPrensaDet daoPrecioPrensaDet;
	
	@Override
	public TblPrensa getPrensa(int IdPrensa){
		TblPrensa retVal = null;
		try {
			retVal = daoPrensa.getById(IdPrensa);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return retVal;
	}
	
	@Override
	public boolean guardarPrecioPrensa(TblPreciosPrensa precioPrensa){
		boolean retVal = true;
		try {
			daoPrecioPrensa.saveOrUpdate(precioPrensa);
			retVal=true;
		} catch (Exception e) {
			LOG.error(e);
			retVal = false;
		}
		
		return retVal;
	}

	public DAOPrecioPrensa getDaoPrecioPrensa() {
		return daoPrecioPrensa;
	}

	public void setDaoPrecioPrensa(DAOPrecioPrensa daoPrecioPrensa) {
		this.daoPrecioPrensa = daoPrecioPrensa;
	}

	public DAOPrecioPrensaDet getDaoPrecioPrensaDet() {
		return daoPrecioPrensaDet;
	}

	public void setDaoPrecioPrensaDet(DAOPrecioPrensaDet daoPrecioPrensaDet) {
		this.daoPrecioPrensaDet = daoPrecioPrensaDet;
	}

	public DAOPrensa getDaoPrensa() {
		return daoPrensa;
	}

	public void setDaoPrensa(DAOPrensa daoPrensa) {
		this.daoPrensa = daoPrensa;
	}

}
