package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblPreciosFolleto;
import com.adinfi.seven.business.domain.TblPreciosFolletoDet;

public interface ServicePrecioFolleto {

	TblFolleto getFolleto(int IdFolleto);

	boolean guardarPrecioFolleto(TblPreciosFolleto precioFolleto);

	List<TblPreciosFolleto> getPreciosFolletoByFolleto(int idFolleto);

	boolean deletePreciosFolleto(TblPreciosFolleto precioFolleto);

	List<TblPreciosFolletoDet> getPreciosArticuloHoja(int idSistemaVenta, int idFolleto);

}
