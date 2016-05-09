package com.sogeti.dao;

import java.util.List;

import com.sogeti.db.models.FixedContract;

public interface FixedDAO  extends GenericDao<FixedContract>{

	public List<FixedContract> getFixedContractsForAContract(Integer contractId);
	
}
