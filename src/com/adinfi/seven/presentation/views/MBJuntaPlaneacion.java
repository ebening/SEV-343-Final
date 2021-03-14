package com.adinfi.seven.presentation.views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.TblJuntaArchivos;
import com.adinfi.seven.business.domain.TblJuntaParticipantes;
import com.adinfi.seven.business.domain.TblJuntaPlaneacion;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceJuntaPlaneacion;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;

import edu.emory.mathcs.backport.java.util.Collections;

public class MBJuntaPlaneacion implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MBJuntaPlaneacion.class);
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceJuntaPlaneacion serviceJuntaPlaneacion; 
	private int idResponsable;
	private Map<String, String> convocaAJunta;
	private String estatus;
	private Date fecha;
	private Date horaInicio;
	private Date horaFin;
	private String lugar;
	private String temasTratar;
	private String comentarios;
	private List<TblJuntaArchivos> archivosAdjuntos;
	private List<TblJuntaParticipantes> participantes;
	private JuntaPlaneacionDM juntaPlaneacionDM;
	private List<EstructuraMenu> estrMenu;
	private TblJuntaPlaneacion juntaPlaneacionSelected;
	private TblJuntaPlaneacion[] juntaPlaneacionSelecteds;
	private UsuariosDM usuariosDM;
	private UsuarioDTO[] usuarioInvitados;
	private List<UsuarioDTO> lUsuarios;
	private String[] mesesLetra = { "Enero", "Febrero", "Marzo", "Abril",
			"Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
			"Noviembre", "Diciembre" };
	private String carpetaTemp = "/Temp";
	private boolean juntaNueva;
	private TblJuntaArchivos fileDownload;
	private List<TblJuntaPlaneacion> lJuntaPlaneacionAlm;
	private int anioFiltro = 0;
	private int mesFiltro = -1;
	private boolean mostrarForm;
	private boolean blockField;
	private Map<String, String> mapStausBorrador;
	private Map<String, String> mapStausEnviada;
	private Date minDate = new Date();
	private static final String MSG_ERROR_ADJUNTAR = "Ocurrio un error al adjuntar el archivo";
	private static final String MSG_ERROR_DESCARGAR = "Ocurrio un error al descargar el archivo";
	private static final String MSG_ERROR_GUARDAR = "Ocurrio un error al guardar el archivo en carpeta del servidor";
	private int tamanioByte = 1024;

	public MBJuntaPlaneacion() {

	}
	
	public void add(){
		juntaPlaneacionSelected = new TblJuntaPlaneacion();
		Random random = new Random(); 
		int x = 1000;
		int i = random.nextInt(x) + 1;
		carpetaTemp = carpetaTemp + "/" + i;
		juntaNueva = true;
		mostrarForm = true;
		archivosAdjuntos = new ArrayList<TblJuntaArchivos>();
		juntaPlaneacionSelected.setIdEstatus(Constants.STATUS_JUNTA_BORRADOR);
		juntaPlaneacionSelected.setEstatus(mapStausBorrador.get(Constants.ATT_CODE));
	}
	
	public void edit(){
		if (juntaPlaneacionSelecteds != null && juntaPlaneacionSelecteds.length > 0){
			if (juntaPlaneacionSelecteds.length == 1){
				juntaPlaneacionSelected = juntaPlaneacionSelecteds[0];
				UsuarioDTO usrLoged = Util.getSessionAttribute("userLoged");
				if (usrLoged.getUserId() == juntaPlaneacionSelected.getIdResponsable()){
					Set<TblJuntaArchivos> sTblArchivos = juntaPlaneacionSelected.getTblArchivos();
					archivosAdjuntos = new ArrayList<TblJuntaArchivos>();
					for (TblJuntaArchivos tblArc : sTblArchivos){
						tblArc.setName(tblArc.getUrl().substring(tblArc.getUrl().lastIndexOf("/") + 1));
						archivosAdjuntos.add(tblArc);
					}
					
					Set<TblJuntaParticipantes> sTblParticipantes = juntaPlaneacionSelected.getTblParticipantes();
					if (sTblParticipantes != null){
						usuarioInvitados = new UsuarioDTO[sTblParticipantes.size()];
						int i = 0;
						for (TblJuntaParticipantes tblPart : sTblParticipantes){
							for(UsuarioDTO usu : lUsuarios){
								if (usu.getUserId() == tblPart.getIdUsuario()){
									usuarioInvitados[i] = usu;
									break;
								}
							}
							i++;
						}
					}
					juntaNueva = false;
					mostrarForm = true;
				
				}else{
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es posible modificar la Junta, solamente se modificará por el usuario que la convocó",
							"No es posible modificar la Junta, solamente se modificará por el usuario que la convocó");
					FacesContext.getCurrentInstance().addMessage(null, fm);
				}
			} else {
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar solo un registro",
						"Debe seleccionar solo un registro");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} else {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un registro",
					"Debe seleccionar un registro");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}
	
	public void show(){
		if (juntaPlaneacionSelected != null && juntaPlaneacionSelected.getIdJunta() != 0){
			Set<TblJuntaArchivos> sTblArchivos = juntaPlaneacionSelected.getTblArchivos();
			archivosAdjuntos = new ArrayList<TblJuntaArchivos>();
			for (TblJuntaArchivos tblArc : sTblArchivos){
				tblArc.setName(tblArc.getUrl().substring(tblArc.getUrl().lastIndexOf("/") + 1));
				archivosAdjuntos.add(tblArc);
			}
			
			Set<TblJuntaParticipantes> sTblParticipantes = juntaPlaneacionSelected.getTblParticipantes();
			if (sTblParticipantes != null){
				usuarioInvitados = new UsuarioDTO[sTblParticipantes.size()];
				int i = 0;
				for (TblJuntaParticipantes tblPart : sTblParticipantes){
					for(UsuarioDTO usu : lUsuarios){
						if (usu.getUserId() == tblPart.getIdUsuario()){
							usuarioInvitados[i] = usu;
							break;
						}
					}
					i++;
				}
			}
			juntaNueva = false;
			mostrarForm = true;
			
		}
	}
	
	private void savePart() throws Exception{
		if (usuarioInvitados != null){
			for (UsuarioDTO usu: usuarioInvitados){
				TblJuntaParticipantes tblParticipantes = new TblJuntaParticipantes();
				tblParticipantes.setIdUsuario(usu.getUserId());
				tblParticipantes.setTblJuntaPlaneacion(juntaPlaneacionSelected);
				serviceJuntaPlaneacion.saveJuntaParticipantes(tblParticipantes);
			}
		}
	}
	
	private void saveFields() throws Exception{
		if (archivosAdjuntos != null){
			if (juntaNueva){
				changeDirFile();
			}
			for (TblJuntaArchivos tblArchivos: archivosAdjuntos){
				if (juntaNueva){
					tblArchivos.setUrl(Constants.DIRECCION_ARCHIVOS + juntaPlaneacionSelected.getIdJunta() + "/" + tblArchivos.getName());
				}
				tblArchivos.setTblJuntaPlaneacion(juntaPlaneacionSelected);
				serviceJuntaPlaneacion.saveJuntaArchivos(tblArchivos);
			}
			
			
		}
	}
	
	public void save(){
		try{
			if (validateFields()){	
				if (archivosAdjuntos != null){
					juntaPlaneacionSelected.setTblArchivos(new HashSet<TblJuntaArchivos>(archivosAdjuntos));
				}
				
				if (usuarioInvitados != null){
					juntaPlaneacionSelected.setTblParticipantes(new HashSet<TblJuntaParticipantes>());
					for (UsuarioDTO usu: usuarioInvitados){
						TblJuntaParticipantes tblParticipantes = new TblJuntaParticipantes();
						tblParticipantes.setIdUsuario(usu.getUserId());
						tblParticipantes.setTblJuntaPlaneacion(juntaPlaneacionSelected);
						juntaPlaneacionSelected.getTblParticipantes().add(tblParticipantes);
					}
				}
				
				if (juntaNueva){
					serviceJuntaPlaneacion.saveJuntaPlaneacion(juntaPlaneacionSelected);
				} else {
					serviceJuntaPlaneacion.updateJuntaPlaneacion(juntaPlaneacionSelected);
					serviceJuntaPlaneacion.deleteJuntaArchivosByIdJunta(juntaPlaneacionSelected.getIdJunta());
					serviceJuntaPlaneacion.deleteJuntaParticipantesByIdJunta(juntaPlaneacionSelected.getIdJunta());
				}
				saveFields();
				savePart();
				juntaPlaneacionSelected = new TblJuntaPlaneacion();
				init();
				
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "La junta fue guardada exitosamente",
						"La junta fue guardada exitosamente");
				FacesContext.getCurrentInstance().addMessage(null, fm);
				mostrarForm = false;
			}
		} catch(Exception e){
			LOG.error(e);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al guaradar la junta",
					"Ocurrio un error al eliminar la junta");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}
	
	public void preDelete(){
		boolean showDialog = true;
		UsuarioDTO usrLoged = Util.getSessionAttribute("userLoged");
		if (null != juntaPlaneacionSelecteds && juntaPlaneacionSelecteds.length > 0){
			for (TblJuntaPlaneacion tblJunta : juntaPlaneacionSelecteds){
				if (tblJunta != null && tblJunta.getIdJunta() != 0){ 
					if (usrLoged.getUserId() != tblJunta.getIdResponsable()){
						FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solamente el usuario que convocó la junta puede eliminarla",
								"Solamente el usuario que convocó la junta puede eliminarla");
						FacesContext.getCurrentInstance().addMessage(null, fm);
						showDialog = false;
						break;
					}
				}
			}
		} else {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar uno o mas registros",
					"Debe seleccionar uno o mas registros");
			FacesContext.getCurrentInstance().addMessage(null, fm);
			showDialog = false;
		}
		
		if (showDialog){
			RequestContext requestContext = RequestContext.getCurrentInstance();  
			requestContext.execute("confElimJunta.show();");
		}
	}
	
	public void delete(){
		try{		
			for (TblJuntaPlaneacion tblJunta : juntaPlaneacionSelecteds){
				serviceJuntaPlaneacion.deleteJuntaArchivosByIdJunta(tblJunta.getIdJunta());
				
				serviceJuntaPlaneacion.deleteJuntaParticipantesByIdJunta(tblJunta.getIdJunta());
							
				serviceJuntaPlaneacion.deleteJuntaPlaneacion(tblJunta);
				
				deleteDir(new File(Constants.DIRECCION_ARCHIVOS + tblJunta.getIdJunta()));
			}
			
			init();
			
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Los registros se eliminaron exitosamente",
					"Los registros se eliminaron exitosamente");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		} catch(Exception e){
			LOG.error(e);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al eliminar la junta",
					"Ocurrio un error al eliminar la junta");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}
	
	public void close(){
		juntaNueva = false;
		juntaPlaneacionSelected = new TblJuntaPlaneacion();
		juntaPlaneacionSelecteds = null;
		usuarioInvitados = null;
		archivosAdjuntos = null;
		mostrarForm = false;
		blockField = false;
	}
	
	private void initUsers() throws Exception{
		lUsuarios = new ArrayList<UsuarioDTO>();
		List<CatRegs> lCatRegs = serviceDynamicCatalogs.getRegs("CAT_USER", null);
		for (CatRegs catReg : lCatRegs){
			Set<CatRegValues> setReg = catReg.getRegValues();
			if(setReg!=null){
				UsuarioDTO usuario= new UsuarioDTO();
				for(CatRegValues  regVals : setReg  ) {
					
					String attName = regVals.getCatAttrs().getAttribs().getAttrName();	
					if(attName.equals(Constants.ATT_NAME)){
						usuario.setName(regVals.getValue());
					}else if(attName.equals(Constants.ATT_PLAST_NAME)){
						usuario.setPlastName(regVals.getValue());
					}else if(attName.equals(Constants.ATT_EMAIL)){
						usuario.setEmail(regVals.getValue());
					}else if(attName.equals(Constants.ATT_ID)){
						usuario.setUserId(Integer.valueOf(regVals.getValue()));
					}
				}
				lUsuarios.add(usuario);
			}
		}
		usuariosDM = new UsuariosDM(lUsuarios);
	}
	
	private void initLJunta() throws Exception{
		List<TblJuntaPlaneacion> lJuntaPlaneacion = serviceJuntaPlaneacion.getAllJuntaPlaneacion();
		if (lJuntaPlaneacion != null){
			for (int i = 0; i < lJuntaPlaneacion.size(); i++){
				TblJuntaPlaneacion jp = lJuntaPlaneacion.get(i);
				for(UsuarioDTO usu : lUsuarios){
					if (usu.getUserId() == jp.getIdResponsable()){
						StringBuffer nombre = new StringBuffer();
						nombre.append(usu.getName());
						nombre.append(" ");
						nombre.append(usu.getPlastName());
						lJuntaPlaneacion.get(i).setNombreResponsable(nombre.toString());
						break;
					}
				}

				if (!Hibernate.isInitialized(jp.getTblArchivos())){
					Hibernate.initialize(jp.getTblArchivos());
				}
				
				if (!Hibernate.isInitialized(jp.getTblParticipantes())){
					Hibernate.initialize(jp.getTblParticipantes());
				}
				
				jp.setEstatus(Integer.valueOf(jp.getIdEstatus()).equals(2)? mapStausEnviada.get(Constants.ATT_CODE) : mapStausBorrador.get(Constants.ATT_CODE));
			}
			
			Collections.sort(lJuntaPlaneacion, new JuntaPlaneacionComparator());
			lJuntaPlaneacionAlm = lJuntaPlaneacion;
			initMenu(lJuntaPlaneacion);
		}
		
		
		
		juntaPlaneacionDM = new JuntaPlaneacionDM(lJuntaPlaneacion);
	}
	
	private void initMenu(List<TblJuntaPlaneacion> lJuntaPlaneacion){
		int iAnioTemp = 0;
		int iMesTemp = -1;
		estrMenu = new ArrayList<EstructuraMenu>();
		EstructuraMenu eM = new EstructuraMenu();
		for (TblJuntaPlaneacion tblJP : lJuntaPlaneacion){
			Calendar calFecha = Calendar.getInstance();
			calFecha.setTime(tblJP.getFecha());
			
			int iAnio = calFecha.get(Calendar.YEAR);
			int iMes = calFecha.get(Calendar.MONTH);
			if (!Integer.valueOf(iAnioTemp).equals(iAnio)) {
				if (!Integer.valueOf(iAnioTemp).equals(0)){
					estrMenu.add(eM);
				}
				iAnioTemp = iAnio;
				eM = new EstructuraMenu();
				eM.setAnio(String.valueOf(iAnio));
				eM.setMeses(new ArrayList<Meses>());
				eM.getMeses().add(new Meses(iMes, mesesLetra[iMes]));
				iMesTemp = iMes;
			} else {
				if (!Integer.valueOf(iMesTemp).equals(iMes)){
					eM.getMeses().add(new Meses(iMes, mesesLetra[iMes]));
					iMesTemp = iMes;
				}
			}
		}
		estrMenu.add(eM);
	}
	
	@PostConstruct
	public void init(){
		try{
			List<Map<String, String>> lMapStaus = MBUtil.getCatalogValues("CAT_STATUS_JUN", serviceDynamicCatalogs);
			for (Map<String, String> mapStatus : lMapStaus){
				int id = Integer.valueOf(mapStatus.get(Constants.ATT_ID));
				if (Integer.valueOf(Constants.STATUS_JUNTA_BORRADOR).equals(id)){
					mapStausBorrador = mapStatus;
				} else if (id == Constants.STATUS_JUNTA_ENVIADA){
					mapStausEnviada = mapStatus;
				}
			}
			
			initUsers();
			
			initLJunta();
			
			juntaPlaneacionSelecteds = null;
			usuarioInvitados = null;
			archivosAdjuntos = null;
			juntaNueva = false;
			blockField = false;
		} catch(Exception e){
			LOG.error(e);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al inicializar pantalla Juntas de Planeación",
					"Ocurrio un error al inicializar pantalla Juntas de Planeación");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}
	
	public void filterGrid(){
		
		if (anioFiltro == 0){
			juntaPlaneacionDM = new JuntaPlaneacionDM(lJuntaPlaneacionAlm);
		} else if (anioFiltro > 0 && mesFiltro < 0){
			List<TblJuntaPlaneacion> lJuntaPlaneacion = new ArrayList<TblJuntaPlaneacion>();
			for (TblJuntaPlaneacion tblJP : lJuntaPlaneacionAlm){
				Calendar calFecha = Calendar.getInstance();
				calFecha.setTime(tblJP.getFecha());
				
				int iAnio = calFecha.get(Calendar.YEAR);
				if (anioFiltro == iAnio){
					lJuntaPlaneacion.add(tblJP);
				}
			}
			juntaPlaneacionDM = new JuntaPlaneacionDM(lJuntaPlaneacion);
		} else {
			List<TblJuntaPlaneacion> lJuntaPlaneacion = new ArrayList<TblJuntaPlaneacion>();
			for (TblJuntaPlaneacion tblJP : lJuntaPlaneacionAlm){
				Calendar calFecha = Calendar.getInstance();
				calFecha.setTime(tblJP.getFecha());
				
				int iAnio = calFecha.get(Calendar.YEAR);
				int iMes = calFecha.get(Calendar.MONTH);
				if (anioFiltro == iAnio && mesFiltro == iMes){
					lJuntaPlaneacion.add(tblJP);
				}
			}
			juntaPlaneacionDM = new JuntaPlaneacionDM(lJuntaPlaneacion);
		}
		
		close();
		blockField = false;
		mostrarForm = false;
	}

	public void sendMail() {
		if (validateFields()){
			UsuarioDTO usuRespDTO = usuariosDM.getRowData(String
					.valueOf(juntaPlaneacionSelected.getIdResponsable()));
			StringBuffer destinatarios = new StringBuffer(); 
			destinatarios.append(usuRespDTO.getEmail());
			destinatarios.append(",");
			StringBuffer invitados = new StringBuffer();

			for (UsuarioDTO usuInviDTO : usuarioInvitados) {
				destinatarios.append(usuInviDTO.getEmail());
				destinatarios.append(",");
				invitados.append(usuInviDTO.getName());
				invitados.append(" ");
				invitados.append(usuInviDTO.getPlastName());
				invitados.append("<br/>");
			}
	
			GregorianCalendar fechaGC = new GregorianCalendar();
			fechaGC.setTime(juntaPlaneacionSelected.getFecha());
			GregorianCalendar dHoraIni = new GregorianCalendar();
			dHoraIni.setTime(juntaPlaneacionSelected.getHoraInicio());
			GregorianCalendar dHoraFin = new GregorianCalendar();
			dHoraFin.setTime(juntaPlaneacionSelected.getHoraFin());
			
			Object[] params = new Object[] {
					usuRespDTO.getName() + " " + usuRespDTO.getPlastName(),
					fechaGC.get(Calendar.DAY_OF_MONTH),
					mesesLetra[fechaGC.get(Calendar.MONTH)],
					String.valueOf(fechaGC.get(Calendar.YEAR)),
					juntaPlaneacionSelected.getLugar(),
					new SimpleDateFormat("HH:mm").format(juntaPlaneacionSelected.getHoraInicio()),
					new SimpleDateFormat("HH:mm").format(juntaPlaneacionSelected.getHoraFin()),
					juntaPlaneacionSelected.getTemasTratar(),
					juntaPlaneacionSelected.getComentarios() != null && !juntaPlaneacionSelected.getComentarios().isEmpty() ? juntaPlaneacionSelected.getComentarios()
							: "N/A",
					invitados.toString() };
			Calendar calendarIni = Calendar.getInstance();
			calendarIni.set(fechaGC.get(Calendar.YEAR), fechaGC.get(Calendar.MONTH), fechaGC.get(Calendar.DAY_OF_MONTH), dHoraIni.get(Calendar.HOUR) ,dHoraIni.get(Calendar.MINUTE));
			Calendar calendarFin = Calendar.getInstance();
			calendarFin.set(fechaGC.get(Calendar.YEAR), fechaGC.get(Calendar.MONTH), fechaGC.get(Calendar.DAY_OF_MONTH), dHoraFin.get(Calendar.HOUR) ,dHoraFin.get(Calendar.MINUTE));
			try{
				SendMail.sendGenericMeeting(calendarIni,
						calendarFin,
						usuRespDTO.getEmail(),
						destinatarios.substring(0, destinatarios.lastIndexOf(",")).split(","),
						Constants.EMAIL_JUNTA_PLANEACION_TITLE,
						Constants.EMAIL_JUNTA_PLANEACION_DETAIL, params);
				mostrarForm = false;
				juntaPlaneacionSelected.setIdEstatus(Constants.STATUS_JUNTA_ENVIADA);
				save();

				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "La invitación se envió exitosamente",
						"La invitacion se envio exitosamente");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			} catch (Exception e){
				LOG.error(e);
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al enviar la invitacion",
						"Ocurrio un error al enviar la invitacion");
				FacesContext.getCurrentInstance().addMessage(null, fm);
			}
		} 
	}
	
	private boolean validateFields(){
		boolean correcto = true;
		if (Integer.valueOf(juntaPlaneacionSelected.getIdResponsable()).equals(0)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un reponsable que convoca la junta",
					"Debe seleccionar un reponsable que convoca la junta"));
			correcto = false;
		} else if (null == juntaPlaneacionSelected.getLugar() || juntaPlaneacionSelected.getLugar().trim().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe proporcionar un lugar",
					"Debe seleccionar una fecha"));
			correcto = false;
		} else if (null == juntaPlaneacionSelected.getFecha()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una fecha",
					"Debe seleccionar una fecha"));
			correcto = false;
		} else if (null == juntaPlaneacionSelected.getHoraInicio()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una hora de inicio",
					"Debe seleccionar una hora de inicio"));
			correcto = false;
		} else if (null == juntaPlaneacionSelected.getHoraFin()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una hora final",
					"Debe seleccionar una hora final"));
			correcto = false;
		} else if (juntaPlaneacionSelected.getHoraInicio().getTime() > juntaPlaneacionSelected.getHoraFin().getTime()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La hora de inicio no puede ser mayor que la final",
					"La hora de inicio no puede ser mayor que la final"));
			correcto = false;
		}

		return correcto;
	}
	
	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public boolean isBlockField() {
		return blockField;
	}

	public void setBlockField(boolean blockField) {
		this.blockField = blockField;
	}

	public boolean isMostrarForm() {
		return mostrarForm;
	}

	public void setMostrarForm(boolean mostrarForm) {
		this.mostrarForm = mostrarForm;
	}

	public int getAnioFiltro() {
		return anioFiltro;
	}

	public void setAnioFiltro(int anioFiltro) {
		this.anioFiltro = anioFiltro;
	}

	public int getMesFiltro() {
		return mesFiltro;
	}

	public void setMesFiltro(int mesFiltro) {
		this.mesFiltro = mesFiltro;
	}

	public boolean isJuntaNueva() {
		return juntaNueva;
	}

	public void setJuntaNueva(boolean juntaNueva) {
		this.juntaNueva = juntaNueva;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public int getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}

	public Map<String, String> getConvocaAJunta() {
		return convocaAJunta;
	}

	public void setConvocaAJunta(Map<String, String> convocaAJunta) {
		this.convocaAJunta = convocaAJunta;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {

		return horaFin;
	}
	
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getTemasTratar() {
		return temasTratar;
	}

	public void setTemasTratar(String temasTratar) {
		this.temasTratar = temasTratar;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public List<TblJuntaArchivos> getArchivosAdjuntos() {
		return archivosAdjuntos;
	}

	public void setArchivosAdjuntos(List<TblJuntaArchivos> archivosAdjuntos) {
		this.archivosAdjuntos = archivosAdjuntos;
	}

	public List<TblJuntaParticipantes> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<TblJuntaParticipantes> participantes) {
		this.participantes = participantes;
	}

	public TblJuntaArchivos getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(TblJuntaArchivos fileDownload) {
		this.fileDownload = fileDownload;
	}

	public List<EstructuraMenu> getEstrMenu() {
		return estrMenu;
	}

	public void setEstrMenu(List<EstructuraMenu> estrMenu) {
		this.estrMenu = estrMenu;
	}

	public JuntaPlaneacionDM getJuntaPlaneacionDM() {
		return juntaPlaneacionDM;
	}

	public void setJuntaPlaneacionDM(JuntaPlaneacionDM juntaPlaneacionDM) {
		this.juntaPlaneacionDM = juntaPlaneacionDM;
	}

	public TblJuntaPlaneacion getJuntaPlaneacionSelected() {
		if (juntaPlaneacionSelected == null){
			juntaPlaneacionSelected = new TblJuntaPlaneacion();
		}
		return juntaPlaneacionSelected;
	}

	public void setJuntaPlaneacionSelected(
			TblJuntaPlaneacion juntaPlaneacionSelected) {
		this.juntaPlaneacionSelected = juntaPlaneacionSelected;
	}

	public UsuarioDTO[] getUsuarioInvitados() {
		return usuarioInvitados;
	}

	public void setUsuarioInvitados(UsuarioDTO[] usuarioInvitados) {
		if (usuarioInvitados != null){
			this.usuarioInvitados = usuarioInvitados.clone();
		}
	}

	public UsuariosDM getUsuariosDM() {
		return usuariosDM;
	}

	public void setUsuariosDM(UsuariosDM usuariosDM) {
		this.usuariosDM = usuariosDM;
	}
	
	public ServiceJuntaPlaneacion getServiceJuntaPlaneacion() {
		return serviceJuntaPlaneacion;
	}

	public void setServiceJuntaPlaneacion(
			ServiceJuntaPlaneacion serviceJuntaPlaneacion) {
		this.serviceJuntaPlaneacion = serviceJuntaPlaneacion;
	}

	public void agregarParticipantes(){
		if (usuariosDM == null) {
			usuariosDM = new UsuariosDM(lUsuarios);
		}
	}

	public List<UsuarioDTO> getlUsuarios() {
		return lUsuarios;
	}

	public void setlUsuarios(List<UsuarioDTO> lUsuarios) {
		this.lUsuarios = lUsuarios;
	}
	
	public TblJuntaPlaneacion[] getJuntaPlaneacionSelecteds() {
		return juntaPlaneacionSelecteds;
	}

	public void setJuntaPlaneacionSelecteds(
			TblJuntaPlaneacion[] juntaPlaneacionSelecteds) {
		if (juntaPlaneacionSelecteds != null){
			this.juntaPlaneacionSelecteds = juntaPlaneacionSelecteds.clone();
		}
	}

	public void upload(FileUploadEvent event) {
		String fileName = event.getFile().getFileName();
		try {
			TblJuntaArchivos tblArchivo = new TblJuntaArchivos();
			tblArchivo.setMimeType(event.getFile().getContentType());
			tblArchivo.setSizeFile((int)event.getFile().getSize());
			tblArchivo.setName(fileName);
			if (juntaNueva){
				copyFileTemp(fileName, event.getFile().getInputstream());
			} else {
				copyFile(fileName, event.getFile().getInputstream());
				tblArchivo.setUrl(Constants.DIRECCION_ARCHIVOS + juntaPlaneacionSelected.getIdJunta() + "/" + fileName);
			}
			if (archivosAdjuntos == null){
				archivosAdjuntos = new ArrayList<TblJuntaArchivos>();
			}
			archivosAdjuntos.add(tblArchivo);
		} catch (Exception e) {
			LOG.error(e);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_ADJUNTAR,
					MSG_ERROR_ADJUNTAR);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		LOG.debug("Finaliza carga");
	}
	
	public void downloadFile() {

	    File file = new File(fileDownload.getUrl());
	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

	    response.setHeader("Content-Disposition", "attachment;filename=" + fileDownload.getName());  
	    response.setContentLength((int) file.length());  
	    ServletOutputStream out = null;  
	    try {
	    	FileInputStream input = new FileInputStream(file); 
	    	try{
		        
		        byte[] buffer = new byte[tamanioByte];  
		        out = response.getOutputStream();  
		        
		        while (input.read(buffer) != -1) {  
		            out.write(buffer);  
		            out.flush();  
		        }  
	    	} finally {
	    		if (input != null){
	    			input.close();
	    		}
	    	}
	    		
	        FacesContext.getCurrentInstance().getResponseComplete();  
	    } catch (IOException err) {  
	    	LOG.error(err);
	    	FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_DESCARGAR,
					MSG_ERROR_DESCARGAR);
			FacesContext.getCurrentInstance().addMessage(null, fm);
	    } finally {  
	        try {  
	            if (out != null) {  
	                out.close();  
	            }  
	        } catch (IOException err) {  
	        	LOG.error(err);
	            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_DESCARGAR,
						MSG_ERROR_DESCARGAR);
				FacesContext.getCurrentInstance().addMessage(null, fm);
	        }  
	    }  

	}
	
	private void changeDirFile(){
		ServletContext servContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
		String dir = servContext.getRealPath(Constants.DIRECCION_ARCHIVOS_TEMP) + "/" + carpetaTemp + "/";
		try {
			for (TblJuntaArchivos tblJuntaArchivos : archivosAdjuntos){
				File file = new File(dir + tblJuntaArchivos.getName());
				FileInputStream fis = new FileInputStream(file);
				copyFile(tblJuntaArchivos.getName(), fis);
			}
			deleteDir(new File(dir));
		} catch(Exception e){
			LOG.error(e);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_GUARDAR,
					MSG_ERROR_GUARDAR);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		
	}
	
	private void copyFileTemp(String fileName, InputStream in) {
		 try { 
			 ServletContext servContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();  
			 
			 String destination = servContext.getRealPath(Constants.DIRECCION_ARCHIVOS_TEMP) + "/";
			 new File(destination  + carpetaTemp).mkdirs();

			 OutputStream out = null;
			 try{ 
				 out = new FileOutputStream(new File(destination + carpetaTemp + "/" + fileName));
				 int read = 0; byte[] bytes = new byte[tamanioByte]; 
				 while ((read = in.read(bytes)) != -1) { out.write(bytes, 0, read); } 
			 } finally { 
				 try { 
					 if (out != null){
						 out.flush(); out.close(); in.close();
					 }
				 } catch (IOException e){
					 LOG.error(e);
					 FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_ADJUNTAR,
								MSG_ERROR_ADJUNTAR);
					 FacesContext.getCurrentInstance().addMessage(null, fm);
				 } 
			 }
		 } catch (Exception e) { 
			 LOG.error(e);
			 FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_ADJUNTAR,
						MSG_ERROR_ADJUNTAR);
			 FacesContext.getCurrentInstance().addMessage(null, fm);
		 } 

	}

	private void copyFile(String fileName, InputStream in) {
		 try { 
			 String destination = Constants.DIRECCION_ARCHIVOS + juntaPlaneacionSelected.getIdJunta();
			 new File(destination).mkdirs();		 
			 OutputStream out = null;

			 try{ 
				 out = new FileOutputStream(new File(destination + "/" + fileName));
				 int read = 0; byte[] bytes = new byte[tamanioByte];
				 while ((read = in.read(bytes)) != -1) { out.write(bytes, 0, read); } 
			 } finally { 
				 try { 
					 if (out != null){
						 out.flush(); out.close(); in.close();
					 }
				 } catch (IOException e){
					 LOG.error(e);
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_GUARDAR,
							MSG_ERROR_GUARDAR);
					FacesContext.getCurrentInstance().addMessage(null, fm);
				 } 
			 }

		 } catch (Exception e) { 
			 LOG.error(e);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_GUARDAR,
					MSG_ERROR_GUARDAR);
			FacesContext.getCurrentInstance().addMessage(null, fm);
		 } 

	}
	
	public boolean deleteDir(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return dir.delete();
	}

	public static class EstructuraMenu implements java.io.Serializable{
		private static final long serialVersionUID = 8421849073332760082L;
		private String anio;
		private List<Meses> meses;

		public String getAnio() {
			return anio;
		}

		public void setAnio(String anio) {
			this.anio = anio;
		}

		public List<Meses> getMeses() {
			return meses;
		}

		public void setMeses(List<Meses> meses) {
			this.meses = meses;
		}

	}
	
	public static class Meses implements java.io.Serializable{
		private static final long serialVersionUID = -3045384573413258758L;
		private int iMes;
		private String sMes;
		
		public Meses(int iMes, String sMes){
			this.iMes = iMes;
			this.sMes = sMes;
		}
		
		public int getiMes() {
			return iMes;
		}
		public void setiMes(int iMes) {
			this.iMes = iMes;
		}
		public String getsMes() {
			return sMes;
		}
		public void setsMes(String sMes) {
			this.sMes = sMes;
		}
		
	}

	public static class JuntaPlaneacionDM extends ListDataModel<TblJuntaPlaneacion>
			implements SelectableDataModel<TblJuntaPlaneacion>, java.io.Serializable{

		private static final long serialVersionUID = 4971939606451981707L;

		public JuntaPlaneacionDM() {
		}

		public JuntaPlaneacionDM(List<TblJuntaPlaneacion> data) {
			super(data);
		}

		@Override
		public TblJuntaPlaneacion getRowData(String rowKey) {
			@SuppressWarnings("unchecked")
			List<TblJuntaPlaneacion> lJuntas = (List<TblJuntaPlaneacion>) getWrappedData();
			for (TblJuntaPlaneacion tblJP : lJuntas) {
				if (tblJP.getIdJunta() == Integer.valueOf(rowKey).intValue()) {
					return tblJP;
				}
			}
			return new TblJuntaPlaneacion();
		}

		@Override
		public Object getRowKey(TblJuntaPlaneacion tblJP) {
			return tblJP.getIdJunta();
		}

	}

	public static class UsuariosDM extends ListDataModel<UsuarioDTO> implements
			SelectableDataModel<UsuarioDTO>, java.io.Serializable{

		private static final long serialVersionUID = -7589249420600674540L;

		public UsuariosDM(List<UsuarioDTO> lUsuarioDTO) {
			super(lUsuarioDTO);
		}

		@Override
		public UsuarioDTO getRowData(String idUsuario) {
			@SuppressWarnings("unchecked")
			List<UsuarioDTO> lUsuario = (List<UsuarioDTO>) getWrappedData();;
			for (UsuarioDTO usu : lUsuario) {
				if (usu.getUserId() == Integer.valueOf(idUsuario).intValue()) {
					return usu;
				}
			}
			return new UsuarioDTO();
		}

		@Override
		public Object getRowKey(UsuarioDTO usu) {
			return usu.getUserId();
		}

	}
	
	public static class JuntaPlaneacionComparator implements Comparator<TblJuntaPlaneacion>{
		public int compare(TblJuntaPlaneacion tblJunta1, TblJuntaPlaneacion tblJunta2){
			return tblJunta1.getFecha().compareTo(tblJunta2.getFecha());
		}
	}

}
