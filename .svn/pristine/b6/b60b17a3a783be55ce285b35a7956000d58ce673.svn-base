package com.adinfi.seven.scheduler;

import java.util.List;

import com.adinfi.seven.business.services.ServiceCatEstatus;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.adinfi.catalogs.bos.exception.catalogs.GeneralException;
import com.adinfi.seven.business.domain.TblCampanaActividades;
import com.adinfi.seven.business.services.ServiceCampana;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.adinfi.seven.presentation.views.util.Constants;
import com.adinfi.seven.presentation.views.util.MBUtil;

public class FinishActivityJob implements Job {
	private ServiceCampana serviceCampana;
	private ServiceCatEstatus serviceCatEstatus;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private Logger LOG = Logger.getLogger(FinishActivityJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			serviceCampana = (ServiceCampana) context.getJobDetail()
					.getJobDataMap().get("serviceCampana");
			serviceDynamicCatalogs = (ServiceDynamicCatalogs) context
					.getJobDetail().getJobDataMap()
					.get("serviceDynamicCatalogs");
			serviceCatEstatus = (ServiceCatEstatus) context.getJobDetail().getJobDataMap().get("serviceCatEstatus");
			finishActivity();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void finishActivity() throws GeneralException {
		List<TblCampanaActividades> listResp = serviceCampana
				.getActivityListToFinish();
		for (TblCampanaActividades activity : listResp) {
			String cierraAuto = MBUtil.genericSearchJob(Constants.ATT_ID,
					String.valueOf(activity.getIdActividad()),
					Constants.ATT_CLOSEABLE, Constants.CAT_ACTIVITY,
					serviceDynamicCatalogs);
			Boolean bCierraAuto = Boolean.valueOf(cierraAuto);
			if (bCierraAuto) {
				activity.setCatEstatus(serviceCatEstatus.getEstatusById(Constants.STATUS_CERRADO));
				serviceCampana.saveActivity(activity);
			}
		}
	}
}
