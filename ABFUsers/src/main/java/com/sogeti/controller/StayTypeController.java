package com.sogeti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.constants.ABFConstants;
import com.sogeti.db.models.StayType;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.StayTypeDT;
import com.sogeti.service.StayTypeService;

/**
 * FixedContractController class provides implementations for the contract.
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
@RequestMapping("/stayType")
public class StayTypeController {
	private Logger logger = Logger.getLogger(StayTypeController.class);

	@Autowired
	StayTypeService stayTypeService;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getStayTypes() {

		ABFResponse response = new ABFResponse();
		List<StayType> stayTypes = new ArrayList<StayType>();
		List<StayTypeDT> stayTypesDT = new ArrayList<StayTypeDT>();
		stayTypes = stayTypeService.findAll();
		for(StayType stayType : stayTypes){
			StayTypeDT tempDT = new StayTypeDT();
			tempDT.setStayTypeId(stayType.getStayTypeId());
			tempDT.setStayType(stayType.getStayType());
			tempDT.setActive(stayType.getActive());
			stayTypesDT.add(tempDT);
		}
		response.setSuccessResponse(stayTypesDT);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody StayTypeDT stayTypeDT) {

		ABFResponse response = new ABFResponse();
		StayType stayTypeEntity = new StayType();
		BeanUtils.copyProperties(stayTypeDT, stayTypeEntity);
		logger.info("skillData:" + stayTypeDT);

		try {
			stayTypeService.create(stayTypeEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody StayTypeDT stayTypeDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			StayType stayTypeEntity = new StayType();
			BeanUtils.copyProperties(stayTypeDT, stayTypeEntity);
			stayTypeService.update(stayTypeEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") int stayTypeId)

	{
		ABFResponse response = new ABFResponse();

		try {
			stayTypeService.delete(stayTypeId);
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
	public ABFResponse find(@PathVariable("id") int stayTypeId)

	{
		ABFResponse response = new ABFResponse();

		try {

			StayType stayType = stayTypeService.find(stayTypeId);
			response.setSuccessResponse(stayType);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

}
