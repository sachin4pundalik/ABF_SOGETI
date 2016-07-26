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
import com.sogeti.db.models.Band;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.BandDT;
import com.sogeti.service.BandService;
import com.sogeti.service.ResourceTypeManager;

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
@RequestMapping("/band")
public class BandController {
	private Logger logger = Logger.getLogger(BandController.class);

	@Autowired
	BandService bandService;

	@Autowired
	ResourceTypeManager resourceTypeManager;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getBands() {

		ABFResponse response = new ABFResponse();
		List<Band> bands = new ArrayList<Band>();
		List<BandDT> bandsDt = new ArrayList<BandDT>();
		bands = bandService.findAll();
		for(Band band : bands){
			BandDT tempDt = new BandDT();
			tempDt.setBandId(band.getBandId());
			tempDt.setBandName(band.getBandName());
			tempDt.setActive(band.getActive());
			bandsDt.add(tempDt);
		}
		response.setSuccessResponse(bandsDt);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody BandDT bandDT) {

		ABFResponse response = new ABFResponse();
		Band bandEntity = new Band();
		BeanUtils.copyProperties(bandDT, bandEntity);
		logger.info("bandEntity:" + bandEntity);

		try {
			bandService.create(bandEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody BandDT bandDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			Band bandEntity = new Band();
			BeanUtils.copyProperties(bandDT, bandEntity);
			bandService.update(bandEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") int bandId)

	{
		ABFResponse response = new ABFResponse();

		try {
			bandService.delete(bandId);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} 
		catch(DataIntegrityViolationException diEx){
			response.setFailureResponse(diEx.getMessage());
			response.setStatus(ABFConstants.STATUS_DI_EXCEPTION);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ABFResponse find(@PathVariable("id") int bandId)

	{
		ABFResponse response = new ABFResponse();

		try {

			Band band = bandService.find(bandId);
			response.setSuccessResponse(band);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

}
