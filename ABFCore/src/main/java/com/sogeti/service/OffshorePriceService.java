package com.sogeti.service;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.Band;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.OffshorePrice;
import com.sogeti.db.models.StayType;

public interface OffshorePriceService  extends GenericService<OffshorePrice>{
	
	public OffshorePrice getOnShorePriceIdFor(BusinessLine bline, Band band, StayType stayType) throws TechnicalException;

}
