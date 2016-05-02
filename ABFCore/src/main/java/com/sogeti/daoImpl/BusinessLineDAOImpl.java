package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.BusinessLineDAO;
import com.sogeti.db.models.BusinessLine;



@Component("businessLineDAO")
public class BusinessLineDAOImpl extends GenericDaoImpl<BusinessLine> implements BusinessLineDAO {

}