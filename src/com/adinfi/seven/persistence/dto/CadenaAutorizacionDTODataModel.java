package com.adinfi.seven.persistence.dto;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class CadenaAutorizacionDTODataModel extends
		ListDataModel<CadenaAutorizacionDTO> implements
		SelectableDataModel<CadenaAutorizacionDTO> {

	public CadenaAutorizacionDTODataModel() {
	}

	public CadenaAutorizacionDTODataModel(List<CadenaAutorizacionDTO> data) {
		super(data);
	}

	@Override
	public CadenaAutorizacionDTO getRowData(String rowKey) {

		@SuppressWarnings("unchecked")
		List<CadenaAutorizacionDTO> solicitudes = (List<CadenaAutorizacionDTO>) getWrappedData();

		for (CadenaAutorizacionDTO cadenaAutorizacionDTO : solicitudes) {
			Long idAut = (long) cadenaAutorizacionDTO
					.getTblCadenaAutorizacion().getIdCadenaAutorizacion();
			if (idAut.toString().equals(rowKey))
				return cadenaAutorizacionDTO;
		}

		return null;
	}

	@Override
	public Object getRowKey(CadenaAutorizacionDTO cadenaAutorizacionDTO) {
		return cadenaAutorizacionDTO.getTblCadenaAutorizacion()
				.getIdCadenaAutorizacion();
	}
}
