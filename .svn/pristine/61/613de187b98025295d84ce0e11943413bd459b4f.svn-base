package com.adinfi.seven.business.services;

import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCadenaAutorizacion;
import com.adinfi.seven.business.domain.TblCadenaAutorizacionDet;

public interface ServiceCadenaAutorizacion {

	void crearCadenaAutorizacion(TblCadenaAutorizacion tblCadenaAutorizacion);

	void eliminarCadenaAutorizacion(TblCadenaAutorizacion tblCadenaAutorizacion);

	List<TblCadenaAutorizacion> getCadenaAutorizacionList() throws Exception;

	List<TblCadenaAutorizacion> getListCadenaAutorizacionDet(
			TblCadenaAutorizacion idTblCadenaAutorizacion)
			throws GeneralException;

	void guardarAutorizacionDet(TblCadenaAutorizacion tblCadenaAutorizacion);

	void eliminarAutorizacionDet(TblCadenaAutorizacion tblCadenaAutorizacion);

	void eliminarAutorizacionDetById(
			TblCadenaAutorizacionDet tblCadenaAutorizacionDet);

	TblCadenaAutorizacionDet getIdOrdenByIdCadenaAutorizacion(
			int idCadenaAutorizacion);

	void crearCadenaAutorizacionDet(
			TblCadenaAutorizacionDet tblCadenaAutorizacionDet);

	List<TblCadenaAutorizacionDet> obtenerCadenaAutorizacionDetList(
			TblCadenaAutorizacion tblCadenaAutorizacion);

	TblCadenaAutorizacion getCadenaAutorizacionById(TblCadenaAutorizacion tblCadenaAutorizacion);
}
