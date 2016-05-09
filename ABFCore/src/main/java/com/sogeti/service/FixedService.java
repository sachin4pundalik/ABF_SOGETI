package com.sogeti.service;

import java.util.List;

import com.sogeti.db.models.FixedContract;

public interface FixedService  extends GenericService<FixedContract>{
	public List<FixedContract> getFixedContractsForAContract(Integer contractId);
}
