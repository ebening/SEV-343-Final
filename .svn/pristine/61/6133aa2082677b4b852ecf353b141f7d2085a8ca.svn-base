package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.Bitacora;
import com.adinfi.seven.business.domain.BitacoraParam;
import com.adinfi.seven.business.domain.BitacoraTipo;

public interface ServiceBitacora {
	List<Bitacora> getBitacora() throws GeneralException;

	void saveBitacora(Bitacora bitacora) throws GeneralException;

	void saveBitacoraParam(BitacoraParam param) throws GeneralException;

	List<BitacoraTipo> getTipoActivo() throws GeneralException;

	List<BitacoraTipo> getNiveles() throws GeneralException;

	void saveNiveles(Integer id, int active) throws GeneralException;

	List<BitacoraParam> getParamsListByBitacoraId(int bitacora)
			throws GeneralException;
}