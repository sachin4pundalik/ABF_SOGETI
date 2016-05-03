package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.OnshorePriceDAO;
import com.sogeti.db.models.OnshorePrice;

@Component("onshorePriceDAO")
public class OnshorePriceDAOImpl extends GenericDaoImpl<OnshorePrice> implements OnshorePriceDAO {

}