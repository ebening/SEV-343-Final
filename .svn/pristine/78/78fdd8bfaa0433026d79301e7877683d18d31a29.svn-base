package com.adinfi.seven.persistence.dto;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class CategoriaDTODataModel extends ListDataModel<CategoriaDTO>
		implements SelectableDataModel<CategoriaDTO> {

	public CategoriaDTODataModel() {
	}

	public CategoriaDTODataModel(List<CategoriaDTO> data) {
		super(data);
	}

	@Override
	public CategoriaDTO getRowData(String rowKey) {

		@SuppressWarnings("unchecked")
		List<CategoriaDTO> categorias = (List<CategoriaDTO>) getWrappedData();

		for (CategoriaDTO categoriaDTO : categorias) {
			Integer idCat = categoriaDTO.getId();
			if (idCat.toString().equals(rowKey))
				return categoriaDTO;
		}

		return null;
	}

	@Override
	public Object getRowKey(CategoriaDTO categoria) {
		return categoria.getId();
	}
}