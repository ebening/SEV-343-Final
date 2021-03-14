package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.CampaniaTreeRegs;
import com.adinfi.seven.presentation.views.util.Constants;

public class DAOCampaniaTreeRegsImpl extends AbstractDaoImpl<CampaniaTreeRegs>
		implements DAOCampaniaTreeRegs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<CampaniaTreeRegs> todasOrdenada() {
		return getHibernateTemplate().find("from CampaniaTreeRegs order by tipoCampania, anio");
	}
}
