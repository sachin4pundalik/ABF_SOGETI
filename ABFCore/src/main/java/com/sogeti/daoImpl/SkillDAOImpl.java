package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.SkillDAO;
import com.sogeti.db.models.Skill;



@Component("skillDAO")
public class SkillDAOImpl extends GenericDaoImpl<Skill> implements SkillDAO {

}