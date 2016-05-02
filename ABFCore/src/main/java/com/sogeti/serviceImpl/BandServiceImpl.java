package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.BandDAO;
import com.sogeti.db.models.Band;
import com.sogeti.db.models.Skill;
import com.sogeti.service.BandService;

@Service("bandService")
public class BandServiceImpl implements BandService{

	@Autowired
	BandDAO bandDAO;

	public List<Band> findAll() {
		return bandDAO.findAll();
	}

	public void create(Band band) {
		bandDAO.create(band);
	}

	public void delete(Integer bandId) {
		bandDAO.delete(bandId);
		
	}

	public Band find(Integer bandId) {
		return bandDAO.find(bandId);
	}

	public Band update(Band band) {
		return bandDAO.update(band);
	}




}
