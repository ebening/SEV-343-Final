package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCadenaAutorizacion;
import com.adinfi.seven.business.domain.TblCadenaAutorizacionDet;
import com.adinfi.seven.business.domain.TblCadenaAutorizacionDetId;
import com.adinfi.seven.business.services.ServiceCadenaAutorizacion;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.CadenaAutorizacionDTO;
import com.adinfi.seven.persistence.dto.CadenaAutorizacionDTODataModel;
import com.adinfi.seven.persistence.dto.CadenaAutorizacionDetDTO;
import com.adinfi.seven.persistence.dto.CadenaAutorizacionDetDTODataModel;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.MBUtil;

public class MBCadenaAutorizacion implements Serializable {

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	private static final long serialVersionUID = 1L;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private ServiceCadenaAutorizacion serviceCadenaAutorizacion;

	static Logger log = Logger.getLogger(MBCadenaAutorizacion.class.getName());

	private int idCadenaAutorizacion;
	private String nombreCadena;
	private List<CadenaAutorizacionDTO> listCadenaAutorizacion = new ArrayList<CadenaAutorizacionDTO>();
	private CadenaAutorizacionDTODataModel cadenaAutorizacionDTODataModel;
	private CadenaAutorizacionDTO selectedCadenaAutorizacion;
	private int idUsuario;
	private List<UsuarioDTO> usuariosList = new ArrayList<UsuarioDTO>();

	public List<UsuarioDTO> getUsuariosList() {
		return usuariosList;
	}

	public void setUsuariosList(List<UsuarioDTO> usuariosList) {
		this.usuariosList = usuariosList;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	private UsuarioDTO usuarioDTO;

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public List<CadenaAutorizacionDetDTO> getListCadenaAutorizacionDetDTO() {
		return listCadenaAutorizacionDetDTO;
	}

	public void setListCadenaAutorizacionDetDTO(
			List<CadenaAutorizacionDetDTO> listCadenaAutorizacionDetDTO) {
		this.listCadenaAutorizacionDetDTO = listCadenaAutorizacionDetDTO;
	}

	public CadenaAutorizacionDetDTO getSelectedCadenaAutorizacionDetDTO() {
		return selectedCadenaAutorizacionDetDTO;
	}

	public void setSelectedCadenaAutorizacionDetDTO(
			CadenaAutorizacionDetDTO selectedCadenaAutorizacionDetDTO) {
		this.selectedCadenaAutorizacionDetDTO = selectedCadenaAutorizacionDetDTO;
	}

	private CadenaAutorizacionDetDTO selectedCadenaAutorizacionDetDTO;
	private List<CadenaAutorizacionDetDTO> listCadenaAutorizacionDetDTO = new ArrayList<CadenaAutorizacionDetDTO>();
	private CadenaAutorizacionDetDTODataModel cadenaAutorizacionDetDTODataModel;

	public CadenaAutorizacionDetDTODataModel getCadenaAutorizacionDetDTODataModel() {
		return cadenaAutorizacionDetDTODataModel;
	}

	public void setCadenaAutorizacionDetDTODataModel(
			CadenaAutorizacionDetDTODataModel cadenaAutorizacionDetDTODataModel) {
		this.cadenaAutorizacionDetDTODataModel = cadenaAutorizacionDetDTODataModel;
	}

	public ServiceCadenaAutorizacion getServiceCadenaAutorizacion() {
		return serviceCadenaAutorizacion;
	}

	public void setServiceCadenaAutorizacion(
			ServiceCadenaAutorizacion serviceCadenaAutorizacion) {
		this.serviceCadenaAutorizacion = serviceCadenaAutorizacion;
	}

	public CadenaAutorizacionDTO getSelectedCadenaAutorizacion() {
		return selectedCadenaAutorizacion;
	}

	public void setSelectedCadenaAutorizacion(
			CadenaAutorizacionDTO selectedCadenaAutorizacion) {
		this.selectedCadenaAutorizacion = selectedCadenaAutorizacion;
	}

	public CadenaAutorizacionDTODataModel getCadenaAutorizacionDTODataModel() {
		return cadenaAutorizacionDTODataModel;
	}

	public void setCadenaAutorizacionDTODataModel(
			CadenaAutorizacionDTODataModel cadenaAutorizacionDTODataModel) {
		this.cadenaAutorizacionDTODataModel = cadenaAutorizacionDTODataModel;
	}

	public int getIdCadenaAutorizacion() {
		return idCadenaAutorizacion;
	}

	public void setIdCadenaAutorizacion(int idCadenaAutorizacion) {
		this.idCadenaAutorizacion = idCadenaAutorizacion;
	}

	public String getNombreCadena() {
		return nombreCadena;
	}

	public void setNombreCadena(String nombreCadena) {
		this.nombreCadena = nombreCadena;
	}

	public List<CadenaAutorizacionDTO> getListCadenaAutorizacion() {
		return listCadenaAutorizacion;
	}

	public void setListCadenaAutorizacion(
			List<CadenaAutorizacionDTO> listCadenaAutorizacion) {
		this.listCadenaAutorizacion = listCadenaAutorizacion;
	}

	public String eliminarCadena() throws GeneralException {
		log.info("Eliminar Cadena");
		if (this.selectedCadenaAutorizacion != null) {
			log.info(this.selectedCadenaAutorizacion.getTblCadenaAutorizacion()
					.getIdCadenaAutorizacion());
			log.info(this.selectedCadenaAutorizacion.getTblCadenaAutorizacion()
					.getNombreCadena());
			List<TblCadenaAutorizacion> tblCadenaAutorizacionList = this.serviceCadenaAutorizacion
					.getListCadenaAutorizacionDet(this.selectedCadenaAutorizacion
							.getTblCadenaAutorizacion());
			if (tblCadenaAutorizacionList != null) {
				for (TblCadenaAutorizacion tblCadenaAutorizacion : tblCadenaAutorizacionList) {
					log.info(this.selectedCadenaAutorizacion
							.getTblCadenaAutorizacion()
							.getIdCadenaAutorizacion());
					log.info(this.selectedCadenaAutorizacion
							.getTblCadenaAutorizacion().getNombreCadena());
					this.selectedCadenaAutorizacion
							.setTblCadenaAutorizacion(tblCadenaAutorizacion);
				}
			}

			this.serviceCadenaAutorizacion
					.eliminarAutorizacionDet(this.selectedCadenaAutorizacion
							.getTblCadenaAutorizacion());

			this.serviceCadenaAutorizacion
					.eliminarCadenaAutorizacion(this.selectedCadenaAutorizacion
							.getTblCadenaAutorizacion());
		}
		this.init();
		return null;
	}

	@PostConstruct
	public void init() {

		try {
			this.obtenerCadenaAutorizacionList();
			this.cargarUsuarios();
		} catch (Exception e) {
			log.info(e);
		}
	}

	private void obtenerCadenaAutorizacionList() throws Exception {
		List<TblCadenaAutorizacion> cadenaAutorizacionList = serviceCadenaAutorizacion
				.getCadenaAutorizacionList();

		this.selectedCadenaAutorizacion = null;
		listCadenaAutorizacion.clear();

		for (Iterator<TblCadenaAutorizacion> iterator = cadenaAutorizacionList
				.iterator(); iterator.hasNext();) {
			TblCadenaAutorizacion tblCadenaAutorizacion = (TblCadenaAutorizacion) iterator
					.next();
			CadenaAutorizacionDTO cadenaAutorizacionDTO = new CadenaAutorizacionDTO();
			cadenaAutorizacionDTO
					.setTblCadenaAutorizacion(tblCadenaAutorizacion);
			listCadenaAutorizacion.add(cadenaAutorizacionDTO);
		}

		cadenaAutorizacionDTODataModel = new CadenaAutorizacionDTODataModel(
				listCadenaAutorizacion);

	}

	public String getDescripcionCadena() throws Exception {

		if (this.selectedCadenaAutorizacion != null) {
			this.setDisableNombreCadenaAut(true);
			log.info(this.selectedCadenaAutorizacion.getTblCadenaAutorizacion()
					.getIdCadenaAutorizacion());
			log.info(this.selectedCadenaAutorizacion.getTblCadenaAutorizacion()
					.getNombreCadena());

			List<TblCadenaAutorizacion> tblCadenaAutorizacionList = this.serviceCadenaAutorizacion
					.getListCadenaAutorizacionDet(this.selectedCadenaAutorizacion
							.getTblCadenaAutorizacion());

			for (Iterator<TblCadenaAutorizacion> iterator = tblCadenaAutorizacionList
					.iterator(); iterator.hasNext();) {
				TblCadenaAutorizacion tblCadenaAutorizacion = (TblCadenaAutorizacion) iterator
						.next();
				log.info(this.selectedCadenaAutorizacion
						.getTblCadenaAutorizacion().getIdCadenaAutorizacion());
				log.info(this.selectedCadenaAutorizacion
						.getTblCadenaAutorizacion().getNombreCadena());
				this.selectedCadenaAutorizacion
						.setTblCadenaAutorizacion(tblCadenaAutorizacion);

			}
			this.listCadenaAutorizacionDetDTO.clear();
			this.selectedCadenaAutorizacionDetDTO = null;
			for (Iterator<?> iterator = this.selectedCadenaAutorizacion
					.getTblCadenaAutorizacion().getTblCadenaAutorizacionDets()
					.iterator(); iterator.hasNext();) {
				TblCadenaAutorizacionDet tblCadenaAutorizacionDet = (TblCadenaAutorizacionDet) iterator
						.next();
				log.info(tblCadenaAutorizacionDet.getId().getIdOrden());
				log.info(tblCadenaAutorizacionDet.getId()
						.getIdCadenaAutorizacion());
				log.info(tblCadenaAutorizacionDet.getIdUsuario());
				CadenaAutorizacionDetDTO cadenaAutorizacionDetDTO = new CadenaAutorizacionDetDTO();
				cadenaAutorizacionDetDTO
						.setTblCadenaAutorizacionDet(tblCadenaAutorizacionDet);
				cadenaAutorizacionDetDTO
						.setTblCadenaAutorizacion(this.selectedCadenaAutorizacion
								.getTblCadenaAutorizacion());
				this.listCadenaAutorizacionDetDTO.add(cadenaAutorizacionDetDTO);

			}
			this.cadenaAutorizacionDetDTODataModel = new CadenaAutorizacionDetDTODataModel(
					this.listCadenaAutorizacionDetDTO);

			this.setDisplayCrear(false);
			this.setDisplayAgregar(true);

		} else {
			log.info("No selecciono Cadena");
		}
		return "detalleCadena.xhtml";
	}

	public String getDescripcionCadenaAutorizacionDetalle() {
		if (this.isDisplayAgregar() == false && displayCrear == true) {
			try {
				return this.getDescripcionCadenaAutDet();
			} catch (Exception e) {
				log.info(e);
			}
		} else {
			try {
				return this.getDescripcionCadena();
			} catch (Exception e) {
				log.info(e);
			}
		}
		return null;
	}

	public String getDescripcionCadenaAutDet() throws Exception {
		log.info("Descripcion Cadena");

		if (this.tblCadenaAutorizacionNueva != null) {
			log.info(this.tblCadenaAutorizacionNueva.getIdCadenaAutorizacion());
			log.info(this.tblCadenaAutorizacionNueva.getNombreCadena());

			this.listCadenaAutorizacionDetDTO.clear();

			log.info("Size="
					+ this.tblCadenaAutorizacionNueva
							.getTblCadenaAutorizacionDets().size());
			List<TblCadenaAutorizacionDet> listCadenaAutDet = this.serviceCadenaAutorizacion
					.obtenerCadenaAutorizacionDetList(tblCadenaAutorizacionNueva);

			log.info("Size="
					+ this.tblCadenaAutorizacionNueva
							.getTblCadenaAutorizacionDets().size());

			for (Iterator<TblCadenaAutorizacionDet> iterator = listCadenaAutDet
					.iterator(); iterator.hasNext();) {
				TblCadenaAutorizacionDet tblCadenaAutorizacionDet = (TblCadenaAutorizacionDet) iterator
						.next();
				log.info(tblCadenaAutorizacionDet.getId().getIdOrden());
				log.info(tblCadenaAutorizacionDet.getId()
						.getIdCadenaAutorizacion());
				log.info(tblCadenaAutorizacionDet.getIdUsuario());
				CadenaAutorizacionDetDTO cadenaAutorizacionDetDTO = new CadenaAutorizacionDetDTO();
				cadenaAutorizacionDetDTO
						.setTblCadenaAutorizacionDet(tblCadenaAutorizacionDet);
				cadenaAutorizacionDetDTO
						.setTblCadenaAutorizacion(this.tblCadenaAutorizacionNueva);
				this.listCadenaAutorizacionDetDTO.add(cadenaAutorizacionDetDTO);

			}
			this.cadenaAutorizacionDetDTODataModel = new CadenaAutorizacionDetDTODataModel(
					this.listCadenaAutorizacionDetDTO);
		} else {
			log.info("La Cadena es Null");
		}
		return null;
	}

	public void eliminarCadenaDet() throws Exception {
		log.info("Descripcion Cadena");

		if (this.selectedCadenaAutorizacionDetDTO != null) {
			log.info(this.selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacion().getIdCadenaAutorizacion());
			log.info(this.selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacion().getNombreCadena());

			log.info(selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacionDet().getId().getIdOrden());
			log.info(selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacionDet().getId()
					.getIdCadenaAutorizacion());
			log.info(selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacionDet().getIdUsuario());
			this.serviceCadenaAutorizacion
					.eliminarAutorizacionDetById(selectedCadenaAutorizacionDetDTO
							.getTblCadenaAutorizacionDet());
		}

		this.getDescripcionCadenaAutorizacionDetalle();
	}

	public String getDescripcionCadenaDet() throws Exception {

		if (this.selectedCadenaAutorizacionDetDTO != null) {
			log.info(this.selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacion().getIdCadenaAutorizacion());
			log.info(this.selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacion().getNombreCadena());

			log.info(selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacionDet().getId().getIdOrden());
			log.info(selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacionDet().getId()
					.getIdCadenaAutorizacion());
			log.info(selectedCadenaAutorizacionDetDTO
					.getTblCadenaAutorizacionDet().getIdUsuario());
		}

		return "cadenaAutorizacionDet.xhtml";
	}

	private List<SelectItem> availableUsers = new ArrayList<SelectItem>();

	private Integer selectedIdUser;

	public Integer getSelectedIdUser() {
		return selectedIdUser;
	}

	public void setSelectedIdUser(Integer selectedIdUser) {
		this.selectedIdUser = selectedIdUser;
	}

	private void cargarUsuarios() {

		try {
			this.usuariosList = MBUtil.getUSers(this.serviceDynamicCatalogs);
			log.info(this.usuariosList.size());
			for (Iterator<UsuarioDTO> iterator = usuariosList.iterator(); iterator
					.hasNext();) {
				UsuarioDTO usuarioDTO = (UsuarioDTO) iterator.next();
				availableUsers.add(new SelectItem(usuarioDTO));
			}

		} catch (Exception e) {
			log.info(e);
		}

	}

	public List<SelectItem> getAvailableUsers() {
		return availableUsers;
	}

	public void setAvailableUsers(List<SelectItem> availableUsers) {
		this.availableUsers = availableUsers;
	}

	public String getCadenaDescripcionDetallePage() {
		this.setDisplayCrear(true);
		this.setDisplayAgregar(false);
		this.listCadenaAutorizacionDetDTO.clear();
		this.selectedCadenaAutorizacionDetDTO = null;

		log.info("0");
		return "detalleCadena.xhtml";
	}

	public String crearCadenaAutorizacion() {
		log.info("Inicio " + this.getNombreCadena());

		if (this.getNombreCadena().equals("")
				|| this.getSelectedIdUser() == null
				|| this.getSelectedIdUser() == 0) {
			log.info("Vacios");
		} else {
			TblCadenaAutorizacion tblCadenaAutorizacion = new TblCadenaAutorizacion(
					this.getNombreCadena());

			this.serviceCadenaAutorizacion
					.crearCadenaAutorizacion(tblCadenaAutorizacion);

			this.setTblCadenaAutorizacionNueva(tblCadenaAutorizacion);
			try {
				this.agregarCadenaAutDetInicio();
			} catch (Exception e1) {
				log.info(e1);
			}
			try {
				getDescripcionCadenaAutDet();
			} catch (Exception e) {
				log.info(e);
			}
		}
		log.info("Es necesario escribir un nombre ");
		log.info("Es necesario seleccionar un usuario");

		return null;
	}

	private TblCadenaAutorizacion tblCadenaAutorizacionNueva;

	public TblCadenaAutorizacion getTblCadenaAutorizacionNueva() {
		return tblCadenaAutorizacionNueva;
	}

	public void setTblCadenaAutorizacionNueva(
			TblCadenaAutorizacion tblCadenaAutorizacionNueva) {
		this.tblCadenaAutorizacionNueva = tblCadenaAutorizacionNueva;
	}

	public void agregarCadenaDet() throws Exception {
		log.info("Descripcion Cadena Det");
		if (this.selectedCadenaAutorizacion != null) {
			log.info("Descripcion Cadena Det 0");
			TblCadenaAutorizacionDet idOrden = this.serviceCadenaAutorizacion
					.getIdOrdenByIdCadenaAutorizacion(selectedCadenaAutorizacion
							.getTblCadenaAutorizacion()
							.getIdCadenaAutorizacion());
			if (selectedIdUser != null && selectedIdUser != 0) {
				if (idOrden != null) {
					log.info("IdUsuario=" + selectedIdUser);

					TblCadenaAutorizacionDet tblCadenaAutorizacionDet = new TblCadenaAutorizacionDet(
							new TblCadenaAutorizacionDetId(idOrden.getId()
									.getIdCadenaAutorizacion(), idOrden.getId()
									.getIdOrden() + 1), selectedIdUser);
					this.serviceCadenaAutorizacion
							.crearCadenaAutorizacionDet(tblCadenaAutorizacionDet);
				} else {
					log.info("No hay usuarios en la cadena");
					TblCadenaAutorizacionDet tblCadenaAutorizacionDet = new TblCadenaAutorizacionDet(
							new TblCadenaAutorizacionDetId(
									selectedCadenaAutorizacion
											.getTblCadenaAutorizacion()
											.getIdCadenaAutorizacion(), 1),
							selectedIdUser);
					this.serviceCadenaAutorizacion
							.crearCadenaAutorizacionDet(tblCadenaAutorizacionDet);
				}
			} else {
				log.info("No  Selecciono Usuario");
			}
		}
		log.info("Descripcion Cadena Det1");
		this.getDescripcionCadena();
	}

	public void agregarCadenaAutDetInicio() throws Exception {
		if (tblCadenaAutorizacionNueva != null) {
			TblCadenaAutorizacionDet idOrden = this.serviceCadenaAutorizacion
					.getIdOrdenByIdCadenaAutorizacion(tblCadenaAutorizacionNueva
							.getIdCadenaAutorizacion());
			if (idOrden != null) {
				log.info("Cadena Recien Creada");
				log.info("DetId=+" + idOrden.getId().getIdOrden());
				TblCadenaAutorizacionDet tblCadenaAutorizacionDet = new TblCadenaAutorizacionDet(
						new TblCadenaAutorizacionDetId(
								tblCadenaAutorizacionNueva
										.getIdCadenaAutorizacion(),
								idOrden.getId().getIdOrden() + 1),
						selectedIdUser);
				this.serviceCadenaAutorizacion
						.crearCadenaAutorizacionDet(tblCadenaAutorizacionDet);
			} else {
				log.info("Cadena Nueva");
				TblCadenaAutorizacionDet tblCadenaAutorizacionDet = new TblCadenaAutorizacionDet(
						new TblCadenaAutorizacionDetId(
								tblCadenaAutorizacionNueva
										.getIdCadenaAutorizacion(),
								1), selectedIdUser);
				this.serviceCadenaAutorizacion
						.crearCadenaAutorizacionDet(tblCadenaAutorizacionDet);
			}
		} else {
			log.info("No hay nada");
		}
		this.getDescripcionCadenaAutDet();
	}

	private boolean disable = false;
	private boolean disableNombreCadenaAut = false;

	public boolean isDisableNombreCadenaAut() {
		return disableNombreCadenaAut;
	}

	public void setDisableNombreCadenaAut(boolean disableNombreCadenaAut) {
		this.disableNombreCadenaAut = disableNombreCadenaAut;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	private boolean displayCrear;
	private boolean displayAgregar;

	public boolean isDisplayCrear() {
		return displayCrear;
	}

	public void setDisplayCrear(boolean displayCrear) {
		this.displayCrear = displayCrear;
	}

	public boolean isDisplayAgregar() {
		return displayAgregar;
	}

	public void setDisplayAgregar(boolean displayAgregar) {
		this.displayAgregar = displayAgregar;
	}
}
