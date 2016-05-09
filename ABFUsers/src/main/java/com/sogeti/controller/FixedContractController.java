package com.sogeti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.constants.ABFConstants;
import com.sogeti.db.models.Contract;
import com.sogeti.db.models.FixedContract;
import com.sogeti.db.models.FixedCost;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.FixedContractDT;
import com.sogeti.service.ContractManager;
import com.sogeti.service.FixedCostService;
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
	
	@Autowired
	FixedCostService fixedCostService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getAllFixedContracts() {

		ABFResponse response = new ABFResponse();
		List<FixedContract> contracts = null;
		List<FixedContractDT> fixedContracts = new ArrayList<>();
		FixedContractDT contractDT = null;
		try {
			contracts = fixedService.findAll();
			
			for (FixedContract contract : contracts) {
				contractDT = new FixedContractDT();
				
				BeanUtils.copyProperties(contract, contractDT);
				fixedContracts.add(contractDT);
			}
			
			response.setSuccessResponse(fixedContracts);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
			
		} catch (Exception e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody List<FixedContractDT> contracts) {

		ABFResponse response = new ABFResponse();
		
		try {
			if (!CollectionUtils.isEmpty(contracts)) {
				int contractId = contracts.get(0).getContractId();
				if(contractId > 0){
					List<FixedContract> fcsAlreadyPresentForContracts =  fixedService.getFixedContractsForAContract(contracts.get(0).getContractId());
					if (!CollectionUtils.isEmpty(fcsAlreadyPresentForContracts)) {
						//Delete the existing contract data
						for (FixedContract fc : fcsAlreadyPresentForContracts) {
							fixedService.delete(fc.getFixedcontractId());
						}
					}
					
					//insert fresh set of data.
					for (FixedContractDT fixedContractDT : contracts) {
						FixedContract fixedContract = new FixedContract();
						BeanUtils.copyProperties(fixedContractDT, fixedContract);
						logger.info("ContractData:" + fixedContractDT);

						Contract contractObj = contractManager.getContract(fixedContractDT.getContractId());
						fixedContract.setContract(contractObj);
						
						FixedCost fixedCostObj = fixedCostService.find(fixedContractDT.getFixedcostId());
						fixedContract.setFixedCost(fixedCostObj);
						
						fixedService.create(fixedContract);
					}
					response.setStatus(ABFConstants.STATUS_SUCCESS);
				}else{
					response.setStatus(ABFConstants.STATUS_FAILURE);
					response.setFailureResponse("There is not valid contract avalable.");
				}
			}else{
				response.setStatus(ABFConstants.STATUS_FAILURE);
				response.setFailureResponse("No data to submit..!! or please provide valid data");
			}
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
		} catch(DataIntegrityViolationException diEx){
			response.setFailureResponse(diEx.getMessage());
			response.setStatus(ABFConstants.STATUS_DI_EXCEPTION);
		} 	catch (TechnicalException e) {
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
	
	
	@RequestMapping(value = "/contract/find/{id}", method = RequestMethod.GET)
	public ABFResponse findFixedForAContract(@PathVariable("id") int contractId)

	{
		ABFResponse response = new ABFResponse();
		FixedContractDT contractDT = null;
		List<FixedContractDT> fixedContracts = new ArrayList<>();
		try {
			List<FixedContract> contracts =  fixedService.getFixedContractsForAContract(contractId);
			if(!CollectionUtils.isEmpty(contracts)){
				for (FixedContract contract : contracts) {
					contractDT = new FixedContractDT();
					
					BeanUtils.copyProperties(contract, contractDT);
					contractDT.setContractId(contract.getContract().getContractId());
					contractDT.setFixedcostId(contract.getFixedcontractId());
					if(contract.getFixedCost()!=null){
						contractDT.setFixedcostId(contract.getFixedCost().getFixedcostId());
						contractDT.setFixedcostName(contract.getFixedCost().getFixedcostName());
						
						fixedContracts.add(contractDT);
					}
				}
				response.setSuccessResponse(fixedContracts);
				response.setStatus(ABFConstants.STATUS_SUCCESS);
			}else{
				response.setStatus(ABFConstants.STATUS_SUCCESS);
			}
			
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}
	
}
