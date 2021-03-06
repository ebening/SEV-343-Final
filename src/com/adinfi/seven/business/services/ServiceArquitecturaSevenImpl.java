package com.adinfi.seven.business.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.primefaces.model.DefaultStreamedContent;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatPrograma;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.CatSenal;
import com.adinfi.seven.business.domain.CatStore;
import com.adinfi.seven.business.domain.CatUsuarios;
import com.adinfi.seven.business.domain.CatZone;
import com.adinfi.seven.business.domain.Componente;
import com.adinfi.seven.business.domain.RelCompSku;
import com.adinfi.seven.business.domain.RelCompUpc;
import com.adinfi.seven.business.domain.RelDisenoSenal;
import com.adinfi.seven.business.domain.RelGrupoZona;
import com.adinfi.seven.business.domain.RelGrupoZonaDiseno;
import com.adinfi.seven.business.domain.RelStore;
import com.adinfi.seven.business.domain.RelStoreDiseno;
import com.adinfi.seven.business.domain.RelUsuariosCategorias;
import com.adinfi.seven.business.domain.RelZona;
import com.adinfi.seven.business.domain.RelZonaDiseno;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.business.domain.TblCategoria;
import com.adinfi.seven.business.domain.TblComponente;
import com.adinfi.seven.business.domain.TblComponenteZonaPrecio;
import com.adinfi.seven.business.domain.TblConfMecanica;
import com.adinfi.seven.business.domain.TblDisenos;
import com.adinfi.seven.business.domain.TblMecanica;
import com.adinfi.seven.business.domain.TblPreciosPromocion;
import com.adinfi.seven.business.domain.TblPreciosPromocionId;
import com.adinfi.seven.business.domain.TblPrograma;
import com.adinfi.seven.business.domain.TblSenalamientos;
import com.adinfi.seven.persistence.daos.DAOCampana;
import com.adinfi.seven.persistence.daos.DAOCampanaProgramas;
import com.adinfi.seven.persistence.daos.DAOCampanaProgramasCategorias;
import com.adinfi.seven.persistence.daos.DAORelCompSku;
import com.adinfi.seven.persistence.daos.DAORelCompUpc;
import com.adinfi.seven.persistence.daos.DAORelGrupoZona;
import com.adinfi.seven.persistence.daos.DAORelStore;
import com.adinfi.seven.persistence.daos.DAORelZona;
import com.adinfi.seven.persistence.daos.DAOTblActividad;
import com.adinfi.seven.persistence.daos.DAOTblCategoria;
import com.adinfi.seven.persistence.daos.DAOTblComponente;
import com.adinfi.seven.persistence.daos.DAOTblComponenteZonaPrecio;
import com.adinfi.seven.persistence.daos.DAOTblConfMecanica;
import com.adinfi.seven.persistence.daos.DAOTblDisenos;
import com.adinfi.seven.persistence.daos.DAOTblMecanica;
import com.adinfi.seven.persistence.daos.DAOTblPreciosPromocion;
import com.adinfi.seven.persistence.daos.DAOTblPrograma;
import com.adinfi.seven.persistence.daos.DAOTblSenalamientos;
import com.adinfi.seven.persistence.dto.ColumnModel;
import com.adinfi.seven.persistence.dto.ComponenteDTO;
import com.adinfi.seven.persistence.dto.ConfMecanicaDTO;
import com.adinfi.seven.persistence.dto.ConsultarEstrategiaDTO;
import com.adinfi.seven.persistence.dto.DisenosDTO;
import com.adinfi.seven.persistence.dto.GenericItem;
import com.adinfi.seven.persistence.dto.GenericItemString;
import com.adinfi.seven.persistence.dto.MecanicaDTO;
import com.adinfi.seven.persistence.dto.PreciosPromocionDTO;
import com.adinfi.seven.persistence.dto.UsuarioDTO;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;

import sun.misc.BASE64Encoder;


public class ServiceArquitecturaSevenImpl implements ServiceArquitecturaSeven {

	private Logger LOG = Logger.getLogger(ServiceArquitecturaSevenImpl.class);
	private DAOCampana daoCampanas = null;
	private DAORelCompUpc daoRelCompUpc = null;
	private DAORelCompSku daoRelCompSku = null;
	private DAOTblSenalamientos daoTblSenalamientos = null;
	private DAORelStore daoRelStore = null;
	private DAORelZona daoRelZona = null;
	private DAORelGrupoZona daoRelGrupoZona = null;
	private DAOTblCategoria daoTblCategoria = null;
	private DAOTblPrograma daoTblPrograma = null;
	private DAOTblMecanica daoTblMecanica = null;
	private DAOTblComponente daoTblComponente = null;
    private DAOTblComponenteZonaPrecio daoTblComponenteZonaPrecio = null;
	private ServiceDynamicCatalogs serviceDynamicCatalogs = null;
	private DAOCampanaProgramasCategorias daoCampanaProgramasCategorias = null;
	private DAOCampanaProgramas daoCampanaProgramas = null;
	private DAOTblConfMecanica daoTblConfMecanica = null;
	private DAOTblPreciosPromocion daoTblPreciosPromocion = null;
	private DAOTblDisenos daoTblDisenos = null;
	private DAOTblActividad daoTblActividad;

	private ServiceCatGZone serviceCatGZone;
	private ServiceCatZone serviceCatZone;
	private ServiceCatStore serviceCatStore;

	private ServiceCatPrograma serviceCatPrograma;
	private ServiceCatSenal serviceCatSenal;
	
	ExecutorService executor;
	private final Integer threadsPerExecutor = 8;

	@Override
	public boolean checkPrecioExistsByMecanica(int idMecanica) {
		return daoTblComponente.checkPrecioExistsByMecanica(idMecanica);
	}

	@Override
	public TblMecanica getMecanicaById(int id) {
		return daoTblMecanica.getMecanicaById(id);
	}

    @Override
    public boolean saveMecanicaDirect(TblMecanica mecanica) throws Exception {
        daoTblMecanica.save(mecanica);
        return true;
    }

    @Override
	public List<TblComponente> getByMecanicaId(int mecanicaId) throws GeneralException {
		try {
			return daoTblComponente.getByMecanicaId(mecanicaId);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new GeneralException(e);
		}
	}

	@Override
	public int saveMecanica(MecanicaDTO mecanicaDTO) throws GeneralException {
		TblMecanica mecanica = generateTblMecanica(mecanicaDTO);
		try {
			saveMecanica(mecanica);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new GeneralException(e);
		}
		return mecanica.getMecanicaId();
	}

	private TblMecanica generateTblMecanica(MecanicaDTO mecanicaDTO) {
		TblMecanica mecanica = new TblMecanica();
		mecanica.setProgramaId(mecanicaDTO.getProgramaId());
		mecanica.setAcuerdo1(mecanicaDTO.getAcuerdo1());
		mecanica.setAcuerdo2(mecanicaDTO.getAcuerdo2());
		mecanica.setAcuerdo3(mecanicaDTO.getAcuerdo3());
		mecanica.setComentarios(mecanicaDTO.getComentarios());
		mecanica.setEsCompartido(mecanicaDTO.getEsCompartido());
		mecanica.setCampana(new TblCampana());
		mecanica.getCampana().setIdCampana(mecanicaDTO.getIdCampana());
		mecanica.setPromoId(mecanicaDTO.getIdPromo());
		mecanica.setTipoPromocionId(mecanicaDTO.getIdTipoPromocion());
		mecanica.setIngresoPopReal(new BigDecimal(mecanicaDTO.getIngresoPopReal()));
		mecanica.setNombrePromo(mecanicaDTO.getNombrePromo());
		mecanica.setMecanicaId(mecanicaDTO.getPkMec());
		mecanica.setHoraIni(mecanicaDTO.getHoraIni());
		mecanica.setHoraFin(mecanicaDTO.getHoraFin());
		mecanica.setLunes(mecanicaDTO.getLunes());
		mecanica.setMartes(mecanicaDTO.getMartes());
		mecanica.setMiercoles(mecanicaDTO.getMiercoles());
		mecanica.setJueves(mecanicaDTO.getJueves());
		mecanica.setViernes(mecanicaDTO.getViernes());
		mecanica.setSabado(mecanicaDTO.getSabado());
		mecanica.setDomingo(mecanicaDTO.getDomingo());
		mecanica.setNombreMecanica(mecanicaDTO.getNombreMecanica());
		mecanica.setNombrePromo(mecanicaDTO.getNombrePromo());
		mecanica.setTblCategorias(getCategoriaListDTO(mecanicaDTO
				.getCategoriaList()));
		mecanica.setTblComponentes(getComponenteListDTO(mecanicaDTO
				.getComponenteList()));
		mecanica.setTblProgramas(getProgramaListDTO(mecanicaDTO
				.getProgramaList()));
		mecanica.setTblSenalamientoses(getSenalesListDTO(mecanicaDTO.getSenalamientoList()));
		mecanica.setRelStores(getStoreListDTO(mecanicaDTO.getStoreList()));
		mecanica.setRelGrupoZonas(getGListDTO(mecanicaDTO.getGroupList()));
		mecanica.setRelZonas(getZListDTO(mecanicaDTO.getZoneList()));
		mecanica.setConfMecanicaLst(getConfMecanicaDTO(mecanicaDTO.getConfMecanicaDTO()));
		mecanica.setPreciosLst(getPrecios(mecanicaDTO.getPreciosLst()));
		return mecanica;
	}

	private MecanicaDTO saveMecanica(final TblMecanica mecanica) throws Exception {
		Long time = System.currentTimeMillis();
		daoTblMecanica.saveOrUpdate(mecanica);
		final int mecanicaId = mecanica.getMecanicaId();
		
		executor = Executors.newFixedThreadPool(threadsPerExecutor);
		Runnable rnn = new Runnable() { @Override public void run() {
				daoTblSenalamientos.deleteByMecanicaId(mecanicaId);
		}};
		executor.execute(rnn);
		rnn = new Runnable() { @Override public void run() {
			daoTblCategoria.deleteByMecanicaId(mecanicaId);
		}};
		executor.execute(rnn);
		rnn = new Runnable() { @Override public void run() {
			daoRelGrupoZona.deleteByMecanicaId(mecanicaId);
		}};
		executor.execute(rnn);
		rnn = new Runnable() { @Override public void run() {
			daoRelZona.deleteByMecanicaId(mecanicaId);
		}};
		executor.execute(rnn);
		rnn = new Runnable() { @Override public void run() {
			daoTblPrograma.deleteByMecanicaId(mecanicaId);
		}};
		executor.execute(rnn);
		rnn = new Runnable() { @Override public void run() {
			daoRelStore.deleteByMecanicaId(mecanicaId);
		}};
		executor.execute(rnn);
		rnn = new Runnable() { @Override public void run() {
			daoTblConfMecanica.deleteByMecanicaId(mecanicaId);
		}};
		executor.execute(rnn);
		rnn = new Runnable() { @Override public void run() {
			daoTblPreciosPromocion.deleteByMecanicaId(mecanicaId);
		}};
		executor.execute(rnn);
		executor.shutdown();
        while (!executor.isTerminated()){}
		daoTblMecanica.flush();
		daoTblMecanica.clearSession();
		
		executor = Executors.newFixedThreadPool(threadsPerExecutor);
		if (mecanica.getTblSenalamientoses() != null) {
			Runnable rn = new Runnable() { public void run() {
				for (TblSenalamientos item : mecanica.getTblSenalamientoses()) {
					item.setTblMecanica(mecanica);
				}
				daoTblSenalamientos.save(new ArrayList<>(mecanica.getTblSenalamientoses()));
			}};
			executor.execute(rn);
		}
		
		if (mecanica.getTblCategorias() != null) {
			Runnable rn = new Runnable() { public void run() {
				for (TblCategoria item : mecanica.getTblCategorias()) {
					item.setTblMecanica(mecanica);
				}
				daoTblCategoria.save(new ArrayList<>(mecanica.getTblCategorias()));
			}};
			executor.execute(rn);
		}
		
		if (mecanica.getRelGrupoZonas() != null) {
			Runnable rn = new Runnable() { public void run() {
				for (RelGrupoZona item : mecanica.getRelGrupoZonas()) {
					item.setTblMecanica(mecanica);
				}
				daoRelGrupoZona.save(new ArrayList<>(mecanica.getRelGrupoZonas()));
			}};
			executor.execute(rn);
		}
		
		if (mecanica.getRelZonas() != null) {
			Runnable rn = new Runnable() { public void run() {
				for (RelZona item : mecanica.getRelZonas()) {
					item.setTblMecanica(mecanica);
				}
				daoRelZona.save(new ArrayList<>(mecanica.getRelZonas()));
			}};
			executor.execute(rn);
		}
		
		if (mecanica.getTblProgramas() != null) {
			Runnable rn = new Runnable() { public void run() {
				for (TblPrograma item : mecanica.getTblProgramas()) {
					item.setTblMecanica(mecanica);
				}
				daoTblPrograma.save(new ArrayList<>(mecanica.getTblProgramas()));
			}};
			executor.execute(rn);
		}
		
		if (mecanica.getRelStores() != null) {
			Runnable rn = new Runnable() { public void run() {
				for (RelStore item : mecanica.getRelStores()) {
					item.setTblMecanica(mecanica);
				}
				daoRelStore.save(new ArrayList<>(mecanica.getRelStores()));
			}};
			executor.execute(rn);
		}
		
		if (mecanica.getConfMecanicaLst() != null) {
			Runnable rn = new Runnable() { public void run() {
				for (TblConfMecanica item : mecanica.getConfMecanicaLst()) {
					item.setTblMecanica(mecanica);
				}
				daoTblConfMecanica.save(new ArrayList<>(mecanica.getConfMecanicaLst()));
			}};
			executor.execute(rn);
		}

		if (mecanica.getPreciosLst() != null) {
			Runnable rn = new Runnable() { public void run() {
				for (TblPreciosPromocion item : mecanica.getPreciosLst()) {
					item.getId().setMecanicaId(mecanicaId);
				}
				daoTblPreciosPromocion.save(new ArrayList<>(mecanica.getPreciosLst()));
			}};
			executor.execute(rn);
		}
		executor.shutdown();
        while (!executor.isTerminated()){}
		if (mecanica.getTblComponentes() != null) {
			List<Integer> ids = new ArrayList<>();
			List<RelCompSku> rcs = new ArrayList<>();
			List<RelCompUpc> rcu = new ArrayList<>();
			for (TblComponente tblComponente : mecanica.getTblComponentes()) {
				ids.add(tblComponente.getComponenteId());
				tblComponente.setTblMecanica(mecanica);
				tblComponente.setComponenteId(0);
				if (tblComponente.getRelCompSkus() != null) {
					for (RelCompSku itemInterno : tblComponente.getRelCompSkus()) {
						itemInterno.setTblComponente(tblComponente);
						rcs.add(itemInterno);
					}
				}
				if (tblComponente.getRelCompUpcs() != null) {
					for (RelCompUpc itemInterno : tblComponente.getRelCompUpcs()) {
						itemInterno.setTblComponente(tblComponente);
						rcu.add(itemInterno);
					}
				}
			}
			daoTblComponente.save(new ArrayList<>(mecanica.getTblComponentes()));
			daoRelCompSku.deleteByComponenteId(ids);
			daoRelCompUpc.deleteByComponenteId(ids);
			daoTblComponente.flush();
			daoTblComponente.clearSession();
			daoRelCompSku.save(rcs);
			daoRelCompUpc.save(rcu);
		}
		System.out.println("fors in saveMecanica: "+(System.currentTimeMillis()-time));
		return getMecanicaDTObyBO(mecanica);
	}

	@Override
	public void saveConfMecanica(List<ConfMecanicaDTO> confMecanicaLstParam) throws Exception {
		daoTblConfMecanica.deleteByMecanicaId(confMecanicaLstParam.get(0).getPkMec());
		Set<TblConfMecanica> confMecanicaLst = getConfMecanicaDTO(confMecanicaLstParam);
		if (confMecanicaLst != null && confMecanicaLst.size() > 0) {
			for (TblConfMecanica item : confMecanicaLst) {
				daoTblConfMecanica.save(item);
			}
		}
	}

	@Override
	public void savePrecios(List<PreciosPromocionDTO> list) throws Exception {
        LOG.info("obteniendo entities a partir de dtos");
		Set<TblPreciosPromocion> items = getPrecios(list);
		if (items != null && items.size() > 0) {
			for (TblPreciosPromocion item : items) {
				//daoTblPreciosPromocion.saveOrUpdate(item);
                daoTblPreciosPromocion.merge(item);
			}
		}
	}

        @Override
	public void updatePrecios(List<PreciosPromocionDTO> list) throws Exception {
		Set<TblPreciosPromocion> items = getPrecios(list);
		if (items != null && items.size() > 0) {
			for (TblPreciosPromocion item : items) {
				daoTblPreciosPromocion.update(item);
			}
		}
	}
    
    public boolean existsPrecioPromocion(PreciosPromocionDTO p) throws Exception{
        TblPreciosPromocionId id = new TblPreciosPromocionId();
        id.setMecanicaId(p.getPkMec());
        id.setComponenteId(p.getPkComp());
        id.setZonaId(p.getZonaId());

        TblPreciosPromocion item = daoTblPreciosPromocion.getPreciosPromocionById(id);
        if (item == null){
            return false;
        }else{
            return true;
        }
    }
        
	@Override
	public String getGenericObjetivo(String skuId) throws Exception {
		return MBUtil.genericSearch(Constants.ATT_ID_ITEM, String.valueOf(skuId),
				Constants.ATT_OBJETIVO, Constants.CAT_LOGISTICA, serviceDynamicCatalogs);
	}

	private void addSAttSearch(List<String> itemLst, String attrName, List<AttrSearch> lstSearchAttrs) {
		if (itemLst != null) {
			for (String item : itemLst) {
				if (item != null && item.length() > 0) {
					addSAttSearch(Integer.valueOf(item), attrName, lstSearchAttrs);
				}
			}
		}
	}

	private void addSAttSearch(Integer item, String attrName, List<AttrSearch> lstSearchAttrs) {
		if (item != null && item.intValue() > 0) {
			AttrSearch searchAdd = new AttrSearch();
			searchAdd.setAttrName(attrName);
			searchAdd.setValue(String.valueOf(item.intValue()));
			lstSearchAttrs.add(searchAdd);
		}
	}

	@Override
	public Object[] getZonaProductoPrecio(Integer descripcionId, Integer skuId, Integer upcId, Integer categoriaId,
			List<String> grupoZonaId, List<String> zonaId, Integer idListaEstra) throws Exception {
		List<AttrSearch> lstSearchAttrs = new ArrayList<>();
		List<Map<String, String>> responseArray = new ArrayList<>();
		List<ColumnModel> columns = new ArrayList<>();
		List<ConsultarEstrategiaDTO> consList = new ArrayList<>();
		Object[] gridZonas = new Object[2];
		try {
			addSAttSearch(descripcionId, Constants.ATT_ID_DESCRIPCION, lstSearchAttrs);
			addSAttSearch(skuId, Constants.ATT_ID_ITEM, lstSearchAttrs);
			addSAttSearch(upcId, Constants.ATT_UPC, lstSearchAttrs);
			addSAttSearch(categoriaId, Constants.ATT_ID_CATEGORIA, lstSearchAttrs);
			addSAttSearch(grupoZonaId, Constants.ATT_ID_GRUPO, lstSearchAttrs);
			addSAttSearch(zonaId, Constants.CODE_ZONA, lstSearchAttrs);
			addSAttSearch(idListaEstra, Constants.ATT_ID_LISTA, lstSearchAttrs);

			List<CatRegs> lstResp = serviceDynamicCatalogs.getRegs(Constants.CAT_REL_Z_P_PRECIO,
					lstSearchAttrs);
			if (lstResp != null) {
				for (CatRegs reg : lstResp) {
					Set<CatRegValues> setValues = reg.getRegValues();
					if (setValues != null) {
						ConsultarEstrategiaDTO estr = new ConsultarEstrategiaDTO();
						for (CatRegValues valueReg : setValues) {
							if (valueReg.getCatAttrs().getAttribs()
									.getAttrName().compareTo(Constants.CODE_ZONA) == 0) {
								String valueStr = MBUtil.genericSearch(Constants.ATT_ID,
										valueReg.getValue(), Constants.ATT_CODE, Constants.CAT_ZONA, serviceDynamicCatalogs);
								estr.setZonaA(valueStr);
							} else if (valueReg.getCatAttrs().getAttribs()
									.getAttrName().compareTo(Constants.ATT_ID_ITEM) == 0) {
								String valueStr = MBUtil.genericSearch(Constants.ATT_CODE,
										valueReg.getValue(), Constants.ATT_MARCA, Constants.CAT_ITEM, serviceDynamicCatalogs);
								estr.setProducto(valueStr);
							} else if (valueReg.getCatAttrs().getAttribs()
									.getAttrName().compareTo(Constants.ATT_PRECIO) == 0) {
								estr.setPrecio(valueReg.getValue());
							}
						}
						consList.add(estr);
					}
				}
			}
			// Se orden los productos para poder hacer salto de linea
			Collections.sort(consList, new Comparator<ConsultarEstrategiaDTO>() {
				@Override
				public int compare(final ConsultarEstrategiaDTO object1,
						final ConsultarEstrategiaDTO object2) {
					return object1.getProducto().compareTo(object2.getProducto());
				}
			});
			ColumnModel col = new ColumnModel(Constants.PRODUCTO, Constants.PRODUCTO);
			columns.add(col);
			int i = 0;
			String producto = null;
			Map<String, String> row = new HashMap<String, String>();
			for (ConsultarEstrategiaDTO rowE : consList) {
				if (producto == null) {
					producto = rowE.getProducto();
				}
				if (producto.compareTo(rowE.getProducto()) != 0) {
					producto = rowE.getProducto();
					responseArray.add(i, row);
					i++;
					row = new HashMap<String, String>();
				}
				col = new ColumnModel(rowE.getZonaA(), rowE.getZonaA());
				if (!columns.contains(col)) {
					columns.add(col);
				}
				row.put(rowE.getZonaA(), rowE.getPrecio());
				if (!row.containsValue(rowE.getProducto())) {
					row.put(Constants.PRODUCTO, rowE.getProducto());
				}
			}
			if (consList.size() > 0) {
				responseArray.add(i, row);
			}
			gridZonas[0] = columns;
			gridZonas[1] = responseArray;
		} catch (Exception e) {
			LOG.error(e);
		}
		return gridZonas;
	}

	@Override
	public Object[] getZonasPrecio(Integer descripcionId, Integer skuId, Integer upcId, Integer categoriaId) throws Exception {
		List<AttrSearch> lstSearchAttrs = new ArrayList<>();
		List<Map<String, String>> responseArray = new ArrayList<>();
		List<ColumnModel> columns = new ArrayList<>();
		List<ConsultarEstrategiaDTO> consList = new ArrayList<>();
		Object[] gridZonas = new Object[2];
		try {
			addSAttSearch(descripcionId, Constants.ATT_ID_DESCRIPCION, lstSearchAttrs);
			addSAttSearch(skuId, Constants.ATT_ID_ITEM, lstSearchAttrs);
			addSAttSearch(upcId, Constants.ATT_UPC, lstSearchAttrs);
			addSAttSearch(categoriaId, Constants.ATT_ID_CATEGORIA, lstSearchAttrs);

			List<CatRegs> lstResp = serviceDynamicCatalogs.getRegs(Constants.CAT_REL_Z_PRECIOS,lstSearchAttrs);
			if (lstResp != null) {
				for (CatRegs reg : lstResp) {
					Set<CatRegValues> setValues = reg.getRegValues();
					if (setValues != null) {
						ConsultarEstrategiaDTO estr = new ConsultarEstrategiaDTO();
						for (CatRegValues valueReg : setValues) {
							if (valueReg.getCatAttrs().getAttribs()
									.getAttrName().compareTo(Constants.ATT_ZONA_A) == 0) {
								estr.setZonaA(MBUtil.genericSearch(Constants.ATT_ID,
										valueReg.getValue(), Constants.ATT_CODE, Constants.CAT_ZONA, serviceDynamicCatalogs));
							} else if (valueReg.getCatAttrs().getAttribs()
									.getAttrName().compareTo(Constants.ATT_ZONA_B) == 0) {
								estr.setZonaB(MBUtil.genericSearch(Constants.ATT_ID,
										valueReg.getValue(), Constants.ATT_CODE, Constants.CAT_ZONA, serviceDynamicCatalogs));
							} else if (valueReg.getCatAttrs().getAttribs()
									.getAttrName().compareTo(Constants.ATT_PRECIO) == 0) {
								estr.setPrecio(valueReg.getValue());
							}
						}
						consList.add(estr);
					}
				}
			}
			// Se orden los productos para poder hacer salto de linea
			Collections.sort(consList, new Comparator<ConsultarEstrategiaDTO>() {
				@Override
				public int compare(final ConsultarEstrategiaDTO object1,
						final ConsultarEstrategiaDTO object2) {
					return object1.getZonaB().compareTo(object2.getZonaB());
				}
			});
			ColumnModel col = new ColumnModel(Constants.CODE_ZONA, Constants.CODE_ZONA);
			columns.add(col);
			int i = 0;
			String zonaB = null;
			Map<String, String> row = new HashMap<String, String>();
			for (ConsultarEstrategiaDTO rowE : consList) {
				if (zonaB == null) {
					zonaB = rowE.getZonaB();
				}
				if (zonaB.compareTo(rowE.getZonaB()) != 0) {
					zonaB = rowE.getZonaB();
					responseArray.add(i, row);
					i++;
					row = new HashMap<String, String>();
				}
				col = new ColumnModel(rowE.getZonaA(), rowE.getZonaA());
				if (!columns.contains(col)) {
					columns.add(col);
				}
				row.put(rowE.getZonaA(), rowE.getPrecio());
				if (!row.containsValue(rowE.getProducto())) {
					row.put(Constants.CODE_ZONA, rowE.getZonaB());
				}
			}
			if (consList.size() > 0) {
				responseArray.add(i, row);
			}
			gridZonas[0] = columns;
			gridZonas[1] = responseArray;
		} catch (Exception e) {
			LOG.error(e);
		}
		return gridZonas;
	}

	@Override
	public List<PreciosPromocionDTO> getPreciosByMecanica(int mecanicaId) throws Exception {
		return getPreciosByMecanica(mecanicaId, null, null);
	}

	@Override
	public List<PreciosPromocionDTO> getPreciosByMecanica(int mecanicaId, Integer estatusRevisionId,
			Integer estatusCapturaId) throws Exception {
		try {
			List<TblPreciosPromocion> list = daoTblPreciosPromocion.getByMecanicaId(mecanicaId, estatusRevisionId, estatusCapturaId);
			Set<TblPreciosPromocion> setList = new HashSet<>(list);
			return getPrecios(setList);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void saveComponentes(ComponenteDTO componentesDTO, int programaID, int mecanicaId) throws GeneralException {
		List<ComponenteDTO> componenteLst = new ArrayList<>();
		componenteLst.add(componentesDTO);
		saveComponentes(componenteLst, programaID, mecanicaId);
	}

	@Override
	public void saveComponentes(List<ComponenteDTO> listComponentesDTO, int programaID, int mecanicaId) throws GeneralException {
            try {
                TblMecanica mecanica = new TblMecanica();
                mecanica.setProgramaId(programaID);
                mecanica.setMecanicaId(mecanicaId);
                Set<TblComponente> listComponentes = getComponenteListDTO(listComponentesDTO);
                if (listComponentes != null) {
                    for (TblComponente tblComponente : listComponentes) {
						tblComponente.setTblMecanica(getMecanicaById(mecanicaId));
                        daoTblComponente.saveOrUpdate(tblComponente);
                        daoRelCompSku.deleteByComponenteId(tblComponente.getComponenteId());
                        daoRelCompUpc.deleteByComponenteId(tblComponente.getComponenteId());
                        daoTblComponente.flush();
                        daoTblComponente.clearSession();
                        if (tblComponente.getRelCompSkus() != null && !tblComponente.getRelCompSkus().isEmpty()) {
                            List<RelCompSku> slist = new ArrayList<>(tblComponente.getRelCompSkus());
                            slist.get(0).setTblComponente(tblComponente);
                            daoRelCompSku.save(slist.get(0));
                            /*for (RelCompSku itemInterno : tblComponente.getRelCompSkus()) {
                                    itemInterno.setTblComponente(tblComponente);
                                    daoRelCompSku.save(itemInterno);
                            } */
                        }
                        if (tblComponente.getRelCompUpcs() != null && !tblComponente.getRelCompUpcs().isEmpty()) {
                            for (RelCompUpc itemInterno : tblComponente.getRelCompUpcs()) {
                                itemInterno.setTblComponente(tblComponente);
                                daoRelCompUpc.save(itemInterno);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                throw new GeneralException(e);
            }
	}

	public DAOTblActividad getDaoTblActividad() {
		return daoTblActividad;
	}

	public void setDaoTblActividad(DAOTblActividad daoTblActividad) {
		this.daoTblActividad = daoTblActividad;
	}

	@Override
	public void deleteMecanica(int mecanicaId) throws GeneralException {
		try {
			TblMecanica tblMecanica = daoTblMecanica.getById(mecanicaId);
			daoTblSenalamientos.deleteByMecanicaId(mecanicaId);
			daoTblCategoria.deleteByMecanicaId(mecanicaId);
			daoRelGrupoZona.deleteByMecanicaId(mecanicaId);
			daoRelZona.deleteByMecanicaId(mecanicaId);
			daoTblPrograma.deleteByMecanicaId(mecanicaId);
			daoRelStore.deleteByMecanicaId(mecanicaId);
			daoTblConfMecanica.deleteByMecanicaId(mecanicaId);
            daoTblPreciosPromocion.deleteByMecanicaId(mecanicaId);
            daoTblDisenos.deleteByMecanicaId(mecanicaId);
			if (tblMecanica.getTblComponentes() != null) {
				for (TblComponente tblComponente : tblMecanica.getTblComponentes()) {
					deleteComponente(tblComponente.getComponenteId());
				}
			}
			if (daoTblActividad.deleteActividadesByIdMecanica(tblMecanica.getMecanicaId())){
				daoTblMecanica.delete(tblMecanica);
			}else{
				System.out.println("No se borraron actividades");
				throw new GeneralException("No se borraron actividades");
			}

		} catch (Exception e) {
			System.out.println("Error al borrar mecanica:" + mecanicaId + " - Exception: "+ e.getMessage());
			throw new GeneralException(e);
		}
	}

	@Override
	public void deleteComponente(int componenteId) throws GeneralException {
		try {
			TblComponente tblComponente = daoTblComponente.getById(componenteId);
			daoRelCompSku.deleteByComponenteId(tblComponente.getComponenteId());
			daoRelCompUpc.deleteByComponenteId(tblComponente.getComponenteId());
			daoTblComponente.delete(tblComponente);
		} catch (Exception e) {
			LOG.error("Error al borrar componente:" + componenteId, e);
			throw new GeneralException(e);
		}
	}

	@Override
	public double[] valorCalculado(int cantidad, double precioVenta,
			Double precioPromocion, Float promocionPorc, Double ahorroFijo,
			int tipoCalculo) {
		double[] values = new double[3];
		double precioPromo = 0d;
		double porcentajePromo = 0d;
		double ahorroPromo = 0d;
		switch (tipoCalculo) {
		case Constants.PRECIO_PROMO:
			precioPromo = precioPromocion.doubleValue();
			porcentajePromo = (((precioVenta * cantidad) - precioPromo) / precioVenta * cantidad) * 100;
			ahorroPromo = (precioVenta * cantidad) - precioPromo;
			break;
		case Constants.PORCENTAJE_PROMO:
			porcentajePromo = promocionPorc.doubleValue();
			precioPromo = precioVenta * cantidad * (1 - porcentajePromo);
			ahorroPromo = (precioVenta * cantidad) - precioPromo;
			break;
		case Constants.AHORRO_FIJO:
			ahorroPromo = ahorroFijo.doubleValue();
			precioPromo = (precioVenta * cantidad) - ahorroPromo;
			porcentajePromo = (ahorroPromo / (precioVenta * cantidad)) * 100;
			break;
		}
		values[0] = precioPromo;
		values[1] = porcentajePromo;
		values[2] = ahorroPromo;
		return values;
	}

	@Override
	public double porcRecuperacionProveedor(double recuperacionProveedor, double ahorroFijo) {
		return recuperacionProveedor / ahorroFijo * 100;
	}

	@Override
	public double recuperacionProveedor(double porcRecuperacionProveedor, double ahorroFijo) {
		return ahorroFijo * porcRecuperacionProveedor / 100;
	}

	@Override
	public List<TblMecanica> getAllMecanica() {
		try {
			return orderList(daoTblMecanica.getAll());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<TblMecanica>();
	}

	@Override
	public List<TblMecanica> getAllMecanica(int campanaId) {
		try {
			return orderList(daoTblMecanica.getAllMecanica(campanaId));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<TblMecanica>();
	}

	@Override
	public List<TblMecanica> getAllMecanica(int campanaId, int programaId) {
		try {
			List<TblMecanica> mecanicaLst = daoTblMecanica.getAllMecanica(campanaId, programaId);
			for (TblMecanica mecanica : mecanicaLst) {
				/*
				 * mecanica.setTblCategorias();
				 * mecanica.setTblComponentes(getComponenteListDTO(mecanicaDTO
				 * .getComponenteList()));
				 * mecanica.setTblProgramas(getProgramaListDTO(mecanicaDTO
				 * .getProgramaList()));
				 * mecanica.setTblSenalamientoses(getSenalesListDTO
				 * (mecanicaDTO.getSenalamientoList()));
				 * mecanica.setRelStores(getStoreListDTO
				 * (mecanicaDTO.getStoreList()));
				 * mecanica.setRelGrupoZonas(getGListDTO
				 * (mecanicaDTO.getGroupList()));
				 * mecanica.setRelZonas(getZListDTO(mecanicaDTO.getZoneList()));
				 */
				Hibernate.initialize(mecanica.getTblCategorias());
				Hibernate.initialize(mecanica.getTblComponentes());
				Hibernate.initialize(mecanica.getTblProgramas());
				Hibernate.initialize(mecanica.getTblSenalamientoses());
				Hibernate.initialize(mecanica.getRelStores());
				Hibernate.initialize(mecanica.getRelGrupoZonas());
				Hibernate.initialize(mecanica.getRelZonas());
			}
			return orderList(mecanicaLst);
		} catch (Exception e) {
			System.out.println("---------------------------------------");
			System.out.println(programaId);
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<TblMecanica>();
	}
	
	@Override
	public List<TblMecanica> getMecanicaWithChildrens(List<Object[]> list) {
		return daoTblMecanica.getMecanicaWithChildrens(list);
	}

	@Override
	public List<TblMecanica> getMecanicaByPrograma(long campanaId, int programaId) {
		return daoTblMecanica.getMecanicasByPrograma(campanaId, programaId);
	}

	@Override
	public List<TblMecanica> getMecanicasByCampana(long campanaId) {
		return daoTblMecanica.getMecanicasByCampana(campanaId);
	}



	@Override
	public List<TblMecanica> getAllMecanica(Integer campanaId,
			Integer programaId, Integer grupoZona, Integer zona) {
		try {
			return orderList(daoTblMecanica.getAllMecanica(campanaId, programaId, grupoZona, zona));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<TblMecanica>();
	}

	private void orderList(TblMecanica mecanica) {
		if (mecanica != null && mecanica.getTblComponentes() != null) {
			mecanica.setTblComponentes(orderListComp(mecanica.getTblComponentes()));
		}
	}

	private List<TblCampana> orderListCampana(List<TblCampana> campanaLst) {
		if (campanaLst != null) {
			if (campanaLst.size() > 0) {
				Collections.sort(campanaLst, new Comparator<TblCampana>() {
					@Override
					public int compare(final TblCampana object1,
							final TblCampana object2) {
						return object1.getNombre().compareTo(object2.getNombre());
					}
				});
			}
		}
		return campanaLst;
	}

	private List<TblMecanica> orderList(List<TblMecanica> mecanicaLst) {
		if (mecanicaLst != null) {
			if (mecanicaLst.size() > 0) {
				Collections.sort(mecanicaLst, new Comparator<TblMecanica>() {
					@Override
					public int compare(final TblMecanica object1,
							final TblMecanica object2) {
						if(object1==null || object1.getNombreMecanica()==null) return -1;
						if(object2==null || object2.getNombreMecanica()==null) return 1;
						return object1.getNombreMecanica().compareTo(object2.getNombreMecanica());
					}
				});
				for (TblMecanica mecanica : mecanicaLst) {
					if (mecanica != null && mecanica.getTblComponentes() != null) {
						mecanica.setTblComponentes(orderListComp(mecanica.getTblComponentes()));
					}
				}
			}
		}
		return mecanicaLst;
	}

	private Set<TblComponente> orderListComp(Set<TblComponente> compListParam) {
		List<TblComponente> compList = new ArrayList<TblComponente>(compListParam);
		orderListComp(compList);
		Set<TblComponente> foo = new HashSet<TblComponente>(compList);
		return foo;
	}

	private List<TblComponente> orderListComp(List<TblComponente> compList) {
		Collections.sort(compList, new Comparator<TblComponente>() {
			@Override
			public int compare(final TblComponente object1,
					final TblComponente object2) {
				return object1.getNumeroComponente() < object2.getNumeroComponente() ? -1 :
						(object1.getNumeroComponente() < object2.getNumeroComponente() ? 1 :
								0);
			}
		});
		return compList;
	}

	@Override
	public List<TblComponente> getAllComponente() {
		try {
			List<TblComponente> compLst = daoTblComponente.getAll();
			return orderListComp(compLst);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<TblComponente>();
	}

	@Override
	public List<ComponenteDTO> getPreciosPromocionComponetes(Integer categoriaId,
			Integer programaId, Integer mecanicaId, List<Integer> grupoZona,
			List<Integer> zona) {
		try {
			if (grupoZona != null) {
				for (Integer item : grupoZona) {
					if (item == null || item.intValue() <= 0) {
						grupoZona.remove(item);
					}
				}
			}
			if (zona != null) {
				for (Integer item : zona) {
					if (item == null || item.intValue() <= 0) {
						zona.remove(item);
					}
				}
			}
			List<TblComponente> compLst = daoTblComponente.getPreciosPromocionComponentes(categoriaId, programaId, mecanicaId, grupoZona, zona);
			Set<TblComponente> setComp = new HashSet<TblComponente>(compLst);
			return getComponenteList(setComp);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<ComponenteDTO>();
	}

	@Override
	public List<ComponenteDTO> getPreciosPromocionComponentes(int mecanicaId,
			Integer categoriaId, Integer subCategoriaId, Integer descripcionId,
			Integer componenteId, Integer listaId, List<String> skuId,
			List<String> upcListId) {
		try {
			List<TblComponente> compLst = daoTblComponente.getPreciosPromocionComponentes(mecanicaId, categoriaId, subCategoriaId, descripcionId, componenteId, listaId, skuId, upcListId);
			Set<TblComponente> setComp = new HashSet<TblComponente>(compLst);
			return getComponenteList(setComp);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<ComponenteDTO>();
	}

	@Override
	public MecanicaDTO getMecanica(int mecanicaId) {
		MecanicaDTO mecanicaDTO = new MecanicaDTO();
		try {
			TblMecanica mecanicaBO = daoTblMecanica.getMecanicaByIdWithChildren(mecanicaId);
			orderList(mecanicaBO);
			mecanicaDTO = getMecanicaDTObyBO(mecanicaBO);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			mecanicaDTO = null;
		}
		return mecanicaDTO;
	}

	@Override
	public MecanicaDTO getMecanicaForComponenteSenalamiento(int mecanicaId) {
		MecanicaDTO mecanicaDTO = new MecanicaDTO();
		try {
			TblMecanica mecanicaBO = daoTblMecanica.getById(mecanicaId);
			mecanicaDTO = getMecanicaDtoComponenteSenalamientoList(mecanicaBO);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			mecanicaDTO = null;
		}
		return mecanicaDTO;
	}

	@Override
	public MecanicaDTO getMecanicaGrupoZonas(int mecanicaId) {
		MecanicaDTO mecanicaDTO = new MecanicaDTO();
		try {
			TblMecanica mecanicaBO = daoTblMecanica.getById(mecanicaId);
			mecanicaDTO = getMecanicaDTObyBOGrupoZonas(mecanicaBO);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			mecanicaDTO = null;
		}
		return mecanicaDTO;
	}

	@Override
	public boolean updateHojaComponente(int pkComp, int hoja) {
		return daoTblComponente.updateHoja(pkComp, hoja);
	}

	private MecanicaDTO getMecanicaDTObyBO(final TblMecanica mecanicaBO) {
		Long time = System.currentTimeMillis();
		final MecanicaDTO mecanicaDTO = new MecanicaDTO();
		try {
			if (mecanicaBO != null) {
				mecanicaDTO.setProgramaId(mecanicaBO.getProgramaId());
				mecanicaDTO.setAcuerdo1(mecanicaBO.getAcuerdo1());
				mecanicaDTO.setAcuerdo2(mecanicaBO.getAcuerdo2());
				mecanicaDTO.setAcuerdo3(mecanicaBO.getAcuerdo3());
				mecanicaDTO.setComentarios(mecanicaBO.getComentarios());
				mecanicaDTO.setEsCompartido(mecanicaBO.getEsCompartido());
				mecanicaDTO.setIdCampana(mecanicaBO.getCampana().getIdCampana());
				mecanicaDTO.setIdPromo(mecanicaBO.getPromoId());
				mecanicaDTO.setNombrePromo(mecanicaBO.getNombrePromo());
				mecanicaDTO.setIdTipoPromocion(mecanicaBO.getTipoPromocionId());
				mecanicaDTO.setIngresoPopReal(mecanicaBO.getIngresoPopReal() == null ? 0 : mecanicaBO.getIngresoPopReal().doubleValue());
				mecanicaDTO.setNombrePromo(mecanicaBO.getNombrePromo());
				mecanicaDTO.setPkMec(mecanicaBO.getMecanicaId());
				
				mecanicaDTO.setCategoriaList(getCategoriaList(mecanicaBO.getTblCategorias()));
    			mecanicaDTO.setComponenteList(getComponenteList(mecanicaBO.getTblComponentes()));
    			mecanicaDTO.setProgramaList(getProgramaList(mecanicaBO.getTblProgramas()));
    			mecanicaDTO.setSenalamientoList(getSenalesList(mecanicaBO.getTblSenalamientoses()));
    		/*
    			mecanicaDTO.setStoreList(getStoreList(mecanicaBO.getRelStores()));
    			mecanicaDTO.setGroupList(getGList(mecanicaBO.getRelGrupoZonas()));
    			mecanicaDTO.setZoneList(getZList(mecanicaBO.getRelZonas()));
			*/
    			mecanicaDTO.setConfMecanicaDTO(getConfMecanica(mecanicaBO.getConfMecanicaLst()));
    			mecanicaDTO.setPreciosLst(getPrecios(mecanicaBO.getPreciosLst()));

	    		mecanicaDTO.setHoraIni(mecanicaBO.getHoraIni());
				mecanicaDTO.setHoraFin(mecanicaBO.getHoraFin());
				mecanicaDTO.setLunes(mecanicaBO.getLunes());
				mecanicaDTO.setMartes(mecanicaBO.getMartes());
				mecanicaDTO.setMiercoles(mecanicaBO.getMiercoles());
				mecanicaDTO.setJueves(mecanicaBO.getJueves());
				mecanicaDTO.setViernes(mecanicaBO.getViernes());
				mecanicaDTO.setSabado(mecanicaBO.getSabado());
				mecanicaDTO.setDomingo(mecanicaBO.getDomingo());
				mecanicaDTO.setNombreMecanica(mecanicaBO.getNombreMecanica());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getMecanicaDTObyBO: "+(System.currentTimeMillis()-time));
		return mecanicaDTO;
	}
	
	private MecanicaDTO getMecanicaDTObyBOGrupoZonas(final TblMecanica mecanicaBO) {
		final MecanicaDTO mecanicaDTO = new MecanicaDTO();
		ExecutorService ex = Executors.newFixedThreadPool(20);
		try {
			if (mecanicaBO != null) {
				mecanicaDTO.setProgramaId(mecanicaBO.getProgramaId());
				mecanicaDTO.setAcuerdo1(mecanicaBO.getAcuerdo1());
				mecanicaDTO.setAcuerdo2(mecanicaBO.getAcuerdo2());
				mecanicaDTO.setAcuerdo3(mecanicaBO.getAcuerdo3());
				mecanicaDTO.setComentarios(mecanicaBO.getComentarios());
				mecanicaDTO.setEsCompartido(mecanicaBO.getEsCompartido());
				mecanicaDTO.setIdCampana(mecanicaBO.getCampana().getIdCampana());
				mecanicaDTO.setIdPromo(mecanicaBO.getPromoId());
				mecanicaDTO.setNombrePromo(mecanicaBO.getNombrePromo());
				mecanicaDTO.setIdTipoPromocion(mecanicaBO.getTipoPromocionId());
				mecanicaDTO.setIngresoPopReal(mecanicaBO.getIngresoPopReal() == null ? 0 : mecanicaBO.getIngresoPopReal().doubleValue());
				mecanicaDTO.setNombrePromo(mecanicaBO.getNombrePromo());
				mecanicaDTO.setPkMec(mecanicaBO.getMecanicaId());
				
				Runnable r = new Runnable() { @Override public void run() {
					mecanicaDTO.setComponenteList(getComponenteList(mecanicaBO.getTblComponentes()));
				}};
	    		ex.execute(r);
	    		r = new Runnable() { @Override public void run() {
	    			mecanicaDTO.setSenalamientoList(getSenalesList(mecanicaBO.getTblSenalamientoses()));
				}};
	    		ex.execute(r);
	    		r = new Runnable() { @Override public void run() {
	    			mecanicaDTO.setStoreList(getStoreList(mecanicaBO.getRelStores()));
				}};
	    		ex.execute(r);
	    		r = new Runnable() { @Override public void run() {
	    			mecanicaDTO.setGroupList(getGList(mecanicaBO.getRelGrupoZonas()));
				}};
	    		ex.execute(r);
	    		r = new Runnable() { @Override public void run() {
	    			mecanicaDTO.setZoneList(getZList(mecanicaBO.getRelZonas()));
				}};
	    		ex.execute(r);
				
				mecanicaDTO.setHoraIni(mecanicaBO.getHoraIni());
				mecanicaDTO.setHoraFin(mecanicaBO.getHoraFin());
				mecanicaDTO.setLunes(mecanicaBO.getLunes());
				mecanicaDTO.setMartes(mecanicaBO.getMartes());
				mecanicaDTO.setMiercoles(mecanicaBO.getMiercoles());
				mecanicaDTO.setJueves(mecanicaBO.getJueves());
				mecanicaDTO.setViernes(mecanicaBO.getViernes());
				mecanicaDTO.setSabado(mecanicaBO.getSabado());
				mecanicaDTO.setDomingo(mecanicaBO.getDomingo());
				mecanicaDTO.setNombreMecanica(mecanicaBO.getNombreMecanica());
				
				ex.shutdown();
		        while (!ex.isTerminated()){}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mecanicaDTO;
	}

	private MecanicaDTO getMecanicaDtoComponenteSenalamientoList(final TblMecanica mecanicaBO) {
		ExecutorService ex = Executors.newFixedThreadPool(20);
		final MecanicaDTO mecanicaDTO = new MecanicaDTO();
		try {
			if (mecanicaBO != null) {
				Runnable r = new Runnable() { @Override public void run() {
					mecanicaDTO.setComponenteList(getComponenteList(mecanicaBO.getTblComponentes()));
				}};
	    		ex.execute(r);
	    		r = new Runnable() { @Override public void run() {
	    			mecanicaDTO.setSenalamientoList(getSenalesList(mecanicaBO.getTblSenalamientoses()));
				}};
	    		ex.execute(r);
	    		ex.shutdown();
		        while (!ex.isTerminated()){}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mecanicaDTO;
	}

	private Set<TblComponente> getComponenteListDTO(List<ComponenteDTO> componentesDTO) {
            Set<TblComponente> items = new HashSet<>();
            try {
                if (componentesDTO != null) {
                    for (ComponenteDTO elemento : componentesDTO) {
                        items.add(parseDTO(elemento));
                    }
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
            return items;
	}

	private TblComponente parseDTO(ComponenteDTO elemento){
            TblComponente item = new TblComponente();
            
            item.setAbastoInicial(elemento.getAbastoInicial());
            
            item.setCantidadProducto(elemento.getCantidadProd());
            item.setCategoriaId(elemento.getIdCateg());
            item.setDescripcionId(elemento.getIdDescription());
            item.setEspacioPromoId(elemento.getIdEspacioPromocional());
            item.setListaId(elemento.getIdLista());
            item.setProveedorId(elemento.getIdProveedor());
            item.setSubCategoriaId(elemento.getIdSubCategoria());
            item.setNumeroComponente(elemento.getNumeroComponente());
            
            item.setNumero(elemento.getNumero());
            
            item.setComponenteId(elemento.getPkComp());
            item.setUnidades(elemento.getUnidades());
            item.setRelCompSkus(getSkuListDTO(elemento.getSkuList()));
            item.setRelCompUpcs(getUpcListDTO(elemento.getUpcList()));
            item.setHoja(elemento.getHoja());
            // PRIMICIA
            item.setPrimDescripcion(elemento.getPrimDescripcion());
            item.setPrimCat(elemento.getPrimCat());
            item.setPrimUpc(elemento.getPrimUpc());
            item.setPrimPrecio(elemento.getPrimPrecio());
            
            item.setPrecioAlCrear(elemento.getPrecioVentaOriginal());
            return item;
	}

	private List<ComponenteDTO> getComponenteList(Set<TblComponente> componentes) {
		List<ComponenteDTO> items = new ArrayList<>();
		List<TblComponente> compList = new ArrayList<TblComponente>(componentes);
		orderListComp(compList);
		try {
			if (componentes != null) {
				for (TblComponente elemento : compList) {
					ComponenteDTO item = new ComponenteDTO();
					item.setAbastoInicial(elemento.getAbastoInicial());
					item.setCantidadProd(elemento.getCantidadProducto());
					item.setIdCateg(elemento.getCategoriaId());
					item.setIdDescription(elemento.getDescripcionId());
					item.setIdEspacioPromocional(elemento.getEspacioPromoId());
					item.setIdLista(elemento.getListaId());
					item.setIdProveedor(elemento.getProveedorId());
					item.setIdSubCategoria(elemento.getSubCategoriaId());
					item.setNumeroComponente(elemento.getNumeroComponente());
					item.setNumero(elemento.getNumero());
					item.setPkComp(elemento.getComponenteId());
					item.setPkMec(elemento.getTblMecanica().getMecanicaId());
					item.setUnidades(elemento.getUnidades());
					item.setSkuList(getSkuList(elemento.getRelCompSkus()));
					item.setUpcList(getUpcList(elemento.getRelCompUpcs()));
					item.setHoja(elemento.getHoja());
					// PRIMICIA
					item.setPrimDescripcion(elemento.getPrimDescripcion());
					item.setPrimCat(elemento.getPrimCat());
					item.setPrimUpc(elemento.getPrimUpc());
					item.setPrimPrecio(elemento.getPrimPrecio());
                    
                    LOG.info("seteando precio de venta original"+ elemento.getPrecioAlCrear());
                    item.setPrecioVentaOriginal(elemento.getPrecioAlCrear());
                    
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<RelStore> getStoreListDTO(List<GenericItem> lista) {
		Set<RelStore> items = new HashSet<>();
		try {
			if (lista != null) {
				for (GenericItem bo : lista) {
					RelStore item = new RelStore();
					item.setStoreId(bo.getId());
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<GenericItem> getStoreList(Set<RelStore> lista) {
		List<GenericItem> items = new ArrayList<>();
		try {
			if (lista != null) {
				for (RelStore bo : lista) {
					GenericItem item = new GenericItem();
					item.setId(bo.getStoreId());
					item.setObj(bo);
					item.setValue(MBUtil.genericSearch(Constants.ATT_ID,
							String.valueOf(bo.getStoreId()),
							Constants.ATT_CODE, Constants.CAT_STORE,
							serviceDynamicCatalogs));
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<TblPreciosPromocion> getPrecios(List<PreciosPromocionDTO> list) {
		Set<TblPreciosPromocion> items = new HashSet<TblPreciosPromocion>();
		try {
            if (list != null) {
				for (PreciosPromocionDTO bo : list) {

					TblPreciosPromocionId id = new TblPreciosPromocionId();
					id.setMecanicaId(bo.getPkMec());
					id.setComponenteId(bo.getPkComp());
					id.setZonaId(bo.getZonaId());

					TblPreciosPromocion item = daoTblPreciosPromocion.getPreciosPromocionById(id);
					if (item == null){
						item = new TblPreciosPromocion();
						item.setId(id);
					}

					item.setAhorroFijo(bo.getAhorroFijo());
					item.setComentario(bo.getComentario());
					item.setEstatusCaptura(bo.getEstatusCaptura());
                    
                    if(bo.getEstatusRevision() > 0){
                       item.setEstatusRevision(bo.getEstatusRevision()); 
                    }else{
                        item.setEstatusRevision(1);
                    }
                    
					item.setNombre(bo.getNombre());
					item.setObjetivo(bo.getObjetivo());
					item.setPorcentaje(bo.getPorcentaje());
					item.setPrecio(bo.getPrecio());
					item.setPromoId(bo.getPromoId());
					item.setRecuperacion(bo.getRecuperacion());
					item.setRecuperacionPorcentaje(bo.getRecuperacionPorcentaje());
					item.setPrecioVenta(bo.getPrecioVenta());
					item.setEstatusDiseno(bo.getEstatusDiseno());
                    
                    //se agregan los nuevos valores
                    item.setIva(bo.getIva());
                    item.setImpuesto(bo.getImpuesto());
                    item.setPrecioRegularNuevo(bo.getPrecioRegularNuevo());
                    
                    item.setDistribucionRebaja(bo.getDistribucionRebajaCantidad());
                    item.setDistribucionRebajaPor(bo.getDistribucionRebajaPorcentaje());
                    
                    item.setPorcentajeDescuento(bo.getPorcentajeDescuento());
                    item.setPorcentajeRetencion(bo.getPorcentajeRetencion());
                    item.setElasticidad(bo.getElasticidad());
                    
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<PreciosPromocionDTO> getPrecios(Set<TblPreciosPromocion> list) {
		List<PreciosPromocionDTO> items = new ArrayList<PreciosPromocionDTO>();
		try {
			if (list != null) {
				for (TblPreciosPromocion bo : list) {
					PreciosPromocionDTO item = new PreciosPromocionDTO();
					item.setPkMec(bo.getId().getMecanicaId());
					item.setPkComp(bo.getId().getComponenteId());
					item.setZonaId(bo.getId().getZonaId());
					item.setAhorroFijo(bo.getAhorroFijo());
					item.setComentario(bo.getComentario());
					item.setEstatusCaptura(bo.getEstatusCaptura());
					item.setEstatusRevision(bo.getEstatusRevision());
					item.setNombre(bo.getNombre());
					item.setObjetivo(bo.getObjetivo());
					item.setPorcentaje(bo.getPorcentaje());
					item.setPrecio(bo.getPrecio());
					item.setPromoId(bo.getPromoId());
					item.setRecuperacion(bo.getRecuperacion());
					item.setRecuperacionPorcentaje(bo.getRecuperacionPorcentaje());
					item.setPrecioVenta(bo.getPrecioVenta());
					item.setEstatusDiseno(bo.getEstatusDiseno());
                    
                    item.setIva(bo.getIva());
                    item.setImpuesto(bo.getImpuesto());
                    item.setPrecioRegularNuevo(bo.getPrecioRegularNuevo());
                    if(bo.getDistribucionRebaja()!=null)
                    	item.setDistribucionRebajaCantidad(bo.getDistribucionRebaja());
                    if(bo.getDistribucionRebajaPor()!=null)
                    	item.setDistribucionRebajaPorcentaje(bo.getDistribucionRebajaPor());
                    if(bo.getPorcentajeDescuento()!=null)
                    	item.setPorcentajeDescuento(bo.getPorcentajeDescuento());
                    if(bo.getPorcentajeRetencion()!=null)
                    	item.setPorcentajeRetencion(bo.getPorcentajeRetencion());
                    if(bo.getElasticidad()!=null)
                    	item.setElasticidad(bo.getElasticidad());
                    
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<TblConfMecanica> getConfMecanicaDTO(
			List<ConfMecanicaDTO> confMecanicaDTO) {
		Set<TblConfMecanica> items = new HashSet<TblConfMecanica>();
		try {
			if (confMecanicaDTO != null) {
				for (ConfMecanicaDTO bo : confMecanicaDTO) {
					TblConfMecanica item = new TblConfMecanica();
					item.setAhorro(bo.getAhorro());
					item.setAhorroPorcentaje(bo.getAhorroPorcentaje());
					item.setCategoriaId(bo.getCategoriaId());
					TblMecanica mecanica = new TblMecanica();
					mecanica.setMecanicaId(bo.getPkMec());
					item.setTblMecanica(mecanica);
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<ConfMecanicaDTO> getConfMecanica(
			Set<TblConfMecanica> confMecanica) {
		List<ConfMecanicaDTO> items = new ArrayList<ConfMecanicaDTO>();
		try {
			if (confMecanica != null) {
				for (TblConfMecanica bo : confMecanica) {
					ConfMecanicaDTO item = new ConfMecanicaDTO();
					item.setAhorro(bo.getAhorro());
					item.setAhorroPorcentaje(bo.getAhorroPorcentaje());
					item.setCategoriaId(bo.getCategoriaId());
					item.setPkMec(bo.getTblMecanica() != null ? bo.getTblMecanica().getMecanicaId() : 0);
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<RelZona> getZListDTO(List<GenericItem> lista) {
		Set<RelZona> items = new HashSet<>();
		try {
			if (lista != null) {
				for (GenericItem bo : lista) {
					RelZona item = new RelZona();
					item.setZonaId(bo.getId());
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<GenericItem> getZList(Set<RelZona> lista) {
		List<GenericItem> items = new ArrayList<>();
		try {
			if (lista != null) {
				for (RelZona bo : lista) {
					GenericItem item = new GenericItem();
					item.setId(bo.getZonaId());
					item.setObj(bo);
					item.setValue(MBUtil.genericSearch(Constants.ATT_ID,
							String.valueOf(bo.getZonaId()), Constants.ATT_CODE,
							Constants.CAT_ZONA, serviceDynamicCatalogs));
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<TblSenalamientos> getSenalesListDTO(List<GenericItem> lista) {
		Set<TblSenalamientos> items = new HashSet<>();
		try {
			if (lista != null) {
				for (GenericItem bo : lista) {
					TblSenalamientos item = new TblSenalamientos();
					item.setSenalamientoId(bo.getId());
					item.setNombreSenal(bo.getValue());
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<GenericItem> getSenalesList(Set<TblSenalamientos> lista) {
		List<GenericItem> items = new ArrayList<>();
		try {
			if (lista != null) {
				for (TblSenalamientos bo : lista) {
					GenericItem item = new GenericItem();
					item.setId(bo.getSenalamientoId());
					item.setValue(bo.getNombreSenal());
					item.setObj(bo);
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<TblPrograma> getProgramaListDTO(List<GenericItem> lista) {
		Set<TblPrograma> items = new HashSet<>();
		try {
			if (lista != null) {
				for (GenericItem bo : lista) {
					TblPrograma item = new TblPrograma();
					item.setProgramaId(bo.getId());
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<GenericItem> getProgramaList(Set<TblPrograma> tblPrograma) {
		List<GenericItem> items = new ArrayList<>();
		try {
			if (tblPrograma != null) {
				for (TblPrograma programa : tblPrograma) {
					GenericItem item = new GenericItem();
					item.setId(programa.getProgramaId());
					item.setObj(programa);
					item.setValue(MBUtil.genericSearch(Constants.ATT_ID,
							String.valueOf(programa.getProgramaId()),
							Constants.ATT_CODE, Constants.CAT_PROGRAMA,
							serviceDynamicCatalogs));
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<RelGrupoZona> getGListDTO(List<GenericItem> lista) {
		Set<RelGrupoZona> items = new HashSet<>();
		try {
			if (lista != null) {
				for (GenericItem bo : lista) {
					RelGrupoZona item = new RelGrupoZona();
					item.setGrupoId(bo.getId());
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<GenericItem> getGList(Set<RelGrupoZona> grupoZona) {
		List<GenericItem> items = new ArrayList<>();
		try {
			if (grupoZona != null) {
				for (RelGrupoZona gZona : grupoZona) {
					GenericItem item = new GenericItem();
					item.setId(gZona.getGrupoId());
					item.setObj(gZona);
					item.setValue(MBUtil.genericSearch(Constants.ATT_ID,
							String.valueOf(gZona.getGrupoId()),
							Constants.ATT_CODE, Constants.CAT_G_ZONE,
							serviceDynamicCatalogs));
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<RelCompSku> getSkuListDTO(List<GenericItem> lista) {
		Set<RelCompSku> items = new HashSet<>();
		try {
			if (lista != null) {
				for (GenericItem bo : lista) {
					RelCompSku item = new RelCompSku();
					item.setSkuId(bo.getId());
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<GenericItem> getSkuList(Set<RelCompSku> skuSet) {
		List<GenericItem> items = new ArrayList<>();
		try {
			if (skuSet != null) {
				for (RelCompSku sku : skuSet) {
					GenericItem item = new GenericItem();
					item.setId(sku.getSkuId());
					item.setObj(sku);
					item.setValue(MBUtil.genericSearch(Constants.ATT_ID,
							String.valueOf(sku.getSkuId()), Constants.ATT_CODE,
							Constants.CAT_LISTA_DET, serviceDynamicCatalogs));
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private Set<RelCompUpc> getUpcListDTO(GenericItemString lista) {
		Set<RelCompUpc> items = new HashSet<>();
		try {
			if (lista != null) {
				RelCompUpc item = new RelCompUpc();
				item.setUpcId(lista.getId());
				items.add(item);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private GenericItemString getUpcList(Set<RelCompUpc> upcSet) {
		GenericItemString item = new GenericItemString();
		try {
			if (upcSet != null) {
				for (RelCompUpc upc : upcSet) {
					item.setId(upc.getUpcId());
					item.setObj(upc);
					item.setValue(MBUtil.genericSearch(Constants.ATT_ID,
							String.valueOf(upc.getUpcId()), Constants.ATT_CODE,
							Constants.CAT_ARTICULO, serviceDynamicCatalogs));
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return item;
	}

	private Set<TblCategoria> getCategoriaListDTO(List<GenericItem> lista) {
		Set<TblCategoria> items = new HashSet<>();
		try {
			if (lista != null) {
				for (GenericItem bo : lista) {
					TblCategoria item = new TblCategoria();
					item.setCategoriaId(bo.getId());
					items.add(item);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return items;
	}

	private List<GenericItem> getCategoriaList(Set<TblCategoria> tblCategorias) {
		Map<Integer, GenericItem> items = new HashMap<>();
		try {
			if (tblCategorias != null) {
				List<String> ids = new ArrayList<String>();
				for (TblCategoria categoria : tblCategorias) {
					ids.add(categoria.getCategoriaId()+"");
					GenericItem item = new GenericItem();
					item.setId(categoria.getCategoriaId());
					item.setObj(categoria);
					items.put(categoria.getCategoriaId(), item);
				}
                Map<String, String> map = MBUtil.genericSearch(Constants.ATT_ID, ids, Constants.ATT_CODE, Constants.CAT_CATEGORY, serviceDynamicCatalogs);
                for (TblCategoria categoria : tblCategorias) {
                	GenericItem item = items.get(categoria.getCategoriaId());
					item.setValue(map.get(categoria.getCategoriaId()+""));
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<GenericItem>(items.values());
	}

	@Override
	public List<TblCampana> getAllCampana(UsuarioDTO usuario) {
		try {
			Map<Long, TblCampana> campanaList = new HashMap<>();
			List<TblCampanaProgramasCategorias> programas = daoCampanaProgramasCategorias.getProgramasListByUser(usuario);
			if (programas != null) {
				for (TblCampanaProgramasCategorias programa : programas) {
					if (programa.getTblCampanaProgramas() != null
							&& programa.getTblCampanaProgramas()
									.getTblCampana() != null) {
						TblCampanaProgramasCategorias programaAux = programa.clone();
						TblCampana campana = ((TblCampana) daoCampanas.getById(programaAux.getTblCampanaProgramas().getId().getIdCampana())).clone();
						if (campanaList.containsKey(campana.getIdCampana())) {
							TblCampana campanaAux = campanaList.get(campana
									.getIdCampana());
							if (campanaAux.getTblCampanaProgramas() == null) {
								campanaAux
										.setTblCampanaProgramas(new HashSet<TblCampanaProgramas>());
							}
							if (!campanaAux.getTblCampanaProgramas().contains(
									programa.getTblCampanaProgramas())) {
								campanaAux.getTblCampanaProgramas().add(
										programa.getTblCampanaProgramas());
							}
						} else {
							Set<TblCampanaProgramas> sTblCampProgs = new HashSet<>(daoCampanaProgramas.getProgramasByCampanaId(campana.getIdCampana()));
							if (!Hibernate.isInitialized(sTblCampProgs)) {
								Hibernate.initialize(sTblCampProgs);
							}
							if (!Hibernate.isInitialized(sTblCampProgs.iterator().next().getTblCampanaProgramasCategorias())) {
								Hibernate.initialize(sTblCampProgs.iterator().next().getTblCampanaProgramasCategorias());
							}
							campana.setTblCampanaProgramas(sTblCampProgs);
							campanaList.put(campana.getIdCampana(), campana);
						}
					}
				}
			}
			List<TblCampana> campanaLst = new ArrayList<>(campanaList.values());
			return orderListCampana(campanaLst);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}


	@Override
	public void deleteDiseno(int disenoId) throws Exception {
		TblDisenos entity = daoTblDisenos.getById(disenoId);
		daoTblDisenos.delete(entity);
	}

	@Override
	public void saveDiseno(List<DisenosDTO> disenosDTOLst, int rolUsuario, Integer verDisenoAutorizado, String verDisenoComentarios) throws Exception {
		List<TblDisenos> disenosLst = getDisenos(disenosDTOLst, rolUsuario, verDisenoAutorizado, verDisenoComentarios);
		for (TblDisenos diseno : disenosLst) {
			daoTblDisenos.save(diseno);
		}
	}

	@Override
	public void updateDiseno(List<DisenosDTO> disenosDTOLst, int rolUsuario, Integer verDisenoAutorizado, String verDisenoComentarios) throws Exception {
		List<TblDisenos> disenosLst = getDisenos(disenosDTOLst, rolUsuario, verDisenoAutorizado, verDisenoComentarios);
		for (TblDisenos diseno : disenosLst) {
			daoTblDisenos.update(diseno);
		}
	}


	@Override
	public void updateDiseno(DisenosDTO dto) throws Exception {
		TblDisenos disenos = new TblDisenos();
		disenos.setDisenoId(dto.getDisenoId());
		disenos.setPreciosAuth(dto.getPreciosAuth());
		
		daoTblDisenos.update(disenos);
	}

	private List<TblDisenos> getDisenos(List<DisenosDTO> disenosDTOLst, int rolUsuario, 
            Integer verDisenoAutorizado, String verDisenoComentarios) throws SerialException, SQLException {
		List<TblDisenos> disenosLst = new ArrayList<>();
		if (disenosDTOLst != null && disenosDTOLst.size() > 0) {
			for (DisenosDTO item : disenosDTOLst) {
                System.out.println("generando entity de dise???o:");
                System.out.println("valor de verDisenoAutorizado: " + verDisenoAutorizado);
                
                System.out.println("valor auth category: " + item.getCategoryAuth());
                System.out.println("valor auth precios: " + item.getPreciosAuth());

                
				TblDisenos diseno = new TblDisenos();
				diseno.setDisenoId(item.getDisenoId());

				if (item.getImage() != null) {
					Blob image = new SerialBlob(item.getImage());
					diseno.setImage(image);
				}

				TblMecanica mecanica = new TblMecanica();
				mecanica.setMecanicaId(item.getPkMec());
				diseno.setTblMecanica(mecanica);
				diseno.setProgramaId(item.getProgramaId());
				diseno.setRelDisenoSenals(new HashSet<RelDisenoSenal>(item.getSenalList()));
				//diseno.setSenalamientoId(item.getSenalamientoId());
				diseno.setEstatusPrecios(item.getAuth() ? 1 : 0);

				if (rolUsuario == Constants.CATEGORY || rolUsuario == Constants.ADMINISTRADOR) {
                    if(verDisenoAutorizado != null){
                        diseno.setCategoryAuth(verDisenoAutorizado);
                    }else{
                        diseno.setCategoryAuth(item.getCategoryAuth());
                    }
					diseno.setCategoryComenta(verDisenoComentarios);
					diseno.setPreciosAuth(item.getPreciosAuth());
					diseno.setPreciosComenta(item.getPreciosComenta());
				} else {
                    if(verDisenoAutorizado != null){
                        diseno.setPreciosAuth(verDisenoAutorizado);
                    }else{
                        diseno.setPreciosAuth(item.getPreciosAuth());
                    }
					diseno.setCategoryAuth(item.getCategoryAuth());
					diseno.setCategoryComenta(item.getCategoryComenta());
					diseno.setPreciosComenta(verDisenoComentarios);
				}

				if (item.getGrupoZonaLst() != null) {
					diseno.setGrupoZonaLst(new HashSet<RelGrupoZonaDiseno>());
					for (DisenosDTO.RelObj obj : item.getGrupoZonaLst()) {
						RelGrupoZonaDiseno e = new RelGrupoZonaDiseno();
						e.setGrupoId(obj.getId());
						e.setDisenos(diseno);
						diseno.getGrupoZonaLst().add(e);
					}
				}

				if (item.getZonaLst() != null) {
					diseno.setZonaLst(new HashSet<RelZonaDiseno>());
					for (DisenosDTO.RelObj obj : item.getZonaLst()) {
						RelZonaDiseno e = new RelZonaDiseno();
						e.setZonaId(obj.getId());
						e.setDisenos(diseno);
						diseno.getZonaLst().add(e);
					}
				}

				if (item.getStoreLst() != null) {
					diseno.setStoreLst(new HashSet<RelStoreDiseno>());
					for (DisenosDTO.RelObj obj : item.getStoreLst()) {
						RelStoreDiseno e = new RelStoreDiseno();
						e.setStoreId(obj.getId());
						e.setDisenos(diseno);
						diseno.getStoreLst().add(e);
					}
				}

				Set<RelDisenoSenal> senals = new HashSet<>();
				for (RelDisenoSenal rel : item.getSenalList()){
					rel.setTblDisenos(diseno);
					senals.add(rel);
				}

				diseno.setRelDisenoSenals(senals);

				disenosLst.add(diseno);
			}
		}
		return disenosLst;
	}

	/**
	 * Convierte una lista de objetos de la base de datos a DTO para disenos.
	 * 
	 * @param disenosLst
	 *            lista de objetos original
	 * @return
	 * @throws SQLException
	 * @throws GeneralException
	 * @throws IOException
	 */
	private List<DisenosDTO> getDisenosDTO(List<TblDisenos> disenosLst) throws SQLException, GeneralException, IOException {
		List<DisenosDTO> disenosDTOLst = new ArrayList<>();
		if (disenosLst != null && disenosLst.size() > 0) {
			List<Integer> disenoIds=new ArrayList<Integer>();
			for (TblDisenos item : disenosLst) {
				disenoIds.add(item.getDisenoId());
			}
			List<RelStoreDiseno> stores = daoTblDisenos.getStores(disenoIds);
			List<RelZonaDiseno> zonasDiseno = daoTblDisenos.getZonas(disenoIds);
			for (TblDisenos item : disenosLst) {
				DisenosDTO diseno = new DisenosDTO();
				diseno.setDisenoId(item.getDisenoId());

				if (item.getImage() != null) {
					BASE64Encoder base = new BASE64Encoder();
					int blobLength = (int) item.getImage().length();
					byte[] blobAsBytes = item.getImage().getBytes(1, blobLength);
					diseno.setImage(blobAsBytes);
					diseno.setImageStr(base.encode(blobAsBytes));
					diseno.setImageStream(new DefaultStreamedContent(item.getImage().getBinaryStream()));
				}

				diseno.setPkMec(item.getTblMecanica().getMecanicaId());
				diseno.setMecanicaStr(item.getTblMecanica().getNombreMecanica());

				if (item.getGrupoZonaLst() != null) {
					List<Integer> ids = new ArrayList<Integer>();
					for (RelGrupoZonaDiseno obj : item.getGrupoZonaLst()) {
						ids.add(obj.getGrupoId());
					}
					List<CatGZone> zonas = serviceCatGZone.getCatGZoneById(ids);
					Map<Integer, CatGZone> map = new HashMap<Integer, CatGZone>();
					for(CatGZone cs: zonas){
						map.put(cs.getIdGrupoZona(), cs);
					}
					diseno.setGrupoZonaLst(new ArrayList<DisenosDTO.RelObj>());
					for (RelGrupoZonaDiseno obj : item.getGrupoZonaLst()) {
						DisenosDTO.RelObj relObj = new DisenosDTO.RelObj();
						relObj.setId(obj.getGrupoId());
						CatGZone grupoZona = map.get(obj.getGrupoId());
						if (grupoZona != null) {
							relObj.setStr(grupoZona.getCode());
						}
						diseno.getGrupoZonaLst().add(relObj);
					}
				}

				if (zonasDiseno!=null) {
					item.setZonaLst(new HashSet<RelZonaDiseno>());
					List<Integer> ids = new ArrayList<Integer>();
					for (RelZonaDiseno obj : zonasDiseno) {
						if(obj.getDisenos().getDisenoId() == item.getDisenoId()){
							ids.add(obj.getZonaId());
							item.getZonaLst().add(obj);
						}
					}
					List<CatZone> zonas = serviceCatZone.getCatZoneByIds(ids);
					Map<Integer, CatZone> map = new HashMap<Integer, CatZone>();
					for(CatZone cs: zonas){
						map.put(cs.getIdZone(), cs);
					}
					diseno.setZonaLst(new ArrayList<DisenosDTO.RelObj>());
					for (RelZonaDiseno obj : item.getZonaLst()) {
						DisenosDTO.RelObj relObj = new DisenosDTO.RelObj();
						relObj.setId(obj.getZonaId());
						CatZone zona = 	map.get(obj.getZonaId());
						if (zona != null) {
							relObj.setStr(zona.getCode());
						}
						diseno.getZonaLst().add(relObj);
					}
				}

				if (stores!=null) {
					item.setStoreLst(new HashSet<RelStoreDiseno>());
					List<Integer> idsStore = new ArrayList<Integer>();
					for (RelStoreDiseno obj : stores) {
						if(obj.getDisenos().getDisenoId() == item.getDisenoId()){
							idsStore.add(obj.getStoreId());
							item.getStoreLst().add(obj);
						}
					}
					List<CatStore> listCatStore = serviceCatStore.getCatStoreByIds(idsStore);
					Map<Integer, CatStore> map = new HashMap<Integer, CatStore>();
					for(CatStore cs: listCatStore){
						map.put(cs.getIdStore(), cs);
					}
					diseno.setStoreLst(new ArrayList<DisenosDTO.RelObj>());
					for (RelStoreDiseno obj : item.getStoreLst()) {
						DisenosDTO.RelObj relObj = new DisenosDTO.RelObj();
						relObj.setId(obj.getStoreId());
						CatStore store = map.get(obj.getStoreId());
						if (store != null) {
							relObj.setStr(store.getCode());
						}
						diseno.getStoreLst().add(relObj);
					}
				}

				diseno.setProgramaId(item.getProgramaId());
				CatPrograma programa = serviceCatPrograma.getCatPrograma(new CatPrograma(item.getProgramaId()));
				if (programa !=null) {
					diseno.setProgramaStr(programa.getNombre());
				}
				diseno.setSenalList(new ArrayList<RelDisenoSenal>(item.getRelDisenoSenals()));

                System.out.println("Mapeo entity a dto, precios auth entity: " + item.getPreciosAuth());
                System.out.println("Mapeo entity a dto, category auth entity: " + item.getCategoryAuth());
            
				diseno.setPreciosAuth(item.getPreciosAuth());
				diseno.setPreciosComenta(item.getPreciosComenta());
				diseno.setCategoryAuth(item.getCategoryAuth());
				diseno.setCategoryComenta(item.getCategoryComenta());
				diseno.setEstatusPrecios(item.getEstatusPrecios());
                
                System.out.println("Mapeo entity a dto, precios auth: " + diseno.getPreciosAuth());
                System.out.println("Mapeo entity a dto, category auth: " + diseno.getCategoryAuth());
                
                
				disenosDTOLst.add(diseno);
			} // Fin for
		} // Fin if lista es null
		return disenosDTOLst;
	}

	@Override
	public Double getPrecioVentaBySKU(int sku) {
		Double precioVenta = null;
		try {
			List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
			AttrSearch search = new AttrSearch();
			search.setAttrName(Constants.ATT_CODE);
			search.setValue(String.valueOf(sku));
			insertSearch.add(search);
			String codeItem = MBUtil.getCatalogAttribByKey(serviceDynamicCatalogs, Constants.CAT_LISTA_DET, insertSearch, Constants.ATT_ID_ITEM);

			insertSearch.clear();
			search.setValue(codeItem);
			insertSearch.add(search);
			String precioVentaStr = MBUtil.getCatalogAttribByKey(serviceDynamicCatalogs, Constants.CAT_ITEM, insertSearch, Constants.ATT_PRECIO_ACTUAL);
			precioVenta = new Double(precioVentaStr);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return precioVenta;
	}

	@Override
	public List<GenericItem> getUPCbyLista(int listaId) {
		List<GenericItem> upcList = new ArrayList<>();
		try {
                    List<AttrSearch> insertSearch = new ArrayList<>();
                    AttrSearch search = new AttrSearch();
                    search.setAttrName(Constants.ATT_ID_LISTA);
                    search.setValue(String.valueOf(listaId));
                    insertSearch.add(search);

                    List<GenericItem> skuList = MBUtil.genericSearch(Constants.ATT_CODE, Constants.ATT_CODE, Constants.CAT_LISTA_DET, serviceDynamicCatalogs, insertSearch);
                    if (skuList != null && skuList.size() > 0) {
                            insertSearch.clear();
                            for (GenericItem item : skuList) {
                                    search = new AttrSearch();
                                    search.setAttrName(Constants.ATT_CODE);
                                    search.setValue(item.getValue());
                                    insertSearch.add(search);
                            }
                            upcList = MBUtil.genericSearch(Constants.ATT_CODE, Constants.ATT_CODE, Constants.CAT_ITEM, serviceDynamicCatalogs, insertSearch);
                    }
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return upcList;
	}

	public Logger getLOG() {
		return LOG;
	}

	public void setLOG(Logger lOG) {
		LOG = lOG;
	}

	public DAOCampana getDaoCampanas() {
		return daoCampanas;
	}

	public void setDaoCampanas(DAOCampana daoCampanas) {
		this.daoCampanas = daoCampanas;
	}

	public DAORelCompUpc getDaoRelCompUpc() {
		return daoRelCompUpc;
	}

	public void setDaoRelCompUpc(DAORelCompUpc daoRelCompUpc) {
		this.daoRelCompUpc = daoRelCompUpc;
	}

	public DAORelCompSku getDaoRelCompSku() {
		return daoRelCompSku;
	}

	public void setDaoRelCompSku(DAORelCompSku daoRelCompSku) {
		this.daoRelCompSku = daoRelCompSku;
	}

	public DAOTblSenalamientos getDaoTblSenalamientos() {
		return daoTblSenalamientos;
	}

	public void setDaoTblSenalamientos(DAOTblSenalamientos daoTblSenalamientos) {
		this.daoTblSenalamientos = daoTblSenalamientos;
	}

	public DAORelStore getDaoRelStore() {
		return daoRelStore;
	}

	public void setDaoRelStore(DAORelStore daoRelStore) {
		this.daoRelStore = daoRelStore;
	}

	public DAORelZona getDaoRelZona() {
		return daoRelZona;
	}

	public void setDaoRelZona(DAORelZona daoRelZona) {
		this.daoRelZona = daoRelZona;
	}

	public DAORelGrupoZona getDaoRelGrupoZona() {
		return daoRelGrupoZona;
	}

	public void setDaoRelGrupoZona(DAORelGrupoZona daoRelGrupoZona) {
		this.daoRelGrupoZona = daoRelGrupoZona;
	}

	public DAOTblCategoria getDaoTblCategoria() {
		return daoTblCategoria;
	}

	public void setDaoTblCategoria(DAOTblCategoria daoTblCategoria) {
		this.daoTblCategoria = daoTblCategoria;
	}

	public DAOTblPrograma getDaoTblPrograma() {
		return daoTblPrograma;
	}

	public void setDaoTblPrograma(DAOTblPrograma daoTblPrograma) {
		this.daoTblPrograma = daoTblPrograma;
	}

	public DAOTblMecanica getDaoTblMecanica() {
		return daoTblMecanica;
	}

	public void setDaoTblMecanica(DAOTblMecanica daoTblMecanica) {
		this.daoTblMecanica = daoTblMecanica;
	}

	public DAOTblComponente getDaoTblComponente() {
		return daoTblComponente;
	}

	public void setDaoTblComponente(DAOTblComponente daoTblComponente) {
		this.daoTblComponente = daoTblComponente;
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(
			ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}

	public DAOCampanaProgramasCategorias getDaoCampanaProgramasCategorias() {
		return daoCampanaProgramasCategorias;
	}

	public void setDaoCampanaProgramasCategorias(
			DAOCampanaProgramasCategorias daoCampanaProgramasCategorias) {
		this.daoCampanaProgramasCategorias = daoCampanaProgramasCategorias;
	}

	public DAOCampanaProgramas getDaoCampanaProgramas() {
		return daoCampanaProgramas;
	}

	public void setDaoCampanaProgramas(DAOCampanaProgramas daoCampanaProgramas) {
		this.daoCampanaProgramas = daoCampanaProgramas;
	}

	public DAOTblConfMecanica getDaoTblConfMecanica() {
		return daoTblConfMecanica;
	}

	public void setDaoTblConfMecanica(DAOTblConfMecanica daoTblConfMecanica) {
		this.daoTblConfMecanica = daoTblConfMecanica;
	}

	public DAOTblPreciosPromocion getDaoTblPreciosPromocion() {
		return daoTblPreciosPromocion;
	}

	public void setDaoTblPreciosPromocion(DAOTblPreciosPromocion daoTblPreciosPromocion) {
		this.daoTblPreciosPromocion = daoTblPreciosPromocion;
	}

	public DAOTblDisenos getDaoTblDisenos() {
		return daoTblDisenos;
	}

	public void setDaoTblDisenos(DAOTblDisenos daoTblDisenos) {
		this.daoTblDisenos = daoTblDisenos;
	}

	public ServiceCatGZone getServiceCatGZone() {
		return serviceCatGZone;
	}

	public void setServiceCatGZone(ServiceCatGZone serviceCatGZone) {
		this.serviceCatGZone = serviceCatGZone;
	}

	public ServiceCatZone getServiceCatZone() {
		return serviceCatZone;
	}

	public void setServiceCatZone(ServiceCatZone serviceCatZone) {
		this.serviceCatZone = serviceCatZone;
	}

	public ServiceCatStore getServiceCatStore() {
		return serviceCatStore;
	}

	public void setServiceCatStore(ServiceCatStore serviceCatStore) {
		this.serviceCatStore = serviceCatStore;
	}

	public ServiceCatPrograma getServiceCatPrograma() {
		return serviceCatPrograma;
	}

	public void setServiceCatPrograma(ServiceCatPrograma serviceCatPrograma) {
		this.serviceCatPrograma = serviceCatPrograma;
	}

	public ServiceCatSenal getServiceCatSenal() {
		return serviceCatSenal;
	}

	public void setServiceCatSenal(ServiceCatSenal serviceCatSenal) {
		this.serviceCatSenal = serviceCatSenal;
	}

	@Override
	public List<DisenosDTO> searchDisenos(int programaId, int mecanicaId,
			List<CatSenal> senals, List<String> grupoZonaId,
			List<String> zonaId, List<String> storeId, Integer estatusPrecio, Integer estatusDiseno) {
		try {
			List<TblDisenos> disenosLst = daoTblDisenos.getDisenos(programaId, mecanicaId, grupoZonaId, zonaId,
					senals, storeId, estatusPrecio, estatusDiseno);
			List<DisenosDTO> disenosDTOLst = getDisenosDTO(disenosLst);
			return disenosDTOLst;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Double getAhorroMaximoByMecanicaId(int mecanicaId) {
		try {
			return daoTblPreciosPromocion.getAhorroMaximoByMecanicaId(mecanicaId);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

    public TblCampana getCampana(int idCampana, Map<String, String> filters){
        TblCampana campana = daoCampanas.getCampana(idCampana, filters);
        return campana;
    }
    
	@Override
	public List<TblCampana> getAllCampanaSeven(CatUsuarios usuario, List<RelUsuariosCategorias> categoriasUsuarios) {
		try {
			Map<Long, TblCampana> campanaList = new HashMap<>();
			List<TblCampanaProgramasCategorias> programas = daoCampanaProgramasCategorias.getProgramasListByUser(usuario,categoriasUsuarios);
			if (programas != null) {
				for (TblCampanaProgramasCategorias programa : programas) {
					if (programa.getTblCampanaProgramas() != null
							&& programa.getTblCampanaProgramas()
									.getTblCampana() != null) {
						TblCampanaProgramasCategorias programaAux = programa.clone();
					//	TblCampana campana = ((TblCampana) daoCampanas.getById(programaAux.getTblCampanaProgramas().getId().getIdCampana())).clone();
						TblCampana campana = daoCampanas.getCampana(programaAux.getTblCampanaProgramas().getId().getIdCampana(), null);
						if (campanaList.containsKey(campana.getIdCampana())) {
							TblCampana campanaAux = campanaList.get(campana
									.getIdCampana());
							if (campanaAux.getTblCampanaProgramas() == null) {
								campanaAux
										.setTblCampanaProgramas(new HashSet<TblCampanaProgramas>());
							}
							if (!campanaAux.getTblCampanaProgramas().contains(
									programa.getTblCampanaProgramas())) {
								campanaAux.getTblCampanaProgramas().add(
										programa.getTblCampanaProgramas());
							}
						} else {
							Set<TblCampanaProgramas> sTblCampProgs = new HashSet<>(daoCampanaProgramas.getProgramasByCampanaId(campana.getIdCampana()));
							if (!Hibernate.isInitialized(sTblCampProgs)) {
								Hibernate.initialize(sTblCampProgs);
							}
							if (!Hibernate.isInitialized(sTblCampProgs.iterator().next().getTblCampanaProgramasCategorias())) {
								Hibernate.initialize(sTblCampProgs.iterator().next().getTblCampanaProgramasCategorias());
							}
							campana.setTblCampanaProgramas(sTblCampProgs);
							campanaList.put(campana.getIdCampana(), campana);
						}
					}
				}
			}
			List<TblCampana> campanaLst = new ArrayList<>(campanaList.values());
			return orderListCampana(campanaLst);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<Componente> getComponentesFromLista(int idLista) {
		try {
			List<Componente> compList = daoTblComponente.getComponentesFromLista(idLista);
			return compList;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return new ArrayList<Componente>();
	}

	@Override
	public List<DisenosDTO> getDisenosByIdMecanica(Integer idMecanica) {
		
		try {
			List<TblDisenos> disenosLstG = daoTblDisenos.getDiseno(idMecanica);
			List<DisenosDTO> disenosDTOLstG = getDisenosDTO(disenosLstG);
			return disenosDTOLstG;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean updateStatusDisenoByMecanica(Integer idMecanica,
			Integer idStatus) throws Exception {
		return daoTblDisenos.updateStatusDisenoByMecanica(idMecanica, idStatus);
	}

    /**
     * @return the daoTblComponenteZonaPrecio
     */
    public DAOTblComponenteZonaPrecio getDaoTblComponenteZonaPrecio() {
        return daoTblComponenteZonaPrecio;
    }

    /**
     * @param daoTblComponenteZonaPrecio the daoTblComponenteZonaPrecio to set
     */
    public void setDaoTblComponenteZonaPrecio(DAOTblComponenteZonaPrecio daoTblComponenteZonaPrecio) {
        this.daoTblComponenteZonaPrecio = daoTblComponenteZonaPrecio;
    }

    public TblComponenteZonaPrecio insertOrUpdateComponenteZonaPrecio(TblComponenteZonaPrecio e) throws Exception{
        this.daoTblComponenteZonaPrecio.saveOrUpdate(e);
        return e;
    }
    
    public void insertOrUpdateComponenteZonaPrecio(List<TblComponenteZonaPrecio> e) throws Exception{
        this.daoTblComponenteZonaPrecio.save(e);
    }
    
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(int componentId, int zoneId) throws Exception{
        return this.daoTblComponenteZonaPrecio.getByComponentIdAndZoneId(componentId, zoneId);
    }
    
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(List<Object[]> list) throws Exception{
        return this.daoTblComponenteZonaPrecio.getByComponentIdAndZoneId(list);
    }
    
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(String componentList, String zoneList) throws Exception{
        return this.daoTblComponenteZonaPrecio.getByComponentIdAndZoneId(componentList, zoneList);
    }
    
    public void deleteComponenteZonaByComponenteId(int componenteId) throws Exception{
        this.daoTblComponenteZonaPrecio.deleteComponenteZonaByComponenteId(componenteId);
    }
    
    public boolean updateComponentNumber(int componentId, int numeroComponente){
        return this.daoTblComponente.updateComponentNumber(componentId, numeroComponente);
    }
}
