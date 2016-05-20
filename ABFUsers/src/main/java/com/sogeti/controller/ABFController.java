package com.sogeti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.constants.ABFConstants;
import com.sogeti.db.models.Status;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.Contract;
import com.sogeti.service.ContractManager;
import com.sogeti.service.StatusService;
/**
 * ABF controller class  provides implementations for the contract. 
 * <P>
 * <B> Visibility decisions: </B>
 * <P>
 * Unless otherwise noted, attributes are private, and a public getter and
 * setter is provided for each.
 * <P>
 * <B> Design/implementation notes: </B>
 * <P>
 * Document any decisions, assumptions, issues, or other notes regarding the
 * implementation of this class.
 * <P>
 * <P>
 * <B> Revision History: </B>
 * 
 * <PRE>
 * 
 * =============================================================================
 * Prior Date            By                  Version  Project/CSR  Description 
 * ---------- --------------------------   ---------- ------------ ------------ 
 * 05/04/2016       kalyan             N/A          ABF        Created.
 * 
 * =============================================================================
 * 
 * </PRE>
 */
@RestController
@RequestMapping("/contract")
public class ABFController {	
	private Logger logger = Logger.getLogger(ABFController.class);

	@Autowired
	ContractManager contractManager;

	@Autowired
	StatusService statusService;

	@RequestMapping( value = "/create", method = RequestMethod.POST)
	public ABFResponse createContract(@RequestBody Contract contract) {

		ABFResponse response = new ABFResponse();	
		com.sogeti.db.models.Contract dbContract = new com.sogeti.db.models.Contract();
		BeanUtils.copyProperties(contract, dbContract);
		// Set the default status to 1. later change the logic
		Status initialStatus = statusService.find(1);
		dbContract.setStatus(initialStatus);
		logger.info("ContractData:"+contract);	

		try {
			contractManager.createContract(dbContract);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping( value = "/all", method = RequestMethod.GET)
	public ABFResponse getContracts(){ 

		ABFResponse  response = new ABFResponse();			
		List<com.sogeti.db.models.Contract> contracts =null;
		List<Contract> finalContracts = new ArrayList<Contract>();

		try {
			contracts = contractManager.allContracts();	
			Contract locContract = null;
			for (com.sogeti.db.models.Contract contract : contracts) {
				locContract = new Contract();
				BeanUtils.copyProperties(contract, locContract);
				locContract.setStatusId(contract.getStatus().getStatusId());
				locContract.setStatus(contract.getStatus().getStatusName());
				finalContracts.add(locContract);
			}
			response.setSuccessResponse(finalContracts);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (BeansException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse deleteContract(@PathVariable("id") int contractId)

	{
		ABFResponse  response = new ABFResponse();	

		try
		{
			com.sogeti.db.models.Contract contractData = new com.sogeti.db.models.Contract();		
			contractData.setContractId(contractId);
			contractManager.deleteContract(contractData);			
			response.setSuccessResponse(contractData);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		}
		catch (TechnicalException e)
		{
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse updateContract(@RequestBody Contract contract)

	{
		ABFResponse  response = new ABFResponse();

		try
		{
			com.sogeti.db.models.Contract contractData = new com.sogeti.db.models.Contract();
			BeanUtils.copyProperties(contract, contractData);			
			contractManager.updateContract(contractData);
			response.setSuccessResponse(contractData);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		}
		catch (TechnicalException e)
		{
			logger.error("Exception in method ... ABFController.updateContract" + e);

			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ABFResponse getContract(@PathVariable("id") int contractId)

	{
		ABFResponse  response = new ABFResponse();
		com.sogeti.db.models.Contract contract =null;
		
		try
		{
			contract = contractManager.getContract(contractId);	
			Contract locContract = null;	
			BeanUtils.copyProperties(contract, locContract);
			locContract.setStatusId(contract.getStatus().getStatusId());
			locContract.setStatus(contract.getStatus().getStatusName());
			response.setSuccessResponse(locContract);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		}
		catch (TechnicalException e)
		{
			logger.error("Exception in method ... ABFController.getContract" + e);

			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}	

	@RequestMapping( value = "/mycontracts/{loginID}", method = RequestMethod.GET)
	public ABFResponse allContractsByMe(@PathVariable("loginID") int loginId){

		ABFResponse  response = new ABFResponse();			
		List<com.sogeti.db.models.Contract> contracts =null;
		List<Contract> finalContracts = new ArrayList<Contract>();

		try {
			contracts = contractManager.allContractsByMe(loginId);	
			Contract locContract = null;
			for (com.sogeti.db.models.Contract contract : contracts) {
				locContract = new Contract();
				BeanUtils.copyProperties(contract, locContract);
				locContract.setStatusId(contract.getStatus().getStatusId());
				locContract.setStatus(contract.getStatus().getStatusName());
				locContract.setLoginId(contract.getLogin().getLoginId());
				finalContracts.add(locContract);
			}
			response.setSuccessResponse(finalContracts);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (BeansException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}

		return response;
	}
}



