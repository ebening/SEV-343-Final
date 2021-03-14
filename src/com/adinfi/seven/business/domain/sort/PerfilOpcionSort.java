package com.adinfi.seven.business.domain.sort;

import java.util.Comparator;

import com.adinfi.seven.business.domain.PerfilOpcion;

public class PerfilOpcionSort implements Comparator<PerfilOpcion> {
	public int compare(PerfilOpcion o1, PerfilOpcion o2) {
		if (o1.getOpcion().getParentId() < o2.getOpcion().getParentId()) {
			return -1;
		} else if (o1.getOpcion().getParentId() > o2.getOpcion().getParentId()) {
			return 1;
		} else {
			if (o1.getOpcion().getOrderSort() < o2.getOpcion().getOrderSort()) {
				return -1;
			} else {
				return 1;
			}
		}

	}
}