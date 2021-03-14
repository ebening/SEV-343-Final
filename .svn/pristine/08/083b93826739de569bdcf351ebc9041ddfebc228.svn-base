package com.adinfi.seven.persistence.dto;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.business.domain.TblSolicitudAutorizacion;

public class SolicitudAutorizacionDataModel extends
		ListDataModel<TblSolicitudAutorizacion> implements
		SelectableDataModel<TblSolicitudAutorizacion> {

	public SolicitudAutorizacionDataModel() {
	}

	public SolicitudAutorizacionDataModel(List<TblSolicitudAutorizacion> data) {
		super(data);
	}

	@Override
	public TblSolicitudAutorizacion getRowData(String rowKey) {

		@SuppressWarnings("unchecked")
		List<TblSolicitudAutorizacion> solicitudes = (List<TblSolicitudAutorizacion>) getWrappedData();

		for (TblSolicitudAutorizacion solicitud : solicitudes) {
			Long idAut = solicitud.getIdAutorizacion();
			if (idAut.toString().equals(rowKey))
				return solicitud;
		}

		return null;
	}

	@Override
	public Object getRowKey(TblSolicitudAutorizacion car) {
		return car.getIdAutorizacion();
	}
}
