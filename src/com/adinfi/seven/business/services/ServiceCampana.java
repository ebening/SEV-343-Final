package com.adinfi.seven.business.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.business.domain.RelGrupoZonaCampana;
import com.adinfi.seven.business.domain.RelStoreCampana;
import com.adinfi.seven.business.domain.RelZonaCampana;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaActividades;
import com.adinfi.seven.business.domain.TblCampanaAutorizacion;
import com.adinfi.seven.business.domain.TblCampanaCategorias;
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
import com.adinfi.seven.business.domain.TblFolletoTienda;
import com.adinfi.seven.business.domain.TblFolletoZona;
import com.adinfi.seven.business.domain.TblPrensa;
import com.adinfi.seven.business.domain.VerTodas;
import com.adinfi.seven.persistence.dto.ActivityDTO;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.CampanaMedioDTO;
import com.adinfi.seven.persistence.dto.CampanaProgramaDTO;
import com.adinfi.seven.persistence.dto.CampanaResultDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.CampanaController.HojaFolleto;

public interface ServiceCampana {


	boolean deleteAllCampanaProgramas (long idCampana);

	boolean changeEtapaPrograma(int idCampana, int idPrograma, String etapa);
	
	boolean campanaHasMecanica(int campanaId);
    
	TblCampana saveNewCampana(TblCampana tblCampana) throws GeneralException;
	
	void saveCampana(TblCampana tblCampana) throws GeneralException;

	TblCampana getCampana(long idCampana) throws Exception;

	boolean getCampanaByName(String name);

	List<TblCampana> getAllCampana();
	
	List<TblCampana> getAllCampanaByTypeCampana(int typeCampana);
	
	public CampanaResultDTO getCampanas(Integer firstRecord, Integer rowsPerPage, Map<String, String> filters);
	
	List<TblCampana> getAllCampanasByYearAsc(int year, List<PeriodoDTO> periodos);
	
	List<TblCampana> getListCampanaDTOByYearAndTypeCampana(int year,int typeCampana, List<PeriodoDTO> periodos);

	List<TblCampana> getAllCampanaByDashboardFilters(String periodo, int tipo, int estatus, Date fechaInicio, Date fechaFin, int idPeriodo, int ano);

	void updateCampana(TblCampana tblCampana) throws GeneralException;

	int deleteCampana(TblCampana tblCampana) throws GeneralException;

	List<TblCampanaDetalle> getDetalle();

	List<TblCampanaActividades> getActivityListByRole(Integer role)
			throws GeneralException;

	List<TblCampanaActividades> getActivityListByMonth(Date filter)
			throws GeneralException;

	List<TblCampanaActividades> getActivityListByMonthAndRole(Date filter,
			Integer role) throws GeneralException;

	List<TblCampanaActividades> getActivityList() throws GeneralException;

	List<TblCampanaActividades> getActivityListNotif() throws GeneralException;

	List<TblCampanaActividades> getActivityListToFinish()
			throws GeneralException;

	List<TblCampanaActividades> getActivityList(Long idCampana, Date startDate, Date endDate)
			throws GeneralException;

	List<TblCampanaActividades> getActivityList(Long idCampana, Integer role, Date startDate, Date endDate)
			throws GeneralException;

	TblCampanaActividades getActivityDetail(Long activityId)
			throws GeneralException;

	List<TblCampanaActividades> getNotificationActivityList()
			throws GeneralException;

	void saveActivity(TblCampanaActividades activity) throws GeneralException;

        boolean generateActivities(CampanaDTO campana, UsuarioDTO usuario, ServiceDynamicCatalogs serviceDynamicCatalogs) throws GeneralException;
        
	boolean generateActivities(long idCampana, UsuarioDTO usuario,
			ServiceDynamicCatalogs serviceDynamicCatalogs)
			throws GeneralException;

	void editActivity(TblCampanaActividades activity) throws GeneralException;

	void deleteActivity(TblCampanaActividades activity) throws GeneralException;

	void deleteDelegateActivity(TblDelegacionActividades delegateActivity)
			throws GeneralException;

	TblDelegacionActividades getDelegateActivity(Integer id)
			throws GeneralException;

	void saveDelegateActivity(TblDelegacionActividades delegateActivity)
			throws GeneralException;

	List<TblDelegacionActividades> getDelegateActivities()
			throws GeneralException;

	List<TblDelegacionActividades> getDelegateActivities(String idUsuario)
			throws GeneralException;

	List<TblDelegacionActividades> getDelegateActivity(String idUsuarioDelega,
			String idUsuarioDelegado, Date dateInit, Date dateEnd)
			throws GeneralException;

	int deleteAndInsertActivities(int IntEsFlujo, long idCampana)
			throws GeneralException;

	List<CampaniaTreeRegs> todas();

	List<VerTodas> showAllvT();
	
	List<TblCampanaMedio> getCampanaMediosByIdCampana(long idCampana);
	
	void removeCampanaCategoriaById(int idCategoria,long idCampana);

	List<TblCampanaMedio> getCampanaMedio(long idCampana);

	List<TblCampanaCategorias> getCampanaCategorias(long idCampana);
	
	void updateCampanaCategorias(TblCampanaCategorias tblCamCat);
	
	void removePlazaByCategoryAndCampana(int idCategoria,long idCampana);
	
	void saveCampanaCategoriaPlaza(TblCampanaCategoriasPlaza tblCamCatPlaza);
	
	List<TblCampanaProgramas> getCampanaProgramas(long idCampana);

	TblCampanaProgramas getProgramaById(long idCampana, int programaId);
	
	void updateCampanaProgramas(TblCampanaProgramas tblCampanaProgramas) throws GeneralException;
	
	void removeCampanaProgramas(TblCampanaProgramas tblCampanaProgramas) throws GeneralException;
	
	void saveCampanaProgramasCategorias(TblCampanaProgramasCategorias tblCamProgCat);
	
	void removeCampProgCategByProgramaAndCampana(long idCampana, int idPrograma) throws GeneralException;
	
	void saveCampanaProgramasPlazas(TblCampanaProgramasPlazas tblCamProgPlazas) throws GeneralException;
	
	void removeCampProgPlazasByProgramaAndCampana(long idCampana, int idPrograma) throws GeneralException;
	
	int deleteCampanaActividadByIdCampana(long idCampana) throws GeneralException;
	
	void removeCampanaProgramasByIdCampanaIdPrograma(long idCampana, int idPrograma) throws GeneralException;
	void saveFolleto(TblFolleto entity) throws GeneralException;
	void saveFolletoTienda(List<String> sucursal, int folletoId) throws GeneralException;
	void saveFolletoSistemaVenta(List<String> sistemaVentasLst, int folletoId, String sistemaVentaDefaultId) throws GeneralException;
	void saveFolletoHojas(List<ArrayList<HojaFolleto>> hojaLst, int folletoId, int idSistemaVentaDef, Integer tipoMedio) throws GeneralException;
	void saveCampanaMedio(TblCampanaMedio entity) throws GeneralException;
	TblFolleto getFolleto(Serializable id) throws GeneralException;
	List<TblFolletoTienda> getTiendasIdByFolletoId(int idFolleto) throws GeneralException;
	List<TblFolletoSistemaVenta> getSistemaVentaByFolleto(int idFolleto) throws GeneralException;
	List<TblFolletoHojas> getFolletoHojas(int idFolleto);

	void savePrensa(TblPrensa entityPrensa) throws GeneralException;
	void savePrensaTienda(List<String> sucursal, int prensaId) throws GeneralException;
	void savePrensaSistemaVenta(List<String> sistemaVentasLst, int prensaId) throws GeneralException;
	void savePrensaHojas(List<HojaFolleto> hojaLst, int prensaId) throws GeneralException;
	
    List<TblCampanaMedio> getCampanaMedioAll();
	void delegateActivity(ActivityDTO[] activitySelection, Integer idUsuarioDelegado) throws GeneralException;

	void deletePrensa(TblCampana tblCampana) throws Exception;
	void deleteFolleto(TblCampana tblCampana) throws Exception;

	void deleteCampanaMedio(TblCampanaMedio campanaMedio) throws Exception;

	List<TblCampanaDetalle> getCampanaDetalleByIdCampana(long idCampana);

	void deleteCampanaDetalle(TblCampanaDetalle detalle) throws Exception;

	void deleteCampanaCategoria(TblCampanaCategorias categoria)
			throws Exception;

	void deleteCampanaActividadByIdCampanaFull(long idCampana)
			throws GeneralException;

	List<TblCampanaAutorizacion> getAutorizacionLst(long idCampana)
			throws GeneralException;

	void deleteCampanaAuth(TblCampanaAutorizacion auth) throws Exception;

	void deleteSistemaVenta(TblFolletoSistemaVenta entity) throws Exception;

	void deleteTienda(TblFolletoTienda entity) throws Exception;

	void deleteHojas(int folletoId, int ultimaHoja) throws Exception;

	TblCampanaMedio getCampanaMedio(Integer idCampanaMedio);

	void deleteFolleto(int idFolleto) throws Exception;

	boolean generateActivities(long idCampana, UsuarioDTO usuario,
			ServiceDynamicCatalogs serviceDynamicCatalogs, CampanaMedioDTO medioDTO)
			throws GeneralException;

	void saveFolletoZona(List<String> zona, int folletoId)
			throws GeneralException;

	void deleteZona(TblFolletoZona entity) throws Exception;

	List<TblFolletoZona> getZonasIdByFolletoId(int idFolleto)
			throws GeneralException;
	public void updateGrupoZonaTienda(List<TblCampanaProgramas> programs, List<CampanaProgramaDTO> programaDTOs);
	void removeCampProgZonaByProgramaAndCampana(long idCampana, int idPrograma);

	void removeCampProgGrupoZonaByProgramaAndCampana(long idCampana,
			int idPrograma);

	void removeCampProgTiendaByProgramaAndCampana(long idCampana, int idPrograma);
	public void saveCampanaProgramasCategorias(List<TblCampanaProgramasCategorias> list);
	public void removeCampProgGrupoZonaByProgramaAndCampana(List<Long> idCampana, List<Integer> idPrograma);
	public void removeCampProgZonaByProgramaAndCampana(List<Long> idCampana, List<Integer> idPrograma);
	public void removeCampProgTiendaByProgramaAndCampana(List<Long> idCampana, List<Integer> idPrograma);
	
	public List<RelZonaCampana> getRelZonaCampanaByCampanaPrograma(long idCampana, int idPrograma);
	public List<RelGrupoZonaCampana> getRelGrupoZonaCampanaByCampanaPrograma(long idCampana, int idPrograma);
	public List<RelStoreCampana> getRelStoreCampanaByCampanaPrograma(long idCampana, int idPrograma);
    public List<Integer> getCategoriasIdsByCampanaIdAndPrograma(long idCampana, int idPrograma);
	public Boolean campanaExists(Integer year, Integer tipoCampana, String nombre);

}