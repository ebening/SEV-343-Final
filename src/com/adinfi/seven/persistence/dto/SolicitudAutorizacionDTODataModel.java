package com.adinfi.seven.persistence.dto;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class SolicitudAutorizacionDTODataModel extends
		ListDataModel<SolicitudAutorizacionDTO> implements
		SelectableDataModel<SolicitudAutorizacionDTO> {

	public SolicitudAutorizacionDTODataModel() {
	}

	public SolicitudAutorizacionDTODataModel(List<SolicitudAutorizacionDTO> data) {
		super(data);
	}

	@Override
	public SolicitudAutorizacionDTO getRowData(String rowKey) {

		@SuppressWarnings("unchecked")
		List<SolicitudAutorizacionDTO> solicitudes = (List<SolicitudAutorizacionDTO>) getWrappedData();

		for (SolicitudAutorizacionDTO solicitud : solicitudes) {
			Long idAut = solicitud.tblSolicitudAutorizacion.getIdAutorizacion();
			if (idAut.toString().equals(rowKey))
				return solicitud;
		}

		return null;
	}

	@Override
	public Object getRowKey(SolicitudAutorizacionDTO solicitudAutorizacionDTO) {
		return solicitudAutorizacionDTO.getTblSolicitudAutorizacion()
				.getIdAutorizacion();
	}
}
