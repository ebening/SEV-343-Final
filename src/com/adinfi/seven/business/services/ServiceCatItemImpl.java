package com.adinfi.seven.business.services;

import java.util.List;
import org.apache.log4j.Logger;
import com.adinfi.seven.business.domain.CatItem;
import com.adinfi.seven.persistence.daos.DAOCatItem;

public class ServiceCatItemImpl implements ServiceCatItem {
	private Logger LOG = Logger.getLogger(ServiceCatItemImpl.class);
	private DAOCatItem daoCatItem;

	@Override
	public boolean crearCatItem(CatItem catItem) {
		try {
			daoCatItem.saveOrUpdate(catItem);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public List<CatItem> getCatItemList() throws Exception {
		return daoCatItem.getAll();
	}

	@Override
	public boolean eliminarCatItem(CatItem catItem) {
		try {
			daoCatItem.delete(catItem);
			return true;
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	@Override
	public CatItem getCatItemById(CatItem catItem) {
		try {
			return daoCatItem.getCatItem(catItem.getIdItem());
		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	@Override
	public CatItem getCatItemById(String catItem) {
		try {
			return daoCatItem.getCatItem(catItem);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return null;
	}

	public DAOCatItem getDaoCatItem() {
		return daoCatItem;
	}

	public void setDaoCatItem(DAOCatItem daoCatItem) {
		this.daoCatItem = daoCatItem;
	}

   	@Override
	public List<CatItem> getCatItemListBySubCatID(int idSubCat, int idCat) {
        return daoCatItem.getCatItemListBySubCatID(idSubCat, idCat);
	}
        
	@Override
	public List<CatItem> getCatItemByCategory(int idCat){
            return daoCatItem.getCatItemByCategory(idCat);
        }

	@Override
	public List<CatItem> getCatItemByProveedor(int idProveedor, List<Integer> stores) {
		return daoCatItem.getCatItemByProveedor(idProveedor, stores);
	}
}