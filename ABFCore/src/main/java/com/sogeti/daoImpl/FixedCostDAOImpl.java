package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.FixedCostDAO;
import com.sogeti.db.models.FixedCost;

@Component("fixedCostDAO")
public class FixedCostDAOImpl extends GenericDaoImpl<FixedCost> implements FixedCostDAO {

}