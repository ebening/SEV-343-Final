package com.adinfi.seven.persistence.dto;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.adinfi.seven.business.domain.TblTemplate;

public class TemplateDataModel extends ListDataModel<TblTemplate> implements
		SelectableDataModel<TblTemplate>{

	public TemplateDataModel() {
		
	}
	
	public TemplateDataModel(List<TblTemplate> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TblTemplate getRowData(String rowKey) {

		List<TblTemplate> templates = (List<TblTemplate>) getWrappedData();
		TblTemplate tempalte = null;
		for (TblTemplate temp : templates) {
			Integer templateId = temp.getTemplateId();
			if (templateId.toString().equals(rowKey)) {
				tempalte = temp;
				break;
			}
		}
		return tempalte;
	}

	@Override
	public Object getRowKey(TblTemplate template) {
		return template.getTemplateId();
	}

}
