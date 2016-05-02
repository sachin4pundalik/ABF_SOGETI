package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.BandDAO;
import com.sogeti.db.models.Band;



@Component("bandDAO")
public class BandDAOImpl extends GenericDaoImpl<Band> implements BandDAO {

}