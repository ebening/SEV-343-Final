package com.adinfi.seven.persistence.dto;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

public class ActivityModel extends ListDataModel<ActivityDTO> implements
		SelectableDataModel<ActivityDTO>, Serializable {
	public ActivityModel() {
	}

	public ActivityModel(List<ActivityDTO> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActivityDTO getRowData(String arg0) {
		List<ActivityDTO> activities = (List<ActivityDTO>) getWrappedData();

		for (ActivityDTO activityDTO : activities) {
			Long id = Long.valueOf(arg0);
			if (id != null && activityDTO.getId() == id.longValue()) {
				return activityDTO;
			}
		}

		return null;
	}

	@Override
	public Object getRowKey(ActivityDTO activityDTO) {
		return activityDTO.getId();
	}

}
