package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.TblImageArticulo;
import com.adinfi.seven.business.domain.TblImagenes;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.UploadFilesBean;

public class ABCFotosMB implements Serializable {

	private static final long serialVersionUID = -1461944179560246977L;
	private Logger LOG = Logger.getLogger(ABCFotosMB.class);
	private ServiceArquitectura serviceArquitectura;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private List<TblImageArticulo> imgArticulos;
	private String idArticulo;
	private String imagen;
	private String descripcion;
	private UploadFilesBean upload;
	private TblImageArticulo imgArtSel;
	private final String rutaFoto = GlobalDefines.RUTA_FOTO;
	
	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public List<TblImageArticulo> getImgArticulos() {
		return imgArticulos;
	}

	public void setImgArticulos(List<TblImageArticulo> imgArticulos) {
		this.imgArticulos = imgArticulos;
	}

	public ServiceArquitectura getServiceArquitectura() {
		return serviceArquitectura;
	}

	public void setServiceArquitectura(ServiceArquitectura serviceArquitectura) {
		this.serviceArquitectura = serviceArquitectura;
	}
	
	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public UploadFilesBean getUpload() {
		return upload;
	}

	public void setUpload(UploadFilesBean upload) {
		this.upload = upload;
	}
	
	public TblImageArticulo getImgArtSel() {
		if(imgArtSel == null){
			imgArtSel = new TblImageArticulo();
		}
		return imgArtSel;
	}

	public void setImgArtSel(TblImageArticulo imgArtSel) {
		this.imgArtSel = imgArtSel;
	}

	@PostConstruct
	public void init() {
		setUpload(new UploadFilesBean());
		idArticulo=null;
		imagen=null;
		descripcion=null;
		imgArticulos = new ArrayList<TblImageArticulo>();
		imgArtSel = new TblImageArticulo();
	}
	
	public void save(){
		try{
			if( idArticulo == null || "".equals(idArticulo.trim()) ){
				Messages.mensajeErroneo("Se debe introducir una clave de articulo para guardar.");
			}else if(!existeArticulo()){
				Messages.mensajeErroneo("El articulo no existe en el sistema.");
			}else if( imagen == null || "".equals(imagen.trim()) ){
				Messages.mensajeErroneo("Se debe seleccionar una imagen para guardar.");
			}else{
				TblImagenes img = new TblImagenes();
				img.setNombre(imagen);
				img.setPathFile(imagen);
				//serviceArquitectura.saveImagenes(img);
				
				TblImageArticulo imgArt = new TblImageArticulo();
				imgArt.setIdArticulo(idArticulo);
				imgArt.setPathDesc(descripcion);
				imgArt.setTblImagenes(img);
				
				Set<TblImageArticulo> sImgArt = img.getTblImageArticulos();
				sImgArt.add(imgArt);
				
				img.setTblImageArticulos(sImgArt);
				
				//if(serviceArquitectura.saveImagenArt(imgArt)){
				if(serviceArquitectura.saveImagenes(img)){
					Messages.mensajeSatisfactorio("Se guardo satisfactoriamente el registro");
					imagen=null;
					descripcion=null;
				}else{
					Messages.mensajeErroneo("Ocurrio un error al guardar el registro");
				}
			}
		}catch(Exception e){
			LOG.error(e);
			Messages.mensajeErroneo("Ocurrio un error al guardar el registro: "+e);
		}finally{
			try{
				if(existeArticulo()){
					imgArticulos=serviceArquitectura.buscaSKUById(idArticulo);
				}
			}catch(Exception e){
				LOG.error(e);
			}
		}
	}
	
	private boolean existeArticulo(){
		boolean retVal = true;
		try{
			AttrSearch attSearch = new AttrSearch();
			ArrayList<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
			
			attSearch.setAttrName("ID");
			attSearch.setValue( idArticulo );
			lstSearchAttrs.add(attSearch);    			    		
			
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs("CAT_ARTICULO", lstSearchAttrs);  
			
			if(regs==null || regs.size()<=0){
				retVal = false;
			}
		}catch(Exception e){
			LOG.error(e);
			retVal = false;
		}
		
		return retVal;
	}
	
	public void buscaSKUById(){
		try{
			if(!existeArticulo()){
				imgArticulos = null;
				Messages.mensajeErroneo("El SKU no se encuentra en el sistema.");
			}else{
				imgArticulos=serviceArquitectura.buscaSKUById(idArticulo);
				
				if(imgArticulos == null || imgArticulos.size() == 0){
					Messages.mensajeAlerta("No se encontraron imagenes relacionadas al Articulo.");
				}
			}
		}catch(Exception e){
			LOG.error(e);
			Messages.mensajeErroneo("Ocurrio un error al buscar el Articulo: " + e.getMessage());
		}
		
	}

	public void eliminarFoto(){
		if( imgArtSel==null || imgArtSel.getTblImagenes() == null ){
			Messages.mensajeErroneo("No se tiene una imagen seleccionada");
		}else{
			
			try {
				if(serviceArquitectura.deleteImagenArt(imgArtSel)){
					Messages.mensajeSatisfactorio("Se elimino correctamente la imagen.");
				}else{
					Messages.mensajeErroneo("Ocurrio un error al eliminar la imagen.");
				}
			} catch (Exception e) {
				LOG.error(e);
				Messages.mensajeErroneo("Ocurrio un error al eliminar la imagen.");
			}finally{
				try{
					imgArticulos=serviceArquitectura.buscaSKUById(idArticulo);
				}catch(Exception e){
					LOG.error(e);
				}
			}
		}
	}
	
	public void cargarFoto(FileUploadEvent event){
		String foto = upload.upload(event, GlobalDefines.RUTA_FOTO);
		setImagen(foto);
	}
	
	public void cargarDesc(FileUploadEvent event){
		String desc = upload.upload(event, GlobalDefines.RUTA_CARGA);
		setDescripcion(desc);
	}

	public String getRutaFoto() {
		return rutaFoto;
	}
}
