package com.adinfi.seven.persistence.daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;
import org.springframework.dao.DataAccessResourceFailureException;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampana;
import com.adinfi.seven.business.domain.TblCampanaMedio;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.persistence.dto.CampanaResultDTO;
import com.adinfi.seven.persistence.dto.PeriodoDTO;

public class DAOCampanaImpl extends AbstractDaoImpl<TblCampana> implements DAOCampana {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;

    @Override
	public boolean deleteAllCampanaProgramas (long idCampana){
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM TblCampanaProgramas p WHERE p.id.idCampana = ?");
            query.setLong(0, idCampana);
            query.executeUpdate();
            tx.commit();
            return true;
        }catch (HibernateException ex){
            System.out.println(ex);
            if(tx != null){
                tx.rollback();
            }
            return false;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampana> getAllCampanaByDashboardFilters(String periodo, int tipo, int estatus, Date fechaInicio, Date fechaFin, int idPeriodo, int ano) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Map<String, String> filters = new HashMap<String, String>();
			filters.put("loadStatus", "true");
			filters.put("loadCampanaProgramas", "true");

			Criteria criteria = getDefaultCriteria(session, filters);
			if (periodo != null && !periodo.isEmpty()){
                criteria.add(Restrictions.like("evento.nombre", "%"+periodo+"%"));
            }
            if (tipo > 0){
                criteria.add(Restrictions.eq("evento.idTipoCampana", tipo));
            }
            if (estatus > 0){
                criteria.add(Restrictions.eq("evento.catEstatus.idestatus", estatus));
            }
            if (idPeriodo > 0){
                criteria.add(Restrictions.eq("evento.idPeriodo", idPeriodo));
            }else if (fechaInicio != null && fechaFin != null){
                criteria.add(Restrictions.between("evento.fechaInicio", fechaInicio, fechaFin));
				criteria.add(Restrictions.between("evento.fechaFin", fechaInicio, fechaFin));
            }else{
				criteria.add(Restrictions.sqlRestriction("YEAR(this_.FECHA_INICIO) = " + ano));
			}
            List<TblCampana> list = criteria.list();
            tx.commit();
            return list;
		}catch (HibernateException ex){
            ex.printStackTrace();
            if(tx != null){
                tx.rollback();
            }
            return new ArrayList<>();
		}
	}

	@Override
	public TblCampana getCampana(long idCampana, Map<String, String> filters) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		TblCampana result = null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = getDefaultCriteria(session, filters);
			criteria.add(Restrictions.eq("evento.idCampana", idCampana));
			result = (TblCampana) criteria.uniqueResult();
			tx.commit();
		} catch (HibernateException ex){
			ex.printStackTrace();
			if (tx != null){
				tx.rollback();
			}
		}
		return result;
	}

	public int countCampanaActividadByIdCampana(long idCampana) throws GeneralException {
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampanaMedio> getMedioByCampanaId(long idCampana) {
		Iterator<TblCampanaMedio> tblCampanaIterator = getHibernateTemplate()
				.iterate(
						"from TblCampanaMedio campMedio  where campMedio.tblCampana.idCampana = ? ",
						new Object[] { idCampana });
		return toInitializedList(tblCampanaIterator);
	}


	@SuppressWarnings("unchecked")
	@Override
	public CampanaResultDTO getCampanas(Integer firstRecord, Integer rowsPerPage, Map<String, String> filters){
		Long time = System.currentTimeMillis();
		CampanaResultDTO r = new CampanaResultDTO();
		Transaction tx = null;
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Integer total = null;
			if(firstRecord!=null){
				String getCount = null;
				if(filters!=null){
					getCount = filters.get("getCount");
				}
				if(getCount==null || getCount.equals("true")){
					total = getCountCampanas(filters);
					System.out.println("Total de campanas: "+total);
					r.setTotal(total);
				}
			}
			
			Criteria criteria = getDefaultCriteria(session, filters);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.addOrder(Order.asc("evento.fechaInicio"));
			
			if(firstRecord!=null){
				List<Long> ids = null;
				if(filters!=null){
					if(filters.get("idCampana")!=null){
						ids = new ArrayList<Long>();
						ids.add(Long.valueOf(filters.get("idCampana")));
					}
				}
				if(ids==null){
					Criteria c = session.createCriteria(TblCampana.class, "campana");
					addFilters(c, filters);
			        ids = c.setProjection(Projections.property("idCampana"))
			        		.addOrder(Order.asc("fechaInicio"))
			        		.setFirstResult(firstRecord)
			        		.setMaxResults(rowsPerPage)
			        		.list();
				}
		        System.out.println(ids);
		        if(ids.isEmpty()){
		        	ids.add(-1l);
		        }
		        criteria.add(Restrictions.in("evento.idCampana", ids));
			}
			
			List<TblCampana> list = criteria.list();
			r.setCampanas(list);
			if(r.getTotal()==null) r.setTotal(list.size());
			tx.commit();
			System.out.println("getAllCampanasOrderByYearAsc: "+(System.currentTimeMillis()-time));
			return r;
		}catch (HibernateException ex){
			ex.printStackTrace();
			r.setCampanas(new ArrayList<TblCampana>());
			if(tx != null){
				tx.rollback();
			}
			return r;
		}
	}
	
	private Integer getCountCampanas(Map<String, String> filters){
		Criteria c = this.getSession().createCriteria(TblCampana.class);
		addFilters(c, filters);
		return (Integer) c.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void addFilters(Criteria criteria, Map<String, String> filters){
		if(filters!=null){ 
			if(filters.get("globalFilter")!=null){
				criteria.add(Restrictions.like("nombre","%"+filters.get("globalFilter")+"%").ignoreCase());
			}
		}
	}
	
	private Criteria getDefaultCriteria(Session session, Map<String, String> filters){
		Criteria criteria = session.createCriteria(TblCampana.class,"evento");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("evento.catEtiquetas", "etiquetas", Criteria.LEFT_JOIN);
		if(filters!=null){
			if("true".equals(filters.get("loadCatFlujoAct"))) criteria.createAlias("evento.catFlujoAct", "flujo", Criteria.LEFT_JOIN);
			if("true".equals(filters.get("loadStatus"))) criteria.createAlias("evento.catEstatus", "estatus", Criteria.LEFT_JOIN);
			if("true".equals(filters.get("loadCampanaProgramas"))) criteria.createAlias("evento.tblCampanaProgramas", "progs", Criteria.LEFT_JOIN);
			if("true".equals(filters.get("loadCampanaProgramasPrograma"))) criteria.createAlias("progs.programa", "p", Criteria.LEFT_JOIN);
			if("true".equals(filters.get("loadCampanaProgramasCategorias"))) criteria.createAlias("progs.tblCampanaProgramasCategorias", "progsCategs", Criteria.LEFT_JOIN);
			if("true".equals(filters.get("loadGrupoZonas"))) criteria.createAlias("progs.grupoZonas", "progsGZonas", Criteria.LEFT_JOIN);
			if("true".equals(filters.get("loadCampanaCategorias"))) criteria.createAlias("evento.tblCampanaCategoriases", "categs", Criteria.LEFT_JOIN);
		}
		return  criteria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampana> getAllCampanasByYearAsc(int year, List<PeriodoDTO> periodos) {
		try {
			List<Integer> periodosValidos = new ArrayList<>();
			for (PeriodoDTO p : periodos) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(p.getFechaFinal());
				if (cal.get(Calendar.YEAR) == year) {
					periodosValidos.add(p.getId());
				}
			}
			
			Query query = getSession()
					.createQuery("from TblCampana where (idTipoCampana = 3 and year(fechaFin) = :anio) or (idPeriodo in (:periodos))  order by year(fechaInicio) asc");
			query.setParameterList("periodos", periodosValidos);
			query.setParameter("anio", year);
			return toInitializedList(query.iterate());
		} catch (DataAccessResourceFailureException | IllegalStateException | HibernateException e) {
                    System.out.println(e);
		}
		
		return Collections.emptyList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampana> getListCampanaDTOByYearAndTypeCampana(int year,
			int typeCampana, List<PeriodoDTO> periodos) {
		try {
			List<Integer> periodosValidos = new ArrayList<>();
			for (PeriodoDTO p : periodos) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(p.getFechaFinal());
				if (cal.get(Calendar.YEAR) == year) {
					periodosValidos.add(p.getId());
				}
			}
			
			Query query = getSession()
					.createQuery("FROM TblCampana WHERE idTipoCampana = :tipoCampana AND ((idTipoCampana = 3 AND year(fechaFin) = :anio) OR (idPeriodo IN (:periodos))) ORDER BY YEAR(fechaInicio) ASC");
			query.setParameterList("periodos", periodosValidos);
			query.setParameter("anio", year);
			query.setParameter("tipoCampana", typeCampana);
			return toInitializedList(query.iterate());
		} catch (DataAccessResourceFailureException | IllegalStateException | HibernateException e) {
                    System.out.println(e);
		}
		
		return Collections.emptyList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampana> getAllCampanaByTypeCampana(int typeCampana) {
		Iterator<TblCampana> tblCampanaIterator = getHibernateTemplate()
				.iterate("from TblCampana  where  idTipoCampana = ? order by year(fechaInicio) asc",new  Object[] { typeCampana });
		return toInitializedList(tblCampanaIterator);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampana> getCampanaByName(String name) {
		Iterator<TblCampana> tblCampanaIterator = getHibernateTemplate()
				.iterate("from TblCampana  where  nombre = ? order by year(fechaInicio) asc",new  Object[] { name });
		return toInitializedList(tblCampanaIterator);
	}

	public ServiceDynamicCatalogs getServiceDynamicCatalogs() {
		return serviceDynamicCatalogs;
	}

	public void setServiceDynamicCatalogs(ServiceDynamicCatalogs serviceDynamicCatalogs) {
		this.serviceDynamicCatalogs = serviceDynamicCatalogs;
	}
	
	@Override
	public Boolean campanaExists(Integer year, Integer tipoCampana, String nombre) {
		String schema = ((SessionFactoryImplementor)getHibernateTemplate().getSessionFactory()).getSettings().getDefaultSchemaName();
		String query = "select count(1) from "
				+ schema+".TBL_CAMPANA where lcase(nombre)=lcase('"+nombre+"') "
				+ "and id_tipo_campana = "+tipoCampana+" "
				+ "and FECHA_INICIO>='"+year+"-01-01 00:00:00' "
				+ "and FECHA_INICIO<='"+year+"-12-31 23:59:59' ";
		String count = getSession().createSQLQuery(query).uniqueResult().toString();
		if(Long.parseLong(count)>0) return true;
		return false;
	}
	
}
