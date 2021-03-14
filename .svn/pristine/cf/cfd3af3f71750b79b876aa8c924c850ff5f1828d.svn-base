package com.adinfi.seven.business.domain.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.adinfi.seven.presentation.views.util.Constants;

public class SortUtils {

	private SortUtils() {
	}

	/**
	 * Compara dos objetos de los cuales al menos uno es nulo. El orden del
	 * objeto nulo lo determina la constante
	 * {@link Constants#posicionNullsEnOrdenamiento}
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static <T> int ordenDeNulls(T o1, T o2) {
		if (o1 == null) {
			if (o2 == null) {
				return 0;
			}
			switch (Constants.posicionNullsEnOrdenamiento) {
			case NULLS_DESPUES:
				return 1;
			case NULLS_PRIMERO:
			default:
				return -1;
			}
		}

		return 0;
	}

	/**
	 * Ayuda a comparar dos objetos para ordenarlos considerando el tipo de
	 * ordenamiento de nulls default.
	 * 
	 * @param o1
	 * @param o2
	 * 
	 * @return igual que el metodo {@link Comparable#compareTo(Object)}
	 */
	public static <T extends Comparable<T>> int comparar(T o1, T o2) {
		if (o1 == null || o2 == null) {
			return ordenDeNulls(o1, o2);
		}
		else if (o1 instanceof String) {
			return ((String) o1).compareToIgnoreCase((String) o2);
		}
		else {
			return o1.compareTo(o2);
		}
	}

	public static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
		List<T> ordenada = new ArrayList<>(list);
		Collections.sort(list, comparator);
		return ordenada;
	}

}
