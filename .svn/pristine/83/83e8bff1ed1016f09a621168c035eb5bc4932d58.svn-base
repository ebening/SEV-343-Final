package com.adinfi.seven.presentation.views;

import java.io.Serializable;

import com.adinfi.seven.business.domain.*;
import com.adinfi.seven.business.services.*;

public class MBConverter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServiceCatRole serviceCatRole;
	private ServiceUsuarios serviceUsuarios;
	private ServiceCatCategory serviceCatCategory;
	private ServiceCatSenal serviceCatSenal;
	private ServiceArquitecturaSeven serviceArquitecturaSeven;
	private ServiceTblActividades serviceTblActividades;
	private ServiceCatEstatus serviceCatEstatus;
	private ServiceCampana serviceCampana;

	public TblCampana getTblCampanaById(long id) throws Exception {
		return serviceCampana.getCampana(id);
	}

	public CatEstatus getCatEstatusById(int id){
		return serviceCatEstatus.getEstatusById(id);
	}

	public CatFlujoAct getFlujoById(int id){
		return serviceTblActividades.getFlujoById(id);
	}

	public CatActPred getActById(int id){
		return serviceTblActividades.getActById(id);
	}

	public TblMecanica tblMecanicaById(int id){
		return serviceArquitecturaSeven.getMecanicaById(id);
	}

	public CatSenal catSenalById(int id){
		try{
			return serviceCatSenal.getCatSenal(id);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public CatUsuarios catUsuarioById(int id){
		try{
			return serviceUsuarios.getUsuarioById(id);
		}catch(Exception e){
			e.getStackTrace();
			return null;
		}
	}
	
	public CatRole catRoleById(int id){
		try{
			return serviceCatRole.getRoleById(id);
		}catch(Exception e){
			e.getStackTrace();
			return null;
		}
	}
	
	public CatCategory catCategoryById(int idcategory){
		try{
			return serviceCatCategory.getCatCategoryById(idcategory);
		}catch(Exception e){
			System.out.println("MBConverter catCategoryById: " + e);
			return null;
		}
	}
	
	public CatCategory catCategoryByCode(String code){
		try{
			return serviceCatCategory.getCatCategoryByCode(code);
		}catch(Exception e){
			System.out.println("MBConverter catCategoryByCode: " + e);
			return null;
		}
	}

	public ServiceCatRole getServiceCatRole() {
		return serviceCatRole;
	}

	public void setServiceCatRole(ServiceCatRole serviceCatRole) {
		this.serviceCatRole = serviceCatRole;
	}

	public ServiceUsuarios getServiceUsuarios() {
		return serviceUsuarios;
	}

	public void setServiceUsuarios(ServiceUsuarios serviceUsuarios) {
		this.serviceUsuarios = serviceUsuarios;
	}

	public ServiceCatCategory getServiceCatCategory() {
		return serviceCatCategory;
	}

	public void setServiceCatCategory(ServiceCatCategory serviceCatCategory) {
		this.serviceCatCategory = serviceCatCategory;
	}

	public ServiceCatSenal getServiceCatSenal() {
		return serviceCatSenal;
	}

	public void setServiceCatSenal(ServiceCatSenal serviceCatSenal) {
		this.serviceCatSenal = serviceCatSenal;
	}

	public ServiceArquitecturaSeven getServiceArquitecturaSeven() {
		return serviceArquitecturaSeven;
	}

	public void setServiceArquitecturaSeven(ServiceArquitecturaSeven serviceArquitecturaSeven) {
		this.serviceArquitecturaSeven = serviceArquitecturaSeven;
	}

	public ServiceTblActividades getServiceTblActividades() {
		return serviceTblActividades;
	}

	public void setServiceTblActividades(ServiceTblActividades serviceTblActividades) {
		this.serviceTblActividades = serviceTblActividades;
	}

	public ServiceCatEstatus getServiceCatEstatus() {
		return serviceCatEstatus;
	}

	public void setServiceCatEstatus(ServiceCatEstatus serviceCatEstatus) {
		this.serviceCatEstatus = serviceCatEstatus;
	}

    public ServiceCampana getServiceCampana() {
        return serviceCampana;
    }

    public void setServiceCampana(ServiceCampana serviceCampana) {
        this.serviceCampana = serviceCampana;
    }
}
