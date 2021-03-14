package com.adinfi.seven.business.services;

import java.util.List;
import java.util.Map;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.*;
import com.adinfi.seven.persistence.dto.ComponenteDTO;
import com.adinfi.seven.persistence.dto.ConfMecanicaDTO;
import com.adinfi.seven.persistence.dto.DisenosDTO;
import com.adinfi.seven.persistence.dto.GenericItem;
import com.adinfi.seven.persistence.dto.MecanicaDTO;
import com.adinfi.seven.persistence.dto.PreciosPromocionDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;

public interface ServiceArquitecturaSeven {
	boolean checkPrecioExistsByMecanica(int idMecanica);
	List<TblMecanica> getMecanicasByCampana(long campanaId);
	List<TblMecanica> getMecanicaByPrograma(long campanaId, int programaId);
	List<TblCampana> getAllCampana(UsuarioDTO usuario);
	List<TblCampana> getAllCampanaSeven(CatUsuarios usuario, List<RelUsuariosCategorias> categoriasUsuarios);
    TblCampana getCampana(int campanaId, Map<String, String> filters);
	List<TblMecanica> getAllMecanica();
	List<TblMecanica> getAllMecanica(int campanaId);
	List<TblMecanica> getAllMecanica(int campanaId, int programaId);
	List<TblMecanica> getAllMecanica(Integer campanaId, Integer programaId, Integer grupoZona, Integer zona);
	TblMecanica getMecanicaById(int id);
	List<TblComponente> getAllComponente();
	MecanicaDTO getMecanica(int mecanicaId);
	public MecanicaDTO getMecanicaForComponenteSenalamiento(int mecanicaId);
	public MecanicaDTO getMecanicaGrupoZonas(int mecanicaId);
	void updateDiseno(List<DisenosDTO> disenosDTOLst, int rolUsuario, Integer verDisenoAutorizado, String verDisenoComentarios) throws Exception;
    boolean saveMecanicaDirect(TblMecanica mecanica) throws Exception;
	int saveMecanica(MecanicaDTO mecanicaDTO) throws GeneralException;
	void deleteComponente(int componenteId) throws GeneralException;
	void deleteMecanica(int mecanicaId) throws GeneralException;
    
	/**
	 * @author Carlos Félix
	 *
	 * Detalle Tipo de calculo depende del valor capturado los otros pueden ser null
	 * Constants.PRECIO_PROMO, Constants.PORCENTAJE_PROMO, Constants.AHORRO_FIJO
	 * @return double[3], donde en se pocisionan así Precio Promoción, % Promoción, Ahorro Fijo
	 */
	double[] valorCalculado(int cantidad, double precioVenta,
			Double precioPromocion, Float promocionPorc, Double ahorroFijo,
			int tipoCalculo);
	/**
	 * Obtener el valor de recuperación de proveedor en base al porcentaje
	 * @param porcRecuperacionProveedor
	 * @param ahorroFijo
	 * @return
	 */
	double recuperacionProveedor(double porcRecuperacionProveedor,
			double ahorroFijo);
	/**
	 * Obtener porcentaje de recuperación de proveedor en base al valor
	 * @param recuperacionProveedor
	 * @param ahorroFijo
	 * @return
	 */
	double porcRecuperacionProveedor(double recuperacionProveedor,
			double ahorroFijo);
	List<TblComponente> getByMecanicaId(int mecanicaId) throws GeneralException;
	void saveComponentes(ComponenteDTO componentesDTO, int programaID,
			int mecanicaId) throws GeneralException;
	void saveComponentes(List<ComponenteDTO> listComponentesDTO,
			int programaID, int mecanicaId) throws GeneralException;
	void saveConfMecanica(List<ConfMecanicaDTO> confMecanicaLstParam)
			throws Exception;
	void savePrecios(List<PreciosPromocionDTO> list) throws Exception;
    void updatePrecios(List<PreciosPromocionDTO> list) throws Exception;
    boolean existsPrecioPromocion(PreciosPromocionDTO p) throws Exception;    
	List<PreciosPromocionDTO> getPreciosByMecanica(int mecanicaId)
			throws Exception;
	List<PreciosPromocionDTO> getPreciosByMecanica(int mecanicaId, Integer estatusRevisionId,
			Integer estatusCapturaId)
			throws Exception;
	String getGenericObjetivo(String skuId) throws Exception;
	Object[] getZonasPrecio(Integer descripcionId, Integer skuId,
			Integer upcId, Integer categoriaId) throws Exception;
	Object[] getZonaProductoPrecio(Integer descripcionId, Integer skuId, Integer upcId, Integer categoriaId,
			List<String> grupoZonaId, List<String> zonaId, Integer idListaEstra)throws Exception;
	List<ComponenteDTO> getPreciosPromocionComponentes(int mecanicaId,
			Integer categoriaId, Integer subCategoriaId, Integer descripcionId,
			Integer componenteId, Integer listaId, List<String> skuId,
			List<String> upcId);
	void deleteDiseno(int disenoId) throws Exception;
	void saveDiseno(List<DisenosDTO> disenosDTOLst, int rolUsuario, Integer verDisenoAutorizado, String verDisenoComentarios) throws Exception;
	void updateDiseno(DisenosDTO dto) throws Exception;
	Double getPrecioVentaBySKU(int sku);
	List<GenericItem> getUPCbyLista(int listaId);
	List<DisenosDTO> searchDisenos(int programaId, int mecanicaId,List<CatSenal> senals, List<String> grupoZonaId, List<String> zonaId,
			List<String> storeId, Integer estatusPrecio, Integer estatusDiseno);
	Double getAhorroMaximoByMecanicaId(int mecanicaId);
	List<ComponenteDTO> getPreciosPromocionComponetes(Integer categoriaId,
			Integer programaId, Integer mecanicaId, List<Integer> grupoZona,
			List<Integer> zona);
	List<Componente> getComponentesFromLista(int idLista);
	
	List<DisenosDTO> getDisenosByIdMecanica(Integer idMecanica) throws Exception;
	boolean updateStatusDisenoByMecanica(Integer idMecanica, Integer idStatus) throws Exception;

	boolean updateHojaComponente(int pkComp, int hoja);
    
    public TblComponenteZonaPrecio insertOrUpdateComponenteZonaPrecio(TblComponenteZonaPrecio e) throws Exception;
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(int componentId, int zoneId) throws Exception;
    public void deleteComponenteZonaByComponenteId(int componenteId) throws Exception;
    public boolean updateComponentNumber(int componentId, int numeroComponente) throws Exception;
    public List<TblMecanica> getMecanicaWithChildrens(List<Object[]> list);
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(List<Object[]> list) throws Exception;
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(String componentList, String zoneList) throws Exception;
    public void insertOrUpdateComponenteZonaPrecio(List<TblComponenteZonaPrecio> e) throws Exception;
}