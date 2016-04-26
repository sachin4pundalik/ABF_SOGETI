package com.sogeti.dao;

import java.util.List;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.AMContractResource;
import com.sogeti.db.models.Contract;

public interface AmContractDao {
	
	public List<AMContractResource> getAmContractResourcesByContractId(Contract contractId) throws TechnicalException;
	
	public AMContractResource getAmContractResourceById(int amId) throws TechnicalException;
	
	public boolean saveAmContractResource(AMContractResource amContractResource) throws TechnicalException;
	
	public boolean saveAmContractResourceBatch(List<AMContractResource> amContractResources) throws TechnicalException;
	
	public Contract getContractById(int contractId);

}
