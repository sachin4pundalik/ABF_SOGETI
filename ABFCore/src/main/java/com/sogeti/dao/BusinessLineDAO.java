package com.sogeti.dao;

import java.util.List;

import com.sogeti.db.models.BusinessLine;

public interface BusinessLineDAO extends GenericDao<BusinessLine> {

	public List<BusinessLine> getBusinessLinesForResource(Integer resourceId);
	public List<BusinessLine> getBusinessLinesForResourceAndSkill(Integer resourceId,Integer skillId);
}
