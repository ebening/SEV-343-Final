/**
 * 
 */
package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.TblDisenoPromoCm;
import com.adinfi.seven.business.services.ServiceEjecucion;
import com.adinfi.seven.icm.ContentManagerExport;
import com.adinfi.seven.persistence.dto.ImgEjecucionDTO;
import com.adinfi.seven.persistence.dto.NodoTreeCampana;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.TreeCampanaUtil;
import com.adinfi.seven.presentation.views.util.UploadFilesBean;
import com.ibm.mm.sdk.common.DKException;
import com.ibm.mm.sdk.common.DKUsageError;

/**
 * @author OMAR
 * 
 */
public class MBEjecucionSeven implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Logger LOG = Logger.getLogger(MBEjecucionSeven.class);	
	
	private ContentManagerExport content = new ContentManagerExport();
	
	private ServiceEjecucion serviceEjecucion;
	
	private TreeNode root;
	
	private NodoTreeCampana nodoAnteriorSeleccionado ;
	
	private List<String> datosExp;
	
	private String rutaJsp;
	
	private int idHojaSelected;
	
	public static final String OPCION_TEMPLATES = "/pages/EjecucionContent.xhtml";
		
	private String ruta = "";
	
	private UploadFilesBean upload;

	private String nombreArchivo;

	private String rutaImagen = GlobalDefines.RUTA_CM;
	
	private ImgEjecucionDTO imgEDto = new ImgEjecucionDTO();
	
	@PostConstruct
	public void initInfo() {
		setUpload(new UploadFilesBean());
		this.nodoAnteriorSeleccionado = new NodoTreeCampana();
		try {
			root = TreeCampanaUtil.createTreeCampanaSeven(0);
	        }
			catch(Exception e){e.printStackTrace();
			}		
	}
	
	public void onNodeSelect(NodeSelectEvent event) {
		
//		imgEDto = new ImgEjecucionDTO();
		
		TreeNode node 		= event.getTreeNode();
		this.rutaJsp		= Constants.EMPTY;
		
		NodoTreeCampana nodoAccion = (NodoTreeCampana) node.getData();
		if(nodoAccion.isAccion()){
			this.nodoAnteriorSeleccionado.setColor(Constants.EMPTY);
			nodoAccion.setColor(Constants.COLOR_NODO_SELECCIONADO);
			this.nodoAnteriorSeleccionado = nodoAccion;
		}

		this.idHojaSelected = nodoAccion.getId();
		
		imgEDto.setIdCampana(nodoAccion.getId());
		imgEDto.setNombreCampana(nodoAccion.getNombreCampana());
		imgEDto.setIdPrograma(nodoAccion.getIdPrograma());
		imgEDto.setIdCategoria(nodoAccion.getIdCategoria());
		imgEDto.setVigenciaIncio(nodoAccion.getFechaInicio());
		imgEDto.setVigenciaFinal(nodoAccion.getFechaFin());
		LOG.info("LABEL="+nodoAccion.getLabel());
		imgEDto.setNombreCategoria(nodoAccion.getLabel());
		imgEDto.setNombrePrograma(nodoAccion.getNombrePrograma());
		imgEDto.setPid(nodoAccion.getPid());
		existeArchivo(nodoAccion.getPid());
		
		if(this.idHojaSelected > 0){
	    	try{	    	
	    		ruta="/pages/EjecucionContentSeven.xhtml";
	    	}catch(Exception e){
	    		LOG.error(e);
	    	}
		}
	}
	
	public void upload(FileUploadEvent event){
		
		String archivoNombre=upload.upload(event, GlobalDefines.RUTA_CM);
		LOG.info("===============================================");
		LOG.info("======================"+archivoNombre);
		LOG.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		setNombreArchivo(archivoNombre);
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath=(String) servletContext.getRealPath(GlobalDefines.RUTA_CM + nombreArchivo);
		
		imgEDto.setPath(realPath);
		
		if (content.connectToServer()) {
			String pid = content.importDocument(imgEDto);
			
			TblDisenoPromoCm tblDisenoPromoCm = new TblDisenoPromoCm();
			tblDisenoPromoCm.setIdCampana(imgEDto.getIdCampana());
			tblDisenoPromoCm.setIdPrograma(imgEDto.getIdPrograma());
			tblDisenoPromoCm.setPid(pid);		
					
			try {
				TblDisenoPromoCm t = serviceEjecucion.saveDiseno(tblDisenoPromoCm);
				LOG.info("DISENO ID ====== " + t.getId());
				imgEDto.setPid(pid);
				existeArchivo(imgEDto.getPid());
			} catch (GeneralException e) {
				LOG.info(e);
			}
			
		} else {
			LOG.info("No hay Conexion CM");
		}
	}
	
	private String existeArchivo(String pid){
		
		String url = "";
		if (content.connectToServer()) {
			LOG.info("                                            Conexión Exitosa");
			try {
				if (pid.equals("") || pid == null) {
				} else {
					url = content.getUrlDocumentoByPID(pid);
				}
			} catch (DKUsageError e) {
				LOG.info(e);
			} catch (DKException e) {
				LOG.info(e);
			} catch (Exception e) {
				LOG.info(e);
			}
		}
		
		LOG.info("====================================***"+url);
		rutaImagen = url;
		return url;
	}
	
	public void cargaArchivo(){
		if(leerArchivo()){
			LOG.info("==========================01");
			Messages.mensajeSatisfactorio("Se guardo correctamente.");
		}else{			
			LOG.info("==========================02");
			Messages.mensajeErroneo("Ocurrio un error al guardars.");
		}
	}
	
	private boolean leerArchivo(){
		boolean retVal = true;
		 		
	try {
		   ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		   String realPath=(String) servletContext.getRealPath(GlobalDefines.RUTA_CM + nombreArchivo);
		   LOG.info("***********************************************");
		   LOG.info("RealPath="+realPath);
		   LOG.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		   		  		  
	} catch (Exception e) {
		 LOG.info("====================================");
		 LOG.info("Ex1="+e);
		 LOG.info("====================================");
		}		   		   		  
		   return retVal;	
	}
	
	public void eliminarPromo(){
		
		content.connectToServer();
		boolean elimino = content.deleteDocCM(imgEDto.getPid());
		
		if (elimino) {
			setNombreArchivo("");
		} else {
			LOG.info("Error al eliminar");
		}						
	}

	/**
	 * @return the root
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * @return the datosExp
	 */
	public List<String> getDatosExp() {
		return datosExp;
	}

	/**
	 * @param datosExp the datosExp to set
	 */
	public void setDatosExp(List<String> datosExp) {
		this.datosExp = datosExp;
	}

	/**
	 * @return the rutaJsp
	 */
	public String getRutaJsp() {
		return rutaJsp;
	}

	/**
	 * @param rutaJsp the rutaJsp to set
	 */
	public void setRutaJsp(String rutaJsp) {
		this.rutaJsp = rutaJsp;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public ImgEjecucionDTO getImgEDto() {
		return imgEDto;
	}

	public void setImgEDto(ImgEjecucionDTO imgEDto) {
		this.imgEDto = imgEDto;
	}	

	public String getRutaImagen() {
		return rutaImagen;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public UploadFilesBean getUpload() {
		return upload;
	}

	public void setUpload(UploadFilesBean upload) {
		this.upload = upload;
	}

	public ServiceEjecucion getServiceEjecucion() {
		return serviceEjecucion;
	}
	
	public void setServiceEjecucion(ServiceEjecucion serviceEjecucion) {
		this.serviceEjecucion = serviceEjecucion;
	}
}