package com.adinfi.seven.presentation.views.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.persistence.dto.CampanaDTO;

public class CampanaDTODataModel extends ListDataModel<CampanaDTO> implements
		SelectableDataModel<CampanaDTO> {

	public CampanaDTODataModel() {
	}

	public CampanaDTODataModel(List<CampanaDTO> data) {
		super(data);
	}

	@Override
	public CampanaDTO getRowData(String rowKey) {

		List<CampanaDTO> campanas = (List<CampanaDTO>) getWrappedData();
		CampanaDTO campanaDTO = null;
		for (CampanaDTO campana : campanas) {
			Long idCampana = campana.getTblCampana().getIdCampana();
			if (idCampana.toString().equals(rowKey)) {
				campanaDTO = campana;
				break;
			}
		}
		return campanaDTO;
	}

	@Override
	public Object getRowKey(CampanaDTO campanaDTO) {
		return campanaDTO.getTblCampana().getIdCampana();
	}
}