package com.sogeti.dao;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.Grade;
import com.sogeti.db.models.OnshorePrice;
import com.sogeti.db.models.Role;

public interface OnshorePriceDAO  extends GenericDao<OnshorePrice>{
	
	public OnshorePrice getOnShorePriceIdFor(BusinessLine bline, Role role, Grade grade) throws TechnicalException;

}
