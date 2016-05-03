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
import com.sogeti.db.models.FixedCost;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.FixedCostDT;
import com.sogeti.service.ContractManager;
import com.sogeti.service.FixedCostService;
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
@RequestMapping("/fixedCost")
public class FixedCostController {
	private Logger logger = Logger.getLogger(FixedCostController.class);

	@Autowired
	FixedCostService fixedCostService;
	
	@Autowired
	ContractManager contractManager;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getAllFixedCosts() {

		ABFResponse response = new ABFResponse();
		List<FixedCost> fixedCosts = new ArrayList<FixedCost>();
		List<FixedCostDT> fixedDt = new ArrayList<>();
		FixedCostDT costDT = null;
		try {
			fixedCosts = fixedCostService.findAll();
			for (FixedCost fixedCost : fixedCosts) {
				costDT = new FixedCostDT();
				costDT.setFixedcostDescription(fixedCost.getFixedcostDescription());
				costDT.setFixedcostId(fixedCost.getFixedcostId());
				costDT.setFixedcostName(fixedCost.getFixedcostName());
				
				fixedDt.add(costDT);
			}
			
			response.setSuccessResponse(fixedDt);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (Exception e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody FixedCostDT fixedCostDt) {

		ABFResponse response = new ABFResponse();
		FixedCost fixedCost = new FixedCost();
		BeanUtils.copyProperties(fixedCostDt, fixedCost);
		logger.info("ContractData:" + fixedCost);

		try {
			
			fixedCostService.create(fixedCost);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody FixedCostDT costDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			FixedCost fixedCost = new FixedCost();
			BeanUtils.copyProperties( costDT, fixedCost);
			fixedCostService.update(fixedCost);
			
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
			fixedCostService.delete(fixedContractId);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ABFResponse find(@PathVariable("id") int fixedCostId)

	{
		ABFResponse response = new ABFResponse();

		try {
			
			FixedCost fixedContract = fixedCostService.find(fixedCostId);
			FixedCostDT costDT = new FixedCostDT();
			BeanUtils.copyProperties( fixedContract, costDT);
			
			response.setSuccessResponse(costDT);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse("Cannot find data.");
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}
	
}
