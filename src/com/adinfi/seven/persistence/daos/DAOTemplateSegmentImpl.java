package com.adinfi.seven.persistence.daos;

import org.apache.log4j.Logger;

import com.adinfi.seven.business.domain.TblTemplateSegments;

public class DAOTemplateSegmentImpl extends AbstractDaoImpl<TblTemplateSegments> implements DAOTemplateSegment {
	
	private Logger LOG = Logger.getLogger(DAOTemplateSegmentImpl.class);
	
	@Override
	public TblTemplateSegments saveTemplateSegment(TblTemplateSegments segment){
		try {
			saveOrUpdate(segment);
			this.flush();
		} catch (Exception e) {
			LOG.error(e);
		}
		return segment;
	}
	
	@Override
	public boolean deleteTemplateSegment(TblTemplateSegments segment){
		boolean retVal=true;
		try {
			delete(segment);
		} catch (Exception e) {
			LOG.error(e);
			retVal=false;
		}
		return retVal;
	}
}
