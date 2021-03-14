package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adinfi.seven.business.domain.CatRole;
import com.adinfi.seven.business.services.ServiceCatRole;
import com.adinfi.seven.presentation.views.util.Messages;

public class MBCatRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceCatRole serviceCatRole;
	private String catalogName = "Roles";
	
	private CatRole current;
	private List<CatRole> allroles;
	private boolean update = false;
	
	@PostConstruct
	public void init(){
		try {
			allroles = serviceCatRole.getRoleAll();
			current = new CatRole();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRole(){
		if(serviceCatRole.deleteRole(current)){
			Messages.mensajeSatisfactorio("Confirmacion", "Role eliminado correctamente");
		}else{
			Messages.mensajeErroneo("Error", "Error al borrar Role");
		}
	}
	
	public void guardarRole(){
		if (update){
			if(serviceCatRole.updateRole(current)){
				update = false;
				Messages.mensajeSatisfactorio("Confirmacion", "Role actualizado correctamente");
				current = new CatRole();
			}else{
				Messages.mensajeErroneo("Error", "Error al actualizar");
			}
		}else{
			if(serviceCatRole.saveRole(current)){
				allroles.add(current);
				Messages.mensajeAlerta("Confirmacion", "Role guardado correctamente");
				current = new CatRole();
			}else{
				Messages.mensajeErroneo("Error", "Error al guardar Role");
			}
			
		}
	}
	
	public void resetInfo(){
		update = false;
		current = new CatRole();
	}
	
	public void setSelectedRole(CatRole item){
		current = item;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public ServiceCatRole getServiceCatRole() {
		return serviceCatRole;
	}

	public void setServiceCatRole(ServiceCatRole serviceCatRole) {
		this.serviceCatRole = serviceCatRole;
	}

	public CatRole getCurrent() {
		return current;
	}

	public void setCurrent(CatRole current) {
		this.current = current;
	}

	public List<CatRole> getAllroles() {
		return allroles;
	}

	public void setAllroles(List<CatRole> allroles) {
		this.allroles = allroles;
	} 
	
	

}
