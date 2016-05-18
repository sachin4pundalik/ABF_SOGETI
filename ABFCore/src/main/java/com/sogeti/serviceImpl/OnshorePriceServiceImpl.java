package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.OnshorePriceDAO;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.Grade;
import com.sogeti.db.models.OnshorePrice;
import com.sogeti.db.models.Role;
import com.sogeti.service.OnshorePriceService;

@Service("onshorePriceService")
public class OnshorePriceServiceImpl implements OnshorePriceService{

	@Autowired
	OnshorePriceDAO onshorePriceDAO;

	public List<OnshorePrice> findAll() {
		return onshorePriceDAO.findAll();
	}

	public void create(OnshorePrice OnshorePrice) {
		onshorePriceDAO.create(OnshorePrice);
	}

	public void delete(Integer stayTypeId) {
		onshorePriceDAO.delete(stayTypeId);
		
	}

	public OnshorePrice find(Integer stayTypeId) {
		return onshorePriceDAO.find(stayTypeId);
	}

	public OnshorePrice update(OnshorePrice OnshorePrice) {
		return onshorePriceDAO.update(OnshorePrice);
	}
	
	public OnshorePrice getOnShorePriceIdFor(BusinessLine bline, Role role, Grade grade) throws TechnicalException{
		return onshorePriceDAO.getOnShorePriceIdFor(bline, role, grade);
	}
}
