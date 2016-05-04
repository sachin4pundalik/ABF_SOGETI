package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.OffshorePriceDAO;
import com.sogeti.db.models.OffshorePrice;

@Component("offShorePriceDAO")
public class OffshorePriceDAOImpl extends GenericDaoImpl<OffshorePrice> implements OffshorePriceDAO {

}