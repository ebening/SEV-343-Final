package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.VerTodas;
import com.adinfi.seven.presentation.views.util.Constants;
import edu.emory.mathcs.backport.java.util.Collections;
import java.util.Comparator;

public class DAOVerTodasImpl extends AbstractDaoImpl<VerTodas> implements
		DAOVerTodas {
	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<VerTodas> obtenerTodasOrdenadas() {
            List<VerTodas> vt = getHibernateTemplate().loadAll(VerTodas.class);
            if(!vt.isEmpty()){
                Collections.sort(vt, new Comparator<VerTodas>() {

                    @Override
                    public int compare(VerTodas o1, VerTodas o2) {
                        return o1.getAnio().compareTo(o2.getAnio());
                    }
                });
            }
            return vt;
	}

}
