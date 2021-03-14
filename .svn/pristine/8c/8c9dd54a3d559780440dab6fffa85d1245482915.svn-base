package com.adinfi.seven.persistence.dto;

import java.util.List;

import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class PreciosDataModel extends ListDataModel<CampanaMedioDTO> implements
		SelectableDataModel<CampanaMedioDTO> {
	public PreciosDataModel(){
		
	}
	
	public PreciosDataModel(List<CampanaMedioDTO> data){
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CampanaMedioDTO getRowData(String rowKey) {
		List<CampanaMedioDTO> precios = (List<CampanaMedioDTO>) getWrappedData();
		CampanaMedioDTO precio = null;
		for (CampanaMedioDTO temp : precios) {
			String id = String.valueOf(temp.getCampana().getIdCampana());
			if (id.equals(rowKey)) {
				precio = temp;
				break;
			}
		}
		return precio;
	}
	
	@Override
	public Object getRowKey(CampanaMedioDTO precio) {
		return precio.getCampana().getIdCampana();
	}
}
