package com.adinfi.seven.persistence.daos;

import java.util.Date;
import java.util.List;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampanaActividades;

public interface DAOCampanaActividades extends
		AbstractDao<TblCampanaActividades> {
	List<TblCampanaActividades> getActivityList(Integer role, Date startDate, Date endDate)
			throws GeneralException;

	List<TblCampanaActividades> getActivityList(Date filter)
			throws GeneralException;

	List<TblCampanaActividades> getActivityList(Date filter, Integer role)
			throws GeneralException;

	List<TblCampanaActividades> getActivityList(Long idCampana, Date startDate, Date endDate)
			throws GeneralException;

	List<TblCampanaActividades> getActivityList(Long idCampana, Integer role, Date startDate, Date endDate)
			throws GeneralException;

	List<TblCampanaActividades> getActivityListFullAll();

	List<TblCampanaActividades> getNotificationActivityList()
			throws GeneralException;

	int deleteCampanaActividadByIdCampana(long idCampana)
			throws GeneralException;

	List<TblCampanaActividades> getActivityListByRole(Integer role);

	List<TblCampanaActividades> getActivityListByMonth(Date filter);

	List<TblCampanaActividades> getActivityListByMonth(Date filter, Integer role);

	List<TblCampanaActividades> getActivityListByIsFlujo(Long idCampana)
			throws GeneralException;

	void editActivity(TblCampanaActividades activity) throws GeneralException;

	List<TblCampanaActividades> getActivityListNotif() throws GeneralException;

	List<TblCampanaActividades> getActivityListToFinish()
			throws GeneralException;

	List<TblCampanaActividades> getActivityList(Date startDate, Date endDate) throws GeneralException;

	int deleteCampanaActividadByIdCampanaFull(long idCampana)
			throws GeneralException;
}