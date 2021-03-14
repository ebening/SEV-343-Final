package com.adinfi.seven.business.domain.sort;

import java.util.Comparator;

import javax.faces.model.SelectItem;

public class SelectItemSorter implements Comparator<SelectItem> {

	@Override
	public int compare(SelectItem o1, SelectItem o2) {
		String s1 = o1 != null ? o1.getLabel() : null;
		String s2 = o2 != null ? o2.getLabel() : null;

		return SortUtils.comparar(s1, s2);
	}

}
