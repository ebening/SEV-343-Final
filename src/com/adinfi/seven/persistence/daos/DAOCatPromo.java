package com.adinfi.seven.persistence.daos;

import java.util.List;
import com.adinfi.seven.business.domain.CatPromo;

public interface DAOCatPromo extends AbstractDao<CatPromo> {
	
	CatPromo getCatPromo(int idCatPromo);
	
	List<CatPromo> getCatPromos();
	
	List<CatPromo> getCatPromosByTipoPromo(int idTipoPromo);

}
