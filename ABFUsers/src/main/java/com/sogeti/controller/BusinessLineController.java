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
import com.sogeti.db.models.ResourceType;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.BusinessLineDT;
import com.sogeti.service.BusinessLineService;
import com.sogeti.service.ResourceTypeManager;
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
@RequestMapping("/businessLine")
public class BusinessLineController {
	private Logger logger = Logger.getLogger(BusinessLineController.class);

	@Autowired
	BusinessLineService businessLineService;
	
	@Autowired
	ResourceTypeManager resourceTypeManager;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getBusinessLines() {

		ABFResponse response = new ABFResponse();
		List<BusinessLine> businessLines = new ArrayList<BusinessLine>();
		businessLines = businessLineService.findAll();
		response.setSuccessResponse(businessLines);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse createBusinessLine(@RequestBody BusinessLineDT businessLine) {

		ABFResponse response = new ABFResponse();
		BusinessLine businessLineEntity = new BusinessLine();
		BeanUtils.copyProperties(businessLine, businessLineEntity);
		ResourceType resourceType = resourceTypeManager.find(businessLine.getResourceTypeId());
		businessLineEntity.setResourceType(resourceType);
		logger.info("ContractData:" + businessLineEntity);

		try {
			businessLineService.create(businessLineEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse updateContract(@RequestBody BusinessLineDT businessLine)

	{
		ABFResponse response = new ABFResponse();

		try {
			BusinessLine businessLineEntity = new BusinessLine();
			BeanUtils.copyProperties(businessLine, businessLineEntity);
			businessLineService.update(businessLineEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.updateContract" + e);

			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse deleteContract(@PathVariable("id") int businessLineId)

	{
		ABFResponse response = new ABFResponse();

		try {
			BusinessLine businessLineEntity = new BusinessLine();
			businessLineEntity.setBusinesslineId(businessLineId);
			businessLineService.delete(businessLineId);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}
	
	

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ABFResponse findBusinessLine(@PathVariable("id") int businessLineId)

	{
		ABFResponse response = new ABFResponse();

		try {
			
			BusinessLine businessLine = businessLineService.find(businessLineId);
			response.setSuccessResponse(businessLine);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

}
