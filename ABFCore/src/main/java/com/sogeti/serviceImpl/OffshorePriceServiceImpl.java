package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.OffshorePriceDAO;
import com.sogeti.db.models.Band;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.OffshorePrice;
import com.sogeti.db.models.StayType;
import com.sogeti.service.OffshorePriceService;

@Service("offShorePriceService")
public class OffshorePriceServiceImpl implements OffshorePriceService{

	@Autowired
	OffshorePriceDAO OffshorePriceDAO;

	public List<OffshorePrice> findAll() {
		return OffshorePriceDAO.findAll();
	}

	public void create(OffshorePrice offshorePrice) {
		OffshorePriceDAO.create(offshorePrice);
	}

	public void delete(Integer stayTypeId) {
		OffshorePriceDAO.delete(stayTypeId);
		
	}

	public OffshorePrice find(Integer stayTypeId) {
		return OffshorePriceDAO.find(stayTypeId);
	}

	public OffshorePrice update(OffshorePrice offshorePrice) {
		return OffshorePriceDAO.update(offshorePrice);
	}
	
	public OffshorePrice getOnShorePriceIdFor(BusinessLine bline, Band band, StayType stayType) throws TechnicalException{
		return OffshorePriceDAO.getOnShorePriceIdFor(bline, band, stayType);
	}

}
