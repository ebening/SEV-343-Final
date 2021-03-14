package com.adinfi.seven.persistence.dto;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class DelegateModel extends ListDataModel<DelegateDTO> implements
		SelectableDataModel<DelegateDTO> {
	public DelegateModel() {
	}

	public DelegateModel(List<DelegateDTO> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DelegateDTO getRowData(String arg0) {
		List<DelegateDTO> lstDelegate = (List<DelegateDTO>) getWrappedData();

		for (DelegateDTO delegate : lstDelegate) {
			Integer id = Integer.valueOf(arg0);
			if (id != null && delegate.getId().intValue() == id.intValue()) {
				return delegate;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(DelegateDTO delegate) {
		return delegate.getId();
	}
}