package com.adinfi.seven.scheduler;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.adinfi.seven.presentation.views.util.SendMail;

public class NotificationJob implements Job {
	private ServiceCampana serviceCampana;
	private ServiceDynamicCatalogs serviceDynamicCatalogs;
	private Logger LOG = Logger.getLogger(NotificationJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			serviceCampana = (ServiceCampana) context.getJobDetail()
					.getJobDataMap().get("serviceCampana");
			serviceDynamicCatalogs = (ServiceDynamicCatalogs) context
					.getJobDetail().getJobDataMap()
					.get("serviceDynamicCatalogs");
			checkActivities();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void checkActivities() throws GeneralException {
		List<TblCampanaActividades> listResp = serviceCampana
				.getActivityListNotif();
		List<Map<String, String>> frecuencyList = MBUtil.getCatalogValues(
				Constants.CAT_NACTIVITY, serviceDynamicCatalogs);
		if (frecuencyList != null && listResp != null) {
			for (Map<String, String> frecuency : frecuencyList) {
				String frecuencyType = frecuency.get(Constants.ATT_FRECUENCY);
				String frecuencyQuantity = frecuency
						.get(Constants.ATT_QUANTITY);
				Double frecuencyQty = Double.valueOf(frecuencyQuantity);
				for (TblCampanaActividades activity : listResp) {
					long dateDiff = MBUtil.dateDiff(new Date(),
							activity.getFechaFin(), Constants.MILLISECS_PER_HR);
					if (frecuencyType
							.compareToIgnoreCase(Constants.FRECUENCY_DAY) == 0) {
						frecuencyQty = frecuencyQty.doubleValue()
								* Constants.FRECUENCY_DAY_CONV;
					} else if (frecuencyType
							.compareToIgnoreCase(Constants.FRECUENCY_HRS) == 0) {
						frecuencyQty = frecuencyQty.doubleValue()
								* Constants.FRECUENCY_HRS_CONV;
					} else {
						LOG.error("Frecuencia no reconocida: " + frecuencyType);
						break;
					}
					LOG.error(frecuencyType);
					LOG.error(dateDiff);
					LOG.error(frecuencyQty);
					if (frecuencyQty.longValue() == dateDiff) {
						String email = MBUtil.genericSearchJob(
								Constants.ATT_ID,
								String.valueOf(activity.getIdUsuarioResp()),
								Constants.ATT_EMAIL, Constants.CAT_USER,
								serviceDynamicCatalogs);
						SendMail.sendGenericEmail(email,
								Constants.NOTIF_SUBJECT, Constants.NOTIF_BODY);
					}
				}
			}
		}
	}
}
