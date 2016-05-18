package com.sogeti.dao;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.Band;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.OffshorePrice;
import com.sogeti.db.models.StayType;

public interface OffshorePriceDAO  extends GenericDao<OffshorePrice>{
	public OffshorePrice getOnShorePriceIdFor(BusinessLine bline, Band band, StayType stayType) throws TechnicalException;

}
