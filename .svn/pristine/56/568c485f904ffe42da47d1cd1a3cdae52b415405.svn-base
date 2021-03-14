package com.adinfi.seven.business.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;
import com.adinfi.seven.business.domain.TblFolletoSistemaVentaId;
import com.adinfi.seven.persistence.daos.DAOArticulosHoja;
import com.adinfi.seven.persistence.daos.DAOFolleto;
import com.adinfi.seven.persistence.daos.DAOFolletoHojas;
import com.adinfi.seven.persistence.daos.DAOFolletoSistemaVenta;
import com.adinfi.seven.persistence.daos.DAOFolletoTienda;
import com.adinfi.seven.persistence.daos.DAOPrecioFolleto;

public class ServiceFolletoImpl implements ServiceFolleto {
	private Logger LOG = Logger.getLogger(ServiceFolletoImpl.class);
	private DAOFolleto daoFolleto;
	private DAOPrecioFolleto daoPrecioFolleto;
	private DAOFolletoTienda daoFolletoTienda;
	private DAOFolletoHojas  daoFolletoHojas;
	private DAOFolletoSistemaVenta daoFolletoSistemaVenta;
	private DAOArticulosHoja daoArticulosHoja;

	/**
	 * @return the daoFolleto
	 */
	public DAOFolleto getDaoFolleto() {
		return daoFolleto;
	}

	public DAOFolletoHojas getDaoFolletoHojas() {
		return daoFolletoHojas;
	}

	public void setDaoFolletoHojas(DAOFolletoHojas daoFolletoHojas) {
		this.daoFolletoHojas = daoFolletoHojas;
	}

	/**
	 * @param daoFolleto
	 *            the daoFolleto to set
	 */
	public void setDaoFolleto(DAOFolleto daoFolleto) {
		this.daoFolleto = daoFolleto;
	}

	/**
	 * @return the daoPrecioFolleto
	 */
	public DAOPrecioFolleto getDaoPrecioFolleto() {
		return daoPrecioFolleto;
	}

	/**
	 * @param daoPrecioFolleto
	 *            the daoPrecioFolleto to set
	 */
	public void setDaoPrecioFolleto(DAOPrecioFolleto daoPrecioFolleto) {
		this.daoPrecioFolleto = daoPrecioFolleto;
	}

	/**
	 * @return the daoFolletoTienda
	 */
	public DAOFolletoTienda getDaoFolletoTienda() {
		return daoFolletoTienda;
	}

	/**
	 * @param daoFolletoTienda
	 *            the daoFolletoTienda to set
	 */
	public void setDaoFolletoTienda(DAOFolletoTienda daoFolletoTienda) {
		this.daoFolletoTienda = daoFolletoTienda;
	}

	/**
	 * @return the daoFolletoSistemaVenta
	 */
	public DAOFolletoSistemaVenta getDaoFolletoSistemaVenta() {
		return daoFolletoSistemaVenta;
	}

	/**
	 * @param daoFolletoSistemaVenta
	 *            the daoFolletoSistemaVenta to set
	 */
	public void setDaoFolletoSistemaVenta(
			DAOFolletoSistemaVenta daoFolletoSistemaVenta) {
		this.daoFolletoSistemaVenta = daoFolletoSistemaVenta;
	}
	
	/**
	 * @return the daoArticulosHoja
	 */
	public DAOArticulosHoja getDaoArticulosHoja() {
		return daoArticulosHoja;
	}

	/**
	 * @param daoArticulosHoja
	 *            the daoArticulosHoja to set
	 */
	public void setDaoArticulosHoja(DAOArticulosHoja daoArticulosHoja) {
		this.daoArticulosHoja = daoArticulosHoja;
	}

	public void test() {
		LOG.info("se ejecuto servicio test");
	}

	public TblFolletoHojas getHoja(int idHoja) throws Exception {
		return this.daoFolletoHojas.getById(idHoja);
	}

	public void saveHoja(TblFolletoHojas hoja) throws Exception {

		this.daoFolletoHojas.saveOrUpdate(hoja);
		// this.daoFolletoHojas.merge(hoja);
		// this.daoFolleto.flush();

	}
	
	@Override
	public List<TblFolletoHojas> getFolletoHojaByFolleto(int folletoId){
		return daoFolletoHojas.getHojasByFolletoId(folletoId);
	}
	
	@Override
	public TblFolletoSistemaVenta getFolletoSistemaVenta(TblFolletoSistemaVentaId id){
		TblFolletoSistemaVenta folletoSistemaVenta = null;
		try {
			folletoSistemaVenta = daoFolletoSistemaVenta.getById(id);
		} catch (Exception e) {
			LOG.error(e);
			folletoSistemaVenta = null;
		}
		
		return folletoSistemaVenta;
	}
	
	@Override
	public List<TblFolletoSistemaVenta> getFolletoSistemaVentaByFolleto(int folletoId){
		return daoFolletoSistemaVenta.getSistemaVentaByFolleto(folletoId);
	}
	
	@Override
	public List<TblArticulosHoja> getArticulosHojaByHoja(int idHoja){
		return daoArticulosHoja.getArticulosByHoja(idHoja);
	}
	
	@Override
	public TblFolletoHojas saveFolletoHojas(TblFolletoHojas folletoHojas){
		return daoFolletoHojas.saveFolletoHojas(folletoHojas);
	}
	
	@Override
	public boolean deleteFolletoHoja(TblFolletoHojas folletoHoja){
		boolean retVal=true;
		try{
			daoFolletoHojas.delete(folletoHoja);
		}catch(Exception e){
			LOG.error(e);
			retVal=false;
		}
		return retVal;
	}
	@Override
	public TblFolletoHojas getHojaByIdFolletoIdHoja(int idFolleto, int idHoja)
			throws Exception {
		TblFolletoHojas hoja = daoFolletoHojas.getHojaByIdFolletoIdHoja(
				idFolleto, idHoja);
		return hoja;
	}

	@Override
	public int getTotalNumHojasFolleto(int idHoja) {
		Number numHoja = daoFolletoHojas.getTotalNumHojasFolleto(idHoja);
		return numHoja.intValue();
	}

	@Override
	public List<TblFolletoHojas> getCopias(TblFolletoHojas hoja) {
		List<TblFolletoHojas> copias = daoFolletoHojas.getCopias(hoja);
		return copias;
	}

	@Override
	public List<TblFolletoHojas> getHojasByIdFolleto(int idFolleto)
			throws Exception {
		return daoFolletoHojas.getHojasByFolletoId(idFolleto);
	}

	@Override
	public TblArticulosHoja saveArticulosHoja(TblArticulosHoja articulosHoja) {
		return daoArticulosHoja.saveArticulosHoja(articulosHoja);
	}

	@Override
	public List<TblArticulosHoja> getArticulosHojaPrincipales(int idHoja) {
		return daoArticulosHoja.getArticulosPrincipales(idHoja);
	}

	@Override
	public boolean deleteArticulosHoja(TblArticulosHoja articulosHoja) {
		return daoArticulosHoja.deleteArticulosHoja(articulosHoja);
	}

	@Override
	public Number getCategoriaHoja(int idHoja) {
		return daoFolletoHojas.getTotalNumHojasFolleto(idHoja);
	
	}
	
	@Override
	public TblFolleto getById( int idFolleto ){
		TblFolleto folleto=null;
		try{
		   folleto= daoFolleto.getById(idFolleto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return folleto;
		
	}
	
	

}
