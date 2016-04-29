package com.sogeti.dao;

import java.util.List;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.AmContract;
import com.sogeti.db.models.Contract;

public interface AmContractDao {
	
	public List<AmContract> getAmContractsByContractId(Contract contractId) throws TechnicalException;
	
	public AmContract getAmContractById(int amId) throws TechnicalException;
	
	public boolean saveAmContract(AmContract AmContract) throws TechnicalException;
	
	public boolean saveAmContractBatch(List<AmContract> AmContracts) throws TechnicalException;
	
	public Contract getContractById(int contractId);

}
