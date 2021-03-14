package com.adinfi.seven.persistence.dto;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.business.domain.TblCadenaAutorizacionDetId;

public class CadenaAutorizacionDetDTODataModel extends
		ListDataModel<CadenaAutorizacionDetDTO> implements
		SelectableDataModel<CadenaAutorizacionDetDTO> {

	public CadenaAutorizacionDetDTODataModel() {
	}

	public CadenaAutorizacionDetDTODataModel(List<CadenaAutorizacionDetDTO> data) {
		super(data);
	}

	@Override
	public CadenaAutorizacionDetDTO getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<CadenaAutorizacionDetDTO> solicitudes = (List<CadenaAutorizacionDetDTO>) getWrappedData();
		for (CadenaAutorizacionDetDTO cadenaAutorizacionDetDTO : solicitudes) {
			TblCadenaAutorizacionDetId tblCadenaAutorizacionDetId = (TblCadenaAutorizacionDetId) cadenaAutorizacionDetDTO
					.getTblCadenaAutorizacionDet().getId();
			if ((String.valueOf(tblCadenaAutorizacionDetId
					.getIdCadenaAutorizacion()).concat(String
					.valueOf(tblCadenaAutorizacionDetId.getIdOrden())))
					.equals(rowKey)) {
				return cadenaAutorizacionDetDTO;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(CadenaAutorizacionDetDTO cadenaAutorizacionDetDTO) {
		return cadenaAutorizacionDetDTO.getCompositeKey();
	}
}
