package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblPreciosFolleto;
import com.adinfi.seven.business.domain.TblPreciosFolletoDet;
import com.adinfi.seven.persistence.daos.DAOFolleto;
import com.adinfi.seven.persistence.daos.DAOPrecioFolleto;
import com.adinfi.seven.persistence.daos.DAOPreciosFolletoDet;

public class ServicePrecioFolletoImpl implements ServicePrecioFolleto {

	private Logger LOG = Logger.getLogger(ServicePrecioFolletoImpl.class);
	private DAOFolleto daoFolleto;
	private DAOPrecioFolleto daoPrecioFolleto;
	private DAOPreciosFolletoDet daoPrecioFolletoDet;
	
	@Override
	public TblFolleto getFolleto(int IdFolleto){
		TblFolleto retVal = null;
		try {
			retVal = daoFolleto.getById(IdFolleto);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return retVal;
	}
	
	@Override
	public boolean guardarPrecioFolleto(TblPreciosFolleto precioFolleto){
		boolean retVal = true;
		try {
			daoPrecioFolleto.saveOrUpdate(precioFolleto);
		} catch (Exception e) {
			LOG.error(e);
			retVal=false;
		}
		
		return retVal;
	}
	
	@Override
	public List<TblPreciosFolleto> getPreciosFolletoByFolleto(int idFolleto){
		return daoPrecioFolleto.getPreciosFolletoByFolleto(idFolleto);
	}
	
	@Override 
	public boolean deletePreciosFolleto(TblPreciosFolleto precioFolleto){
		boolean retVal=true;
		try{
			daoPrecioFolleto.delete(precioFolleto);
		}catch(Exception e){
			LOG.error(e);
			retVal=false;
		}
		return retVal;
	}
	
	@Override
	public List<TblPreciosFolletoDet> getPreciosArticuloHoja(int idSistemaVenta, int idFolleto) {
		List<TblPreciosFolletoDet> retVal=null;
		try{
			retVal = daoPrecioFolletoDet.getPreciosArticuloHoja(idSistemaVenta, idFolleto);
		}catch(Exception e){
			LOG.error(e);
			retVal = null;
		}
		return retVal;
	}

	public DAOFolleto getDaoFolleto() {
		return daoFolleto;
	}

	public void setDaoFolleto(DAOFolleto daoFolleto) {
		this.daoFolleto = daoFolleto;
	}

	public DAOPrecioFolleto getDaoPrecioFolleto() {
		return daoPrecioFolleto;
	}

	public void setDaoPrecioFolleto(DAOPrecioFolleto daoPrecioFolleto) {
		this.daoPrecioFolleto = daoPrecioFolleto;
	}

	public DAOPreciosFolletoDet getDaoPrecioFolletoDet() {
		return daoPrecioFolletoDet;
	}

	public void setDaoPrecioFolletoDet(
			DAOPreciosFolletoDet daoPreciosFolletoDet) {
		this.daoPrecioFolletoDet = daoPreciosFolletoDet;
	}

}
