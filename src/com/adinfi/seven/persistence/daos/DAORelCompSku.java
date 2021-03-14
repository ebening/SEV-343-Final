package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.RelCompSku;

public interface DAORelCompSku extends AbstractDao<RelCompSku> {

	void deleteByComponenteId(int componenteId);
	public void deleteByComponenteId(List<Integer> ids);
	public void save(List<RelCompSku> list);
}
