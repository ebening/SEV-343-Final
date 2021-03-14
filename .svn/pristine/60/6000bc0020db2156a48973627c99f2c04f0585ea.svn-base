/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adinfi.seven.persistence.daos;

import java.util.List;

import com.adinfi.seven.business.domain.TblComponenteZonaPrecio;

/**
 *
 * @author joseg
 */
public interface DAOTblComponenteZonaPrecio extends AbstractDao<TblComponenteZonaPrecio>{
    
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(int componentId, int zoneId);
    public void deleteComponenteZonaByComponenteId(int componenteId);
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(List<Object[]> list);
    public List<TblComponenteZonaPrecio> getByComponentIdAndZoneId(String componentList, String zoneList);
    public void save(List<TblComponenteZonaPrecio> list);
}
