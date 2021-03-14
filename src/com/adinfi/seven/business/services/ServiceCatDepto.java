package com.adinfi.seven.business.services;

import com.adinfi.seven.business.domain.CatDepto;
import java.util.List;

public interface ServiceCatDepto {
    
    boolean crearCatDepto(CatDepto catDepto);
    
    boolean eliminarCatDepto(CatDepto catDepto);
    
    List<CatDepto> getCatDeptoList() throws Exception;
    
    CatDepto getCatDeptoById(CatDepto catDepto);
    
}
