package com.adinfi.seven.business.services;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.ActivObj;
import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.Catalogs;
import com.adinfi.seven.business.domain.RelGrupoZonaCampana;
import com.adinfi.seven.business.domain.RelStoreCampana;
import com.adinfi.seven.business.domain.RelZonaCampana;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaActividades;
import com.adinfi.seven.business.domain.TblCampanaAutorizacion;
import com.adinfi.seven.business.domain.TblCampanaCategorias;
import com.adinfi.seven.business.domain.TblCampanaCategoriasId;
import com.adinfi.seven.business.domain.TblCampanaCategoriasPlaza;
import com.adinfi.seven.business.domain.TblCampanaDetalle;
import com.adinfi.seven.business.domain.TblCampanaMedio;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.business.domain.TblCampanaProgramasPlazas;
import com.adinfi.seven.business.domain.TblDelegacionActividades;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.domain.TblFolletoSistemaVenta;
import com.adinfi.seven.business.domain.TblFolletoSistemaVentaId;
import com.adinfi.seven.business.domain.TblFolletoTienda;
import com.adinfi.seven.business.domain.TblFolletoTiendaId;
import com.adinfi.seven.business.domain.TblFolletoZona;
import com.adinfi.seven.business.domain.TblFolletoZonaId;
import com.adinfi.seven.business.domain.TblPreciosFolleto;
import com.adinfi.seven.business.domain.TblPreciosPrensa;
import com.adinfi.seven.business.domain.TblPrensa;
import com.adinfi.seven.business.domain.TblPrensaEspacios;
import com.adinfi.seven.business.domain.TblPrensaEspaciosId;
import com.adinfi.seven.business.domain.TblPrensaSistemaVenta;
import com.adinfi.seven.business.domain.TblPrensaSistemaVentaId;
import com.adinfi.seven.business.domain.TblPrensaTienda;
import com.adinfi.seven.business.domain.TblPrensaTiendaId;
import com.adinfi.seven.business.domain.VerTodas;
import com.adinfi.seven.persistence.daos.DAOCampana;
import com.adinfi.seven.persistence.daos.DAOCampanaActividades;
import com.adinfi.seven.persistence.daos.DAOCampanaAutorizacion;
import com.adinfi.seven.persistence.daos.DAOCampanaCategorias;
import com.adinfi.seven.persistence.daos.DAOCampanaCategoriasPlaza;
import com.adinfi.seven.persistence.daos.DAOCampanaDetalles;
import com.adinfi.seven.persistence.daos.DAOCampanaMedio;
import com.adinfi.seven.persistence.daos.DAOCampanaProgramas;
import com.adinfi.seven.persistence.daos.DAOCampanaProgramasCategorias;
import com.adinfi.seven.persistence.daos.DAOCampanaProgramasPlazas;
import com.adinfi.seven.persistence.daos.DAOCampaniaTreeRegs;
import com.adinfi.seven.persistence.daos.DAOCatEstatus;
import com.adinfi.seven.persistence.daos.DAODelegacionActividades;
import com.adinfi.seven.persistence.daos.DAOFolleto;
import com.adinfi.seven.persistence.daos.DAOFolletoHojas;
import com.adinfi.seven.persistence.daos.DAOFolletoSistemaVenta;
import com.adinfi.seven.persistence.daos.DAOFolletoTienda;
import com.adinfi.seven.persistence.daos.DAOFolletoZona;
import com.adinfi.seven.persistence.daos.DAOPrecioFolleto;
import com.adinfi.seven.persistence.daos.DAOPrecioPrensa;
import com.adinfi.seven.persistence.daos.DAOPrecioPrensaDet;
import com.adinfi.seven.persistence.daos.DAOPreciosFolletoDet;
import com.adinfi.seven.persistence.daos.DAOPrensa;
import com.adinfi.seven.persistence.daos.DAOPrensaEspacios;
import com.adinfi.seven.persistence.daos.DAOPrensaSistemaVenta;
import com.adinfi.seven.persistence.daos.DAOPrensaTienda;
import com.adinfi.seven.persistence.daos.DAORelGrupoZonaCampana;
import com.adinfi.seven.persistence.daos.DAORelStoreCampana;
import com.adinfi.seven.persistence.daos.DAORelZonaCampana;
import com.adinfi.seven.persistence.daos.DAOTblMecanica;
import com.adinfi.seven.persistence.daos.DAOVerTodas;
import com.adinfi.seven.persistence.dto.ActivityDTO;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.CampanaMedioDTO;
import com.adinfi.seven.persistence.dto.CampanaProgramaDTO;
import com.adinfi.seven.persistence.dto.CampanaResultDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.CampanaController.HojaFolleto;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;
import com.adinfi.seven.presentation.views.util.SendMail;
import com.adinfi.seven.presentation.views.util.ValidationUtil;

public class ServiceCampanaImpl implements ServiceCampana {
	private DAOCampana daoCampanas = null;
	private DAOVerTodas daoVerTodas = null;
	private DAOCampaniaTreeRegs daoCampaniaTreeRegs = null;
	private DAOCampanaActividades daoCampanaActividades = null;
	private DAODelegacionActividades daoDelegacionActividades = null;
	private DAOCampanaAutorizacion daoCampanaAutorizacion= null;
	private DAOCampanaDetalles daoCampanasDetalles = null;
	private DAOCampanaMedio daoCampanaMedio = null;
	private DAOCampanaCategoriasPlaza daoCampanaCategoriasPlaza = null;
	private DAOCampanaProgramas daoCampanaProgramas = null;
	private DAOCampanaProgramasPlazas daoCampanaProgramasPlazas = null;
	private DAOCampanaProgramasCategorias daoCampanaProgramasCategorias = null;
	private DAOFolleto daoFolleto= null;
	private DAOPrensa daoPrensa= null;
	private DAOPrecioPrensa daoPrecioPrensa= null;
	private DAOPrecioPrensaDet daoPrecioPrensaDet= null;
	private DAOPrecioFolleto daoPrecioFolleto= null;
	private DAOPreciosFolletoDet daoPrecioFolletoDet= null;
	private DAOFolletoTienda daoFolletoTienda= null;
	private DAOFolletoSistemaVenta daoFolletoSistemaVenta= null;
	private DAOPrensaSistemaVenta daoPrensaSistemaVenta= null;
	private DAOFolletoHojas daoFolletoHojas= null;
	private DAOPrensaTienda daoPrensaTienda= null;
	private DAOPrensaEspacios daoPrensaEspacios= null;
	private DAOCampanaCategorias daoCampanaCategorias = null;
	private DAOFolletoZona daoFolletoZona= null;
	private DAORelStoreCampana daoRelStoreCampana= null;
	private DAORelZonaCampana daoRelZonaCampana= null;
	private DAORelGrupoZonaCampana daoRelGrupoZonaCampana= null;
	private DAOTblMecanica daoTblMecanica;
	private DAOCatEstatus daoCatEstatus;
	private Logger LOG = Logger.getLogger(ServiceCampanaImpl.class);

    @Override
    public boolean deleteAllCampanaProgramas(long idCampana) {
        return daoCampanas.deleteAllCampanaProgramas(idCampana);
    }

    @Override
	public boolean changeEtapaPrograma(int idCampana, int idPrograma, String etapa) {
		return daoCampanaProgramas.changeEtapaPrograma(idCampana, idPrograma, etapa);
	}

	@Override
	public List<TblCampana> getAllCampanaByDashboardFilters(String periodo, int tipo, int estatus, Date fechaInicio, Date fechaFin, int idPeriodo, int ano) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            if (fechaInicio != null && fechaFin == null){
                fechaFin = dateFormat.parse("31/12/2999");
            }else if (fechaInicio == null && fechaFin != null){
                fechaInicio = dateFormat.parse("01/09/2016");
            }
        }catch (ParseException ex){
            System.out.println("Exception: ParseDateFormat Filtros Dashboard: " + ex);
        }
		return daoCampanas.getAllCampanaByDashboardFilters(periodo, tipo, estatus, fechaInicio, fechaFin, idPeriodo, ano);
	}

	public DAOCampanaProgramas getDaoCampanaProgramas() {
		return daoCampanaProgramas;
	}

	public void setDaoCampanaProgramas(DAOCampanaProgramas daoCampanaProgramas) {
		this.daoCampanaProgramas = daoCampanaProgramas;
	}

	public DAOCampanaProgramasPlazas getDaoCampanaProgramasPlazas() {
		return daoCampanaProgramasPlazas;
	}

	public void setDaoCampanaProgramasPlazas(
			DAOCampanaProgramasPlazas daoCampanaProgramasPlazas) {
		this.daoCampanaProgramasPlazas = daoCampanaProgramasPlazas;
	}

	public DAOCatEstatus getDaoCatEstatus() {
		return daoCatEstatus;
	}

	public void setDaoCatEstatus(DAOCatEstatus daoCatEstatus) {
		this.daoCatEstatus = daoCatEstatus;
	}

	public DAOCampanaProgramasCategorias getDaoCampanaProgramasCategorias() {
		return daoCampanaProgramasCategorias;
	}

	public void setDaoCampanaProgramasCategorias(
			DAOCampanaProgramasCategorias daoCampanaProgramasCategorias) {
		this.daoCampanaProgramasCategorias = daoCampanaProgramasCategorias;
	}

	public DAOCampanaDetalles getDaoCampanasDetalles() {
		return daoCampanasDetalles;
	}

	public void setDaoCampanasDetalles(DAOCampanaDetalles daoCampanasDetalles) {
		this.daoCampanasDetalles = daoCampanasDetalles;
	}

	public DAOCampanaCategorias getDaoCampanaCategorias() {
		return daoCampanaCategorias;
	}

	public void setDaoCampanaCategorias(DAOCampanaCategorias daoCampanaCategorias) {
		this.daoCampanaCategorias = daoCampanaCategorias;
	}
	
	public DAOVerTodas getDaoVerTodas() {
		return daoVerTodas;
	}

	public void setDaoVerTodas(DAOVerTodas daoVerTodas) {
		this.daoVerTodas = daoVerTodas;
	}

	public DAOCampaniaTreeRegs getDaoCampaniaTreeRegs() {
		return daoCampaniaTreeRegs;
	}

	public void setDaoCampaniaTreeRegs(DAOCampaniaTreeRegs daoCampaniaTreeRegs) {
		this.daoCampaniaTreeRegs = daoCampaniaTreeRegs;
	}
	
	public DAOCampanaCategoriasPlaza getDaoCampanaCategoriasPlaza() {
		return daoCampanaCategoriasPlaza;
	}

	public void setDaoCampanaCategoriasPlaza(
			DAOCampanaCategoriasPlaza daoCampanaCategoriasPlaza) {
		this.daoCampanaCategoriasPlaza = daoCampanaCategoriasPlaza;
	}

    public DAOTblMecanica getDaoTblMecanica() {
        return daoTblMecanica;
    }

    public void setDaoTblMecanica(DAOTblMecanica daoTblMecanica) {
        this.daoTblMecanica = daoTblMecanica;
    }
        
        

	@Override
	public TblCampana getCampana(long idCampana) throws Exception {
		return (TblCampana) daoCampanas.getById(idCampana);
	}

	@Override
	public boolean getCampanaByName(String name) {
		boolean validacion = false;
		try {
		 validacion = daoCampanas.getCampanaByName(name).isEmpty();
		} catch (Exception e) {
			LOG.error(e);
		}
		return validacion;
	}

	@Override
	public List<TblCampana> getAllCampana() {
		try {
			return daoCampanas.getAll();
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}

	@Override
	public void updateCampana(TblCampana tblCampana) throws GeneralException {
		try {
			daoCampanas.save(tblCampana);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}

	@Override
	public int deleteCampana(TblCampana tblCampana)
			throws GeneralException {
		LOG.info("deleteCampana: " + tblCampana.getIdCampana());
		try {
			daoCampanas.delete(tblCampana);
                        return 1;
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void deleteFolleto(TblCampana tblCampana) throws Exception {
		List<TblFolleto> folletoLst= daoFolleto.getFolletoLstByIdCampana(tblCampana.getIdCampana());
		if(folletoLst!=null){
			for(TblFolleto folleto: folletoLst){
				deleteFolletoPrecios(folleto.getIdFolleto());
				daoFolletoHojas.delete(folleto.getIdFolleto());
				daoFolletoSistemaVenta.delete(folleto.getIdFolleto());
				daoFolletoTienda.delete(folleto.getIdFolleto());
				daoFolletoZona.delete(folleto.getIdFolleto());
				daoFolleto.delete(folleto);
			}
		}
	}
	
	@Override
	public void deleteFolleto(int idFolleto) throws Exception {
		deleteFolletoPrecios(idFolleto);
		daoFolletoHojas.delete(idFolleto);
		daoFolletoSistemaVenta.delete(idFolleto);
		daoFolletoTienda.delete(idFolleto);
		daoFolletoZona.delete(idFolleto);
		daoFolleto.delete(daoFolleto.getById(idFolleto));
	}

	private void deleteFolletoPrecios(int idFolleto) throws Exception {
		List<TblPreciosFolleto> preciosLst= daoPrecioFolleto.getPreciosFolletoByFolleto(idFolleto);
		if(preciosLst!=null){
			for(TblPreciosFolleto precios: preciosLst){
				daoPrecioFolletoDet.delete(precios.getIdPrecFolleto());
				daoPrecioFolleto.delete(precios);
			}
		}
	}

	@Override
	public void deletePrensa(TblCampana tblCampana) throws Exception {
		List<TblPrensa> prensaLst = daoPrensa.getTblPrensaLstByIdCampana(tblCampana.getIdCampana());
		if(prensaLst!=null){
			for(TblPrensa prensa: prensaLst){
				deletePrensaPrecios(prensa);
				daoPrensaEspacios.delete(prensa.getIdPrensa());
				daoPrensaSistemaVenta.delete(prensa.getIdPrensa());
				daoPrensaTienda.delete(prensa.getIdPrensa());
				daoPrensa.delete(prensa);
			}
		}
	}

	private void deletePrensaPrecios(TblPrensa prensa) throws Exception {
		List<TblPreciosPrensa> preciosLst= daoPrecioPrensa.getByPrensaId(prensa.getIdPrensa());
		if(preciosLst!=null){
			for(TblPreciosPrensa precios: preciosLst){
				daoPrecioPrensaDet.delete(precios.getIdPrecPrensa());
				daoPrecioPrensa.delete(precios);
			}
		}
	}

	@Override
	public List<TblCampanaDetalle> getDetalle() {
		return null;
	}

	public DAOCampana getDaoCampanas() {
		return daoCampanas;
	}

	public void setDaoCampanas(DAOCampana daoCampanas) {
		this.daoCampanas = daoCampanas;
	}

	/**
	 * @return the daoCampanaActividades
	 */
	public DAOCampanaActividades getDaoCampanaActividades() {
		return daoCampanaActividades;
	}

	/**
	 * @param daoCampanaActividades
	 *            the daoCampanaActividades to set
	 */
	public void setDaoCampanaActividades(
			DAOCampanaActividades daoCampanaActividades) {
		this.daoCampanaActividades = daoCampanaActividades;
	}

	@Override
	public List<TblCampanaActividades> getActivityList()
			throws GeneralException {
		try {
			//return daoCampanaActividades.getAll();
			return daoCampanaActividades.getActivityListFullAll();
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}

	@Override
	public List<TblCampanaActividades> getActivityListByRole(Integer role)
			throws GeneralException {
		return daoCampanaActividades.getActivityListByRole(role);
	}

	@Override
	public List<TblCampanaActividades> getActivityListByMonth(Date filter)
			throws GeneralException {
		return daoCampanaActividades.getActivityListByMonth(filter);
	}

	@Override
	public List<TblCampanaActividades> getActivityListByMonthAndRole(
			Date filter, Integer role) throws GeneralException {
		return daoCampanaActividades.getActivityListByMonth(filter, role);
	}

	@Override
	public TblCampanaActividades getActivityDetail(Long activityId)
			throws GeneralException {
		TblCampanaActividades activity = null;
		LOG.info("Init getActivityDetail");
		LOG.info(activityId.toString());
		try {
			activity = daoCampanaActividades.getById(activityId);
			activity.setTblCampana(daoCampanas.getById(activity.getTblCampana().getIdCampana()));
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(
					"Error al obtener la detalle en getActivityDetail", e);
		}
		return activity;
	}

        @Override
	public int deleteAndInsertActivities(int IntEsFlujo, long idCampana)
			throws GeneralException {
		int regDelete = 0;
		try {
			List<TblCampanaActividades> validaExistencia = daoCampanaActividades
					.getActivityListByIsFlujo(idCampana);
			if (validaExistencia.size() > 0)
				regDelete = daoCampanaActividades
						.deleteCampanaActividadByIdCampana(idCampana);
			else
				LOG.info("No existen registros por lo tanto no eliminaremos");
		} catch (GeneralException e) {
			LOG.error(e);
			throw new GeneralException(
					"Error al eliminar las actividades con id Campa??a "
							+ idCampana + " ", e);
		}
		return regDelete;
	}
	
        @Override
	public int deleteCampanaActividadByIdCampana(long idCampana) throws GeneralException {
		try {
			return daoCampanaActividades.deleteCampanaActividadByIdCampana(idCampana);
		} catch (GeneralException e) {
			LOG.error(e);
			throw new GeneralException(
					"Error al eliminar las actividades con id Campa?a "
							+ idCampana + " ", e);
		}
	}
	@Override
	public void deleteCampanaActividadByIdCampanaFull(long idCampana) throws GeneralException {
		daoCampanaActividades.deleteCampanaActividadByIdCampanaFull(idCampana);
	}

	/**
	 * @return the daoDelegacionActividades
	 */
	public DAODelegacionActividades getDaoDelegacionActividades() {
		return daoDelegacionActividades;
	}

	/**
	 * @param daoDelegacionActividades
	 *            the daoDelegacionActividades to set
	 */
	public void setDaoDelegacionActividades(
			DAODelegacionActividades daoDelegacionActividades) {
		this.daoDelegacionActividades = daoDelegacionActividades;
	}

	@Override
	public void saveActivity(TblCampanaActividades activity)
			throws GeneralException {
		try {
			if (activity != null
					&& activity.getCatEstatus().getIdestatus() == Constants.STATUS_CERRADO
					&& activity.getFechaCierre() == null) {
				activity.setFechaCierre(new Date());
				activity.setPorcentajeCompletado(100);
			}
			this.daoCampanaActividades.saveOrUpdate(activity);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error al guardar en saveActivity", e);
		}
	}

	@Override
	public void editActivity(TblCampanaActividades activity)
			throws GeneralException {
		try {
			this.daoCampanaActividades.editActivity(activity);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error al guardar en saveActivity", e);
		}
	}

	@Override
	public List<TblCampanaActividades> getNotificationActivityList()
			throws GeneralException {
		List<TblCampanaActividades> notificationActivityList = null;
		try {
			notificationActivityList = this.daoCampanaActividades
					.getNotificationActivityList();
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error al guardar en saveActivity", e);
		}
		return notificationActivityList;
	}

        @Override
        public boolean generateActivities(CampanaDTO campana, UsuarioDTO usuario, ServiceDynamicCatalogs service) throws GeneralException{
            CampanaMedioDTO cm = new CampanaMedioDTO();
            try {
                return findAndInsertActividades(campana.getTblCampana(), null, service, usuario, null);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(ServiceCampanaImpl.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
	@Override
	public boolean generateActivities(long idCampana, UsuarioDTO usuario,
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws GeneralException {
		return generateActivities(idCampana, usuario, serviceDynamicCatalogs, null);
	}
	
	@Override
	public boolean generateActivities(long idCampana, UsuarioDTO usuario,
			ServiceDynamicCatalogs serviceDynamicCatalogs, CampanaMedioDTO medioDTO) throws GeneralException {
		boolean exito = false;
		try {
			exito = tomarFechasDeCampana(idCampana, usuario,
					serviceDynamicCatalogs, medioDTO);
		} catch (Exception e) {
			LOG.error("Error al generar en generateActivities", e);
			throw new GeneralException(e);
		}

		return exito;
	}

	private boolean tomarFechasDeCampana(long idCampana, UsuarioDTO usuario,
			ServiceDynamicCatalogs serviceDynamicCatalogs, CampanaMedioDTO medioDTO) throws Exception {
		boolean exito = false;
		TblCampana tablaCampana = getCampana(idCampana);
		int tipoCampana = tablaCampana.getIdTipoCampana();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		AttrSearch attrSearch = new AttrSearch();
		Date fechaIniCamp = null;
		Date fechaFinCamp = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if (tipoCampana == Integer.valueOf(Constants.TIPO_CAMAPANA_ANUAL)
				|| tipoCampana == Integer
						.valueOf(Constants.TIPO_CAMPANA_PROMOCION_ADICIONAL)) {
			if(tablaCampana.getIdPeriodo()==null){
				fechaIniCamp = tablaCampana.getFechaInicio();
				fechaFinCamp = tablaCampana.getFechaInicio();
			}else{
				int idPeriodo = tablaCampana.getIdPeriodo();
				attrSearch.setAttrName(Constants.ATT_ID);
				attrSearch.setValue(String.valueOf(idPeriodo));
				insertSearch.add(attrSearch);
				List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
						Constants.CAT_PERIODO, insertSearch);
				for (CatRegs reg : regs) {
					Set<CatRegValues> setReg = reg.getRegValues();
					if (setReg != null) {
						for (CatRegValues regVals : setReg) {
							String attName = regVals.getCatAttrs().getAttribs()
									.getAttrName();
							if (attName.equals(Constants.ATT_FECHA_INICIO)) {
								LOG.info("ATT_FECHA_INICIO: "
										+ regVals.getValue().replace('/', '-'));
								fechaIniCamp = dateFormat.parse(regVals.getValue()
										.replace('/', '-'));
							}
							if (attName.equals(Constants.ATT_FECHA_FIN)) {
								LOG.info("ATT_FECHA_FIN: "
										+ regVals.getValue().replace('/', '-'));
								fechaFinCamp = dateFormat.parse(regVals.getValue()
										.replace('/', '-'));
							}
						}
					}
				}
			}
		} else if (tipoCampana == Integer
				.valueOf(Constants.TIPO_CAMPANA_ESPECIAL)) {
			fechaIniCamp = tablaCampana.getFechaInicio();
		}
		
		tablaCampana.setFechaInicio(fechaIniCamp);
		if (fechaFinCamp != null){
			tablaCampana.setFechaFin(fechaFinCamp);
		}
		exito = findAndInsertActividades(tablaCampana, dateFormat,
				serviceDynamicCatalogs, usuario, medioDTO);
		return exito;
	}

	private boolean findAndInsertActividades(TblCampana tablaCampana,
			SimpleDateFormat formatoFecha, 
			ServiceDynamicCatalogs serviceDynamicCatalogs, UsuarioDTO usuario, CampanaMedioDTO medioDTO)
			throws Exception {
		boolean exito = false;
		Calendar fechaActual = Calendar.getInstance();
		ActivObj objetoActividad = new ActivObj();
		List<ActivObj> listObjetosActividades = new ArrayList<>();
		//Map<Integer, Object> mapActividades = new HashMap<Integer, Object>();

		UsuarioDTO usuarioByUsuarioCreacion;
		UsuarioDTO usuarioByUsuarioModificacion;
		Catalogs catalogActividades = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_ACTIVITY);
		List<TblCampanaActividades> actividadesAInsertar = new ArrayList<>();
		TblCampanaActividades tblCampanaAct;
		String week = "0";
		String dur_days = "0";
		String dur_hrs = "0";
		int rol = 0;
		int idActividad = 0;
		deleteAndInsertActivities(Constants.NUMERO_ES_FLUJO,
				tablaCampana.getIdCampana());
		List<String> correosLst= new ArrayList<>();
		if (catalogActividades != null) {
			List<AttrSearch> insertSearch = new ArrayList<>();
			if(null == medioDTO){
				AttrSearch attr = new AttrSearch();
				attr.setAttrName(Constants.ATT_ID_TIPO_EVENTO);
				attr.setValue(String.valueOf(tablaCampana.getIdTipoEvento()));
				insertSearch.add(attr);
			}else{
				AttrSearch attr = new AttrSearch();
				attr.setAttrName(Constants.ATT_ID_MEDIO);
				attr.setValue(medioDTO.getMedio().getCode());
				insertSearch.add(attr);
				AttrSearch attr2 = new AttrSearch();
				attr2.setAttrName(Constants.ATT_ID_TIPO_MEDIO);
				attr2.setValue(medioDTO.getTipoMedio().getCode());
				insertSearch.add(attr2);
			}
			
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_ACTIVITY, insertSearch);
			for (CatRegs reg : regs) {
				StringBuilder nameActividad = new StringBuilder();
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();

						if (regVals.getCatAttrs().getAttribs().getAttrName()
								.equals(Constants.ATT_ID)) {
							idActividad = Integer.valueOf(regVals.getValue());
							objetoActividad.setId(idActividad);
						} else if (attName.equals(Constants.ATT_NAME)) {
							objetoActividad.setName(regVals.getValue());
						} else if (attName.equals(Constants.ATT_DESCRIPTION)) {
							objetoActividad.setDescripcion(regVals.getValue());
						} else if (attName.equals(Constants.ATT_CODE)) {
							nameActividad.append(regVals.getValue());
							objetoActividad.setCode(regVals.getValue());
						} else if (attName.equals(Constants.ATT_DUR_HRS)) {
							dur_hrs = regVals.getValue();
							objetoActividad.setDurDias(Integer
									.valueOf(dur_days));
						} else if (attName.equals(Constants.ATT_DUR_DAYS)) {
							dur_days = regVals.getValue();
							objetoActividad.setDurDias(Integer
									.valueOf(dur_days));
						} else if (attName.equals(Constants.ATT_WEEK)) {
							week = regVals.getValue();
							objetoActividad.setDiasAntes(Integer.valueOf(week));
						} else if (attName.equals(Constants.ATT_ID_ROL)) {
							rol = Integer.valueOf(regVals.getValue());
							objetoActividad.setIdRol(rol);
						} else if (attName.equals(Constants.ATT_IDNOTIF)) {
							objetoActividad.setIdNotif(Integer.valueOf(regVals
									.getValue()));
						} else if (attName.equals(Constants.ATT_EsGeneral)) {
							if (Boolean.parseBoolean(regVals.getValue())){
								objetoActividad.setIsGeneral(1);
							} else {
								objetoActividad.setIsGeneral(0);
							}
							
						} else if (attName.equals(Constants.ATT_VIGENCIA)) {
							objetoActividad.setVigencia(regVals.getValue());
						}

					}

					//listObjetosActividades.add(objetoActividad);
					boolean bandValFecha = false;
					Calendar fechaIniActividad = new GregorianCalendar();
					fechaIniActividad.setTime(tablaCampana.getFechaInicio());
					Calendar fechaFinActividad = new GregorianCalendar();
					fechaIniActividad.add(Calendar.DATE, (-1 * Integer.parseInt(week)));
					fechaIniActividad.add(Calendar.HOUR_OF_DAY, Integer.parseInt(dur_hrs));

					while (!bandValFecha) {
						if (ValidationUtil.validateDate(fechaIniActividad,
								serviceDynamicCatalogs)) {

							bandValFecha = true;
						} else
							fechaIniActividad.add(Calendar.DATE, -1);
					}
					fechaFinActividad.setTime(fechaIniActividad.getTime());
					
					fechaFinActividad.add(Calendar.DATE,
							Integer.valueOf(dur_days));
					
					Calendar fechaIniAux = new GregorianCalendar();
					fechaIniAux.setTime(fechaIniActividad.getTime());
					Calendar fechaFinAux = new GregorianCalendar();
					fechaFinAux.setTime(fechaFinActividad.getTime());
					fechaFinAux.add(Calendar.DATE, 1);
					
					int diasInhabiles = 0;
					while (fechaIniAux.before(fechaFinAux)){
						fechaIniAux.add(Calendar.DATE, 1);
						if (!ValidationUtil.validateDate(fechaIniAux,
								serviceDynamicCatalogs)) {
							diasInhabiles++;
						}
					}
					
					fechaFinActividad.add(Calendar.DATE, diasInhabiles);
					bandValFecha = false;
					while (!bandValFecha) {
						if (ValidationUtil.validateDate(fechaFinActividad,
								serviceDynamicCatalogs)) {
							bandValFecha = true;
						} else
							fechaFinActividad.add(Calendar.DATE, 1);
					}
					usuarioByUsuarioCreacion = usuario;
					usuarioByUsuarioModificacion = usuario;

					/*List<UsuarioDTO> listUsuariosByIdUsuarioResp = tomarActividadesDelCatalogUsuariosPorIdRol(
							rol, serviceDynamicCatalogs);
					if (listUsuariosByIdUsuarioResp.size() > 0) {
						nameActividad.append("-" + tablaCampana.getNombre());
						for (UsuarioDTO userResponsable : listUsuariosByIdUsuarioResp) {
							//correosLst.add(userResponsable.getEmail());
							perfil.setPerfil(rol);
							tblCampanaAct = new TblCampanaActividades(
									tablaCampana, idActividad,
									Integer.valueOf(userResponsable
											.getUserId()),
									nameActividad.toString(),
									Constants.NUMERO_ES_FLUJO, Constants.STATUS_ABIERTO, null,
									fechaIniActividad.getTime(),
									fechaFinActividad.getTime(), rol,
									usuarioByUsuarioCreacion.getUserId(),
									fechaActual.getTime(),
									usuarioByUsuarioModificacion
											.getUserId(),
									fechaActual.getTime(), 0, null, null);

							ActividadesAInsertar.add(tblCampanaAct);
						}
					}*/
//					List<TblFolletoHojas> hojaLst = daoFolletoHojas.getHojasByFolletoId(medioDTO.getFolletoId());
                                        List<TblFolletoHojas> hojaLst = new ArrayList<>();
					if(!hojaLst.isEmpty()){
						nameActividad.append("-");
						nameActividad.append(tablaCampana.getNombre());
						nameActividad.append("-");
						nameActividad.append(medioDTO.getTitulo());
						nameActividad.append("-");
						StringBuilder nameActividadAux;
						int index= 0;
						int numHoja=0;
						for(TblFolletoHojas hoja: hojaLst){
							if(numHoja!=hoja.getNumHoja()){
								index=0;
								numHoja=hoja.getNumHoja();
							}
							nameActividadAux= new StringBuilder();
							nameActividadAux.append("H");
							nameActividadAux.append(numHoja);
							nameActividadAux.append("-E");
							nameActividadAux.append(index++);
							//DISE?ADOR
							if(hoja.getIdDesigner()!=null&&rol==Constants.DISE?O){
								UsuarioDTO designer= MBUtil.getUsuario(serviceDynamicCatalogs, hoja.getIdDesigner());
								correosLst.add(designer.getEmail());
								tblCampanaAct = new TblCampanaActividades(
										tablaCampana, idActividad,
										designer.getUserId(),
										nameActividad.toString()+nameActividadAux.toString(),
										Constants.NUMERO_ES_FLUJO, daoCatEstatus.getEstatusById(Constants.STATUS_ABIERTO)  , null,
										fechaIniActividad.getTime(),
										fechaFinActividad.getTime(), rol,
										usuarioByUsuarioCreacion.getUserId(),
										fechaActual.getTime(),
										usuarioByUsuarioModificacion.getUserId(),
										fechaActual.getTime(), 0, null, null);
								actividadesAInsertar.add(tblCampanaAct);
								listObjetosActividades.add(objetoActividad);
							}
							//COMPRADOR
	//						if(hoja.getIdUserInvitado()!=null&&rol==Constants.COMPRADOR){
							if(hoja.getIdUserInvitado()!=null&&rol==9){
								UsuarioDTO comprador= MBUtil.getUsuario(serviceDynamicCatalogs, hoja.getIdUserInvitado());
								correosLst.add(comprador.getEmail());
								tblCampanaAct = new TblCampanaActividades(
										tablaCampana, idActividad,
										comprador.getUserId(),
										nameActividad.toString()+nameActividadAux.toString(),
										Constants.NUMERO_ES_FLUJO, daoCatEstatus.getEstatusById(Constants.STATUS_ABIERTO), null,
										fechaIniActividad.getTime(),
										fechaFinActividad.getTime(), rol,
										usuarioByUsuarioCreacion.getUserId(),
										fechaActual.getTime(),
										usuarioByUsuarioModificacion.getUserId(),
										fechaActual.getTime(), 0, null, null);
								actividadesAInsertar.add(tblCampanaAct);
								objetoActividad.setCampanaActividad(tblCampanaAct);
								listObjetosActividades.add(objetoActividad);
							}
						}
					}
				}
				//mapActividades.put(idActividad, objetoActividad);
				week = "0";
				dur_days = "0";
				dur_hrs = "0";
				rol = 0;
				idActividad = 0;
			}
			if (actividadesAInsertar.size() > 0) {
				for (TblCampanaActividades activity : actividadesAInsertar) {
					saveActivity(activity);
				}
				
				if (notificarUsuarios(listObjetosActividades,
						serviceDynamicCatalogs, correosLst)) {
					UsuarioDTO responsable = MBUtil.getUsuario(serviceDynamicCatalogs, tablaCampana.getIdResponsable());
					SendMail.sendGenericEmail(responsable.getEmail(),
							"EMAIL_ACTIVIDADES_RESPONSABLE_TITLE",
							"EMAIL_ACTIVIDADES_RESPONSABLE_DETAIL",
							tablaCampana.getNombre());

					TblCampanaMedio campanaMedio= daoCampanaMedio.getById(medioDTO.getIdCampanaMedio());
					UsuarioDTO responsableMedio= MBUtil.getUsuario(serviceDynamicCatalogs, campanaMedio.getResponsableId());
					SendMail.sendGenericEmail(responsableMedio.getEmail(),
							"EMAIL_ACTIVIDADES_RESPONSABLE_TITLE",
							"EMAIL_ACTIVIDADES_RESPONSABLE_MEDIO_DETAIL",
							campanaMedio.getTitulo(),
							tablaCampana.getNombre());
					exito = true;
				}
				actividadesAInsertar.clear();
				//mapActividades.clear();
			} else
				exito = true;
		}
		return exito;
	}
	
	private String replaceValues(String mail, String campanaName, String detalle){
		mail= mail.replaceAll("CAMPANA", campanaName);
		return mail.replaceAll("DETALLE", detalle);
	}

	private boolean notificarUsuarios(List<ActivObj> listObjetosActividades,
			ServiceDynamicCatalogs serviceDynamicCatalogs, List<String> listaCorreos) throws Exception {
		boolean exito = false;
		int valorEsGeneral = 0;
		int idNotificacion = 0;
		String subject = null;
		String body = null;
		for(ActivObj objetoActividad: listObjetosActividades){
			idNotificacion = objetoActividad.getIdNotif();
			valorEsGeneral = objetoActividad.getIsGeneral();
			String actividadTitulo= "";
			try{actividadTitulo=objetoActividad.getCampanaActividad().getNombreActividad();}catch(Exception e){}
			TblCampana campana=new TblCampana();
			try{campana= objetoActividad.getCampanaActividad().getTblCampana();}catch(Exception e){}
			String[] partesCorreo = procTraerPartesDelCorreo(idNotificacion, serviceDynamicCatalogs);
			subject = replaceValues(partesCorreo[0], campana.getNombre(), actividadTitulo);
			body = "<p>" + replaceValues(partesCorreo[1], campana.getNombre(), actividadTitulo) + "</p><p>"
					+ replaceValues(partesCorreo[2], campana.getNombre(), actividadTitulo)
					+ "</p><p>" + replaceValues(partesCorreo[3], campana.getNombre(), actividadTitulo) + "</p>";
			if (Constants.NUMERO_ES_GENERAL == valorEsGeneral) {
				//List<String> listaCorreos = getCorreoByIdRolDeCatUser(idRol,serviceDynamicCatalogs);
				if (listaCorreos != null&&listaCorreos.size()>0) {
					if (enviarCorreosByCatalogoTemplateNotificaciones(idNotificacion, listaCorreos,
							serviceDynamicCatalogs, subject, body)) {
						exito = true;
					} else
						exito = false;
					listaCorreos.clear();
				}
			} else {
				if (mandarCorreosByIdCampanaYIdCategoria(objetoActividad,
						campana.getIdCampana(), serviceDynamicCatalogs,
						subject, body)) {
					exito = true;
				} else
					exito = false;
			}
		}
		return exito;
	}

	private String[] procTraerPartesDelCorreo(int idNotificacion,
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		String[] partesDeCorreo = new String[4];
		AttrSearch attr = new AttrSearch();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		attr.setAttrName(Constants.ATT_ID);
		attr.setValue(String.valueOf(idNotificacion));
		insertSearch.add(attr);
		Catalogs catalogTemplateCorreoNotificacion = serviceDynamicCatalogs
				.getCatalogByName(Constants.CAT_TEM_NOTF);
		if (catalogTemplateCorreoNotificacion != null) {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_TEM_NOTF, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						if (attName.equals(Constants.ATT_ASUNTO_MSG)) {
							partesDeCorreo[0] = regVals.getValue();
						} else if (attName.equals(Constants.ATT_SALUDO_MSG)) {
							partesDeCorreo[1] = regVals.getValue();
						} else if (attName.equals(Constants.ATT_CUERPO_MSG)) {
							partesDeCorreo[2] = regVals.getValue();
						} else if (attName.equals(Constants.ATT_DESPEDIDA_MSG)) {
							partesDeCorreo[3] = regVals.getValue();
						}

					}
				}
			}
		}
		return partesDeCorreo;
	}

	/**
	 * barrer Tbl_CampanaDetalle con el ID de la Campa??a en curso
	 * 
	 * @param objetoActividad
	 * @return
	 * @throws Exception
	 */
	private boolean mandarCorreosByIdCampanaYIdCategoria(
			ActivObj objetoActividad, long idCampana,
			ServiceDynamicCatalogs serviceDynamicCatalogs, String subject,
			String body) throws Exception {
		//int idCategoriaByUser = 0;
		boolean enviado = false;
		List<TblCampanaCategorias> listTablaCampanaCategorias = daoCampanaCategorias.getCategoriasByCampanaId(idCampana);
		for (TblCampanaCategorias registroTablaCampanaCategoria : listTablaCampanaCategorias) {
			UsuarioDTO userDTO = MBUtil.getUsuarioByIdCategoria(serviceDynamicCatalogs, registroTablaCampanaCategoria.getId().getIdCategoria());
			//idCategoriaByUser = registroTablaCampanaDetalle.getIdCategoria();
			/*List<String> listaCorreos = getCorreoByIdCategoriaCampana(
					idCategoriaByUser, serviceDynamicCatalogs);*/
			if (userDTO.getEmail() != null){
				List<String> listaCorreos = new ArrayList<String>();
			
				listaCorreos.add(userDTO.getEmail());
				if (mandarCorreos(listaCorreos, subject,
						body)) {
					enviado = true;
				}
				listaCorreos.clear();
			}
		}

		return enviado;
	}

	private boolean enviarCorreosByCatalogoTemplateNotificaciones(int idNotificacion,
			List<String> listaCorreos,
			ServiceDynamicCatalogs serviceDynamicCatalogs, String subject,
			String body) {
		boolean enviado = false;
		if (mandarCorreos(listaCorreos, subject,
				body)) {
			enviado = true;
		}
		return enviado;
	}

	private boolean mandarCorreos(List<String> listaCorreos, String subject, String body) {
		boolean envioCorreo = false;
		int numero = listaCorreos.size();
		String[] correo = new String[numero];
		for (int i = 0; i < listaCorreos.size(); i++) {
			correo[i] = listaCorreos.get(i);
		}
		SendMail.sendGenericEmail(correo,
				subject,
				body);
		envioCorreo = true;
		return envioCorreo;
	}

	@Override
	public List<TblCampanaActividades> getActivityList(Long idCampana, Date startDate, Date endDate)
			throws GeneralException {
		if (idCampana != null && idCampana.intValue() == -1) {
			try {
				return daoCampanaActividades.getActivityList(startDate,endDate);
			} catch (Exception e) {
				LOG.error(e);
				throw new GeneralException(e);
			}
		}
		return daoCampanaActividades.getActivityList(idCampana, startDate,endDate);
	}

	@Override
	public List<TblCampanaActividades> getActivityList(Long idCampana,
			Integer role, Date startDate, Date endDate) throws GeneralException {
		if (idCampana != null && idCampana.intValue() == -1) {
			try {
				return daoCampanaActividades.getActivityList(role, startDate, endDate);
			} catch (Exception e) {
				LOG.error(e);
				throw new GeneralException(e);
			}
		}
		return daoCampanaActividades.getActivityList(idCampana, role, startDate,endDate);
	}

	@Override
	public void deleteActivity(TblCampanaActividades activity)
			throws GeneralException {
		try {
			this.daoCampanaActividades.delete(activity);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error en deleteActivity", e);
		}
	}

	@Override
	public void deleteDelegateActivity(TblDelegacionActividades delegateActivity)
			throws GeneralException {
		try {
			this.daoDelegacionActividades.delete(delegateActivity);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error en deleteDelegateActivity", e);
		}
	}

	@Override
	public TblDelegacionActividades getDelegateActivity(Integer id)
			throws GeneralException {
		try {
			return this.daoDelegacionActividades.getById(id);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error en getDelegateActivity", e);
		}
	}

	@Override
	public void saveDelegateActivity(TblDelegacionActividades delegateActivity)
			throws GeneralException {
		try {
			this.daoDelegacionActividades.save(delegateActivity);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error en saveDelegateActivity", e);
		}
	}

	@Override
	public List<TblDelegacionActividades> getDelegateActivities()
			throws GeneralException {
		try {
			return this.daoDelegacionActividades.getAll();
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error en getDelegateActivities", e);
		}
	}

	@Override
	public List<TblDelegacionActividades> getDelegateActivities(String idUsuario)
			throws GeneralException {
		try {
			if (idUsuario == null) {
				return getDelegateActivities();
			}
			return this.daoDelegacionActividades.getDelegateActivity(idUsuario);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error en getDelegateActivities", e);
		}
	}

	@Override
	public List<TblDelegacionActividades> getDelegateActivity(
			String idUsuarioDelega, String idUsuarioDelegado, Date dateInit,
			Date dateEnd) throws GeneralException {
		try {
			return this.daoDelegacionActividades.getDelegateActivity(
					idUsuarioDelega, idUsuarioDelegado, dateInit, dateEnd);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error en getDelegateActivities", e);
		}
	}

	@Override
	public List<TblCampanaActividades> getActivityListNotif()
			throws GeneralException {
		return daoCampanaActividades.getActivityListNotif();
	}

	@Override
	public List<TblCampanaActividades> getActivityListToFinish()
			throws GeneralException {
		return daoCampanaActividades.getActivityListToFinish();
	}

	@Override
	public List<CampaniaTreeRegs> todas() {
		return daoCampaniaTreeRegs.todasOrdenada();
	}

	@Override
	public List<VerTodas> showAllvT() {
		return daoVerTodas.obtenerTodasOrdenadas();
	}

	@Override
	public void saveCampana(TblCampana tblCampana) throws GeneralException {
		try {
			daoCampanas.saveOrUpdate(tblCampana);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
	}
	
	@Override
	public TblCampana saveNewCampana(TblCampana tblCampana) throws GeneralException {
		LOG.info("saveCampana " + tblCampana.getIdCampana());
		try {
			daoCampanas.saveOrUpdate(tblCampana);
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException(e);
		}
		return tblCampana;
	}

	/**
	 * @return the daoCampanaMedio
	 */
	public DAOCampanaMedio getDaoCampanaMedio() {
		return daoCampanaMedio;
	}

	/**
	 * @param daoCampanaMedio
	 *            the daoCampanaMedio to set
	 */
	public void setDaoCampanaMedio(DAOCampanaMedio daoCampanaMedio) {
		this.daoCampanaMedio = daoCampanaMedio;
	}

	@Override
	public List<TblCampanaMedio> getCampanaMediosByIdCampana(long idCampana) {
		List<TblCampanaMedio> list = new ArrayList<>();
		try {
			list = daoCampanas.getMedioByCampanaId(idCampana);
		} catch (Exception e) {
			LOG.error(e);
		}
		return list;

	}
	
	@Override
	public List<TblCampanaAutorizacion> getAutorizacionLst(long idCampana) throws GeneralException {
		return daoCampanaAutorizacion.getAutorizacionLst(idCampana);
	}
	
	@Override
	public void deleteCampanaAuth(TblCampanaAutorizacion auth) throws Exception{
		daoCampanaAutorizacion.delete(auth);
	}
	
	@Override
	public List<TblCampanaDetalle> getCampanaDetalleByIdCampana(long idCampana){
		List<TblCampanaDetalle> list = new ArrayList<>();
		try {
			list= daoCampanasDetalles.getCampanaDetallesListByidCampana(idCampana);
		} catch (Exception e) {
			LOG.error(e);
		}
		return list;
	}
	
	@Override
	public void deleteCampanaMedio(TblCampanaMedio campanaMedio) throws Exception{
		daoCampanaMedio.delete(campanaMedio);
	}
	
	@Override
	public void deleteCampanaDetalle(TblCampanaDetalle detalle) throws Exception{
		daoCampanasDetalles.delete(detalle);
	}

	@Override
	public CampanaResultDTO getCampanas(Integer firstRecord, Integer rowsPerPage, Map<String, String> filters) {
		return daoCampanas.getCampanas(firstRecord, rowsPerPage, filters);
	}

	@Override
	public List<TblCampana> getAllCampanasByYearAsc(int year, List<PeriodoDTO> periodos) {
		List<TblCampana> list = new ArrayList<>();
		try {
			list = daoCampanas.getAllCampanasByYearAsc(year, periodos);
		} catch (Exception e) {
			LOG.error(e);
		}
		return list;
	}

	@Override
	public List<TblCampana> getListCampanaDTOByYearAndTypeCampana(int year,
			int typeCampana, List<PeriodoDTO> periodos) {
		List<TblCampana> list = new ArrayList<>();
		try {
			list = daoCampanas.getListCampanaDTOByYearAndTypeCampana(year, typeCampana, periodos);
		} catch (Exception e) {
			LOG.error(e);
		}
		return list;
	}

	@Override
	public List<TblCampana> getAllCampanaByTypeCampana(int typeCampana) {
		List<TblCampana> list = new ArrayList<>();
		try {
			list = daoCampanas.getAllCampanaByTypeCampana(typeCampana);
		} catch (Exception e) {
			LOG.error(e);
		}
		return list;
	}

	@Override
	public List<TblCampanaMedio> getCampanaMedio(long idCampana) {
		List<TblCampanaMedio> tblCampanaMedio = null;
		try {
			tblCampanaMedio = daoCampanaMedio.getCatByCampanaId(idCampana);
		} catch (Exception e) {
			LOG.error(e);
		}
		return tblCampanaMedio;
	}
	
	@Override
	public TblCampanaMedio getCampanaMedio(Integer idCampanaMedio) {
		TblCampanaMedio tblCampanaMedio = null;
		try {
			tblCampanaMedio = daoCampanaMedio.getById(idCampanaMedio);
		} catch (Exception e) {
			LOG.error(e);
		}
		return tblCampanaMedio;
	}
	
        @Override
	public void saveCampanaMedio(TblCampanaMedio entity) throws GeneralException{
		try {
			daoCampanaMedio.save(entity);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
	
	@Override
	public List<TblCampanaCategorias> getCampanaCategorias(long idCampana) {
		List<TblCampanaCategorias> categoriaLst = null;
		try {
			categoriaLst = daoCampanaCategorias.getCategoriasByCampanaId(idCampana);
		} catch (Exception e) {
			LOG.error(e);
		}
		return categoriaLst;
	}
	
	@Override
	public void deleteCampanaCategoria(TblCampanaCategorias categoria) throws Exception{
		daoCampanaCategorias.delete(categoria);
	}
	
	@Override
	public void removeCampanaCategoriaById(int idCategoria,long idCampana) {
		
		TblCampanaCategoriasId tblCamCatId = new TblCampanaCategoriasId();
						 
		tblCamCatId.setIdCampana(idCampana);
		tblCamCatId.setIdCategoria(idCategoria);
		
		try {
			TblCampanaCategorias tblCamCat = daoCampanaCategorias.getById(tblCamCatId);
			daoCampanaCategorias.delete(tblCamCat);
		} catch (Exception e) {
			LOG.error(e);
		}	
	}
	
	@Override
	public void updateCampanaCategorias(TblCampanaCategorias tblCamCat) {
		try {
			daoCampanaCategorias.save(tblCamCat);
		} catch (Exception e) {
			LOG.error(e);
		}	
	}
	
        @Override
	public void removePlazaByCategoryAndCampana(int idCategoria,long idCampana) {
		List<TblCampanaCategoriasPlaza> lTblCampCatPlaza = daoCampanaCategoriasPlaza.getPlazasByCampanaIdAndCategory(idCampana, idCategoria);
		for (TblCampanaCategoriasPlaza lblCampCatPlaza : lTblCampCatPlaza){
			try {
				daoCampanaCategoriasPlaza.delete(lblCampCatPlaza);
			} catch (Exception e) {
				LOG.error(e);
			}	
		}
	
	}
	
        @Override
	public void saveCampanaCategoriaPlaza(TblCampanaCategoriasPlaza tblCamCatPlaza){
		try {
			daoCampanaCategoriasPlaza.save(tblCamCatPlaza);
		} catch (Exception e){
			LOG.error(e);
		}
	}

	@Override
	public TblCampanaProgramas getProgramaById(long idCampana, int programaId) {
		return daoCampanaProgramas.getProgramaById(idCampana, programaId);
	}

	@Override
	public List<TblCampanaProgramas> getCampanaProgramas(long idCampana){
		return daoCampanaProgramas.getProgramasByCampanaId(idCampana);
	}
	
        @Override
	public void updateCampanaProgramas(TblCampanaProgramas tblCampanaProgramas) {
		try{
			//daoCampanaProgramas.save(tblCampanaProgramas);
			daoCampanaProgramas.clearSession();
			daoCampanaProgramas.flush();
			daoCampanaProgramas.saveOrUpdate(tblCampanaProgramas);
		} catch(Exception e){
			LOG.error(e.getMessage());
		}
	}
	
        @Override
	public void removeCampanaProgramas(TblCampanaProgramas tblCampanaProgramas) throws GeneralException{
		try{
			daoCampanaProgramas.delete(tblCampanaProgramas);
		} catch(Exception e){
			LOG.error(e.getMessage());
			throw new GeneralException(e);
		}
	}
	
        @Override
	public void removeCampanaProgramasByIdCampanaIdPrograma(long idCampana, int idPrograma) throws GeneralException{
		try{
			daoCampanaProgramas.deleteProgramasByCampanaIdProgramaID(idCampana, idPrograma);
		} catch(Exception e){
			LOG.error(e.getMessage());
			throw new GeneralException(e);
		}
	}
	
        @Override
	public void saveCampanaProgramasCategorias(TblCampanaProgramasCategorias tblCamProgCat){
		try {
			daoCampanaProgramasCategorias.save(tblCamProgCat);
		} catch (Exception e){
			LOG.error(e);
		}
	}
    
    @Override
	public void saveCampanaProgramasCategorias(List<TblCampanaProgramasCategorias> list){
		daoCampanaProgramasCategorias.saveCampanaProgramasCategorias(list);
	}
	
        @Override
	public void removeCampProgCategByProgramaAndCampana(long idCampana, int idPrograma) throws GeneralException {
		List<TblCampanaProgramasCategorias> ltblCampProgCat = daoCampanaProgramasCategorias.getCategoriasByCampanaIdAndPrograma(idCampana, idPrograma);
		for (TblCampanaProgramasCategorias tblCampProgCat : ltblCampProgCat){
			try {
				daoCampanaProgramasCategorias.delete(tblCampProgCat);
			} catch (Exception e) {
				LOG.error(e.getMessage());
				throw new GeneralException(e);
			}	
		}
	
	}
	
        @Override
	public void saveCampanaProgramasPlazas(TblCampanaProgramasPlazas tblCamProgPlazas) throws GeneralException{
		try {
			daoCampanaProgramasPlazas.save(tblCamProgPlazas);
		} catch (Exception e){
			LOG.error(e.getMessage());
			throw new GeneralException(e);
		}
	}
	
	public void removeCampProgPlazasByProgramaAndCampana(long idCampana, int idPrograma) throws GeneralException {
		List<TblCampanaProgramasPlazas> ltblCampProgPlazas = daoCampanaProgramasPlazas.getPlazasByCampanaIdAndPrograma(idCampana, idPrograma);
		for (TblCampanaProgramasPlazas tblCampProgPlaza : ltblCampProgPlazas){
			try {
				daoCampanaProgramasPlazas.delete(tblCampProgPlaza);
			} catch (Exception e) {
				LOG.error(e.getMessage());
				throw new GeneralException(e);
			}	
		}
	
	}

	public DAOFolleto getDaoFolleto() {
		return daoFolleto;
	}

	public void setDaoFolleto(DAOFolleto daoFolleto) {
		this.daoFolleto = daoFolleto;
	}

	public DAOPrensa getDaoPrensa() {
		return daoPrensa;
	}

	public void setDaoPrensa(DAOPrensa daoPrensa) {
		this.daoPrensa = daoPrensa;
	}

	public DAOFolletoTienda getDaoFolletoTienda() {
		return daoFolletoTienda;
	}

	public void setDaoFolletoTienda(DAOFolletoTienda daoFolletoTienda) {
		this.daoFolletoTienda = daoFolletoTienda;
	}
	
	public void saveFolleto(TblFolleto entity) throws GeneralException{
		try {
			daoFolleto.saveOrUpdate(entity);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
	
	public TblFolleto getFolleto(Serializable id) throws GeneralException{
		try {
			return daoFolleto.getById(id);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void saveFolletoZona(List<String> zona, int folletoId)
			throws GeneralException {
		 if(zona!=null){
			 try{
				for(String zonaId: zona){
					TblFolletoZonaId id= new TblFolletoZonaId();
					id.setIdFolleto(folletoId);
					id.setIdZona(Integer.parseInt(zonaId));
					TblFolletoZona entity= new TblFolletoZona();
					entity.setId(id);
					daoFolletoZona.saveOrUpdate(entity);
				}
		 	} catch (Exception e) {
		 		throw new GeneralException(e);
			}
		 }
	}
	
	@Override
	public List<TblFolletoZona> getZonasIdByFolletoId(int idFolleto) throws GeneralException{
		try {
			return daoFolletoZona.getZonasById(idFolleto);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}

	@Override
	public void saveFolletoTienda(List<String> sucursal, int folletoId)
			throws GeneralException {
		 if(sucursal!=null){
			 try{
				for(String sucursalId: sucursal){
					TblFolletoTiendaId id= new TblFolletoTiendaId();
					id.setIdFolleto(folletoId);
					id.setIdTienda(Integer.parseInt(sucursalId));
					TblFolletoTienda entity= new TblFolletoTienda();
					entity.setId(id);
					daoFolletoTienda.saveOrUpdate(entity);
				}
		 	} catch (Exception e) {
		 		throw new GeneralException(e);
			}
		 }
	}
	
	public List<TblFolletoTienda> getTiendasIdByFolletoId(int idFolleto) throws GeneralException{
		try {
			return daoFolletoTienda.getTiendasIdByFolletoId(idFolleto);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
	
	public List<TblFolletoSistemaVenta> getSistemaVentaByFolleto(int idFolleto) throws GeneralException{
		try {
			return daoFolletoSistemaVenta.getSistemaVentaByFolleto(idFolleto);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}
	
	@Override
	public void saveFolletoSistemaVenta(List<String> sistemaVentasLst, int folletoId, String sistemaVentaDefaultId)
			throws GeneralException {
		 if(sistemaVentasLst!=null){
			 try{
				for(String idSistemaVenta: sistemaVentasLst){
					TblFolletoSistemaVenta entity= new TblFolletoSistemaVenta();
					TblFolletoSistemaVentaId id= new TblFolletoSistemaVentaId();
					id.setIdFolleto(folletoId);
					id.setIdSistemaVenta(Integer.parseInt(idSistemaVenta));
					entity.setId(id);
					if(sistemaVentaDefaultId.equals(idSistemaVenta)){
						entity.setSistemaDefault('Y');
					}else{
						entity.setSistemaDefault('N');
					}
					daoFolletoSistemaVenta.saveOrUpdate(entity);
					
				}
		 	} catch (Exception e) {
		 		throw new GeneralException(e);
			}
		 }
	}
	
	@Override
	public List<TblFolletoHojas> getFolletoHojas(int idFolleto){
		return daoFolletoHojas.getHojasByFolletoId(idFolleto);
	}
	
	@Override
	public void saveFolletoHojas(List<ArrayList<HojaFolleto>> hojaLst, int folletoId, int idSistemaVentaDef, Integer tipoMedio)
			throws GeneralException {
		if (hojaLst != null) {
			try {
				short numHoja= 0;
				for(ArrayList<HojaFolleto> espaciosHoja: hojaLst){
					if(espaciosHoja!=null){
						Integer parentId= null;
						numHoja++;
						StringBuffer idHoja= new StringBuffer();
						int segId= 1;
						for (HojaFolleto hoja : espaciosHoja) {
							TblFolletoHojas entity = new TblFolletoHojas();
							entity.setIdFolleto(folletoId);
							
							if(null!=hoja.getCategory()&&null!=hoja.getCategory().getId()){
								entity.setIdCategory(hoja.getCategory().getId());
							}
							if(null!=hoja.getSubcategory()&&null!=hoja.getSubcategory().getId()){
								entity.setIdScategory(hoja.getSubcategory().getId());
							}
							if(null!=hoja.getHostedBuyer()&&null!=hoja.getHostedBuyer().getUserId()){
								entity.setIdUserInvitado(hoja.getHostedBuyer().getUserId());
							}
							if(null!=hoja.getDesigner()&&null!=hoja.getDesigner().getUserId()){
								entity.setIdDesigner(hoja.getDesigner().getUserId());
							}
							entity.setIdHojaPadre(parentId);
							entity.setHojaParentSegId(segId);
							segId++;

							entity.setIdHoja(hoja.getHojaId());
							entity.setNumHoja(numHoja);
							
							entity.setIdSistemaVenta(idSistemaVentaDef);
							daoFolletoHojas.saveOrUpdate(entity);
							if(parentId==null){
								parentId= entity.getIdHoja();
							}
							
							idHoja.append(hoja.getHojaId());
							idHoja.append(",");
						}
						if(idHoja.length()>0){
							idHoja.deleteCharAt(idHoja.length()-1);
						}
						daoFolletoHojas.deleteHojas(folletoId, numHoja, idHoja.toString());
						idHoja= new StringBuffer();
					}
				}
			} catch (Exception e) {
				throw new GeneralException(e);
			}
		}
	}
	
	@Override
	public void deleteHojas(int folletoId, int ultimaHoja) throws Exception{
		daoFolletoHojas.deleteHojas(folletoId, ultimaHoja);
	}
	
	@Override
	public List<TblCampanaMedio> getCampanaMedioAll() {
		List<TblCampanaMedio> tblCampanaMedio = null;
		try {
			tblCampanaMedio = daoCampanaMedio.getAll();
		} catch (Exception e) {
			LOG.error(e);
		}
		return tblCampanaMedio;
	}
	
	@Override
	public void deleteSistemaVenta(TblFolletoSistemaVenta entity) throws Exception{
		daoFolletoSistemaVenta.delete(entity);
	}
	
	@Override
	public void deleteTienda(TblFolletoTienda entity) throws Exception{
		daoFolletoTienda.delete(entity);
	}
	
	@Override
	public void deleteZona(TblFolletoZona entity) throws Exception{
		daoFolletoZona.delete(entity);
	}

	public DAOFolletoSistemaVenta getDaoFolletoSistemaVenta() {
		return daoFolletoSistemaVenta;
	}

	public void setDaoFolletoSistemaVenta(
			DAOFolletoSistemaVenta daoFolletoSistemaVenta) {
		this.daoFolletoSistemaVenta = daoFolletoSistemaVenta;
	}

	public DAOPrensaSistemaVenta getDaoPrensaSistemaVenta() {
		return daoPrensaSistemaVenta;
	}

	public void setDaoPrensaSistemaVenta(DAOPrensaSistemaVenta daoPrensaSistemaVenta) {
		this.daoPrensaSistemaVenta = daoPrensaSistemaVenta;
	}

	public DAOFolletoHojas getDaoFolletoHojas() {
		return daoFolletoHojas;
	}

	public void setDaoFolletoHojas(DAOFolletoHojas daoFolletoHojas) {
		this.daoFolletoHojas = daoFolletoHojas;
	}

	@Override
	public void savePrensa(TblPrensa entityPrensa) throws GeneralException {
		try {
			daoPrensa.save(entityPrensa);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}

	@Override
	public void savePrensaTienda(List<String> sucursal, int prensaId)
			throws GeneralException {
		if(sucursal!=null){
			 try{
				for(String sucursalId: sucursal){
					TblPrensaTiendaId id= new TblPrensaTiendaId();
					id.setIdPrensa(prensaId);
					id.setIdTienda(Integer.parseInt(sucursalId));
					TblPrensaTienda entity= new TblPrensaTienda();
					entity.setId(id);
					daoPrensaTienda.save(entity);
				}
		 	} catch (Exception e) {
		 		throw new GeneralException(e);
			}
		 }
	}

	@Override
	public void savePrensaSistemaVenta(List<String> sistemaVentasLst,
			int prensaId) throws GeneralException {
		if(sistemaVentasLst!=null){
			 try{
				for(String idSistemaVenta: sistemaVentasLst){
					TblPrensaSistemaVenta entity= new TblPrensaSistemaVenta();
					TblPrensaSistemaVentaId id= new TblPrensaSistemaVentaId();
					id.setIdPrensa(prensaId);
					id.setIdSistemaVenta(Integer.parseInt(idSistemaVenta));
					entity.setId(id);
					daoPrensaSistemaVenta.save(entity);
				}
		 	} catch (Exception e) {
		 		throw new GeneralException(e);
			}
		 }
	}

	@Override
	public void savePrensaHojas(List<HojaFolleto> hojaLst, int prensaId)
			throws GeneralException {
		if (hojaLst != null) {
			try {
				int i = 0;
				for (HojaFolleto hoja : hojaLst) {
					TblPrensaEspaciosId id = new TblPrensaEspaciosId();
					id.setIdPrensa(prensaId);
					id.setIdEspacio(i++);
					
					TblPrensaEspacios entity = new TblPrensaEspacios();
					entity.setId(id);
					entity.setIdCategory(hoja.getCategory().getId());
					entity.setIdScategory(hoja.getSubcategory().getId());
					entity.setIdUserInvitado(hoja.getHostedBuyer().getUserId());
					daoPrensaEspacios.save(entity);
				}
			} catch (Exception e) {
				throw new GeneralException(e);
			}
		}
	}
	@Override
	public void updateGrupoZonaTienda(List<TblCampanaProgramas> programs, List<CampanaProgramaDTO> programaDTOs){
		try{
			System.out.println("init updateGrupoZonaTienda...");

			daoRelGrupoZonaCampana.clearSession();
			List<RelGrupoZonaCampana> relGrupoZonaCampanas = new ArrayList<>();
			List<RelZonaCampana> relZonaCampana = new ArrayList<>();
			List<RelStoreCampana> relStoreCampanas = new ArrayList<>();
			for(TblCampanaProgramas program: programs){
				for(CampanaProgramaDTO programaDTO: programaDTOs){
					if(programaDTO.getGrupoZonas()!=null){
						for(String grupoZona: programaDTO.getGrupoZonas()){
							RelGrupoZonaCampana relGrupoZona= new RelGrupoZonaCampana();
							relGrupoZona.setGrupoId(Integer.valueOf(grupoZona));
							relGrupoZona.setTblCampanaProgramas(program);
							relGrupoZonaCampanas.add(relGrupoZona);
						}
					}
					if(programaDTO.getZonas()!=null){
						for(String zona: programaDTO.getZonas()){
							RelZonaCampana relZona= new RelZonaCampana();
							relZona.setZonaId(Integer.valueOf(zona));
							relZona.setTblCampanaProgramas(program);
							relZonaCampana.add(relZona);
						}
					}
					if(programaDTO.getTiendas()!=null){
						for(String tienda: programaDTO.getTiendas()){
							RelStoreCampana relTienda= new RelStoreCampana();
							relTienda.setStoreId(Integer.valueOf(tienda));
							relTienda.setTblCampanaProgramas(program);
							relStoreCampanas.add(relTienda);
						}
					}
				}
			}
			System.out.println("init saving updateGrupoZonaTienda...");

			daoRelGrupoZonaCampana.save(relGrupoZonaCampanas);
			daoRelZonaCampana.save(relZonaCampana);
			daoRelStoreCampana.save(relStoreCampanas);
		}catch(Exception e){
			LOG.error(e);
		}
	}

	public DAOPrensaTienda getDaoPrensaTienda() {
		return daoPrensaTienda;
	}

	public void setDaoPrensaTienda(DAOPrensaTienda daoPrensaTienda) {
		this.daoPrensaTienda = daoPrensaTienda;
	}

	public DAOPrensaEspacios getDaoPrensaEspacios() {
		return daoPrensaEspacios;
	}

	public void setDaoPrensaEspacios(DAOPrensaEspacios daoPrensaEspacios) {
		this.daoPrensaEspacios = daoPrensaEspacios;
	}

	@Override
	public void delegateActivity(
			ActivityDTO[] activitySelection, Integer idUsuarioDelegado)
			throws GeneralException {
		try {
			for(ActivityDTO activityDTO: activitySelection){
				TblCampanaActividades activity= this.getActivityDetail(activityDTO.getId());
				activity.setActivo(Constants.INACTIVO);
				daoCampanaActividades.saveOrUpdate(activity);
				activity.setFechaCreacion(new Date());
				activity.setFechaModificacion(null);
				activity.setIdUsuarioResp(idUsuarioDelegado);
				activity.setActivo(Constants.ACTIVO);
				daoCampanaActividades.save(activity);
			}
		} catch (Exception e) {
			LOG.error(e);
			throw new GeneralException("Error en getDelegateActivities", e);
		}
	}

	public DAOPrecioPrensa getDaoPrecioPrensa() {
		return daoPrecioPrensa;
	}

	public void setDaoPrecioPrensa(DAOPrecioPrensa daoPrecioPrensa) {
		this.daoPrecioPrensa = daoPrecioPrensa;
	}

	public DAOPrecioPrensaDet getDaoPrecioPrensaDet() {
		return daoPrecioPrensaDet;
	}

	public void setDaoPrecioPrensaDet(DAOPrecioPrensaDet daoPrecioPrensaDet) {
		this.daoPrecioPrensaDet = daoPrecioPrensaDet;
	}

	public DAOPrecioFolleto getDaoPrecioFolleto() {
		return daoPrecioFolleto;
	}

	public void setDaoPrecioFolleto(DAOPrecioFolleto daoPrecioFolleto) {
		this.daoPrecioFolleto = daoPrecioFolleto;
	}

	public DAOPreciosFolletoDet getDaoPrecioFolletoDet() {
		return daoPrecioFolletoDet;
	}

	public void setDaoPrecioFolletoDet(DAOPreciosFolletoDet daoPrecioFolletoDet) {
		this.daoPrecioFolletoDet = daoPrecioFolletoDet;
	}

	public DAOCampanaAutorizacion getDaoCampanaAutorizacion() {
		return daoCampanaAutorizacion;
	}

	public void setDaoCampanaAutorizacion(
			DAOCampanaAutorizacion daoCampanaAutorizacion) {
		this.daoCampanaAutorizacion = daoCampanaAutorizacion;
	}

	public DAOFolletoZona getDaoFolletoZona() {
		return daoFolletoZona;
	}

	public void setDaoFolletoZona(DAOFolletoZona daoFolletoZona) {
		this.daoFolletoZona = daoFolletoZona;
	}

	public DAORelStoreCampana getDaoRelStoreCampana() {
		return daoRelStoreCampana;
	}

	public void setDaoRelStoreCampana(DAORelStoreCampana daoRelStoreCampana) {
		this.daoRelStoreCampana = daoRelStoreCampana;
	}

	public DAORelZonaCampana getDaoRelZonaCampana() {
		return daoRelZonaCampana;
	}

	public void setDaoRelZonaCampana(DAORelZonaCampana daoRelZonaCampana) {
		this.daoRelZonaCampana = daoRelZonaCampana;
	}

	public DAORelGrupoZonaCampana getDaoRelGrupoZonaCampana() {
		return daoRelGrupoZonaCampana;
	}

	public void setDaoRelGrupoZonaCampana(
			DAORelGrupoZonaCampana daoRelGrupoZonaCampana) {
		this.daoRelGrupoZonaCampana = daoRelGrupoZonaCampana;
	}

	@Override
	public void removeCampProgZonaByProgramaAndCampana(long idCampana,
			int idPrograma) {
		daoRelZonaCampana.deleteById(idCampana, idPrograma);
	}
	
	@Override
	public void removeCampProgZonaByProgramaAndCampana(List<Long> idCampana, List<Integer> idPrograma) {
		daoRelZonaCampana.deleteById(idCampana, idPrograma);
	}
	
	public List<RelZonaCampana> getRelZonaCampanaByCampanaPrograma(long idCampana, int idPrograma) {
		return daoRelZonaCampana.getByCampanaPrograma(idCampana, idPrograma);
	}

	@Override
	public void removeCampProgGrupoZonaByProgramaAndCampana(long idCampana,
			int idPrograma) {
		daoRelGrupoZonaCampana.deleteById(idCampana, idPrograma);
	}

	@Override
	public void removeCampProgGrupoZonaByProgramaAndCampana(List<Long> idCampana, List<Integer> idPrograma) {
		daoRelGrupoZonaCampana.deleteById(idCampana, idPrograma);
	}
	
	public List<RelGrupoZonaCampana> getRelGrupoZonaCampanaByCampanaPrograma(long idCampana, int idPrograma) {
		return daoRelGrupoZonaCampana.getByCampanaPrograma(idCampana, idPrograma);
	}
	

	@Override
	public void removeCampProgTiendaByProgramaAndCampana(long idCampana,
			int idPrograma) {
		daoRelStoreCampana.deleteById(idCampana, idPrograma);
	}
	@Override
	public void removeCampProgTiendaByProgramaAndCampana(List<Long> idCampana, List<Integer> idPrograma) {
		daoRelStoreCampana.deleteById(idCampana, idPrograma);
	}
	
	public List<RelStoreCampana> getRelStoreCampanaByCampanaPrograma(long idCampana, int idPrograma) {
		return daoRelStoreCampana.getByCampanaPrograma(idCampana, idPrograma);
	}

    @Override
    public boolean campanaHasMecanica(int campanaId) {
        return daoTblMecanica.campanaHasMecanica(campanaId);
    }
    
    @Override
    public List<Integer> getCategoriasIdsByCampanaIdAndPrograma(long idCampana, int idPrograma){
    	return daoCampanaProgramasCategorias.getCategoriasIdsByCampanaIdAndPrograma(idCampana, idPrograma);
    }
    
    @Override
	public Boolean campanaExists(Integer year, Integer tipoCampana, String nombre) {
    	return daoCampanas.campanaExists(year, tipoCampana, nombre);
    }
}
