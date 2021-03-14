package com.adinfi.seven.presentation.views;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.adinfi.seven.presentation.views.util.*;
import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.LoginException;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.Opcion;
import com.adinfi.seven.business.domain.RolOpcion;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceUsuarios;
import com.adinfi.seven.business.services.admin.ServiceMenuAndRoles;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import org.primefaces.context.RequestContext;

public class MBUsuarios implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8394047584381988575L;
	private static final int PASSWORD_LENGTH = 6;
	private static transient final Logger LOG = Logger.getLogger(MBUsuarios.class);
	private ServiceMenuAndRoles serviceMenuAndRoles = null;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	
	private ServiceUsuarios serviceUsuarios;

	private UsuarioDTO usuario = null;
	private List<Opcion> opciones = null;
	private List<Opcion> opcionesMenVer = null;
	private List<Opcion> opcionesFinal= null;
	private Integer opcionId;
	private Integer opcionTraspaso;
	
	public List<Opcion> getOpcionesMenVer() {
		return opcionesMenVer;
	}

	public void setOpcionesMenVer(List<Opcion> opcionesMenVer) {
		this.opcionesMenVer = opcionesMenVer;
	}

	public List<Opcion>  findOpcion(List<Opcion> opcs ,   int opcId){
		if(opcId<=0) return null;
		List<Opcion> opcsTmp;
		if(opcs==null || opcs.size()<=0  ) return null;
		for( Opcion opc : opcs  ){
			if( opc.getOpcId()==opcId ){
				return opc.getChildsAsArray();
			}else{
				opcsTmp=findOpcion( opc.getChildsAsArray() , opcId  );
				if(opcsTmp!=null) return opcsTmp;
			}
		}
		return null;
	}

	public ServiceMenuAndRoles getServiceMenuAndRoles() {
		return serviceMenuAndRoles;
	}

	public void setServiceMenuAndRoles(ServiceMenuAndRoles serviceMenuAndRoles) {
		this.serviceMenuAndRoles = serviceMenuAndRoles;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	private String newPassword;
	private SecureRandom random = new SecureRandom();
	private List<Opcion> currentOpciones = null;

	public String asignarOpciones(String url, List<Opcion> currentOpciones) {
		this.currentOpciones = currentOpciones;
		return url + "?faces-redirect=true";
	}

	public List<Opcion> getCurrentOpciones() {
		return currentOpciones;
	}

	
	public void setCurrentOpciones(List<Opcion> currentOpciones) {
		System.out.println("Current Opciones is :"+ currentOpciones );
		this.currentOpciones = currentOpciones;
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
	
//	public void setCurrentOpcionesView(List<Opcion> currentOpciones) {
//		LOG.info("Current Opciones View is :"+ currentOpciones );
//		this.currentOpciones = currentOpciones;
//		Opcion opc1=null;
//		this.opcionesMenVer=null;
//		if( this.currentOpciones!=null && this.currentOpciones.size()>0 ){
//			opc1=this.currentOpciones.get(0);
//			if(opc1!=null){
//				if(opc1.getOpcLevel()==1){
//					opcionesFinal= null;
//				}
//				if(opc1.getOpcLevel()==2 && opc1.isMenHor().booleanValue()){
//					this.opcionesMenVer=currentOpciones;
//					this.currentOpciones=null;
//				}
//			}
//		}
//	}
	
	public void test() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		LOG.info("ID :" + id);

		String url_des = params.get("url");
		LOG.info("URL :" + url_des);

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
			LOG.error(e);
		}

	}
	
	public void cargarOpciones(){
		//this.currentOpciones=findOpcion( this.opciones , opcionTraspaso == null ? 0 : opcionTraspaso );
	}
	
	public void setCurrentOpciones1(ActionEvent ev){
		LOG.info("ActionEvent");
	}

	public MBUsuarios() {
		this.usuario = new UsuarioDTO();
		currentOpciones = new ArrayList<>();
	}

	public List<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}

	public void login() {
		String url = "/pages/Home1.xhtml";
		if (usuario.getPassword() != null && usuario.getPassword().trim().length() > 0 && validate()) {
                    if (needChancePassword()) {
                            url = "/pages/admin/ChangePassword.xhtml";
                    } else {
                            Util.setSessionAttribute("userLoged", usuario);
                    }
                    try {
                        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                        ec.redirect(ec.getRequestContextPath() + url);
                    } catch (Exception e){
                            LOG.error(e);
                            FacesMessage fm = new FacesMessage("Ocurrio un error al redireccionar");
                            FacesContext.getCurrentInstance().addMessage(null, fm);
                    }
		} else {
			FacesMessage fm = new FacesMessage("El usuario o password es incorrecto ");
			FacesContext.getCurrentInstance().addMessage(null, fm);
             RequestContext.getCurrentInstance().execute("loader.hide()");
		}
	}

	public void logout() throws IOException {
		Util.setSessionAttribute("userLoged", null);
         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
         ec.redirect(ec.getRequestContextPath() + "/pages/admin/Login.xhtml");
	}

	public String recoveryPassword() throws LoginException {
		try {
			if (usuario.getLogin().trim().length() > 0) {
				UsuarioDTO updUsuario;
				updUsuario = makeUser(usuario.getLogin());
				if (updUsuario != null && updUsuario.getEmail() != null) {
					String password = calculateNewPassword();
					updUsuario.setChangePassword('Y');
					updUsuario.setPassword(password);
					saveUser(updUsuario);
					SendMail.sendPassword(updUsuario.getEmail(), password);

					FacesMessage fm = new FacesMessage(
							"Se le envio un correo con su nueva contraseña");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				} else {
					FacesMessage fm = new FacesMessage("Usuario incorrecto");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			} else {
				FacesMessage fm = new FacesMessage("El usuario es requerido");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} catch (Exception e) {
			LOG.error(e);
			FacesMessage fm = new FacesMessage(
					"Ocurrio un error y no se pudo obtener los datos del usuario");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return null;
	}

	private String calculateNewPassword() {
		return new BigInteger(130, random).toString(32).substring(0, 12);
	}

	public String chancePassword() throws LoginException {
		if (newPassword != null && usuario.getPassword() != null
				&& usuario.getPassword().compareTo(newPassword) == 0) {
			if (newPassword.matches("^[a-zA-Z0-9_]*$")
					&& newPassword.length() == PASSWORD_LENGTH) {
				UsuarioDTO updUsuario;
				try {
					updUsuario = makeUser(usuario.getLogin());
					updUsuario.setPassword(newPassword);
					updUsuario.setChangePassword(Character.valueOf('N'));
					saveUser(updUsuario);
					return "Login.xhtml";
				} catch (Exception e) {
					LOG.error(e);
					FacesMessage fm = new FacesMessage("Ocurrio un error al cambiar la contraseña");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			} else {
				FacesMessage fm = new FacesMessage("La contraseña debe ser alfanumerica y de 6 caracteres");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage("Password no coincide");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		return null;
	}

	private Boolean needChancePassword() {
		if (usuario != null && usuario.getChangePassword()!=null  && usuario.getChangePassword().compareTo('Y') == 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
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
						itemsTmp = new ArrayList<Opcion>();
						itemsTmp.add(rolOpc.getOpcion());

						opcTmp = new Opcion();
						opcTmp.setChildsAsArray(itemsTmp);
						lstAllItems.put(rolOpc.getOpcion().getParentId(),
								opcTmp);

					} else {
						itemsTmp = opcTmp.getChildsAsArray();
						if (itemsTmp == null) {
							itemsTmp = new ArrayList<Opcion>();
							opcTmp.setChildsAsArray(itemsTmp);
						}
						itemsTmp.add(rolOpc.getOpcion());
					}
					lstAllItems.put(rolOpc.getOpcion().getOpcId(),
							rolOpc.getOpcion());

				}

			}
			itemsTmp = null;
			opcTmp = lstAllItems.get(Integer.valueOf(0));
			if (opcTmp != null) {
				itemsTmp = opcTmp.getChildsAsArray();
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return itemsTmp;
	}

	private void saveUser(UsuarioDTO updUsuario) throws Exception {
		List<AttrSearch> lstSearchAttrs = null;
		AttrSearch attSearch = null;
		lstSearchAttrs = new ArrayList<AttrSearch>();
		if (updUsuario.getLogin() != null
				&& updUsuario.getLogin().trim().length() > 0) {
			attSearch = new AttrSearch();
			attSearch.setAttrName(Constants.ATT_LOGIN);
			attSearch.setValue(updUsuario.getLogin());
			lstSearchAttrs.add(attSearch);
		}
		Map<String, Object> updateVals = new HashMap<String, Object>();
		updateVals.put(Constants.ATT_N_CHANGE,
				String.valueOf(updUsuario.getChangePassword()));
		updateVals.put(Constants.ATT_PASSWORD, updUsuario.getPassword());
		serviceDynamicCatalogs.updateRegs(Constants.CAT_USER, updateVals,
				lstSearchAttrs);
	}

	private UsuarioDTO makeUser(String login) throws Exception {
		return MBUtil.makeUser(login, null, serviceDynamicCatalogs);
	}

	public boolean validate() {
		boolean isOk = false;
		List<AttrSearch> lstSearchAttrs = null;
		AttrSearch attSearch = null;
		Set<CatRegValues> setReg = null;
		CatRegs reg = null;
		String attrName = null;
		try {
			lstSearchAttrs = new ArrayList<AttrSearch>();
			attSearch = new AttrSearch();
			attSearch.setAttrName("LOGIN");
			attSearch.setValue(this.usuario.getLogin());
			lstSearchAttrs.add(attSearch);

			attSearch = new AttrSearch();
			attSearch.setAttrName("PASSWORD");
			attSearch.setValue(this.usuario.getPassword());
			lstSearchAttrs.add(attSearch);

			List<CatRegs> regs = serviceDynamicCatalogs.getRegs("CAT_USER",
					lstSearchAttrs);
			
			if (regs != null && regs.size() > 0) {

				reg = regs.get(0);

				setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						attrName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attrName == null || attrName.isEmpty())
							continue;
						if ("NAME".equals(attrName)) {
							this.usuario.setName(regVals.getValue());
						} else if ("EMAIL".equals(attrName)) {
							this.usuario.setEmail(regVals.getValue());
						} else if ("PLAST_NAME".equals(attrName)) {
							this.usuario.setPlastName(regVals.getValue());
						} else if ("ROLE".equals(attrName)) {
							this.usuario.setRole(regVals.getValue());
						} else if (Constants.ATT_N_CHANGE.equals(attrName)) {
							this.usuario.setChangePassword(regVals.getValue()
									.charAt(0));
						} else if (Constants.ATT_CATEGORY.equals(attrName)) {
//							if(usuario.getCategorias()==null){
//								// TODO: Agregar categorias al usuario
//								usuario.setCategorias(new ArrayList<CategoriaDTO>());
//							}
//							CategoriaDTO categ= new CategoriaDTO();
//							categ.setId(new Integer(regVals.getValue()));
//							usuario.getCategorias().add(categ);
						} else if (Constants.ATT_ID.equals(attrName)) {
							Integer idUsuario = Integer.valueOf(regVals.getValue());
							this.usuario.setUserId(idUsuario);
						}
					}
					this.opciones = armaOpciones(this.usuario.getRole());
					isOk = true;
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return isOk;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setOpcionId(Integer opcionId) {
		this.opcionId = opcionId;
		if(opcionId!=null){
			this.currentOpciones=findOpcion( this.opciones , opcionId );
		}
	}

	public Integer getOpcionId() {
		return opcionId;
	}

	public Integer getOpcionTraspaso() {
		return opcionTraspaso;
	}

	public void setOpcionTraspaso(Integer opcionTraspaso) {
		this.opcionTraspaso = opcionTraspaso;
	}

	public List<Opcion> getOpcionesFinal() {
		return opcionesFinal;
	}

	public void setOpcionesFinal(List<Opcion> opcionesFinal) {
		this.opcionesFinal = opcionesFinal;
	}

	public ServiceUsuarios getServiceUsuarios() {
		return serviceUsuarios;
	}

	public void setServiceUsuarios(ServiceUsuarios serviceUsuarios) {
		this.serviceUsuarios = serviceUsuarios;
	}
	
	
}