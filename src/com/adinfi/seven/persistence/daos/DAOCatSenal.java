package com.adinfi.seven.persistence.daos;

import java.util.List;
import com.adinfi.seven.business.domain.CatSenal;

public interface DAOCatSenal extends AbstractDao<CatSenal> {
	
	CatSenal getCatSenal(Integer idSenal);
	
	List<CatSenal> getCatSenales();

}
