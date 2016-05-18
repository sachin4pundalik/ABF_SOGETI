package com.sogeti.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.OffshorePrice;
import com.sogeti.db.models.StayType;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.OffshorePriceDT;
import com.sogeti.service.BandService;
import com.sogeti.service.BusinessLineService;
import com.sogeti.service.OffshorePriceService;
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
@RequestMapping("/offshorePrice")
public class OffshorePriceController {
	private Logger logger = Logger.getLogger(OffshorePriceController.class);

	@Autowired
	OffshorePriceService offshorePriceService;
	
	@Autowired
	BusinessLineService businessLineService;
	
	@Autowired
    BandService bandService;
    
	@Autowired
    StayTypeService stayTypeService;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getOffshorePrices() {

		ABFResponse response = new ABFResponse();
		List<OffshorePrice> offshorePrices = new ArrayList<OffshorePrice>();
		List<OffshorePriceDT> offshorePricesDTList = new ArrayList<OffshorePriceDT>();
		offshorePrices = offshorePriceService.findAll();
		
		for(OffshorePrice offshorePrice:offshorePrices){
			
			OffshorePriceDT offshorePriceDT = new OffshorePriceDT();
			offshorePriceDT.setOffshorepriceId(offshorePrice.getOffshorepriceId());
			offshorePriceDT.setPrice(offshorePrice.getPrice());
			offshorePriceDT.setLastUpdatedBy(offshorePrice.getLastUpdatedBy());
			offshorePriceDT.setLastUpdatedDatetime(offshorePrice.getLastUpdatedDatetime()+"");
			offshorePriceDT.setDescription(offshorePrice.getDescription());
			offshorePriceDT.setBandId(offshorePrice.getBand().getBandId());
			offshorePriceDT.setBandName(offshorePrice.getBand().getBandName());
			offshorePriceDT.setStayTypeId(offshorePrice.getStayType().getStayTypeId());
			offshorePriceDT.setStayTypeName(offshorePrice.getStayType().getStayType());
			offshorePriceDT.setBusinessLineId(offshorePrice.getBusinessLine().getBusinesslineId());
			offshorePriceDT.setBusinessLineName(offshorePrice.getBusinessLine().getBusinesslineName());
			offshorePricesDTList.add(offshorePriceDT)	;
			
		}
		
		response.setSuccessResponse(offshorePricesDTList);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody OffshorePriceDT offshorePriceDT) {
		ABFResponse response = new ABFResponse();
		try {
		
		OffshorePrice offshorePriceEntity = new OffshorePrice();
		BeanUtils.copyProperties(offshorePriceDT, offshorePriceEntity);
		offshorePriceEntity.setLastUpdatedDatetime(new Date());
		offshorePriceEntity.setBusinessLine(businessLineService.find(offshorePriceDT.getBusinessLineId()));
		offshorePriceEntity.setBand(bandService.find(offshorePriceDT.getBandId()));
		offshorePriceEntity.setStayType(stayTypeService.find(offshorePriceDT.getStayTypeId()));
		
		
		logger.info("OffshorePriceData:" + offshorePriceDT);

		
		offshorePriceService.create(offshorePriceEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody OffshorePriceDT offshorePriceDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			OffshorePrice offshorePriceEntity = new OffshorePrice();
			BeanUtils.copyProperties(offshorePriceDT, offshorePriceEntity);
			offshorePriceEntity.setLastUpdatedDatetime(new Date());
			offshorePriceEntity.setBusinessLine(businessLineService.find(offshorePriceDT.getBusinessLineId()));
			offshorePriceEntity.setBand(bandService.find(offshorePriceDT.getBandId()));
			offshorePriceEntity.setStayType(stayTypeService.find(offshorePriceDT.getStayTypeId()));
			offshorePriceService.update(offshorePriceEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") int offshorePriceId)

	{
		ABFResponse response = new ABFResponse();

		try {
			offshorePriceService.delete(offshorePriceId);
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
	public ABFResponse findBusinessLine(@PathVariable("id") int offshorePriceId)

	{
		ABFResponse response = new ABFResponse();

		try {

			OffshorePrice offshorePrice = offshorePriceService.find(offshorePriceId);
			OffshorePriceDT offshorePriceDT = new OffshorePriceDT();
			offshorePriceDT.setOffshorepriceId(offshorePrice.getOffshorepriceId());
			offshorePriceDT.setPrice(offshorePrice.getPrice());
			offshorePriceDT.setLastUpdatedBy(offshorePrice.getLastUpdatedBy());
			offshorePriceDT.setLastUpdatedDatetime(offshorePrice.getLastUpdatedDatetime()+"");
			offshorePriceDT.setDescription(offshorePrice.getDescription());
			offshorePriceDT.setBandId(offshorePrice.getBand().getBandId());
			offshorePriceDT.setBandName(offshorePrice.getBand().getBandName());
			offshorePriceDT.setStayTypeId(offshorePrice.getStayType().getStayTypeId());
			offshorePriceDT.setStayTypeName(offshorePrice.getStayType().getStayType());
			offshorePriceDT.setBusinessLineId(offshorePrice.getBusinessLine().getBusinesslineId());
			offshorePriceDT.setBusinessLineName(offshorePrice.getBusinessLine().getBusinesslineName());
		
			response.setSuccessResponse(offshorePriceDT);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse("Unable to find price for the given combination");
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}
	
	@RequestMapping(value = "/find/{bline}/{band}/{stayType}", method = RequestMethod.GET)
	public ABFResponse findOffShorePriceFor(@PathVariable("bline") int blineId, @PathVariable("band") int bandId, @PathVariable("stayType") int stayTypeId){
		{
			ABFResponse response = new ABFResponse();

			try {
				BusinessLine bline = businessLineService.find(blineId);
				Band band = bandService.find(bandId);
				StayType stayType = stayTypeService.find(stayTypeId);
				
				OffshorePrice offshorePrice = offshorePriceService.getOnShorePriceIdFor(bline, band, stayType);
				OffshorePriceDT offshorePriceDT = new OffshorePriceDT();
				offshorePriceDT.setOffshorepriceId(offshorePrice.getOffshorepriceId());
				offshorePriceDT.setPrice(offshorePrice.getPrice());
				offshorePriceDT.setLastUpdatedBy(offshorePrice.getLastUpdatedBy());
				offshorePriceDT.setLastUpdatedDatetime(offshorePrice.getLastUpdatedDatetime()+"");
				offshorePriceDT.setDescription(offshorePrice.getDescription());
				offshorePriceDT.setBandId(offshorePrice.getBand().getBandId());
				offshorePriceDT.setBandName(offshorePrice.getBand().getBandName());
				offshorePriceDT.setStayTypeId(offshorePrice.getStayType().getStayTypeId());
				offshorePriceDT.setStayTypeName(offshorePrice.getStayType().getStayType());
				offshorePriceDT.setBusinessLineId(offshorePrice.getBusinessLine().getBusinesslineId());
				offshorePriceDT.setBusinessLineName(offshorePrice.getBusinessLine().getBusinesslineName());
			
				response.setSuccessResponse(offshorePriceDT);
				response.setStatus(ABFConstants.STATUS_SUCCESS);
			} catch (TechnicalException e) {
				response.setFailureResponse("Unable to find price for the given combination");
				response.setStatus(ABFConstants.STATUS_FAILURE);
			}

			return response;
		}
	}

}
