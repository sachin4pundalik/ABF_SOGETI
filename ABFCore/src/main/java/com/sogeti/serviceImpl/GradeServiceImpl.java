package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.GradeDAO;
import com.sogeti.db.models.Grade;
import com.sogeti.service.GradeService;

@Service("gradeService")
public class GradeServiceImpl implements GradeService{

	@Autowired
	GradeDAO gradeDAO;

	public List<Grade> findAll() {
		return gradeDAO.findAll();
	}

	public void create(Grade grade) {
		gradeDAO.create(grade);
	}

	public void delete(Integer gradeId) {
		gradeDAO.delete(gradeId);
		
	}

	public Grade find(Integer gradeId) {
		return gradeDAO.find(gradeId);
	}

	public Grade update(Grade grade) {
		return gradeDAO.update(grade);
	}




}
