package com.adinfi.seven.presentation.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adinfi.seven.business.domain.Opcion;
import com.adinfi.seven.business.domain.RolOpcion;
import com.adinfi.seven.business.services.admin.ServiceMenuAndRoles;
import com.adinfi.seven.persistence.dto.MenuElement;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.MBPrueba.ColumnModel;
import com.adinfi.seven.presentation.views.util.Util;
import com.adinfi.seven.presentation.views.util.Utileria;
import java.io.Serializable;

public class MenuBean implements Serializable{

	private static final long serialVersionUID = 1L;

	MBPrueba bean;
	private ServiceMenuAndRoles serviceMenuAndRoles;
	private String pagina;

	@PostConstruct
	public void init() {
		this.pagina = "catalogos/blanco";
	}

	public void goCatalog(String catName, String catalogNametitle) {
		bean.setCatalogNametitle(catalogNametitle);
        bean.setCatName(catName);
		bean.setModel(null);
		bean.setColumns(new ArrayList<ColumnModel>());
		bean.setUpdate(false);
		bean.setRowSelect(null);
		bean.setResponseArray(null);
		bean.fillCatalog();
        bean.fillUpdate(-1);
		this.pagina = "prueba";

	}

	public MBPrueba getBean() {
		return bean;
	}

	public void setBean(MBPrueba bean) {
		this.bean = bean;
	}

	private void print(List<Opcion> opciones) {
		for (Opcion o : opciones) {
			Utileria.logger(getClass()).info(o.getOpcName());
		}

	}

	private List<MenuElement> getMenuElemets() throws Exception {
		List<MenuElement> elementList = new ArrayList<>();
		UsuarioDTO usuario = Util.getSessionAttribute("userLoged");
		List<RolOpcion> rolOpcionList = serviceMenuAndRoles.getOpcionesPorRol(usuario.getRole());
		Opcion opcion;
		for (RolOpcion e : rolOpcionList) {
			opcion = e.getOpcion();
		}

		return null;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {

		this.pagina = pagina;
	}

}
