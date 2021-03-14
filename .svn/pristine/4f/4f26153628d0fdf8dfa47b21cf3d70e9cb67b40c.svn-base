package com.adinfi.seven.presentation.views.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;

/**
 * Utilerias para el manejo de Validaciones
 * 
 * @author Miguel Ramirez
 * @date 07/Oct/2013
 * 
 */
public class ValidationUtil {

	/**
	 * Valida fecha para saber si es fin de semana o dia festivo
	 * 
	 * @param fecha
	 *            expresada en tipo Calendar
	 * @param serviceDynamicCatalogs
	 *            implementa catalogo CAT_FESTIVOS
	 * @return true si la fecha es valida para laborar.
	 * @throws Exception
	 */
	public static boolean validateDate(Calendar calendarAValidar,
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		boolean exito = true;

		Map<String, Object> mapDateValues = tomarFechasFestivas(serviceDynamicCatalogs);
		/**
		 * Parameters: Returns: false if this map maps one or more keys to the
		 * specified value
		 */
		if (calendarAValidar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| calendarAValidar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				|| mapDateValues.containsValue(calendarAValidar)) {
			exito = false;
		}

		return exito;
	}

	private static Map<String, Object> tomarFechasFestivas(
			ServiceDynamicCatalogs serviceDynamicCatalogs) throws Exception {
		Map<String, Object> mapDateValues = new HashMap<String, Object>();
		List<AttrSearch> insertSearch = new ArrayList<AttrSearch>();
		try {
			List<CatRegs> regs = serviceDynamicCatalogs.getRegs(
					Constants.CAT_FESTIVOS, insertSearch);
			for (CatRegs reg : regs) {
				Set<CatRegValues> setReg = reg.getRegValues();
				if (setReg != null) {
					for (CatRegValues regVals : setReg) {
						String attName = regVals.getCatAttrs().getAttribs()
								.getAttrName();
						mapDateValues.put(attName, regVals.getValue());
					}
				}
			}
		} catch (GeneralException e) {
			throw new GeneralException(e);
		}
		return mapDateValues;
	}

}
