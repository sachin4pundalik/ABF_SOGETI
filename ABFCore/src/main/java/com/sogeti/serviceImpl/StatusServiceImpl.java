package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.StatusDAO;
import com.sogeti.db.models.Status;
import com.sogeti.service.StatusService;

@Service("statusService")
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusDAO statusDAO;

	public List<Status> findAll() {
		return statusDAO.findAll();
	}

	public void create(Status Status) {
		statusDAO.create(Status);
	}

	public void delete(Integer stayTypeId) {
		statusDAO.delete(stayTypeId);
		
	}

	public Status find(Integer statusId) {
		return statusDAO.find(statusId);
	}

	public Status update(Status status) {
		return statusDAO.update(status);
	}




}
