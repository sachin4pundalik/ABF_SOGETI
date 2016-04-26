package com.sogeti.service;

import java.util.List;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.AMContractResource;

public interface AmContractService {
	
public List<AMContractResource> getAmContractResourcesByContractId(int contractId) throws TechnicalException;
	
	public AMContractResource getAmContractById(int amId) throws TechnicalException;
	
	public boolean saveAmContractResource(AMContractResource amContractResource) throws TechnicalException;
	
	public boolean saveAmContractResourceBatch(List<AMContractResource> amContractResources) throws TechnicalException;

}
