package com.sogeti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.constants.ABFConstants;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.Contract;
import com.sogeti.db.models.FixedContract;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.FixedContractDT;
import com.sogeti.service.ContractManager;
import com.sogeti.service.FixedService;
/**
 * FixedContractController class  provides implementations for the contract. 
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
 * 25/04/2016       Mohan             N/A          ABF        Created.
 * 
 * =============================================================================
 * 
 * </PRE>
 */
@RestController
@RequestMapping("/fixedContract")
public class FixedContractController {
	private Logger logger = Logger.getLogger(FixedContractController.class);

	@Autowired
	FixedService fixedService;
	
	@Autowired
	ContractManager contractManager;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getAllFixedContracts() {

		ABFResponse response = new ABFResponse();
		List<FixedContract> contracts = new ArrayList<FixedContract>();
		contracts = fixedService.findAll();
		response.setSuccessResponse(contracts);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody FixedContractDT contract) {

		ABFResponse response = new ABFResponse();
		FixedContract fixedContract = new FixedContract();
		BeanUtils.copyProperties(contract, fixedContract);
		logger.info("ContractData:" + contract);

		try {
			Contract contractObj = contractManager.getContract(contract.getContractId());
			fixedContract.setContract(contractObj);
			fixedService.create(fixedContract);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody FixedContractDT contract)

	{
		ABFResponse response = new ABFResponse();

		try {
			FixedContract fixedContract = new FixedContract();
			BeanUtils.copyProperties(contract, fixedContract);
			fixedService.update(fixedContract);
			response.setSuccessResponse(fixedContract);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.updateContract" + e);

			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") Integer fixedContractId)

	{
		ABFResponse response = new ABFResponse();

		try {
			fixedService.delete(fixedContractId);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ABFResponse find(@PathVariable("id") int fixedContractId)

	{
		ABFResponse response = new ABFResponse();

		try {
			
			FixedContract fixedContract = fixedService.find(fixedContractId);
			response.setSuccessResponse(fixedContract);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}
	
}
