package com.adinfi.seven.presentation.views.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.persistence.dto.ReporteArticulo;

public class ReporteArticuloDataModel extends ListDataModel<ReporteArticulo>
		implements SelectableDataModel<ReporteArticulo>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 923091050994771693L;

	public ReporteArticuloDataModel() {
	}

	public ReporteArticuloDataModel(List<ReporteArticulo> data) {
		super(data);
	}

	@Override
	public ReporteArticulo getRowData(String rowKey) {

		List<ReporteArticulo> articulos = (List<ReporteArticulo>) getWrappedData();
		ReporteArticulo reporte = null;
		for (ReporteArticulo articulo : articulos) {
			Long idArticulo = Long.valueOf(articulo.getSku());
			if (idArticulo.toString().equals(rowKey)) {
				reporte = articulo;
				break;
			}
		}
		return reporte;
	}

	@Override
	public Object getRowKey(ReporteArticulo reporte) {
		return reporte;
	}
}
