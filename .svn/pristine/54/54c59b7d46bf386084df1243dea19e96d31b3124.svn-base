package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;
import com.adinfi.seven.business.domain.TblFolletoSistemaVentaId;

public interface ServiceFolleto {
	void test();	
	TblFolletoHojas getHoja(int idHoja) throws Exception;
	void saveHoja(TblFolletoHojas  hoja) throws Exception;
	TblFolletoSistemaVenta getFolletoSistemaVenta(TblFolletoSistemaVentaId id);
	List<TblFolletoSistemaVenta> getFolletoSistemaVentaByFolleto(int folletoId);
	List<TblFolletoHojas> getFolletoHojaByFolleto(int folletoId);
	List<TblArticulosHoja> getArticulosHojaByHoja(int idHoja);
	TblFolletoHojas saveFolletoHojas(TblFolletoHojas folletoHojas);
	boolean deleteFolletoHoja(TblFolletoHojas folletoHoja);
	List<TblFolletoHojas> getHojasByIdFolleto (int idFolleto)throws Exception;
	TblFolletoHojas getHojaByIdFolletoIdHoja(int idFolleto, int idHoja) throws Exception;
	int getTotalNumHojasFolleto(int idHoja);
	List<TblFolletoHojas> getCopias(TblFolletoHojas hoja);
	TblArticulosHoja saveArticulosHoja(TblArticulosHoja articulosHoja);
	List<TblArticulosHoja> getArticulosHojaPrincipales(int idHoja);
	boolean deleteArticulosHoja(TblArticulosHoja articulosHoja);
	Number getCategoriaHoja(int idHoja);
	TblFolleto getById(int idFolleto);
	
}
