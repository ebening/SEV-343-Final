package com.adinfi.seven.business.domain.sort;

import java.util.Comparator;

import com.adinfi.seven.business.domain.CatPrograma;

public class CatProgramaSorter implements Comparator<CatPrograma> {

	@Override
	public int compare(CatPrograma o1, CatPrograma o2) {
		String s1 = o1 != null ? o1.getNombre() : null;
		String s2 = o2 != null ? o2.getNombre() : null;

		return SortUtils.comparar(s1, s2);
	}

}
