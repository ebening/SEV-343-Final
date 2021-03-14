package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.TblMarcaLogo;
import com.adinfi.seven.business.domain.TblMarcaLogoDet;
import com.adinfi.seven.business.services.ServiceArquitectura;
import com.adinfi.seven.presentation.views.util.Messages;
import com.adinfi.seven.presentation.views.util.UploadFilesBean;

public class ABCMarcaLogoMB implements Serializable {

	private static final long serialVersionUID = -6586435605169309L;
	private Logger LOG = Logger.getLogger(ABCMarcaLogoMB.class);
	private ServiceArquitectura serviceArquitectura;
	private Map<String, String> marcaLogo;
	private List<TblMarcaLogoDet> marcaLogoDet;
	private Integer idMarcaLogo;
	private String imagen;
	private UploadFilesBean upload;
	private final String rutaCarga = GlobalDefines.RUTA_CARGA;

	public ServiceArquitectura getServiceArquitectura() {
		return serviceArquitectura;
	}

	public void setServiceArquitectura(ServiceArquitectura serviceArquitectura) {
		this.serviceArquitectura = serviceArquitectura;
	}

	public Map<String, String> getMarcaLogo() {
		return marcaLogo;
	}

	public void setMarcaLogo(Map<String, String> marcaLogo) {
		this.marcaLogo = marcaLogo;
	}

	public Integer getIdMarcaLogo() {
		return idMarcaLogo;
	}

	public void setIdMarcaLogo(Integer idMarcaLogo) {
		this.idMarcaLogo = idMarcaLogo;
	}

	public void generarImagenes() {
		TblMarcaLogo mr = null;
		try {
			marcaLogoDet = new ArrayList<TblMarcaLogoDet>();
			mr = serviceArquitectura.getMarcaLogo(idMarcaLogo); 
			for (TblMarcaLogoDet img : mr.getTblMarcaLogoDets()) {
				marcaLogoDet.add(img);
			}
			
		} catch (Exception e) {
			LOG.error(e);
		}finally{
			mr=null;
		}
	}

	public List<TblMarcaLogoDet> getMarcaLogoDet() {
		return marcaLogoDet;
	}

	public void setMarcaLogoDet(List<TblMarcaLogoDet> marcaLogoDet) {
		this.marcaLogoDet = marcaLogoDet;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void subirFoto(FileUploadEvent event) {
		String imagen = getUpload().upload(event, GlobalDefines.RUTA_CARGA);
		setImagen(imagen);
	}

	@PostConstruct
	public void init() {
		try {
			setUpload(new UploadFilesBean());
			setMarcaLogo(serviceArquitectura.getMarcas());
			setIdMarcaLogo(0);
			setImagen(null);
			setMarcaLogoDet(null);
		} catch (GeneralException e) {
			LOG.info(e);
		}
	}

	public void save() { 
		try {
			
			if(imagen==null || "".equals(imagen.trim()))
			{
				Messages.mensajeErroneo("No se ha seleccionado una imagen.");
			}else if( idMarcaLogo==null || idMarcaLogo<=0 ){
				Messages.mensajeErroneo("No se ha seleccionado la Marca.");
			}else{
				TblMarcaLogo ml = serviceArquitectura
						.getMarcaLogo(getIdMarcaLogo());
				TblMarcaLogoDet mld = new TblMarcaLogoDet(ml, imagen);
	
				Set<TblMarcaLogoDet> mldSet = ml.getTblMarcaLogoDets();
				mldSet.add(mld);
	
				ml.setTblMarcaLogoDets(mldSet);
	
				ml = serviceArquitectura.saveMarcas(ml);
				generarImagenes();
				resetImg();
				ml = null;
				
				Messages.mensajeSatisfactorio("Se guardo satisfactoriamente la imagen");
			}
			
		} catch (Exception e) {
			LOG.info(e);
			Messages.mensajeErroneo("Ocurrio un error al guardar el registro. "+e.getMessage());
		}

	}

	public void resetImg() {
		setImagen(null);
		setIdMarcaLogo(0);

	}

	/**
	 * @return the upload
	 */
	public UploadFilesBean getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(UploadFilesBean upload) {
		this.upload = upload;
	}

	public String getRutaCarga() {
		return rutaCarga;
	}
}