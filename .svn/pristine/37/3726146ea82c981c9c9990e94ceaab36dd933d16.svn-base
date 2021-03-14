package com.adinfi.seven.persistence.dto;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class TemplateUserDataModel extends ListDataModel<TemplateUserDTO> implements
		SelectableDataModel<TemplateUserDTO>{
	public TemplateUserDataModel() {
		
	}
	
	public TemplateUserDataModel(List<TemplateUserDTO> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TemplateUserDTO getRowData(String rowKey) {

		List<TemplateUserDTO> templates = (List<TemplateUserDTO>) getWrappedData();
		TemplateUserDTO tempalte = null;
		for (TemplateUserDTO temp : templates) {
			Integer templateId = temp.getIdTemplateUser();
			if (templateId.toString().equals(rowKey)) {
				tempalte = temp;
				break;
			}
		}
		return tempalte;
	}

	@Override
	public Object getRowKey(TemplateUserDTO template) {
		return template.getIdTemplateUser();
	}
}
