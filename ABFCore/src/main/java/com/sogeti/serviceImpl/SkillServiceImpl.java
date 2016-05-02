package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.SkillDAO;
import com.sogeti.db.models.Skill;
import com.sogeti.service.SkillService;

@Service("skillService")
public class SkillServiceImpl implements SkillService{

	@Autowired
	SkillDAO skillDAO;

	public List<Skill> findAll() {
		return skillDAO.findAll();
	}

	public void create(Skill skill) {
		skillDAO.create(skill);
	}

	public void delete(Integer skillId) {
		skillDAO.delete(skillId);
		
	}

	public Skill find(Integer skillId) {
		return skillDAO.find(skillId);
	}

	public Skill update(Skill skill) {
		return skillDAO.update(skill);
	}




}
