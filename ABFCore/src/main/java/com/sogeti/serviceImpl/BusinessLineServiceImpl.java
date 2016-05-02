package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.BusinessLineDAO;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.service.BusinessLineService;

@Service("businessLineService")
public class BusinessLineServiceImpl implements BusinessLineService{

	@Autowired
	BusinessLineDAO businessLineDAO;

	public List<BusinessLine> findAll() {
		return businessLineDAO.findAll();
	}

	public void create(BusinessLine businessLine) {
		businessLineDAO.create(businessLine);
	}

	public void delete(Integer businessLineId) {
		businessLineDAO.delete(businessLineId);
		
	}

	public BusinessLine find(Integer businessLineId) {
		return businessLineDAO.find(businessLineId);
	}

	public BusinessLine update(BusinessLine businessLine) {
		return businessLineDAO.update(businessLine);
	}




}
