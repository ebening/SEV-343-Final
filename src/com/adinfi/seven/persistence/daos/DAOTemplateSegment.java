package com.adinfi.seven.persistence.daos;

import com.adinfi.seven.business.domain.TblTemplateSegments;

public interface DAOTemplateSegment extends AbstractDao<TblTemplateSegments> {

	TblTemplateSegments saveTemplateSegment(TblTemplateSegments segment);

	boolean deleteTemplateSegment(TblTemplateSegments segment);

}
