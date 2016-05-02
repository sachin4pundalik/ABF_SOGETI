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
import com.sogeti.db.models.Status;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.StatusDT;
import com.sogeti.service.StatusService;

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
@RequestMapping("/status")
public class StatusController {
	private Logger logger = Logger.getLogger(StatusController.class);

	@Autowired
	StatusService statusService;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getAll() {

		ABFResponse response = new ABFResponse();
		List<Status> statuses = new ArrayList<Status>();
		statuses = statusService.findAll();
		response.setSuccessResponse(statuses);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody StatusDT statusDT) {

		ABFResponse response = new ABFResponse();
		Status statusEntity = new Status();
		BeanUtils.copyProperties(statusDT, statusEntity);
		logger.info("statusData:" + statusDT);

		try {
			statusService.create(statusEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody StatusDT statusDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			Status statusEntity = new Status();
			BeanUtils.copyProperties(statusDT, statusEntity);
			statusService.update(statusEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") int statusId)

	{
		ABFResponse response = new ABFResponse();

		try {
			statusService.delete(statusId);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ABFResponse find(@PathVariable("id") int statusId)

	{
		ABFResponse response = new ABFResponse();

		try {

			Status status = statusService.find(statusId);
			response.setSuccessResponse(status);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

}
