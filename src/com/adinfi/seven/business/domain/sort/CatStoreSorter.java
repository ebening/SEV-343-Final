package com.adinfi.seven.business.domain.sort;

import java.util.Comparator;

import com.adinfi.seven.business.domain.CatStore;

public class CatStoreSorter implements Comparator<CatStore> {

	@Override
	public int compare(CatStore o1, CatStore o2) {
		String s1 = o1 != null ? o1.getCode() : null;
		String s2 = o2 != null ? o2.getCode() : null;

		return SortUtils.comparar(s1, s2);
	}

}
