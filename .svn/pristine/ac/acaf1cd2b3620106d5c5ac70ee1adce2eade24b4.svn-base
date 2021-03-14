package com.adinfi.seven.persistence.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;

import com.adinfi.seven.business.domain.CatGZone;
import com.adinfi.seven.business.domain.CatZone;
import com.google.common.base.Joiner;

public class DAOCatGZoneImpl extends AbstractDaoImpl<CatGZone> implements DAOCatGZone {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public CatGZone getCatGZone(Integer idCatGZone) {
		List<CatGZone> l = getHibernateTemplate().find("from CatGZone catGZone where catGZone.idGrupoZona = ?", idCatGZone);
		if(l!=null) return l.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatGZone> getCatGZone(List<Integer> idCatGZone) {
		String in = Joiner.on(",").join(idCatGZone);
		if(!in.isEmpty()){
			return getHibernateTemplate().find("from CatGZone catGZone where catGZone.idGrupoZona in ("+in+") ");
		}
		return null;
	}

    @SuppressWarnings("unchecked")
	@Override
    public Map<Integer, Boolean> hasZoneStores(List<CatZone> catZoneList){
    	Map<Integer, Boolean> r = new HashMap<Integer, Boolean>();
        StringBuilder sb = new StringBuilder();      
        for(CatZone c: catZoneList){
        	if(sb.length()!=0){
        		sb.append(" union ");
        	}
        	sb.append("SELECT ").append(c.getIdZone()).append(" as ID_ZONE, count(1) as total FROM SEV343DEV.REL_ZONE_STORE WHERE ID_ZONE = ").append(c.getIdZone());
        }
        System.out.println(sb);
        if(sb.length()>0){
	        SQLQuery query = this.getSession().createSQLQuery(sb.toString());
	        List<Object[]> result = query.list();
	        if(result != null && !result.isEmpty()){
	        	for(Object[] o: result){
	        		Integer idZone = Integer.valueOf(o[0].toString());
	        		Long count = Long.valueOf(o[1].toString());
	        		r.put(idZone, count>0);
	        		if(count==0){
	        			System.out.println("no se encontraron tiendas asociadas a la zona: "+idZone);
	        		}
	        	}
	            
	        }else{
	            System.out.println("no se encontraron tiendas asociadas a las zonas");
	        }
        }
        return r;
    }

}
