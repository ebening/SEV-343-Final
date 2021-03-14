package com.adinfi.seven.presentation.views.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.persistence.dto.CampanaDTO;
import com.adinfi.seven.persistence.dto.CampanaResultDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;
import com.adinfi.seven.persistence.dto.TipoCampanaDTO;
import com.adinfi.seven.presentation.views.util.CampanaUtil;

public class LazyCampanaDataModel extends LazyDataModel<CampanaDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceCampana serviceCampana;
	
	private Map<Integer, TipoCampanaDTO> tipoCampanas;
	private Map<Integer, PeriodoDTO> periodos;
	private Map<Integer, CatGZone> gZonas;
	
	public LazyCampanaDataModel(ServiceCampana serviceCampana, Map<Integer, TipoCampanaDTO> tipoCampanas, Map<Integer, PeriodoDTO> periodos, Map<Integer, CatGZone> gZonas){
		this.serviceCampana = serviceCampana;
		this.tipoCampanas = tipoCampanas;
		this.periodos = periodos;
		this.gZonas = gZonas;
	}

	@Override
    public CampanaDTO getRowData(String rowKey) {
		System.out.println("getRowData...");
        return new CampanaDTO();
    }
	
	@Override
    public Object getRowKey(CampanaDTO o) {
		System.out.println("getRowKey...");
        return o.getTblCampana().getIdCampana();
    }
	
	@Override
	public List<CampanaDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters){
		System.out.println("Loading campanas...");
		System.out.println("first: "+first);
		System.out.println("pageSize: "+pageSize);
		System.out.println("sortField: "+sortField);
		System.out.println("sortOrder: "+sortOrder);
		System.out.println("filters: "+filters);
		
		List<CampanaDTO> list = new ArrayList<>();
		CampanaResultDTO result = serviceCampana.getCampanas(first, pageSize, filters);
		for (TblCampana campana : result.getCampanas()) {
			CampanaDTO campanaDTO = CampanaUtil.llenadoCampanaDTO(campana, this.tipoCampanas, this.periodos, this.gZonas);
			list.add(campanaDTO);
		}
		this.setRowCount(result.getTotal());
		return list;
	}

	public void setServiceCampana(ServiceCampana serviceCampana) {
		this.serviceCampana = serviceCampana;
	}

	public void setTipoCampanas(Map<Integer, TipoCampanaDTO> tipoCampanas) {
		this.tipoCampanas = tipoCampanas;
	}

	public void setPeriodos(Map<Integer, PeriodoDTO> periodos) {
		this.periodos = periodos;
	}

	public void setgZonas(Map<Integer, CatGZone> gZonas) {
		this.gZonas = gZonas;
	}
}
