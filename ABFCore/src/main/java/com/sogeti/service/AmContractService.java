package com.sogeti.service;

import java.util.List;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.AmContract;

public interface AmContractService {
	
public List<AmContract> getAmContractsByContractId(int contractId) throws TechnicalException;
	
	public AmContract getAmContractById(int amId) throws TechnicalException;
	
	public boolean saveAmContract(AmContract AmContract) throws TechnicalException;
	
	public boolean saveAmContractBatch(List<AmContract> AmContracts) throws TechnicalException;
	
	public boolean deleteAmContract(AmContract amContract) throws TechnicalException;

}
