package com.adinfi.seven.presentation.views.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.RelGrupoZonaCampana;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaProgramas;
import com.adinfi.seven.business.domain.TblCampanaProgramasCategorias;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.CampanaProgramaDTO;
import com.adinfi.seven.persistence.dto.DisenosDTO;
import com.adinfi.seven.persistence.dto.DisenosDTO.RelObj;
import com.adinfi.seven.persistence.dto.EtiquetaDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;

public class CampanaUtil {

	public static CampanaDTO llenadoCampanaDTO(TblCampana campana, Map<Integer, TipoCampanaDTO> tipoCampanas, Map<Integer, PeriodoDTO> periodos, Map<Integer, CatGZone> gZonas) {
        TipoCampanaDTO tipoDTO;
        EtiquetaDTO etiquetaDTO;
        CampanaDTO campanaDTO;
        PeriodoDTO periodoDTO = new PeriodoDTO();
        Calendar cal = Calendar.getInstance();
        cal.setTime(campana.getFechaInicio());
        campanaDTO = new CampanaDTO();

        tipoDTO = tipoCampanas.get(campana.getIdTipoCampana());

        etiquetaDTO = Constants.getEtiquetaCode(campana.getCatEtiquetas().getIdetiqueta());

        periodoDTO.setFechaInicial(campana.getFechaInicio());
        periodoDTO.setFechaFinal(campana.getFechaFin());
        
        periodoDTO.setFechaFin(campana.getFechaFin());
        periodoDTO.setFechaInicio(campana.getFechaInicio());
        periodoDTO.setId(campana.getIdPeriodo());
        campanaDTO.setEtiqueta(etiquetaDTO);

        if (campana.getIdPeriodo() != null) {
                periodoDTO = periodos.get(campana.getIdPeriodo());
        }

        campanaDTO.setPeriodo(periodoDTO);
        campanaDTO.setTipo(tipoDTO);
        campanaDTO.setTblCampana(campana);
        cal.setTime(campana.getFechaInicio());
        campanaDTO.setYear(String.valueOf(cal.get(Calendar.YEAR)));	
        //campanaDTO.setCategorias(getCategoriasByCampana(campana));
        //campanaDTO.setlCampanaProgramaDTO(getListCamapanaProgramasDTO(campana.getTblCampanaProgramas(), gZonas));
        return campanaDTO;
	}
	
	public static List<CampanaProgramaDTO> getListCamapanaProgramasDTO(Set<TblCampanaProgramas> setTblCampProgs, Map<Integer, CatGZone> gZonas){
		List<CampanaProgramaDTO> lCampProgsDTO = new ArrayList<CampanaProgramaDTO>();
		Iterator<TblCampanaProgramas> iterTblCampProgs = setTblCampProgs.iterator();
		while(iterTblCampProgs.hasNext()){
			TblCampanaProgramas tblCampProgs = iterTblCampProgs.next();
			CampanaProgramaDTO campProgDTO = new CampanaProgramaDTO();
			campProgDTO.setIdPrograma(tblCampProgs.getId().getIdPrograma());
			campProgDTO.setSencillo(tblCampProgs.getEsSencillo());
			campProgDTO.setIngreso(tblCampProgs.getIngreso());
            campProgDTO.setEtapa(tblCampProgs.getEtapa());
			
			Iterator<TblCampanaProgramasCategorias> iterTblCampProgsCats = tblCampProgs.getTblCampanaProgramasCategorias().iterator();
			List<String> lCategoria = new ArrayList<String>();
			while (iterTblCampProgsCats.hasNext()){
				TblCampanaProgramasCategorias tblCampProgsCats = iterTblCampProgsCats.next();
				lCategoria.add(String.valueOf(tblCampProgsCats.getIdCategoria()));
			}
			campProgDTO.setCategoriaSelect(lCategoria);
			campProgDTO.setGrupoZonas(new ArrayList<String>());
			campProgDTO.setGrupoZonaLst(new ArrayList<DisenosDTO.RelObj>());
			
			if(tblCampProgs.getGrupoZonas()!=null){
				for( RelGrupoZonaCampana grupoZona: tblCampProgs.getGrupoZonas()){
					addGrupoZona(grupoZona.getGrupoId(),campProgDTO, gZonas);
					campProgDTO.getGrupoZonas().add(String.valueOf(grupoZona.getGrupoId()));
				}
			}
			
			campProgDTO.setZonas(new ArrayList<String>());
			campProgDTO.setZonaLst(new ArrayList<DisenosDTO.RelObj>());
		/*	if(tblCampProgs.getZonas()!=null){
				for( RelZonaCampana zona: tblCampProgs.getZonas()){
					addZona(zona.getZonaId(), campProgDTO);
					campProgDTO.getZonas().add(String.valueOf(zona.getZonaId()));
				}
			} */
			
			campProgDTO.setTiendas(new ArrayList<String>());
			campProgDTO.setStoreLst(new ArrayList<DisenosDTO.RelObj>());
		/*	if(tblCampProgs.getTiendas()!=null){
				for( RelStoreCampana tienda: tblCampProgs.getTiendas()){
					addTienda(tienda.getStoreId(), campProgDTO);
					campProgDTO.getTiendas().add(String.valueOf(tienda.getStoreId()));
				}
			} */
			lCampProgsDTO.add(campProgDTO);
		}
		
		return lCampProgsDTO;
	}
	
	public static void addGrupoZona(int grupoZonaId, CampanaProgramaDTO campProgDTO, Map<Integer, CatGZone> gZonas) {
		CatGZone catGZone = new CatGZone();
		catGZone = gZonas.get(grupoZonaId);
		RelObj relObj= new RelObj();
		relObj.setId(catGZone.getIdGrupoZona());
		relObj.setStr(catGZone.getCode());
		campProgDTO.getGrupoZonaLst().add(relObj);
	}

}
