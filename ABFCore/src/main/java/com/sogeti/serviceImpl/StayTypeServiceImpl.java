package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.StayTypeDAO;
import com.sogeti.db.models.StayType;
import com.sogeti.service.StayTypeService;

@Service("stayTypeService")
public class StayTypeServiceImpl implements StayTypeService{

	@Autowired
	StayTypeDAO staytypeDAO;

	public List<StayType> findAll() {
		return staytypeDAO.findAll();
	}

	public void create(StayType StayType) {
		staytypeDAO.create(StayType);
	}

	public void delete(Integer stayTypeId) {
		staytypeDAO.delete(stayTypeId);
		
	}

	public StayType find(Integer stayTypeId) {
		return staytypeDAO.find(stayTypeId);
	}

	public StayType update(StayType StayType) {
		return staytypeDAO.update(StayType);
	}




}
