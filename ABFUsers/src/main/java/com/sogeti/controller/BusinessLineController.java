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
@RequestMapping("/businessline")
public class BusinessLineController {
	private Logger logger = Logger.getLogger(BusinessLineController.class);

	@Autowired
	BusinessLineService businessLineService;
	
	@Autowired
	ResourceTypeManager resourceTypeManager;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getAll() {

		ABFResponse response = new ABFResponse();
		List<BusinessLine> businessLines = new ArrayList<BusinessLine>();
		List<BusinessLineDT> finalList = new ArrayList<>();
		BusinessLineDT businessLineDT = null;
		try {
			businessLines = businessLineService.findAll();
			for (BusinessLine businessLine : businessLines) {
				
				businessLineDT = new BusinessLineDT();
				businessLineDT.setBusinesslineId(businessLine.getBusinesslineId());
				businessLineDT.setBusinesslineName(businessLine.getBusinesslineName());
				businessLineDT.setResourceTypeId(businessLine.getResourceType().getResourcetypeId());
				businessLineDT.setResourceType(businessLine.getResourceType().getResourceType());
				businessLineDT.setSkillId(businessLine.getSkill().getSkillId());
				businessLineDT.setSkillName(businessLine.getSkill().getSkillName());
				finalList.add(businessLineDT);
			}
			
			response.setSuccessResponse(finalList);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (Exception e) {
			logger.error("Exception :: ", e);
			response.setSuccessResponse("No Business Lines, please ask Admin to setup data.");
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody BusinessLineDT businessLine) {

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
	public ABFResponse update(@RequestBody BusinessLineDT businessLine)

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
	public ABFResponse delete(@PathVariable("id") int businessLineId)

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
	public ABFResponse find(@PathVariable("id") int businessLineId)

	{
		ABFResponse response = new ABFResponse();

		try {
			
			BusinessLine businessLine = businessLineService.find(businessLineId);
			
			BusinessLineDT businessLineDT = new BusinessLineDT();
			businessLineDT.setBusinesslineId(businessLine.getBusinesslineId());
			businessLineDT.setBusinesslineName(businessLine.getBusinesslineName());
			businessLineDT.setResourceTypeId(businessLine.getResourceType().getResourcetypeId());
			businessLineDT.setResourceType(businessLine.getResourceType().getResourceType());
			businessLineDT.setSkillId(businessLine.getSkill().getSkillId());
			businessLineDT.setSkillName(businessLine.getSkill().getSkillName());
			
			response.setSuccessResponse(businessLineDT);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... ABFController.deleteContract" + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

}
