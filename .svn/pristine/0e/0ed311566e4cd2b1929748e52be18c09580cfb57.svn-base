package com.adinfi.seven.business.domain.sort;

import java.util.Comparator;

import com.adinfi.seven.business.domain.CatGZone;

public class CatGZoneSorter implements Comparator<CatGZone> {

	@Override
	public int compare(CatGZone o1, CatGZone o2) {
		String s1 = o1 != null ? o1.getCode() : null;
		String s2 = o2 != null ? o2.getCode() : null;

		return SortUtils.comparar(s1, s2);
	}

}
