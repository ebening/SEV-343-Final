package com.adinfi.seven.persistence.dto;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class BitacoraModel extends ListDataModel<BitacoraDTO> implements
		SelectableDataModel<BitacoraDTO> {
	public BitacoraModel() {
	}

	public BitacoraModel(List<BitacoraDTO> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BitacoraDTO getRowData(String arg0) {
		List<BitacoraDTO> bitacoraList = (List<BitacoraDTO>) getWrappedData();

		for (BitacoraDTO bitacora : bitacoraList) {
			if (bitacora.getBitacoraId() == Integer.valueOf(arg0).intValue()) {
				return bitacora;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(BitacoraDTO bitacora) {
		return bitacora.getBitacoraId();
	}

}
