/**
 * 
 */
package com.adinfi.seven.presentation.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;

// import sun.misc.BASE64Encoder;

import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceEjecucion;
import com.adinfi.seven.persistence.dto.NodoTreeCampana;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.TreeCampanaUtil;
import com.adinfi.seven.presentation.views.util.XMLUtil;
import java.io.ByteArrayInputStream;


/**
 * @author OMAR
 * 
 */
public class MBEjecucion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger LOG = Logger.getLogger(MBEjecucion.class);
	
	
	private ServiceEjecucion serviceEjecucion;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;

	private TreeNode root;
	
	private NodoTreeCampana nodoAnteriorSeleccionado ;
	private int idHoja=0;
	private int idSistVenta=0;
	
	private List<String> datosExp;
	
	private String rutaJsp;
	
	private int idHojaSelected;
	private String image;
	
	
	public static final String OPCION_TEMPLATES = "/pages/EjecucionContent.xhtml";
		
	
	
	@PostConstruct
	public void initInfo() {
		this.nodoAnteriorSeleccionado = new NodoTreeCampana();
		try {
			root = TreeCampanaUtil.createTree(0);
	        }
			catch(Exception e){e.printStackTrace();
			}
		
	}
	
	public void viewDesign(){
		try{
			TblFolletoHojas hoja= serviceEjecucion.getHojaById(this.idHojaSelected);
			if(hoja.getDiseno()!=null){
			//	BASE64Encoder base= new BASE64Encoder();
				int blobLength = (int) hoja.getDiseno().length();  
				byte[] blobAsBytes = hoja.getDiseno().getBytes(1, blobLength);
		//		image= base.encode(blobAsBytes);
			}
			downloadFile(image.getBytes());
		}catch(Exception e){
			LOG.error(e.getMessage(),e);
		}
	}
	private UploadedFile file;
	private StreamedContent fileStream;
	//public void fileUpload(FileUploadEvent event){
	public void fileUpload(){
		try{
			//UploadedFile file = event.getFile();
			TblFolletoHojas hoja= serviceEjecucion.getHojaById(this.idHojaSelected);
			Blob imageBlob= new SerialBlob(file.getContents());
			hoja.setDiseno(imageBlob);
			serviceEjecucion.saveTblFolletoHojas(hoja);
			
		//	BASE64Encoder base= new BASE64Encoder();
			int blobLength = (int) hoja.getDiseno().length();  
			byte[] blobAsBytes = hoja.getDiseno().getBytes(1, blobLength);
		//	image= base.encode(blobAsBytes);
			
			//InputStream stream = new ByteInputStream(blobAsBytes, blobAsBytes.length);
                        InputStream stream = new ByteArrayInputStream(blobAsBytes);
			fileStream = new DefaultStreamedContent(stream, "application/pdf",
			"downloaded_file.pdf");
		}catch(Exception e){
			LOG.error(e.getMessage(),e);
		}
	}
	
	public void downloadFile(byte[] blobAsBytes) {

	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

	    response.setHeader("Content-Disposition", "attachment;filename=diseno.pdf");  
	    response.setContentLength((int) blobAsBytes.length);  
	    ServletOutputStream out = null;  
	    try {
	    	//FileInputStream input = new FileInputStream(file);
	    	//InputStream input = new ByteInputStream(blobAsBytes, blobAsBytes.length); 
                InputStream input = new ByteArrayInputStream(blobAsBytes);
	    	try{
		        
		        byte[] buffer = new byte[blobAsBytes.length];  
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
	    } finally {  
	        try {  
	            if (out != null) {  
	                out.close();  
	            }  
	        } catch (IOException err) {  
	        	LOG.error(err);
	        }  
	    }  

	}
	
	public void exportarIndesing() {
		
		List<TblArticulosHoja> articulosHoja 	= null;
		List<List<TblArticulosHoja>> lstAllArticulos 	= null;
		Document doc 							= null;
		String url 								= Constants.EMPTY;
		//int idHoja 								= 1;
		
		if( datosExp.size()>0){

			try {
				
				this.idHojaSelected=this.idHoja;
				
				articulosHoja = serviceEjecucion.getArticulosByHojaId(this.idHojaSelected);
				
				TblFolletoHojas hoja= serviceEjecucion.getHojaById(this.idHojaSelected);
				int idSistemaVenta=hoja.getIdSistemaVenta();
				
				//int idHojaPadre=hoja.getIdHojaPadre();
				//System.out.println(idHojaPadre);
				
				TblFolletoHojas hojaPadre= serviceEjecucion.getHojaById(this.idHoja);
				Set<TblFolletoHojas> childHojas= hojaPadre.getChildHojas();
				
				List<TblFolletoHojas> lstEspacios=null;
				if( childHojas!=null  ){
					System.out.println( childHojas.size());
					lstEspacios=new ArrayList<TblFolletoHojas>();
					for( TblFolletoHojas tblHoja : childHojas ){
						if(tblHoja.getIdSistemaVenta()!=idSistemaVenta ){
							continue;
						}
						//lstEspacios.add(tblHoja);		
						articulosHoja = serviceEjecucion.getArticulosByHojaId(tblHoja.getIdHoja());
						if(lstAllArticulos==null ){
							lstAllArticulos=new ArrayList<List<TblArticulosHoja>>();
						}
						lstAllArticulos.add(articulosHoja);
 
					}
					
					
					
				}else{
					
				}
				
				 
				doc = XMLUtil.createXMLEjecucion(  lstAllArticulos , datosExp,Constants.FOLLETO, String.valueOf(this.idHojaSelected ), serviceDynamicCatalogs);
				url = FacesContext.getCurrentInstance().getExternalContext().getRealPath(Constants.XML_NOMBRE_ARCHIVO);
				
				saveServerFile(doc, url);
				downloadServerFile(url);
				
			    FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else{
			Messages.mensajeAlerta("No se han seleccionado los datos a exportar");
		}
		
	}
	
	private void saveServerFile(Document doc ,String url) throws TransformerException, IOException{
		
		TransformerFactory transFact = TransformerFactory.newInstance();
		transFact.setAttribute("indent-number", new Integer(3));
		Transformer trans = transFact.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		DOMSource domSource = new DOMSource(doc);
		trans.transform(domSource, sr);
		
		PrintWriter writer = new PrintWriter(new FileWriter(url));
		writer.println(sw.toString());
		writer.close();
		
	}
	
	private void downloadServerFile(String url) throws IOException {
				
		File ficheroXLS = new File(url);
		FacesContext ctx = FacesContext.getCurrentInstance();
		FileInputStream fis = new FileInputStream(url);
		byte[] bytes = new byte[1000];
		int read = 0;

		if (!ctx.getResponseComplete()) {
		   String fileName = ficheroXLS.getName();
		   String contentType = "text/xml";
		   HttpServletResponse response =(HttpServletResponse) ctx.getExternalContext().getResponse();
		   response.setContentType(contentType);
		   response.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
		   ServletOutputStream out = response.getOutputStream();

		   while ((read = fis.read(bytes)) != -1) {
		        out.write(bytes, 0, read);
		   }

		   out.flush();
		   out.close();
		   fis.close();
		   ctx.responseComplete();
		}
	}
	
	


	
	public void onNodeSelect(NodeSelectEvent event) {
		
		TreeNode node 		= event.getTreeNode();
		this.rutaJsp		=Constants.EMPTY;
		
		this.idHoja=0;
		this.idSistVenta=0;
		

		NodoTreeCampana nodoAccion = (NodoTreeCampana) node.getData();
		
		/*
		try{
		
		TblFolletoHojas hoja= this.serviceEjecucion.getHojaById(nodoAccion.getId());
		
		if( hoja!=null && hoja.getIdSistemaVenta()>0  ){
			this.exportarIndesing();
			return ;
		}
		
		}catch(Exception e){
			
		}*/
		
		if(nodoAccion.isAccion()){
			this.nodoAnteriorSeleccionado.setColor(Constants.EMPTY);
			nodoAccion.setColor(Constants.COLOR_NODO_SELECCIONADO);
			this.nodoAnteriorSeleccionado = nodoAccion;
		}

		this.idHojaSelected = nodoAccion.getId();
		this.idHoja=nodoAccion.getIdHoja();
		this.idSistVenta=nodoAccion.getId();
		
		System.out.println("Nodo:" + nodoAccion.getLabel() + "  Accion:" + nodoAccion.isAccion()  + " Id Hoja :" + nodoAccion.getId() + " Tipo:"+ nodoAccion.getTipo());
		
		if(this.idHojaSelected > 0){
	    	try{
	    		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		    	rutaJsp = ec.getRequestContextPath() + "/faces/servlets/servletFolletos?command=load_hoja&hoja_id=" + this.idHojaSelected+"&ejecucion=true"; //"+nodoAccion.getId();
		    	
		    	HttpSession session = (HttpSession) ec.getSession(false);
		    	MBNavigator mnNav =(MBNavigator) session.getAttribute("MBNavigator");
		    	
		    	mnNav.setCurrentPage(OPCION_TEMPLATES);
		    	
		    	TblFolletoHojas hoja= serviceEjecucion.getHojaById(this.idHojaSelected);
				if(hoja.getDiseno()!=null){
					int blobLength = (int) hoja.getDiseno().length();  
					byte[] blobAsBytes = hoja.getDiseno().getBytes(1, blobLength);
					//InputStream stream = new ByteInputStream(blobAsBytes, blobAsBytes.length);
                                        InputStream stream = new ByteArrayInputStream(blobAsBytes);
					fileStream = new DefaultStreamedContent(stream, "application/pdf",
					"downloaded_file.pdf");
				}
				
	    	}catch(Exception e){
	    		LOG.error(e);
	    	}
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
	 * @return the serviceEjecucion
	 */
	public ServiceEjecucion getServiceEjecucion() {
		return serviceEjecucion;
	}



	/**
	 * @param serviceEjecucion the serviceEjecucion to set
	 */
	public void setServiceEjecucion(ServiceEjecucion serviceEjecucion) {
		this.serviceEjecucion = serviceEjecucion;
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

	public   ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return this.serviceDynamicCatalogs;
	}

	public  void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public int getIdHoja() {
		return idHoja;
	}

	public void setIdHoja(int idHoja) {
		this.idHoja = idHoja;
	}

	public int getIdSistVenta() {
		return idSistVenta;
	}

	public void setIdSistVenta(int idSistVenta) {
		this.idSistVenta = idSistVenta;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getFileStream() {
		return fileStream;
	}

	public void setFileStream(StreamedContent fileStream) {
		this.fileStream = fileStream;
	}
	
	

}
