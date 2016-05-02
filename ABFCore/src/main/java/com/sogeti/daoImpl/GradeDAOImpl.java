package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.GradeDAO;
import com.sogeti.db.models.Grade;



@Component("gradeDAO")
public class GradeDAOImpl extends GenericDaoImpl<Grade> implements GradeDAO {

}