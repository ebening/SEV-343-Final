package com.adinfi.seven.business.services;

import java.util.List;
import org.apache.log4j.Logger;
import com.adinfi.seven.business.domain.CatLista;
import com.adinfi.seven.persistence.daos.DAOCatLista;

public class ServiceCatListaImpl implements ServiceCatLista {
	private Logger LOG = Logger.getLogger(ServiceCatListaImpl.class);
	private DAOCatLista daoCatLista;

	@Override
	public boolean crearCatLista(CatLista catLista) {
		try {
			daoCatLista.saveOrUpdate(catLista);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatLista> getCatListaList() throws Exception {
		return daoCatLista.getAll();
	}

	@Override
	public boolean eliminarCatLista(CatLista catLista) {
		try {
			daoCatLista.delete(catLista);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public CatLista getCatListaById(int id) {
		try {
			return daoCatLista.getCatLista(id);
		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	public DAOCatLista getDaoCatLista() {
		return daoCatLista;
	}

	public void setDaoCatLista(DAOCatLista daoCatLista) {
		this.daoCatLista = daoCatLista;
	}

}