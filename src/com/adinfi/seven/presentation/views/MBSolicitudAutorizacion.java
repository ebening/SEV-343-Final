package com.adinfi.seven.presentation.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Calendar;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblCadenaAutorizacionDet;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaAutorizacion;
import com.adinfi.seven.business.domain.TblCampanaAutorizacionId;
import com.adinfi.seven.business.domain.TblSolicitudAutorizacion;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.business.services.ServiceSolicitudAutorizacion;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.EtiquetaDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.SolicitudAutorizacionDTO;
import com.adinfi.seven.persistence.dto.SolicitudAutorizacionDTODataModel;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.Util;

public class MBSolicitudAutorizacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	static Logger LOG = Logger.getLogger(MBSolicitudAutorizacion.class.getName());

	private ServiceSolicitudAutorizacion serviceSolicitudAutorizacion;
	private ServiceCampana serviceCampana;

	private Long idAutorizacion;
	private String idCampana;
	private String idResponsable;
	private String fechaCaptura;
	private String idPeriodo;
	private String usuarioCreacion;
	private String fechaCreacion;
	private String usuarioModificacion;
	private String fechaModificacion;
	private Date date1;
	private boolean esAutorizado;
	private String comentarios;
	UsuarioDTO userLogin;
	private boolean consulta = true;
	private boolean detalle = false;

	private TblCampana tblCampanaDescripcion;

	private SolicitudAutorizacionDTO solicitudAutorizacionDTOPendiente;

	private List<SolicitudAutorizacionDTO> listSolicitudesAutorizaciones;

	public List<SolicitudAutorizacionDTO> getListSolicitudesAutorizaciones() {
		return listSolicitudesAutorizaciones;
	}

	public void setListSolicitudesAutorizaciones(
			List<SolicitudAutorizacionDTO> listSolicitudesAutorizaciones) {
		this.listSolicitudesAutorizaciones = listSolicitudesAutorizaciones;
	}

	private List<SolicitudAutorizacionDTO> solicitudesPendientes = new ArrayList<SolicitudAutorizacionDTO>();

	public List<SolicitudAutorizacionDTO> getSolicitudesPendientes() {
		return solicitudesPendientes;
	}

	public void setSolicitudesPendientes(
			List<SolicitudAutorizacionDTO> solicitudesPendientes) {
		this.solicitudesPendientes = solicitudesPendientes;
	}

	private List<TblCampanaAutorizacion> listTblCampanaAutorizacionAprobadas = new ArrayList<TblCampanaAutorizacion>();

	public MBSolicitudAutorizacion() {

	}

	public Long getIdAutorizacion() {
		return idAutorizacion;
	}

	public void setIdAutorizacion(Long idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}

	public String getIdCampana() {
		return idCampana;
	}

	public void setIdCampana(String idCampana) {
		this.idCampana = idCampana;
	}

	public String getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(String idResponsable) {
		this.idResponsable = idResponsable;
	}

	public String getFechaCaptura() {
		return fechaCaptura;
	}

	public void setFechaCaptura(String fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}

	public String getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(String idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public ServiceSolicitudAutorizacion getServiceSolicitudAutorizacion() {
		return serviceSolicitudAutorizacion;
	}

	public void setServiceSolicitudAutorizacion(
			ServiceSolicitudAutorizacion serviceSolicitudAutorizacion) {
		this.serviceSolicitudAutorizacion = serviceSolicitudAutorizacion;
	}
	
	public void info(){
		LOG.info("INFO");
	}

	public String autorizar(SolicitudAutorizacionDTO solicitudAutorizacionDTO)
			throws Exception {
		LOG.info("AUTORIZAR");
		LOG.info(solicitudAutorizacionDTO.getTblCampanaAutorizacion().getId()
				.getIdAutorizacion());
		LOG.info(solicitudAutorizacionDTO.getTblCampanaAutorizacion().getId()
				.getIdOrden());
		TblSolicitudAutorizacion tblSolicitudAutorizacion = null;
		List<TblSolicitudAutorizacion> nuevo = this.serviceSolicitudAutorizacion
				.getSolicitudAutorizacionActual(solicitudAutorizacionDTO
						.getTblSolicitudAutorizacion().getIdAutorizacion());

		LOG.info(nuevo.size());

		for (Iterator<TblSolicitudAutorizacion> iterator = nuevo.iterator(); iterator.hasNext();) {
			tblSolicitudAutorizacion = (TblSolicitudAutorizacion) iterator.next();
			LOG.info(tblSolicitudAutorizacion.getTblCampanaAutorizacions().size());
		}

		for (Iterator<?> iterator2 = tblSolicitudAutorizacion
				.getTblCampanaAutorizacions().iterator(); iterator2.hasNext();) {
			TblCampanaAutorizacion tblCampanaAutorizacion2 = (TblCampanaAutorizacion) iterator2
					.next();

			if (tblCampanaAutorizacion2.getIdEstatusAutorizacion().equals(
					Constants.PENDIENTE)
					&& tblCampanaAutorizacion2.getId().getIdAutorizacion() == solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getId().getIdAutorizacion()
					&& tblCampanaAutorizacion2.getId().getIdOrden() == solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getId().getIdOrden()) {

				LOG.info("solDTO idAut"+ solicitudAutorizacionDTO.getTblCampanaAutorizacion()
								.getId().getIdAutorizacion());
				LOG.info("solDTO idOrd"+ solicitudAutorizacionDTO.getTblCampanaAutorizacion()
								.getId().getIdOrden());

				LOG.info("tblCamp idAut"+ tblCampanaAutorizacion2.getId().getIdAutorizacion());
				LOG.info("tblCamp idOrd"+ tblCampanaAutorizacion2.getId().getIdOrden());

				tblCampanaAutorizacion2.setIdEstatusAutorizacion(Constants.AUTORIZADO);
				tblCampanaAutorizacion2.setUsuarioModificacion(solicitudAutorizacionDTO
								.getUsuarioDTO().getUserId());
				tblCampanaAutorizacion2.setFechaAutorizacion(new Date());
				tblCampanaAutorizacion2.setFechaModificacion(new Date());
				tblCampanaAutorizacion2.setAccion(Constants.ACCION_AUTORIZAR);
				tblCampanaAutorizacion2.setComentarios(this.getComentarios() != null ? this
								.getComentarios() : "");

				serviceSolicitudAutorizacion.saveCampanaAutorizacion(tblCampanaAutorizacion2);
				for (Iterator<?> iterator3 = tblCampanaAutorizacion2
						.getTblCadenaAutorizacion()
						.getTblCadenaAutorizacionDets().iterator(); iterator3.hasNext();) {
					TblCadenaAutorizacionDet tblCadenaAutorizacionDet = (TblCadenaAutorizacionDet) iterator3
							.next();

					if (tblCadenaAutorizacionDet.getId().getIdOrden() == solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getId().getIdOrden() + 1) {

						TblCampanaAutorizacion newTblCampanaAutorizacion = new TblCampanaAutorizacion(
								new TblCampanaAutorizacionId(
										solicitudAutorizacionDTO
												.getTblCampanaAutorizacion()
												.getId().getIdAutorizacion(),
										tblCadenaAutorizacionDet.getId().getIdOrden()),
								tblCampanaAutorizacion2
										.getTblCadenaAutorizacion(),
								solicitudAutorizacionDTO
										.getTblSolicitudAutorizacion(),
								solicitudAutorizacionDTO
										.getTblCampanaAutorizacion()
										.getIdCampana(), Constants.PENDIENTE,
								tblCadenaAutorizacionDet.getIdUsuario(), null,
								"", "", solicitudAutorizacionDTO
										.getTblCampanaAutorizacion()
										.getUsuarioCreacion(),
								solicitudAutorizacionDTO
										.getTblCampanaAutorizacion()
										.getFechaCreacion());

						serviceSolicitudAutorizacion
								.saveCampanaAutorizacion(newTblCampanaAutorizacion);

						UsuarioDTO usuarioDTO = MBUtil.getUsuario(
								this.serviceDynamicCatalogs,
								tblCadenaAutorizacionDet.getIdUsuario());

						SendMail.sendGenericEmail(usuarioDTO.getEmail(),
								Constants.EMAIL_AUTORIZACIONES_TITLE,
								Constants.EMAIL_AUTORIZACIONES_DETAIL,
								solicitudAutorizacionDTO
										.getTblSolicitudAutorizacion()
										.getTblCampana().getNombre());
					

						break;
					} else {

					}
				}
			} else {

			}
		}

		this.init();

		consulta=true;
		detalle=false;
		return null;
		
	}

	public String rechazar(SolicitudAutorizacionDTO solicitudAutorizacionDTO)
			throws Exception {
		LOG.info("AUTORIZAR");
		LOG.info(solicitudAutorizacionDTO.getTblCampanaAutorizacion().getId()
				.getIdAutorizacion());
		LOG.info(solicitudAutorizacionDTO.getTblCampanaAutorizacion().getId()
				.getIdOrden());
		TblSolicitudAutorizacion tblSolicitudAutorizacion = null;
		List<TblSolicitudAutorizacion> nuevo = this.serviceSolicitudAutorizacion
				.getSolicitudAutorizacionActual(solicitudAutorizacionDTO
						.getTblSolicitudAutorizacion().getIdAutorizacion());

		LOG.info(nuevo.size() + "Rechazo");

		for (Iterator<TblSolicitudAutorizacion> iterator = nuevo.iterator(); iterator
				.hasNext();) {
			tblSolicitudAutorizacion = (TblSolicitudAutorizacion) iterator
					.next();
			LOG.info(tblSolicitudAutorizacion.getTblCampanaAutorizacions()
					.size());
		}

		for (Iterator<?> iterator2 = tblSolicitudAutorizacion
				.getTblCampanaAutorizacions().iterator(); iterator2.hasNext();) {
			TblCampanaAutorizacion tblCampanaAutorizacion2 = (TblCampanaAutorizacion) iterator2
					.next();

			if (tblCampanaAutorizacion2.getIdEstatusAutorizacion().equals(
					Constants.PENDIENTE)
					&& tblCampanaAutorizacion2.getId().getIdAutorizacion() == solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getId()
							.getIdAutorizacion()
					&& tblCampanaAutorizacion2.getId().getIdOrden() == solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getId().getIdOrden()) {

				LOG.info("solDTO idAut"
						+ solicitudAutorizacionDTO.getTblCampanaAutorizacion()
								.getId().getIdAutorizacion());
				LOG.info("solDTO idOrd"
						+ solicitudAutorizacionDTO.getTblCampanaAutorizacion()
								.getId().getIdOrden());

				LOG.info("tblCamp idAut"
						+ tblCampanaAutorizacion2.getId().getIdAutorizacion());
				LOG.info("tblCamp idOrd"
						+ tblCampanaAutorizacion2.getId().getIdOrden());

				tblCampanaAutorizacion2
						.setIdEstatusAutorizacion(Constants.RECHAZADO);
				tblCampanaAutorizacion2
						.setUsuarioModificacion(solicitudAutorizacionDTO
								.getUsuarioDTO().getUserId());
				tblCampanaAutorizacion2.setFechaAutorizacion(new Date());
				tblCampanaAutorizacion2.setFechaModificacion(new Date());
				tblCampanaAutorizacion2.setAccion(Constants.ACCION_RECHAZAR);
				tblCampanaAutorizacion2
						.setComentarios(this.getComentarios() != null ? this
								.getComentarios() : "");

				serviceSolicitudAutorizacion
						.saveCampanaAutorizacion(tblCampanaAutorizacion2);

				UsuarioDTO usuarioDTO = MBUtil.getUsuario(
						this.serviceDynamicCatalogs, Integer
								.parseInt(tblCampanaAutorizacion2
										.getUsuarioCreacion()));

				LOG.info("Email=" + usuarioDTO.getEmail());

				SendMail.sendGenericEmail(usuarioDTO.getEmail(),
						Constants.EMAIL_AUTORIZACIONES_RECHAZO_TITLE,
						Constants.EMAIL_AUTORIZACIONES_RECHAZO_DETAIL,
						solicitudAutorizacionDTO.getTblSolicitudAutorizacion()
								.getTblCampana().getNombre());
			} else {

			}
		}

		this.init();
		consulta=true;
		detalle=false;
		return null;
	}

	public boolean isEsAutorizado() {
		return esAutorizado;
	}

	public void setEsAutorizado(boolean esAutorizado) {
		this.esAutorizado = esAutorizado;
	}

	@PostConstruct
	public void init() {
		this.setUserLogin((UsuarioDTO) Util.getSessionAttribute("userLoged"));
		try {
			this.obtenerSolicitudes();
		} catch (Exception e) {
			LOG.info(e);
		}
	}

	public void obtenerSolicitudes() throws Exception {
		LOG.info("Hola");
		this.getSolicitudesPendientes().clear();

		LOG.info("Login Name=" + this.getUserLogin().getName());
		LOG.info("Login Login=" + this.getUserLogin().getLogin());
		LOG.info("Login Id=" + this.getUserLogin().getUserId());

		List<TblSolicitudAutorizacion> solicitudAutorizacionList = serviceSolicitudAutorizacion
				.getAllSolicitudAutorizacionByUser(this.getUserLogin().getUserId());

		for (Iterator<TblSolicitudAutorizacion> iterator = solicitudAutorizacionList.iterator(); iterator.hasNext();) {
			TblSolicitudAutorizacion tblSolicitudAutorizacion = (TblSolicitudAutorizacion) iterator.next();
			LOG.info("Size="+ tblSolicitudAutorizacion.getTblCampanaAutorizacions().size());
			Set<TblCampanaAutorizacion> newset = tblSolicitudAutorizacion.getTblCampanaAutorizacions();
			this.obtenerTblCampanaAutorizacionNoAprobada(newset,tblSolicitudAutorizacion);
		}
		this.setListSolicitudesAutorizaciones(this.getSolicitudesPendientes());
		solicitudAutorizacionDTODataModel = new SolicitudAutorizacionDTODataModel(
				this.getListSolicitudesAutorizaciones());
	}

	private void obtenerTblCampanaAutorizacionNoAprobada(
			Set<TblCampanaAutorizacion> newset,
			TblSolicitudAutorizacion tblSolicitudAutorizacion) throws Exception {

		for (Iterator<TblCampanaAutorizacion> iterator2 = newset.iterator(); iterator2
				.hasNext();) {
			TblCampanaAutorizacion tblCampanaAutorizacion = (TblCampanaAutorizacion) iterator2.next();

			LOG.info("Estatus="+ tblCampanaAutorizacion.getIdEstatusAutorizacion());

			if (tblCampanaAutorizacion.getIdEstatusAutorizacion() == Constants.PENDIENTE) {

				UsuarioDTO usuarioDTO = MBUtil.getUsuario(
						this.serviceDynamicCatalogs,
						tblCampanaAutorizacion.getIdUsuario());
				UsuarioDTO usuarioDTOSolicitud = MBUtil.getUsuario(
						this.serviceDynamicCatalogs,
						tblSolicitudAutorizacion.getIdResponsable());

				SolicitudAutorizacionDTO solicitudAutorizacionDTO = new SolicitudAutorizacionDTO();
				solicitudAutorizacionDTO.setTblSolicitudAutorizacion(tblSolicitudAutorizacion);
				solicitudAutorizacionDTO.setUsuarioDTO(usuarioDTO);
				solicitudAutorizacionDTO.setUsuarioDTOSolicitud(usuarioDTOSolicitud);				

				solicitudAutorizacionDTO.setTblCampanaAutorizacion(tblCampanaAutorizacion);
				solicitudAutorizacionDTO.setTblCadenaAutorizacion(tblCampanaAutorizacion
								.getTblCadenaAutorizacion());
				
				solicitudAutorizacionDTO.setCampanaDTO(getCampanaDTO(tblSolicitudAutorizacion.getTblCampana().getIdCampana()));
				LOG.info("idCampana="+solicitudAutorizacionDTO.getCampanaDTO().getTblCampana().getNombre());
				this.getSolicitudesPendientes().add(solicitudAutorizacionDTO);

			} else {
				LOG.info("Estatus=Auto");
			}
		}
	}
	
	public CampanaDTO getCampanaDTO(long idCampana) throws Exception {
		CampanaDTO campanaDTO;
		TblCampana campana = serviceCampana.getCampana(idCampana);		 
		campanaDTO = llenadoCampanaDTO(campana);		
		return campanaDTO;
	}
	
	private CampanaDTO llenadoCampanaDTO(TblCampana campana) throws Exception {
		UsuarioDTO usuarioDTO;
		TipoCampanaDTO tipoDTO;
		EtiquetaDTO etiquetaDTO;
		CampanaDTO campanaDTO;
		
		Calendar cal = Calendar.getInstance();
		campanaDTO = new CampanaDTO();
		usuarioDTO = MBUtil.getUsuario(serviceDynamicCatalogs,
				campana.getIdResponsable());
		tipoDTO = MBUtil.getTipoCampana(serviceDynamicCatalogs,
				campana.getIdTipoCampana());
		etiquetaDTO = MBUtil.getEtiqueta(serviceDynamicCatalogs,
				campana.getCatEtiquetas().getIdetiqueta());
		campanaDTO.setEtiqueta(etiquetaDTO);		
		campanaDTO.setTipo(tipoDTO);
		campanaDTO.setResponsable(usuarioDTO);
		campanaDTO.setTblCampana(campana);
		cal.setTime(campana.getFechaInicio());
		campanaDTO.setYear(String.valueOf(cal.get(Calendar.YEAR)));
		
		
		LOG.info("PeriodoId="+campana.getIdPeriodo());
		
		PeriodoDTO periodoDTO = MBUtil.getPeriodoAlterno(
				this.serviceDynamicCatalogs, campana.getIdPeriodo());
		campanaDTO.setPeriodo(periodoDTO);
		LOG.info("PeriodoDTO="+periodoDTO.getId() + " " +
				periodoDTO.getCodigo() + " " +
				periodoDTO.getFechaInicio() + " " +
				periodoDTO.getFechaInicial() + " " + 
				periodoDTO.getFechaFin() + " " +
				periodoDTO.getFechaFinal() + " " + 
				periodoDTO.getDuracionDias());
		
		
		return campanaDTO;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	private void obtenerTblCampanaAutorizacionAprobada(
			Set<TblCampanaAutorizacion> newset,
			TblSolicitudAutorizacion tblSolicitudAutorizacion) throws Exception {
		this.getSolicitudesAprobadas().clear();
		this.getListTblCampanaAutorizacionAprobadas().clear();

		for (Iterator<TblCampanaAutorizacion> iterator2 = newset.iterator(); iterator2
				.hasNext();) {
			TblCampanaAutorizacion tblCampanaAutorizacion = (TblCampanaAutorizacion) iterator2
					.next();

			LOG.info("Estatus="
					+ tblCampanaAutorizacion.getIdEstatusAutorizacion());

			if (tblCampanaAutorizacion.getIdEstatusAutorizacion() == Constants.AUTORIZADO) {
				UsuarioDTO usuarioDTO = MBUtil.getUsuario(
						this.serviceDynamicCatalogs,
						tblCampanaAutorizacion.getIdUsuario());

				SolicitudAutorizacionDTO solicitudAutorizacionDTO = new SolicitudAutorizacionDTO();
				solicitudAutorizacionDTO.setTblSolicitudAutorizacion(tblSolicitudAutorizacion);
				solicitudAutorizacionDTO.setUsuarioDTO(usuarioDTO);

				PeriodoDTO periodoDTO = MBUtil.getPeriodo(
						this.serviceDynamicCatalogs, tblCampanaAutorizacion
								.getTblSolicitudAutorizacion().getIdPeriodo());

				solicitudAutorizacionDTO.setPeriodoDTO(periodoDTO);
				solicitudAutorizacionDTO.setTblCampanaAutorizacion(tblCampanaAutorizacion);
				solicitudAutorizacionDTO.setTblCadenaAutorizacion(tblCampanaAutorizacion
								.getTblCadenaAutorizacion());

				this.getSolicitudesAprobadas().add(solicitudAutorizacionDTO);

			} else {
				LOG.info("Estatus=No Auto");
			}
		}
	}

	private List<SolicitudAutorizacionDTO> solicitudesAprobadas = new ArrayList<SolicitudAutorizacionDTO>();

	public List<SolicitudAutorizacionDTO> getSolicitudesAprobadas() {
		return solicitudesAprobadas;
	}

	public void setSolicitudesAprobadas(
			List<SolicitudAutorizacionDTO> solicitudesAprobadas) {
		this.solicitudesAprobadas = solicitudesAprobadas;
	}

	public void getSolicitud() throws Exception {

		List<SolicitudAutorizacionDTO> checkedItems = new ArrayList<SolicitudAutorizacionDTO>();
		LOG.info("getSolicitud");

		if (selectedSolicitudes != null && !selectedSolicitudes.isEmpty()) {
			LOG.info(selectedSolicitudes.get(0).getTblSolicitudAutorizacion()
					.getIdAutorizacion());
		}

		for (Iterator<?> iterator = this.getSelectedSolicitudes().iterator(); iterator
				.hasNext();) {
			SolicitudAutorizacionDTO solicitudAutorizacionDTO = (SolicitudAutorizacionDTO) iterator
					.next();
			checkedItems.add(solicitudAutorizacionDTO);
		}

		for (Iterator<SolicitudAutorizacionDTO> iterator = checkedItems
				.iterator(); iterator.hasNext();) {
			SolicitudAutorizacionDTO solicitudAutorizacionDTO = (SolicitudAutorizacionDTO) iterator
					.next();

			TblCampanaAutorizacion tblCampanaAutorizacion = new TblCampanaAutorizacion(
					new TblCampanaAutorizacionId(solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getId()
							.getIdAutorizacion(), solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getId().getIdOrden()),
					solicitudAutorizacionDTO.getTblCampanaAutorizacion()
							.getTblCadenaAutorizacion(),
					solicitudAutorizacionDTO.getTblSolicitudAutorizacion(),
					solicitudAutorizacionDTO.getTblCampanaAutorizacion()
							.getIdCampana(), Constants.AUTORIZADO,
					solicitudAutorizacionDTO.getTblCampanaAutorizacion()
							.getIdUsuario(), new Date(), "",
					Constants.ACCION_AUTORIZAR, solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getUsuarioCreacion(),
					solicitudAutorizacionDTO.getTblCampanaAutorizacion()
							.getFechaCreacion(), solicitudAutorizacionDTO
							.getTblCampanaAutorizacion().getIdUsuario(),
					new Date());

			serviceSolicitudAutorizacion
					.saveCampanaAutorizacion(tblCampanaAutorizacion);

			TblCadenaAutorizacionDet newtblCadenaAutorizacionDet = null;
			LOG.info(solicitudAutorizacionDTO.getTblSolicitudAutorizacion()
					.getTblCampanaAutorizacions().size());

			for (Iterator<?> iterator2 = solicitudAutorizacionDTO
					.getTblSolicitudAutorizacion().getTblCampanaAutorizacions()
					.iterator(); iterator2.hasNext();) {
				TblCampanaAutorizacion tblCampanaAutorizacion2 = (TblCampanaAutorizacion) iterator2
						.next();
				LOG.info("SIZE===000=="
						+ tblCampanaAutorizacion2.getTblCadenaAutorizacion()
								.getTblCadenaAutorizacionDets().size());

				if (tblCampanaAutorizacion2.getIdEstatusAutorizacion().equals(
						Constants.PENDIENTE)) {

					for (Iterator<?> iterator3 = tblCampanaAutorizacion2
							.getTblCadenaAutorizacion()
							.getTblCadenaAutorizacionDets().iterator(); iterator3
							.hasNext();) {
						TblCadenaAutorizacionDet tblCadenaAutorizacionDet = (TblCadenaAutorizacionDet) iterator3
								.next();

						if (tblCadenaAutorizacionDet.getId().getIdOrden() == solicitudAutorizacionDTO
								.getTblCampanaAutorizacion().getId()
								.getIdOrden() + 1) {
							newtblCadenaAutorizacionDet = tblCadenaAutorizacionDet;
							break;
						} else {

						}
					}
				} else {

				}
			}
			if (newtblCadenaAutorizacionDet != null) {
				LOG.info("nuevoUsuario"
						+ newtblCadenaAutorizacionDet.getIdUsuario());
				TblCampanaAutorizacion newTblCampanaAutorizacion = new TblCampanaAutorizacion(
						new TblCampanaAutorizacionId(solicitudAutorizacionDTO
								.getTblCampanaAutorizacion().getId()
								.getIdAutorizacion(),
								newtblCadenaAutorizacionDet.getId()
										.getIdOrden()),
						solicitudAutorizacionDTO.getTblCampanaAutorizacion()
								.getTblCadenaAutorizacion(),
						solicitudAutorizacionDTO.getTblSolicitudAutorizacion(),
						solicitudAutorizacionDTO.getTblCampanaAutorizacion()
								.getIdCampana(), Constants.PENDIENTE,
						newtblCadenaAutorizacionDet.getIdUsuario(), null, "",
						"", solicitudAutorizacionDTO
								.getTblCampanaAutorizacion()
								.getUsuarioCreacion(), solicitudAutorizacionDTO
								.getTblCampanaAutorizacion().getFechaCreacion());
				serviceSolicitudAutorizacion
						.saveCampanaAutorizacion(newTblCampanaAutorizacion);

				UsuarioDTO usuarioDTO = MBUtil.getUsuario(
						this.serviceDynamicCatalogs,
						newtblCadenaAutorizacionDet.getIdUsuario());

				SendMail.sendGenericEmail(usuarioDTO.getEmail(),
						Constants.EMAIL_AUTORIZACIONES_TITLE,
						Constants.EMAIL_AUTORIZACIONES_DETAIL,
						solicitudAutorizacionDTO.getTblSolicitudAutorizacion()
								.getTblCampana().getNombre());

			} else {
				LOG.info("No hay mas usuarios en la cadena");
			}
		}

		this.init();
	}

	public String getAutorizaciones() {

		return null;
	}

	public String getCampanaDescripcion(SolicitudAutorizacionDTO solicitudAutorizacionDTO) {

		LOG.info("3000="+ solicitudAutorizacionDTO.getTblSolicitudAutorizacion().getTblCampana().getIdCampana());
		LOG.info("3000="+ solicitudAutorizacionDTO.getTblSolicitudAutorizacion().getTblCampana().getNombre());

		this.setSolicitudAutorizacionDTOPendiente(solicitudAutorizacionDTO);
		consulta=false;
		detalle=true;
		return null;
	}
	
	public void createViewCampanas(ActionEvent e) {		
		consulta = false;
		detalle = true;

	}	

	public ServiceCampana getServiceCampana() {
		return serviceCampana;
	}

	public void setServiceCampana(ServiceCampana serviceCampana) {
		this.serviceCampana = serviceCampana;
	}

	public TblCampana getTblCampanaDescripcion() {
		return tblCampanaDescripcion;
	}

	public void setTblCampanaDescripcion(TblCampana tblCampanaDescripcion) {
		this.tblCampanaDescripcion = tblCampanaDescripcion;
	}

	public String getAutorizacionesPrevias(
			TblSolicitudAutorizacion tblSolicitudAutorizacion) throws Exception {
		LOG.info("Size="
				+ tblSolicitudAutorizacion.getTblCampanaAutorizacions().size());
		Set<TblCampanaAutorizacion> newset = tblSolicitudAutorizacion
				.getTblCampanaAutorizacions();

		this.obtenerTblCampanaAutorizacionAprobada(newset,
				tblSolicitudAutorizacion);
		return null;
	}

	public List<TblCampanaAutorizacion> getListTblCampanaAutorizacionAprobadas() {
		return listTblCampanaAutorizacionAprobadas;
	}

	public void setListTblCampanaAutorizacionAprobadas(
			List<TblCampanaAutorizacion> tblCampanaAutorizacionAprobadas) {
		this.listTblCampanaAutorizacionAprobadas = tblCampanaAutorizacionAprobadas;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	private List<TblSolicitudAutorizacion> solicitudes;

	private TblSolicitudAutorizacion selectedSolicitud;

	private List<SolicitudAutorizacionDTO> selectedSolicitudes;

	private SolicitudAutorizacionDTODataModel solicitudAutorizacionDTODataModel;

	public SolicitudAutorizacionDTODataModel getSolicitudAutorizacionDTODataModel() {
		return solicitudAutorizacionDTODataModel;
	}

	public void setSolicitudAutorizacionDTODataModel(
			SolicitudAutorizacionDTODataModel solicitudAutorizacionDTODataModel) {
		this.solicitudAutorizacionDTODataModel = solicitudAutorizacionDTODataModel;
	}

	public TblSolicitudAutorizacion getSelectedSolicitud() {
		return selectedSolicitud;
	}

	public void setSelectedSolicitud(TblSolicitudAutorizacion selectedSolicitud) {
		this.selectedSolicitud = selectedSolicitud;
	}

	public void setSolicitudes(List<TblSolicitudAutorizacion> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public List<TblSolicitudAutorizacion> getSolicitudes() {
		return solicitudes;
	}

	public List<SolicitudAutorizacionDTO> getSelectedSolicitudes() {
		return selectedSolicitudes;
	}

	public void setSelectedSolicitudes(
			List<SolicitudAutorizacionDTO> selectedSolicitudes) {
		this.selectedSolicitudes = selectedSolicitudes;
	}

	public UsuarioDTO getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UsuarioDTO userLogin) {
		this.userLogin = userLogin;
	}

	public SolicitudAutorizacionDTO getSolicitudAutorizacionDTOPendiente() {
		return solicitudAutorizacionDTOPendiente;
	}

	public void setSolicitudAutorizacionDTOPendiente(
			SolicitudAutorizacionDTO solicitudAutorizacionDTOPendiente) {
		this.solicitudAutorizacionDTOPendiente = solicitudAutorizacionDTOPendiente;
	}

	public boolean isConsulta() {
		return consulta;
	}

	public boolean isDetalle() {
		return detalle;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

}
