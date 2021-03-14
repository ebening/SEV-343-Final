package com.adinfi.seven.persistence.daos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.*;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampanaActividades;
import com.adinfi.seven.presentation.views.util.Constants;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class DAOCampanaActividadesImpl extends
		AbstractDaoImpl<TblCampanaActividades> implements DAOCampanaActividades {

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getActivityList(Integer role, Date startDate, Date endDate)
			throws GeneralException {
		Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
				.iterate(" from TblCampanaActividades a where a.activo= 1 and a.idRol= ? " +
						" and a.fechaInicio between ? and ?",
						new Object[]{role, startDate, endDate});
		return toInitializedList(itActivity);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getActivityList(Date filter)
			throws GeneralException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
		String dateFormat = formato.format(filter);
		Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
				.iterate(
						" from TblCampanaActividades a "
								+ "where a.activo= 1 and (to_char(a.fechaInicio, 'YYYYMM') =? OR to_char(a.fechaFin, 'YYYYMM') =?)",
						new Object[] { dateFormat, dateFormat });
		return toInitializedList(itActivity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TblCampanaActividades> getActivityList(Date filter, Integer role)
			throws GeneralException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
		String dateFormat = formato.format(filter);
		Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
				.iterate(
						" from TblCampanaActividades a "
								+ "where a.activo= 1 and (to_char(a.fechaInicio, 'YYYYMM') =? OR to_char(a.fechaFin, 'YYYYMM') =?)"
								+ " a.idRol= ?",
						new Object[] { dateFormat, dateFormat, role });
		return toInitializedList(itActivity);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getNotificationActivityList()
			throws GeneralException {
		Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
				.iterate(" from TblCampanaActividades a where a.esFlujo= 1");
		return toInitializedList(itActivity);
	}

	@Override
	public int deleteCampanaActividadByIdCampana(long idCampana) throws GeneralException {
            String sentence = "delete from TblCampanaActividades campAct where campAct.esFlujo =1 and campAct.tblCampana.idCampana= " + idCampana;
            Query query = getSession().createQuery(sentence);
            return query.executeUpdate();
	}
	
	@Override
	public int deleteCampanaActividadByIdCampanaFull(long idCampana)
			throws GeneralException {
		Query query = this.getSession()
				.createQuery("delete from TblCampanaActividades campAct where campAct.tblCampana.idCampana= " + idCampana);
		return query.executeUpdate();
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getActivityList(Long idCampana, Date startDate, Date endDate)
			throws GeneralException {
	/*	Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
				.iterate(" from TblCampanaActividades a where " + " a.activo= 1 and a.tblCampana.idCampana= ? " +
								" and a.fechaInicio between ? and ?", new Object[]{idCampana, startDate, endDate});
		return toInitializedList(itActivity); */

		Session session = getSessionFactory().getCurrentSession();
		try{
			Criteria criteria = getCriteriaDefault(session);
			criteria.add(Restrictions.eq("a.activo", 1));
			criteria.add(Restrictions.eq("a.tblCampana.idCampana", idCampana));
			criteria.add(Restrictions.between("a.fechaInicio", startDate, endDate));
			return criteria.list();
		}catch(HibernateException ex){
			System.out.println(ex);
			return new ArrayList<>();
		}


	}

	@Override
	@Transactional
	public List<TblCampanaActividades> getActivityListFullAll(){
		Session session = getSessionFactory().getCurrentSession();
		try{
			Criteria criteria = getCriteriaDefault(session);
			return criteria.list();
		}catch(HibernateException ex){
			System.out.println(ex);
			return new ArrayList<>();
		}
	}

	private Criteria getCriteriaDefault(Session session){
		Criteria criteria = session.createCriteria(TblCampanaActividades.class, "a");
		criteria.createAlias("a.tblCampana", "evento", Criteria.LEFT_JOIN);
		criteria.createAlias("a.catEstatus", "estatus", Criteria.LEFT_JOIN);
		//criteria.createAlias("evento.mecanicas", "mecanicas", Criteria.LEFT_JOIN);
		return criteria;
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getActivityList(Long idCampana,
			Integer role, Date startDate, Date endDate) throws GeneralException {
		Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
				.iterate(
						" from TblCampanaActividades a where "
								+ " a.activo= 1 and a.tblCampana.idCampana= ? and a.idRol= ? " +
								" and a.fechaInicio between ? and ?",
						new Object[] { idCampana, role, startDate, endDate });
		return toInitializedList(itActivity);
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getActivityListByIsFlujo(Long idCampana)
			throws GeneralException {
		Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
				.iterate(
						" from TblCampanaActividades a where "
								+ " a.tblCampana.idCampana= ? and a.esFlujo= 1",
						new Object[] { idCampana });
		return toInitializedList(itActivity);
	}

	public List<TblCampanaActividades> getActivityListByRole(Integer role) {
		return null;
	}

	public List<TblCampanaActividades> getActivityListByMonth(Date filter) {
		return null;

	}

	public List<TblCampanaActividades> getActivityListByMonth(Date filter,
			Integer role) {
		return null;

	}

	@Override
	public void editActivity(TblCampanaActividades activity)
			throws GeneralException {
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getActivityListNotif()
			throws GeneralException {
		try {
			Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
					.iterate(
							" from TblCampanaActividades a where a.activo= 1 and a.esFlujo= 1"
									+ " and a.fechaFin>?", new Date());
			return toInitializedList(itActivity);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getActivityListToFinish()
			throws GeneralException {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
			String dateFormat = formato.format(new Date());
			Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
					.iterate(
							" from TblCampanaActividades a where a.activo= 1 and to_char(a.fechaFin, 'YYYYMMDD') =? and a.idActividad!=null and a.estatus != ?",
							new Object[] { dateFormat, Constants.STATUS_CERRADO });
			return toInitializedList(itActivity);
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}

	@SuppressWarnings(Constants.SUPRESS_WARNING_UNCHECKED)
	@Override
	public List<TblCampanaActividades> getActivityList(Date startDate, Date endDate) throws GeneralException {
	/*	Iterator<TblCampanaActividades> itActivity = getHibernateTemplate()
				.iterate(
						" from TblCampanaActividades a where "
								+ " a.activo= 1 and a.fechaInicio between ? and ?",
						new Object[] {startDate, endDate });
		return toInitializedList(itActivity); */

		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Criteria criteria = getCriteriaDefault(session);
			criteria.add(Restrictions.eq("a.activo", 1));
			criteria.add(Restrictions.between("a.fechaInicio", startDate, endDate));
			List<TblCampanaActividades> list = criteria.list();
			tx.commit();
			return list;
		}catch(HibernateException ex){
			System.out.println(ex);
			if (tx != null){
				tx.rollback();
			}
			return new ArrayList<>();
		}
	}
}
