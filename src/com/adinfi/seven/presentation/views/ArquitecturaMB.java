package com.adinfi.seven.presentation.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.TblArchivoArticulo;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblComentarioArticulo;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateSegments;
import com.adinfi.seven.business.domain.TblTemplateUser;
import com.adinfi.seven.business.domain.TemplateVO;
import com.adinfi.seven.business.domain.ViewTemplateUser;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceFolleto;
import com.adinfi.seven.persistence.daos.TemplateDAO;
import com.adinfi.seven.persistence.dto.ArticulosHojaDataModel;
import com.adinfi.seven.persistence.dto.CategoriaDTO;
import com.adinfi.seven.persistence.dto.NodoTreeCampana;
import com.adinfi.seven.persistence.dto.TemplateDataModel;
import com.adinfi.seven.persistence.dto.TemplateUserDTO;
import com.adinfi.seven.persistence.dto.TemplateUserDataModel;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.TreeCampanaUtil;
import com.adinfi.seven.presentation.views.util.UploadFilesBean;

public class ArquitecturaMB implements Serializable {
	
	private static final long serialVersionUID = 5950449003496757438L;
	
	public static final String OPCION_ASOCIACION = "/pages/ArqPersonaTemplate.xhtml";
	public static final String OPCION_TEMPLATES = "/pages/ArqTemplate.xhtml";
	public static final String OPCION_PRECIOS = "/pages/ArqPrecios.xhtml";
	public static final String OPCION_ASOCIACION_CREAR = "/pages/ArqPersonaTemplateNuevo.xhtml";
	public static final String OPCION_ASOCIACION_EDITAR = "/pages/ArqPersonaTemplateNuevo.xhtml";
	public static final String OPCION_TEMPLATES_CREAR = "/pages/ArqTemplateCon.xhtml";
	public static final String OPCION_FOLLETO_PREVIEW = "/pages/ArqPreview.xhtml";	
	public static final String OPCION_TEMPLATES_EDITAR = "/pages/ArqTemplateCon.xhtml";
	public static final String OPCION_PRECIOS_CARGAR = "/pages/ArqPreciosCarga.xhtml";
	
	private static final int BUFFER_DIV = 1024;
	
	private Logger LOG = Logger.getLogger(ArquitecturaMB.class);
	private TemplateDataModel templateDataModel;
	private TblTemplate[] templates;
	private TemplateUserDataModel templateUserDataModel;
	private TemplateUserDTO[] templateUser;
	private TemplateUserDTO tempDTO;
	private ServiceArquitectura serviceArquitectura;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceFolleto serviceFolleto;
	
	private Map<String, String> cmbUsuarios;
	private Integer usuarioId = null;
	private Map<String, String> cmbCategorias;
	private Integer categoriaId = null;
	private Map<String, String> cmbTemplates;
	private Integer templateId = null;
	
	/**PopUps*/
	private Map<String, String> cmbTempSeg;
	private Integer tempSegId;
	private Integer segmentoId=0;
	private Integer hojaId=0;
	private String comentario;
	private Integer templateIdSel;
	private Integer numEleDel = 0;
	private TblArchivoArticulo archivoArticulo;
	private List<TblComentarioArticulo> coment;
	private List<TblArchivoArticulo> archivosAdjuntos;
	private ArticulosHojaDataModel articulosHojaDM;
	private TblArticulosHoja[] articulosHojaDelSel;
	
	private TemplateVO templateVo;
	
	private TblTemplate tblTemplateGen;
	private Set<TblTemplateSegments> tsRowGen;
	private Set<TblTemplateSegments> tsColGen;
	private TblTemplateSegments tsParent;
	
	private String opMostrada;
	private String rutaJsp;
	private UploadFilesBean upload;
	private String archivo;
	private String tipo;
	
	/**Resize image*/
	private int w_img = 0;
	private int h_img = 0;
	private String id_img_resize = "";
	
	/**Tree*/
	private TreeNode root;
	private NodoTreeCampana nodoAnteriorSeleccionado = new NodoTreeCampana();

	public ArquitecturaMB() {
		
	}
	
	@PostConstruct
	public void init() {
		recreateModel();
		cargarCmbTempUser();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    rutaJsp = ec.getRequestContextPath() + "/pages/ArqGenTemplate.jsp";
	    setUpload(new UploadFilesBean());
	    try {
			root = TreeCampanaUtil.createTree(0);
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void onNodeSelect(NodeSelectEvent event)  {
		rutaJsp="";
		TreeNode node = event.getTreeNode();

		NodoTreeCampana nodoAccion = (NodoTreeCampana) node.getData();
		if(nodoAccion.isAccion()){
			this.nodoAnteriorSeleccionado.setColor(Constants.EMPTY);
			nodoAccion.setColor(Constants.COLOR_NODO_SELECCIONADO);
			this.nodoAnteriorSeleccionado = nodoAccion;
		}

		if( nodoAccion.getLabel()!=null && nodoAccion.getLabel().equals("Folleto") ){
			int idFolleto= nodoAccion.getId();
			
    		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    	rutaJsp =  ec.getRequestContextPath() + "/faces/servlets/servletFolletos?command=display_preview&idFolleto=" +idFolleto;
	    	HttpSession session = (HttpSession) ec.getSession(false);
	    	MBNavigator mnNav =(MBNavigator) session.getAttribute("MBNavigator");
	    	
	    	mnNav.setCurrentPage(OPCION_FOLLETO_PREVIEW);
	    	
	    	return;
	    	
		}	
		
		
		
		
		LOG.info("Nodo:" + nodoAccion.getLabel() + "  Accion:" + nodoAccion.isAccion()  + " Id Hoja :" + nodoAccion.getId() + " Tipo:"+ nodoAccion.getTipo());
		
		Character estatus = null;
		try {
			estatus = serviceFolleto.getHoja(nodoAccion.getId()).getEstatus();
		} catch (Exception e1) {
			LOG.error("Fallo ");
		}
		
		if(estatus == null || estatus.equals("")){
			estatus = 'A';
		}
		
		LOG.info(estatus);
		
		if(nodoAccion.getId() > 0){
	    	try{
	    		
	    		
	    		
	    		
		    		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			    	rutaJsp = (estatus.equals('C')) ?  ec.getRequestContextPath() + "/faces/servlets/servletFolletos?command=hojaBlanco" : ec.getRequestContextPath() + "/faces/servlets/servletFolletos?command=load_hoja&hoja_id="+nodoAccion.getId();
			    	HttpSession session = (HttpSession) ec.getSession(false);
			    	MBNavigator mnNav =(MBNavigator) session.getAttribute("MBNavigator");
			    	
			    	mnNav.setCurrentPage(OPCION_TEMPLATES_CREAR);
		    	
	    		
	    	}catch(Exception e){
	    		LOG.error(e);
	    	}
		}
	}

	private void recreateModel() {
		try {
			setTemplateDataModel(new TemplateDataModel(getListTemplates()));
			setTemplateUserDataModel(new TemplateUserDataModel(getListTemplatesUser()));
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public String preCrearTemplate(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    rutaJsp = ec.getRequestContextPath() + "/faces/pages/ArqGenTemplate.jsp";
	    opMostrada = OPCION_TEMPLATES_CREAR;
		return opMostrada;
	}
	
	public String preEditarTemplate(){
		opMostrada = editTemplate();
		return opMostrada;
	}
	
	public void createTempleate(){
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("/seven/servlets/servletTemplate?command=hola");
		} catch (IOException e) {
			LOG.error(e);
		}
	}
	
	public void deleteTempleate(){
		try {
			if(templates==null || templates.length<=0){
				Messages.mensajeErroneo("!Se debe seleccionar al menos un elemento!");
				return;
			}else{
				serviceArquitectura.deleteTemplate(templates);
			}
		} catch (Exception e) {
			LOG.error(e);
			Messages.mensajeErroneo("Error al eliminar el registro : " + e.getMessage());
		}finally{
			recreateModel();
		}
		Messages.mensajeSatisfactorio("Se elimino satisfactoriamente.");
	}
	
	public String editTemplate(){
		String op = OPCION_TEMPLATES_EDITAR;
		try {
			if(templates == null || templates.length != 1 ){
				Messages.mensajeErroneo("!Se debe seleccionar un elemento!");
				op=OPCION_TEMPLATES;
			}else{
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			    rutaJsp = ec.getRequestContextPath() + "/faces/pages/ArqGenTemplate.jsp";
				rutaJsp = rutaJsp + "?template_id=" + templates[0].getTemplateId();
			}
		} catch (Exception e) {
			Messages.mensajeAlerta("Error al cargar el registro : " + e.getMessage());
			op=OPCION_TEMPLATES;
			LOG.error(e);
		}
		return op;
	}
	
	public String asociarPersonaTemplate() {
		String msj = "";
		try {
			
			List<String> valid = validaTempUserInsert();
			if(valid.size() > 0)
			{
				for (String val : valid) {
					Messages.mensajeErroneo(val);
				}
				return opMostrada;
			}
			
			TblTemplateUser tblTempUser=new TblTemplateUser();
			
			msj="El resgistro se edito satisfactoriamente.";
			if(tempDTO != null && tempDTO.getIdTemplateUser() > 0){
				tblTempUser = serviceArquitectura.getTemplateUser(tempDTO.getIdTemplateUser());
				msj="El resgistro se guardo satisfactoriamente.";
			}
			
			tblTempUser.setIdCategory(categoriaId.intValue());
			tblTempUser.setIdUser(usuarioId.intValue());
			tblTempUser.setTblTemplate(serviceArquitectura.getTemplate(templateId));
			
			if(serviceArquitectura.usedTemplateByUser(tblTempUser)){
				Messages.mensajeErroneo("!La presente asociación que intenta guardar ya existe!");
				tblTempUser=null;
				return opMostrada;
			}
			
			serviceArquitectura.saveTemplateUser(tblTempUser);
			opMostrada = OPCION_ASOCIACION;
		} catch (Exception e) {
			Messages.mensajeAlerta("Error al guardar el registro : " + e.getMessage());
			LOG.error(e);
			return opMostrada;
		}
		Messages.mensajeSatisfactorio(msj);
		return opMostrada;
	}
	
	private List<String> validaTempUserInsert(){
		List<String> retVal = new ArrayList<String>();
		if(usuarioId == null || usuarioId < 1){
			retVal.add("Se debe seleccionar una Persona");
		}
		
		if(categoriaId == null || categoriaId < 1){
			retVal.add("Se debe seleccionar una Categoria");
		}
		
		if(templateId == null || templateId < 1){
			retVal.add("Se debe seleccionar un Template");
		}
		return retVal;
	}
	
	public String preCrearAsociacion(){
		limpiarVentanaAsociacion(OPCION_ASOCIACION_CREAR);
		opMostrada = OPCION_ASOCIACION_CREAR;
		return opMostrada;
	}
	
	public String preEdicionAsociacion(){
		limpiarVentanaAsociacion(OPCION_ASOCIACION_EDITAR);
		opMostrada = editarAsocioPersonaTemplate();
		return opMostrada;
	}
	
	public String editarAsocioPersonaTemplate(){
		String op=OPCION_ASOCIACION_EDITAR;
		try{
			if(templateUser == null || templateUser.length == 0 || templateUser.length > 1){
				Messages.mensajeErroneo("!Se debe seleccionar un elemento!");
				op=OPCION_ASOCIACION;
			}else{
				setTempDTO(templateUser[0]);
				
				setUsuarioId(tempDTO.getUsuario().getUserId());
				setCategoriaId(tempDTO.getCategoria().getId());
				setTemplateId(tempDTO.getTblTemplate().getTemplateId());
				
				setCmbUsuarios(serviceArquitectura.getUsuarios(serviceDynamicCatalogs));
				setCmbCategorias(serviceArquitectura.getCategoriasByUsuario(serviceDynamicCatalogs, usuarioId));
				setCmbTemplates(serviceArquitectura.getTemplatesUser());
				
			}
		}catch (Exception e){
			Messages.mensajeAlerta("Error al cargar el registro : " + e.getMessage());
			op=OPCION_ASOCIACION;
			LOG.error(e);
		}
		return op;
	}
	
	public void eliminarAsociacionPersona(){
		try{
			if(templateUser == null || templateUser.length == 0){
				Messages.mensajeErroneo("!Se debe seleccionar al menos un elemento!");
				return;
			}else{
				for (TemplateUserDTO tdto : templateUser) {
					TblTemplateUser tudel = serviceArquitectura.getTemplateUser(tdto.getIdTemplateUser());
					serviceArquitectura.deleteTemplateUser(tudel);
				}
			}
		}catch (Exception e){
			Messages.mensajeAlerta("Error al eliminar el registro : " + e.getMessage());
			LOG.error(e);
		}finally{
			recreateModel();
		}
		
		Messages.mensajeSatisfactorio("Se elimino satisfactoriamente.");
	}
	
	private List<TblTemplate> getListTemplates(){
		List<TblTemplate> retVal= new ArrayList<TblTemplate>();
		try {
			retVal = serviceArquitectura.getAllTemplate();
			Collections.sort(retVal, new Comparator<TblTemplate>() {
				@Override
				public int compare(TblTemplate p1, TblTemplate p2) {
					return p1.getTemplateName().compareTo( p2.getTemplateName() );
				}
			});
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return retVal;
	}
	
	private List<TemplateUserDTO> getListTemplatesUser(){
		List<TemplateUserDTO> retVal= new ArrayList<TemplateUserDTO>();
		try {
			List<ViewTemplateUser> tUsers = serviceArquitectura.getPersonaTemplate();
			TemplateUserDTO tuDTO = null;
			TblTemplate tblTemplate = null;
			for (ViewTemplateUser tUser : tUsers) {
				tuDTO = new TemplateUserDTO();
				tblTemplate = serviceArquitectura.getTemplate( tUser.getTemplateId() );
				tuDTO.setIdTemplateUser( tUser.getIdTemplateUser() );
				tuDTO.setTblTemplate( tblTemplate );
				tuDTO.setUsuario( getUsuarioDTO( tUser ) );
				tuDTO.setCategoria( getCategoriaDTO( tUser ) );
				
				retVal.add(tuDTO);
			}
			
			if(retVal != null){
				Collections.sort(retVal, new Comparator<TemplateUserDTO>() {
					@Override
					public int compare(TemplateUserDTO p1, TemplateUserDTO p2) {
						return p1.getTblTemplate().getTemplateName().compareTo( p2.getTblTemplate().getTemplateName() );
					}
				});
			}
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return retVal;
	}
	
	private UsuarioDTO getUsuarioDTO(ViewTemplateUser vtempUser){
		UsuarioDTO usDTO = new UsuarioDTO();
		try {
			usDTO.setUserId(vtempUser.getIdUser());
			usDTO.setName(vtempUser.getUsuario());
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return usDTO; 
	}
	
	private CategoriaDTO getCategoriaDTO(ViewTemplateUser vtempUser){
		CategoriaDTO catDTO = new CategoriaDTO();
		try {
			catDTO.setId(vtempUser.getIdCategory());
			catDTO.setCodigo(vtempUser.getCategoria());
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return catDTO; 
	}
	
	public void cargarCmbTempUser(){
		try {
			if(usuarioId != null && usuarioId > 0){
				setCmbCategorias(serviceArquitectura.getCategoriasByUsuario(serviceDynamicCatalogs, usuarioId));
				setCategoriaId(null);
			}else{
				setCmbUsuarios(serviceArquitectura.getUsuarios(serviceDynamicCatalogs));
				setCmbCategorias(null);
				setUsuarioId(null);
				setCategoriaId(null);
			} 
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	private void limpiarVentanaAsociacion(String op){
		try {
			setUsuarioId(null);
			setCategoriaId(null);
			setCmbCategorias(null);
			setCmbUsuarios(null);
			setCmbTemplates(serviceArquitectura.getTemplatesUser());
			setTemplateId(null);
			cargarCmbTempUser();
			if(!OPCION_ASOCIACION_EDITAR.equals(op)){
				setTempDTO(null);
			}
		} catch (GeneralException e) {
			LOG.error(e);
		}
	}
	
	public void cargarTemplateVo(){
		TemplateDAO tdao=new TemplateDAO();
		try {
			templateVo = tdao.getTemplate(templates[0].getTemplateId());
		} catch (Exception e) {
			LOG.error(e);
		}finally{
			tdao=null;
		}
	}
	
	public void downloadFile() {
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		String filePath = extContext.getRealPath( GlobalDefines.RUTA_CARGA + archivoArticulo.getPathArchivo() );
		
	    File file = new File(filePath);
	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

	    response.setHeader("Content-Disposition", "attachment;filename=" + archivoArticulo.getPathArchivo());  
	    response.setContentLength((int) file.length());  
	    ServletOutputStream out = null;  
	    try {
	    	FileInputStream input = new FileInputStream(file); 
	    	try{
		        
		        byte[] buffer = new byte[BUFFER_DIV];  
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
	    	/**FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_DESCARGAR,
					MSG_ERROR_DESCARGAR);
			FacesContext.getCurrentInstance().addMessage(null, fm);*/
	    } finally {  
	        try {  
	            if (out != null) {  
	                out.close();  
	            }  
	        } catch (IOException err) {  
	        	LOG.error(err);
	            /**FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR_DESCARGAR,
						MSG_ERROR_DESCARGAR);
				FacesContext.getCurrentInstance().addMessage(null, fm);*/
	        }  
	    }  

	}
	
	public void verificarCoincidenciaTemplate(){
		try {
			TblTemplate templateSel = serviceArquitectura.getTemplate(templateIdSel);
			TblFolletoHojas folletoHoja = serviceFolleto.getHoja(hojaId);
			
			List<TblTemplateSegments> segmentDisp = new ArrayList<TblTemplateSegments>();
    		for (TblTemplateSegments segment : templateSel.getTblTemplateSegments()) {
    			if( 'N' != segment.getTipo() ){
    				continue;
    			}
    			
    			segmentDisp.add(segment);
			}
    		
    		List<TblArticulosHoja> artPrincipales = serviceFolleto.getArticulosHojaPrincipales(folletoHoja.getIdHoja());
    		
    		if(artPrincipales != null && artPrincipales.size() > segmentDisp.size()){
    			numEleDel = artPrincipales.size() - segmentDisp.size();
    			setArticulosHojaDM(new ArticulosHojaDataModel(artPrincipales));
    		}else{
    			numEleDel = 0;
    			setArticulosHojaDM(new ArticulosHojaDataModel( new ArrayList<TblArticulosHoja>() ));
    		}
		} catch (Exception e) {
			LOG.error(e);
		}
	}
	
	public void eliminarArticulosSeleccionados(){
		try{
			if(articulosHojaDelSel==null || articulosHojaDelSel.length <= 0){
				Messages.mensajeErroneo("Se debe seleccionar al menos un elemento para eliminar.");
			}else{
				for (TblArticulosHoja articuloHoja : articulosHojaDelSel) {
					serviceFolleto.deleteArticulosHoja(articuloHoja);
				}
			}
		}catch(Exception e){
			LOG.error(e);
		}finally{
			verificarCoincidenciaTemplate();
		}
	}
	
	public void cambiarTemplate(){
		if( numEleDel != null && numEleDel > 0){
			Messages.mensajeErroneo("Se deben eliminar un total de " + numEleDel + " elementos para poder cambiar el Template.");
		}else{
			numEleDel=0;
		}
	}
	
	public void cargarArchivo(FileUploadEvent event){
		String arch = upload.upload(event, GlobalDefines.RUTA_CARGA);
		setArchivo(arch);
	}
	
	public void initComment(){
		try{
			HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			List<TblComentarioArticulo> lstComentario = (List<TblComentarioArticulo>) req.getSession().getAttribute(GlobalDefines.SESS_CURR_COMENT );
			
			coment= serviceArquitectura.getComentarioArticulo(segmentoId, hojaId);
			
			if(lstComentario != null && lstComentario.size() > 0)
			{
				for (TblComentarioArticulo comentariosAdj : lstComentario) {
					if(comentariosAdj.getSegmentId() == segmentoId.intValue() && comentariosAdj.getIdHoja() == hojaId.intValue()){
						coment.add(0, comentariosAdj);
					}
				}
			}
			
		}catch(Exception e){
			LOG.error(e);
		}
	}
	
	public void initArchivosAdjuntos(){
		try{
			HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			List<TblArchivoArticulo> lstArchivos=(List<TblArchivoArticulo>)req.getSession().getAttribute(GlobalDefines.SESS_CURR_ARCHIVO );
			
			archivosAdjuntos=serviceArquitectura.getArchivoArticulo(segmentoId, hojaId);
			
			if(lstArchivos != null && lstArchivos.size() > 0)
			{
				for (TblArchivoArticulo archivosAdj : lstArchivos) {
					if(archivosAdj.getSegmentId() == segmentoId.intValue() && archivosAdj.getIdHoja() == hojaId.intValue()){
						archivosAdjuntos.add(0, archivosAdj);
					}
				}
			}
			
		}catch(Exception e){
			LOG.error(e);
		}
	}

	public TemplateDataModel getTemplateDataModel() {
		return templateDataModel;
	}

	public void setTemplateDataModel(TemplateDataModel templateDataModel) {
		this.templateDataModel = templateDataModel;
	}

	public TblTemplate[] getTemplates() {
		return templates;
	}

	public void setTemplates(TblTemplate[] templates) {
		this.templates = templates.clone();
	}

	public ServiceArquitectura getServiceArquitectura() {
		return serviceArquitectura;
	}

	public void setServiceArquitectura(ServiceArquitectura serviceArquitectura) {
		this.serviceArquitectura = serviceArquitectura;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public TemplateUserDataModel getTemplateUserDataModel() {
		return templateUserDataModel;
	}

	public void setTemplateUserDataModel(TemplateUserDataModel templateUserDataModel) {
		this.templateUserDataModel = templateUserDataModel;
	}

	public TemplateUserDTO[] getTemplateUser() {
		return templateUser;
	}

	public void setTemplateUser(TemplateUserDTO[] templateUser) {
		this.templateUser = templateUser.clone();
	}

	public Map<String, String> getCmbUsuarios() {
		return cmbUsuarios;
	}

	public void setCmbUsuarios(Map<String, String> cmbUsuarios) {
		this.cmbUsuarios = cmbUsuarios;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Map<String, String> getCmbCategorias() {
		return cmbCategorias;
	}

	public void setCmbCategorias(Map<String, String> cmbCategorias) {
		this.cmbCategorias = cmbCategorias;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Map<String, String> getCmbTemplates() {
		return cmbTemplates;
	}

	public void setCmbTemplates(Map<String, String> cmbTemplates) {
		this.cmbTemplates = cmbTemplates;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public TemplateUserDTO getTempDTO() {
		return tempDTO;
	}

	public void setTempDTO(TemplateUserDTO tempDTO) {
		this.tempDTO = tempDTO;
	}

	public Integer getSegmentoId() {
		return segmentoId;
	}

	public void setSegmentoId(Integer segmentoId) {
		this.segmentoId = segmentoId;
	}

	public Map<String, String> getCmbTempSeg() {
		try {
			cmbTempSeg=serviceArquitectura.getTemplatesUser();
		} catch (GeneralException e) {
			LOG.error(e);
		}
		return cmbTempSeg;
	}

	public void setCmbTempSeg(Map<String, String> cmbTempSeg) {
		this.cmbTempSeg = cmbTempSeg;
	}

	public Integer getTempSegId() {
		return tempSegId;
	}

	public void setTempSegId(Integer tempSegId) {
		this.tempSegId = tempSegId;
	}

	public TemplateVO getTemplateVo() {
		return templateVo;
	}

	public void setTemplateVo(TemplateVO templateVo) {
		this.templateVo = templateVo;
	}

	public TblTemplate getTblTemplateGen() {
		return tblTemplateGen;
	}

	public void setTblTemplateGen(TblTemplate tblTemplateGen) {
		this.tblTemplateGen = tblTemplateGen;
	}

	public Set<TblTemplateSegments> getTsRowGen() {
		return tsRowGen;
	}

	public void setTsRowGen(Set<TblTemplateSegments> tsRowGen) {
		this.tsRowGen = tsRowGen;
	}

	public Set<TblTemplateSegments> getTsColGen() {
		return tsColGen;
	}

	public void setTsColGen(Set<TblTemplateSegments> tsColGen) {
		this.tsColGen = tsColGen;
	}

	public TblTemplateSegments getTsParent() {
		return tsParent;
	}

	public void setTsParent(TblTemplateSegments tsParent) {
		this.tsParent = tsParent;
	}

	public String getRutaJsp() {
		return rutaJsp;
	}

	public void setRutaJsp(String rutaJsp) {
		this.rutaJsp = rutaJsp;
	}

	public UploadFilesBean getUpload() {
		return upload;
	}

	public void setUpload(UploadFilesBean upload) {
		this.upload = upload;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getHojaId() {
		return hojaId;
	}

	public void setHojaId(Integer hojaId) {
		this.hojaId = hojaId;
	}

	public List<TblComentarioArticulo> getComent() {
		return coment;
	}

	public void setComent(List<TblComentarioArticulo> coment) {
		this.coment = coment;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<TblArchivoArticulo> getArchivosAdjuntos() {
		return archivosAdjuntos;
	}

	public void setArchivosAdjuntos(List<TblArchivoArticulo> archivosAdjuntos) {
		this.archivosAdjuntos = archivosAdjuntos;
	}

	public TblArchivoArticulo getArchivoArticulo() {
		return archivoArticulo;
	}

	public void setArchivoArticulo(TblArchivoArticulo archivoArticulo) {
		this.archivoArticulo = archivoArticulo;
	}

	public Integer getTemplateIdSel() {
		return templateIdSel;
	}

	public void setTemplateIdSel(Integer templateIdSel) {
		this.templateIdSel = templateIdSel;
	}

	public Integer getNumEleDel() {
		return numEleDel;
	}

	public void setNumEleDel(Integer numEleDel) {
		this.numEleDel = numEleDel;
	}

	public ServiceFolleto getServiceFolleto() {
		return serviceFolleto;
	}

	public void setServiceFolleto(ServiceFolleto serviceFolleto) {
		this.serviceFolleto = serviceFolleto;
	}

	public ArticulosHojaDataModel getArticulosHojaDM() {
		return articulosHojaDM;
	}

	public void setArticulosHojaDM(ArticulosHojaDataModel articulosHojaDM) {
		this.articulosHojaDM = articulosHojaDM;
	}

	public TblArticulosHoja[] getArticulosHojaDelSel() {
		return articulosHojaDelSel;
	}

	public void setArticulosHojaDelSel(TblArticulosHoja[] articulosHojaDelSel) {
		this.articulosHojaDelSel = articulosHojaDelSel.clone();
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public int getW_img() {
		return w_img;
	}

	public void setW_img(int w_img) {
		this.w_img = w_img;
	}

	public int getH_img() {
		return h_img;
	}

	public void setH_img(int h_img) {
		this.h_img = h_img;
	}

	public String getId_img_resize() {
		return id_img_resize;
	}

	public void setId_img_resize(String id_img_resize) {
		this.id_img_resize = id_img_resize;
	}
}
