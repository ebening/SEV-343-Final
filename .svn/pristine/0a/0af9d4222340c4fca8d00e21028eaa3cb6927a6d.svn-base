package com.adinfi.seven.presentation.views.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.persistence.dto.ReporteArticuloSinFotoDescripcion;

public class ReporteArticulosSinFotoDescripcionDataModel extends ListDataModel<ReporteArticuloSinFotoDescripcion>
		implements SelectableDataModel<ReporteArticuloSinFotoDescripcion>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 923091050994771693L;

	public ReporteArticulosSinFotoDescripcionDataModel() {
	}

	public ReporteArticulosSinFotoDescripcionDataModel(List<ReporteArticuloSinFotoDescripcion> data) {
		super(data);
	}

	@Override
	public ReporteArticuloSinFotoDescripcion getRowData(String rowKey) {

		List<ReporteArticuloSinFotoDescripcion> articulos = (List<ReporteArticuloSinFotoDescripcion>) getWrappedData();
		ReporteArticuloSinFotoDescripcion reporte = null;
		for (ReporteArticuloSinFotoDescripcion articulo : articulos) {
			Long idArticulo = Long.valueOf(articulo.getSku());
			if (idArticulo.toString().equals(rowKey)) {
				reporte = articulo;
				break;
			}
		}
		return reporte;
	}

	@Override
	public Object getRowKey(ReporteArticuloSinFotoDescripcion reporte) {
		return reporte;
	}
}
