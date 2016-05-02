package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.StayTypeDAO;
import com.sogeti.db.models.StayType;

@Component("stayTypeDAO")
public class StayTypeDAOImpl extends GenericDaoImpl<StayType> implements StayTypeDAO {

}