package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.TblPreciosPrensa;
import com.adinfi.seven.business.domain.TblPrensa;

public interface ServicePrecioPrensa {

	TblPrensa getPrensa(int IdPrensa);

	boolean guardarPrecioPrensa(TblPreciosPrensa precioPrensa);

}
