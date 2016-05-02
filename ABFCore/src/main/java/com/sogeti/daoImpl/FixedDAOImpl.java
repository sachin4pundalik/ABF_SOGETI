package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.FixedDAO;
import com.sogeti.db.models.FixedContract;

@Component("fixedDAO")
public class FixedDAOImpl extends GenericDaoImpl<FixedContract> implements FixedDAO {

}