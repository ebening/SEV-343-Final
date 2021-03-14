package com.adinfi.seven.presentation.views;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.adinfi.seven.business.domain.CatCategory;
import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.CatRole;
import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.Opcion;
import com.adinfi.seven.business.domain.RelUsuariosCategorias;
import com.adinfi.seven.business.domain.RolOpcion;
import com.adinfi.seven.business.domain.UserConnection;
import com.adinfi.seven.business.services.ServiceCatCategory;
import com.adinfi.seven.business.services.ServiceCatRole;
import com.adinfi.seven.business.services.ServiceRelUsuariosCategorias;
import com.adinfi.seven.business.services.ServiceUsuarios;
import com.adinfi.seven.business.services.admin.ServiceMenuAndRoles;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;
import com.adinfi.seven.presentation.views.util.Utileria;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MBCatUsuarios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PASSWORD_LENGTH = 5;
	private String catalogName = "USUARIOS";
	private String nameUserLogged = "";
	private ServiceUsuarios serviceUsuarios;
	private ServiceCatRole serviceCatRole;
	private ServiceCatCategory serviceCategory;
	private ServiceMenuAndRoles serviceMenuAndRoles;
	private ServiceRelUsuariosCategorias serviceRelUsuariosCategorias;
	private CatUsuarios usuario;
	private CatUsuarios jefeSelected;
	private List<CatUsuarios> usuariosAll;
	private List<CatCategory> categorias;
	private List<CatRole> roleAll;
	private List<Opcion>opciones;
	private List<Opcion>currentOpciones;
	private List<Opcion> opcionesMenVer = null;
	private List<Opcion> opcionesFinal= null;
	private Integer opcionId;
	private Integer opcionTraspaso;
	private boolean update = false;
	private boolean level1 = false;
	private boolean inactive = false;
	private String loginID;
	private String password;
	private String newPassword;
	private  List<String> categoriasSelected;
	
	@PostConstruct
	public void init(){
		usuario = new CatUsuarios();
		currentOpciones = new ArrayList<>();
		llenarListaUsuarios();
	}
	
	public void llenarListaUsuarios(){
		try {
			categorias = serviceCategory.getCatCategoryList();
			usuariosAll = serviceUsuarios.getUsuariosAll();
			roleAll = serviceCatRole.getRoleAll();
		} catch (Exception e) {
			System.out.println("Llenar Lista Method: "+e);
		}
	}
	
	public void setSelectedUser(CatUsuarios user){
		usuario = user;
		level1 = usuario.getIslevel1() == 1;
		inactive = usuario.getInactive() == 1;
		for(CatUsuarios u : usuariosAll){
			if(usuario.getIdjefe() == u.getIdusuarios()){
				jefeSelected = u;
				break;
			}
		}
		update = true;
		List<RelUsuariosCategorias> rcu = new ArrayList<>(usuario.getRelUsuariosCategoriases());
		categoriasSelected = new ArrayList<>();
		for(RelUsuariosCategorias iter : rcu){
			categoriasSelected.add(String.valueOf(iter.getCatCategory().getIdCategory()));
		}
	}
	
	public void deleteUsuario(){
		if(serviceUsuarios.deleteUsuario(usuario)){
			Messages.mensajeSatisfactorio("Usuario eliminado correctamente", "Usuario eliminado correctamente");
			usuariosAll.remove(usuario);
		}else{
			Messages.mensajeErroneo("Error al eliminar usuario", "Error al eliminar usuario");
		}
		resetInfo();
	}
	
	private List<RelUsuariosCategorias> getCategosList(){
		List<RelUsuariosCategorias> list = new ArrayList<>();
		for(String cstring : categoriasSelected){
			RelUsuariosCategorias rucs = new RelUsuariosCategorias();
			rucs.setIdRel(0);
			rucs.setCatCategory(serviceCategory.getCatCategoryById(Integer.valueOf(cstring)));
			rucs.setCatUsuarios(usuario);
			list.add(rucs);
		}
		return list;
	}

	private boolean validateUserInfo(){
        String msj = "";
        boolean completo = true;
        if (usuario.getNombre().isEmpty()){
            completo = false;
            msj += "Nombre, ";
        }
        if (usuario.getPlastName().isEmpty()){
            completo = false;
            msj += "Apellido Paterno, ";
        }
        if (usuario.getMlastName().isEmpty()){
            completo = false;
            msj += "Apellido Materno, ";
        }
        if (usuario.getNempleado().isEmpty()){
            completo = false;
            msj += "Numero de Empleado, ";
        }
        if (usuario.getEmail().isEmpty()){
            completo = false;
            msj += "Correo, ";
        }
        if (usuario.getLogin().isEmpty()){
            completo = false;
            msj += "Login, ";
        }
        if (getCategosList().isEmpty()){
            completo = false;
            msj += "Al menos 1 categoria";
        }
        if (!completo){
            Messages.mensajeAlerta("Falta Informacion: " + msj, "");
        }
        return completo;
    }
	
	public void guardarUsuario(){
        if (validateUserInfo()){
            Set<RelUsuariosCategorias> relCategos = new HashSet<>(getCategosList());
            usuario.setIdjefe(jefeSelected == null ? 0 : jefeSelected.getIdusuarios());
            usuario.setInactive(inactive ? 1 : 0);
            usuario.setIslevel1(level1 ? 1 : 0);
            usuario.setRelUsuariosCategoriases(relCategos);
            serviceRelUsuariosCategorias.borrarRelUsuarioCategoria(usuario.getIdusuarios());
            if(update){
                if(serviceUsuarios.updateUsuario(usuario)){
                    Messages.mensajeSatisfactorio("Usuario guardado correctamente", "Usuario guardado correctamente");
                    update = false;
                    resetInfo();
                }else{
                    Messages.mensajeErroneo("Error al actualizar", "Error al actualizar");
                    resetInfo();
                }
            }else{
                usuario.setIdusuarios(0);
                usuario.setNchange(1);
                if(serviceUsuarios.saveUsuario(usuario)){
                    Messages.mensajeSatisfactorio("Usuario guardado correctamente", "Usuario guardado correctamente");
                    usuariosAll.add(usuario);
                    update = false;
                    resetInfo();
                }else{
                    Messages.mensajeErroneo("Error al guardar nuevo usuario", "Error al guardar nuevo usuario");
                    resetInfo();
                }
            }
        }else{
            usuariosAll = serviceUsuarios.getUsuariosAll();
        }
	}
	
	public void resetInfo(){
        update = false;
		usuario = new CatUsuarios();
        jefeSelected = null;
        categoriasSelected = null;
		inactive = false;
		level1 = false;
	}
	
	public String getNombreJefe(String id){
		int idjefe = Integer.valueOf(id);
		if (idjefe == 0){
			return "Sin Jefe";
		}
		for (CatUsuarios ctu : usuariosAll){
			if(idjefe == ctu.getIdusuarios()){
				return ctu.getNombre() + " " + ctu.getPlastName();
			}
		}
		return "";
	}
	
	public String changePassword(){
		if(usuario.getPassword().length() > PASSWORD_LENGTH && usuario.getPassword().matches("^[a-zA-Z0-9_]*$")){
			if(usuario.getPassword().equals(newPassword)){
				usuario.setNchange(0);
				if(serviceUsuarios.updateUsuario(usuario)){
					Messages.mensajeSatisfactorio("Contraseña Actualizada, debe ingresar con la nueva contraseña", "");
					return "Login.xhtml";
				}else{
					FacesMessage fm = new FacesMessage("Ocurrio un error al cambiar la contraseña");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			}else{
				FacesMessage fm = new FacesMessage("Password no coincide");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		}else{
			FacesMessage fm = new FacesMessage("La contraseña debe ser alfanumerica y de 6 caracteres");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return null;
	}
	
	public void recoveryPassword(){
		if(loginID.trim().length() > 0){
			CatUsuarios user = serviceUsuarios.getUsuarioByLogin(loginID);
			if(user != null){
				String pass = calculateNewPassword();
				user.setNchange(1);
				user.setPassword(pass);
				if(serviceUsuarios.updateUsuario(user)){
					SendMail.sendPassword(user.getEmail(), pass);
					FacesMessage fm = new FacesMessage("Se le envio un correo con su nueva contraseña");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}else{
					FacesMessage fm = new FacesMessage("Error al generar nueva contraseña");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			}else{
				FacesMessage fm = new FacesMessage("Usuario incorrecto");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		}else{
			FacesMessage fm = new FacesMessage("El usuario es requerido");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}
	
	private String calculateNewPassword() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32).substring(0, 12);
	}
	
	public void login(){
		String url = "/pages/welcome.xhtml";
        boolean nChange = false;
		if (password != null && password.trim().length() > 0 && validate()){
			if (usuario.getNchange() == 1) {
                nChange = true;
	        }
	                Util.setSessionAttribute("userLoged", usuario);
					nameUserLogged = usuario.getNombre() + " " + usuario.getPlastName();
                    
                    /*se gestiona el control de acceso a usuarios*/
                   
                    /*pregunta si hay una sesion de usuario active*/
                    System.out.println("Buscando sesiones activas para usuario: " + usuario.getIdusuarios());
                    
                    UserConnection currentConnection = serviceUsuarios.getUserConnectionByUserId(usuario.getIdusuarios());
                    //si hay una activa, se actualiza el timestamp
                    Long currentTime = new Date().getTime();
                    if (currentConnection != null){
                        System.out.println("session activa, actualizando timestamp");
                        currentConnection.setLastConnection(currentTime);
                        serviceUsuarios.updateUserConnection(currentConnection);
                        System.out.println("timestamp actualizado");
                    //si no hay activa, se inserta una nueva
                    }else{
                        System.out.println("sin sesion activa, guardando nueva");
                        UserConnection uc = new UserConnection();
                        uc.setUserId(usuario.getIdusuarios());
                        uc.setLastConnection(currentTime);
                        uc.setActive(1);
                        int res = serviceUsuarios.insertUserConnection(uc);
                        System.out.println("nueva sesion guardada: " + res);
                    }
                    
                    Util.setSessionAttribute("currentSessionTimestamp", currentTime);
                    
	                resetInfo();
	       // }
			if (nChange) {
				url = "/pages/admin/ChangePassword.xhtml";
                usuario = Util.getSessionAttribute("userLoged");
			}
			 try {
                 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                 ec.redirect(ec.getRequestContextPath() + url);
             } catch (Exception e){
				 e.getStackTrace();
				 FacesMessage fm = new FacesMessage("Ocurrio un error al redireccionar");
				 FacesContext.getCurrentInstance().addMessage(null, fm);
             }
		}else {
			FacesMessage fm = new FacesMessage("El usuario o password es incorrecto ");
			FacesContext.getCurrentInstance().addMessage(null, fm);
			RequestContext.getCurrentInstance().execute("loader.hide()");
		}
	}
	
	public boolean validate(){
		usuario = serviceUsuarios.getUsuarioForLogin(loginID, password);
		if(usuario == null){
			usuario = new CatUsuarios();
			return false;
		}
		opciones = armaOpciones(String.valueOf(usuario.getCatRole().getIdrole()));
		return true;
	}
	
	public void test() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");

		String url_des = params.get("url");

		this.currentOpciones = findOpcion(this.opciones, Integer.parseInt(id));
		try {
			Opcion opc1;
			if( this.currentOpciones!=null && this.currentOpciones.size()>0 ){
				opc1=this.currentOpciones.get(0);
				if(opc1!=null && opc1.getOpcLevel()==3){
					opcionesFinal= currentOpciones;
				}
			}else{
				opcionesFinal=null;
			}
			if (url_des != null && !url_des.trim().equals("") && !url_des.trim().equals("null")) {
				context.getExternalContext().redirect(Utileria.url(url_des + "?id_op=" + id));
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
	
	public void setOpcionId(Integer opcionId) {
		this.opcionId = opcionId;
		if(opcionId!=null){
			this.currentOpciones=findOpcion( this.opciones , opcionId );
		}
	}
	
	public String asignarOpciones(String url, List<Opcion> currentOpciones) {
		this.currentOpciones = currentOpciones;
		return url + "?faces-redirect=true";
	}
	
	public List<Opcion>  findOpcion(List<Opcion> opcs ,   int opcId){
		if(opcId<=0) return null;
		List<Opcion> opcsTmp;
		if(opcs==null || opcs.size()<=0  ) return null;
		for( Opcion opc : opcs  ){
			if( opc.getOpcId()==opcId ){
				return opc.getChildsAsArray();
			}else{
				opcsTmp = findOpcion( opc.getChildsAsArray() , opcId  );
				if(opcsTmp!=null) return opcsTmp;
			}
		}
		return null;
	}
	
	public List<Opcion> armaOpciones(String role) {
		if (role == null)
			return null;
		List<Opcion> itemsTmp = null;
		try {
			List<RolOpcion> lstOpc = this.serviceMenuAndRoles.getOpcionesPorRol(role);
			Map<Integer, Opcion> lstAllItems = new TreeMap<Integer, Opcion>();
			Opcion opcTmp = null;
			if (lstOpc != null) {
				for (RolOpcion rolOpc : lstOpc) {
					opcTmp = lstAllItems.get(rolOpc.getOpcion().getParentId());
					if (opcTmp == null) {
						itemsTmp = new ArrayList<>();
						itemsTmp.add(rolOpc.getOpcion());

						opcTmp = new Opcion();
						opcTmp.setChildsAsArray(itemsTmp);
						lstAllItems.put(rolOpc.getOpcion().getParentId(), opcTmp);

					} else {
						itemsTmp = opcTmp.getChildsAsArray();
						if (itemsTmp == null) {
							itemsTmp = new ArrayList<>();
							opcTmp.setChildsAsArray(itemsTmp);
						}
						itemsTmp.add(rolOpc.getOpcion());
					}
					lstAllItems.put(rolOpc.getOpcion().getOpcId(),rolOpc.getOpcion());
				}

			}
			itemsTmp = null;
			opcTmp = lstAllItems.get(0);
			if (opcTmp != null) {
				itemsTmp = opcTmp.getChildsAsArray();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return itemsTmp;
	}
	
	public void setCurrentOpcionesView(Opcion currentOpcion) {
		this.currentOpciones = currentOpcion.getChildsAsArray();
		Opcion opc1;
		this.opcionesMenVer=null;
		if( this.currentOpciones!=null && this.currentOpciones.size()>0 ){
			if(currentOpcion.getOpcLevel()==1){
				//opcionesFinal= null;
			}
			opc1= currentOpciones.get(0);
			if(opc1!=null&&opc1.getOpcLevel()==2 && opc1.isMenHor()){
				this.opcionesMenVer=currentOpciones;
				this.currentOpciones=null;
			}
		}
	}
	
	public void logout() throws IOException {
		Util.setSessionAttribute("userLoged", null);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/pages/admin/Login.xhtml");
	}

	// *************** Getters And Setters ********************** //
	
	public ServiceUsuarios getServiceUsuarios() {
		return serviceUsuarios;
	}
	public void setServiceUsuarios(ServiceUsuarios serviceUsuarios) {
		this.serviceUsuarios = serviceUsuarios;
	}
	public CatUsuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(CatUsuarios usuario) {
		this.usuario = usuario;
	}
	public List<CatUsuarios> getUsuariosAll() {
        Collections.sort(usuariosAll, new Comparator<CatUsuarios>() {
            @Override
            public int compare(CatUsuarios o1, CatUsuarios o2) {
                return (o1.getIdusuarios() > o2.getIdusuarios()) ? 1 : (o1.getIdusuarios() == o2.getIdusuarios()) ? 0 : -1;
            }
        });
		return usuariosAll;
	}
	public void setUsuariosAll(List<CatUsuarios> usuariosAll) {
		this.usuariosAll = usuariosAll;
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

	public ServiceCatCategory getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(ServiceCatCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public ServiceMenuAndRoles getServiceMenuAndRoles() {
		return serviceMenuAndRoles;
	}

	public void setServiceMenuAndRoles(ServiceMenuAndRoles serviceMenuAndRoles) {
		this.serviceMenuAndRoles = serviceMenuAndRoles;
	}

	public ServiceRelUsuariosCategorias getServiceRelUsuariosCategorias() {
		return serviceRelUsuariosCategorias;
	}

	public void setServiceRelUsuariosCategorias(ServiceRelUsuariosCategorias serviceRelUsuariosCategorias) {
		this.serviceRelUsuariosCategorias = serviceRelUsuariosCategorias;
	}

	public List<CatRole> getRoleAll() {
		return roleAll;
	}

	public void setRoleAll(List<CatRole> roleAll) {
		this.roleAll = roleAll;
	}

	public boolean isLevel1() {
		return level1;
	}

	public void setLevel1(boolean level1) {
		this.level1 = level1;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public CatUsuarios getJefeSelected() {
		return jefeSelected;
	}

	public void setJefeSelected(CatUsuarios jefeSelected) {
		this.jefeSelected = jefeSelected;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	public List<Opcion> getCurrentOpciones() {
		return currentOpciones;
	}

	public void setCurrentOpciones(List<Opcion> currentOpciones) {
		this.currentOpciones = currentOpciones;
	}

	public List<Opcion> getOpcionesMenVer() {
		return opcionesMenVer;
	}

	public void setOpcionesMenVer(List<Opcion> opcionesMenVer) {
		this.opcionesMenVer = opcionesMenVer;
	}

	public List<Opcion> getOpcionesFinal() {
		return opcionesFinal;
	}

	public void setOpcionesFinal(List<Opcion> opcionesFinal) {
		this.opcionesFinal = opcionesFinal;
	}

	public Integer getOpcionTraspaso() {
		return opcionTraspaso;
	}

	public void setOpcionTraspaso(Integer opcionTraspaso) {
		this.opcionTraspaso = opcionTraspaso;
	}

	public Integer getOpcionId() {
		return opcionId;
	}

	public List<CatCategory> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CatCategory> categorias) {
		this.categorias = categorias;
	}

	public List<String> getCategoriasSelected() {
		return categoriasSelected;
	}

	public void setCategoriasSelected(List<String> categoriasSelected) {
		this.categoriasSelected = categoriasSelected;
	}

	public String getNameUserLogged() {
		return nameUserLogged;
	}

	public void setNameUserLogged(String nameUserLogged) {
		this.nameUserLogged = nameUserLogged;
	}
    
    public boolean renderLeftPanel(){
        return opcionesFinal != null && opcionesFinal.size() > 0;
    }
}
