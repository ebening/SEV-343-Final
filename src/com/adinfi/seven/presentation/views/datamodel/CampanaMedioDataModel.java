package com.adinfi.seven.presentation.views.datamodel;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.persistence.dto.CampanaMedioDTO;

public class CampanaMedioDataModel extends ListDataModel<CampanaMedioDTO> implements
		SelectableDataModel<CampanaMedioDTO> {

	public CampanaMedioDataModel() {
	}

	public CampanaMedioDataModel(List<CampanaMedioDTO> data) {
		super(data);
	}

	@Override
	public CampanaMedioDTO getRowData(String rowKey) {

		List<CampanaMedioDTO> lista = (List<CampanaMedioDTO>) getWrappedData();
		CampanaMedioDTO campanaMedio = null;
		for (CampanaMedioDTO item : lista) {
			Long idCampana = item.getCampana().getIdCampana();
			if (idCampana.toString().equals(rowKey)) {
				campanaMedio = item;
				break;
			}
		}
		return campanaMedio;
	}

	@Override
	public Object getRowKey(CampanaMedioDTO campanaDTO) {
		return campanaDTO.getCampana().getIdCampana();
	}
}