package com.sogeti.service;

import java.util.List;

import com.sogeti.db.models.BusinessLine;

public interface BusinessLineService extends GenericService<BusinessLine> {

	public List<BusinessLine> getBusinessLinesForResource(Integer resourcetypeId);
	public List<BusinessLine> getBusinessLinesForResourceAndSkill(Integer resourceTypeId, Integer skillId);
}
