package com.sogeti.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.lang3.ObjectUtils;
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
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.Grade;
import com.sogeti.db.models.OnshorePrice;
import com.sogeti.db.models.Role;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.OnshorePriceDT;
import com.sogeti.service.BusinessLineService;
import com.sogeti.service.GradeService;
import com.sogeti.service.OnshorePriceService;
import com.sogeti.service.RoleService;

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
@RequestMapping("/onshorePrice")
public class OnshorePriceController {
	private Logger logger = Logger.getLogger(OnshorePriceController.class);

	@Autowired
	OnshorePriceService onshorePriceService;
	
	@Autowired
	BusinessLineService businessLineService;
	
	@Autowired
    GradeService gradeService;
    
	@Autowired
    RoleService roleService;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getOnshorePrices() {

		ABFResponse response = new ABFResponse();
		List<OnshorePrice> onshorePrices = new ArrayList<OnshorePrice>();
		List<OnshorePriceDT> onshorePricesDTList = new ArrayList<OnshorePriceDT>();
		try {
			onshorePrices = onshorePriceService.findAll();
			
			for(OnshorePrice onshorePrice:onshorePrices){
				
				OnshorePriceDT onshorePriceDT = new OnshorePriceDT();
				onshorePriceDT.setOnshorepriceId(onshorePrice.getOnshorepriceId());
				onshorePriceDT.setPrice(onshorePrice.getPrice());
				onshorePriceDT.setLastUpdatedBy(onshorePrice.getLastUpdatedBy());
				onshorePriceDT.setLastUpdatedDatetime(onshorePrice.getLastUpdatedDatetime()+"");
				onshorePriceDT.setDescription(onshorePrice.getDescription());
				
				if(ObjectUtils.notEqual(onshorePrice.getGrade(), null)){
					onshorePriceDT.setGradeType(onshorePrice.getGrade().getGradeType());
					onshorePriceDT.setGradeId(onshorePrice.getGrade().getGradeId());
				}
				if(ObjectUtils.notEqual(onshorePrice.getRole(), null)){
					onshorePriceDT.setRoleId(onshorePrice.getRole().getRoleId());
					onshorePriceDT.setRoleType(onshorePrice.getRole().getRoleType());
				}
				
				if(ObjectUtils.notEqual(onshorePrice.getBusinessLine(), null)){
					onshorePriceDT.setBusinessLineId(onshorePrice.getBusinessLine().getBusinesslineId());
					onshorePriceDT.setBusinessLineName(onshorePrice.getBusinessLine().getBusinesslineName());
				}
				onshorePricesDTList.add(onshorePriceDT)	;
			}
			
			response.setSuccessResponse(onshorePricesDTList);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (Exception e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(ABFConstants.STATUS_DI_EXCEPTION);
		}

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody OnshorePriceDT onshorePriceDT) {
		ABFResponse response = new ABFResponse();
		try {

			OnshorePrice onshorePriceEntity = new OnshorePrice();
			BeanUtils.copyProperties(onshorePriceDT, onshorePriceEntity);
			onshorePriceEntity.setLastUpdatedDatetime(new Date());
			onshorePriceEntity.setBusinessLine(businessLineService.find(onshorePriceDT.getBusinessLineId()));
			onshorePriceEntity.setGrade(gradeService.find(onshorePriceDT.getGradeId()));
			onshorePriceEntity.setRole(roleService.find(onshorePriceDT.getRoleId()));

			logger.info("onshorePriceData:" + onshorePriceDT);

			onshorePriceService.create(onshorePriceEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody OnshorePriceDT onshorePriceDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			OnshorePrice onshorePriceEntity = new OnshorePrice();
			BeanUtils.copyProperties(onshorePriceDT, onshorePriceEntity);
			onshorePriceEntity.setLastUpdatedDatetime(new Date());
			onshorePriceEntity.setBusinessLine(businessLineService.find(onshorePriceDT.getBusinessLineId()));
			onshorePriceEntity.setGrade(gradeService.find(onshorePriceDT.getGradeId()));
			onshorePriceEntity.setRole(roleService.find(onshorePriceDT.getRoleId()));
			onshorePriceService.update(onshorePriceEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} 
		 catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}
	    

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") int onshorePriceId)

	{
		ABFResponse response = new ABFResponse();

		try {
			onshorePriceService.delete(onshorePriceId);
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
	public ABFResponse findBusinessLine(@PathVariable("id") int onshorePriceId)

	{
		ABFResponse response = new ABFResponse();

		try {

			OnshorePrice onshorePrice = onshorePriceService.find(onshorePriceId);
			OnshorePriceDT onshorePriceDT = new OnshorePriceDT();
			onshorePriceDT.setOnshorepriceId(onshorePrice.getOnshorepriceId());
			onshorePriceDT.setPrice(onshorePrice.getPrice());
			onshorePriceDT.setLastUpdatedBy(onshorePrice.getLastUpdatedBy());
			onshorePriceDT.setLastUpdatedDatetime(onshorePrice.getLastUpdatedDatetime()+"");
			onshorePriceDT.setDescription(onshorePrice.getDescription());
			onshorePriceDT.setGradeType(onshorePrice.getGrade().getGradeType());
			onshorePriceDT.setRoleId(onshorePrice.getRole().getRoleId());
			onshorePriceDT.setRoleType(onshorePrice.getRole().getRoleType());
			onshorePriceDT.setGradeId(onshorePrice.getGrade().getGradeId());
			onshorePriceDT.setBusinessLineId(onshorePrice.getBusinessLine().getBusinesslineId());
			onshorePriceDT.setBusinessLineName(onshorePrice.getBusinessLine().getBusinesslineName());
			response.setSuccessResponse(onshorePriceDT);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse("Unable to find price for the given combination");
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}
	
	@RequestMapping(value ="/find/{bline}/{role}/{grade}", method = RequestMethod.GET)
	public ABFResponse fineOnShorePriceFor(@PathVariable("bline") int blineId, @PathVariable("role") int roleId, @PathVariable("grade") int gradeId){
		ABFResponse response = new ABFResponse();
		
		try {
			
			BusinessLine bline = businessLineService.find(blineId);
			Role role = roleService.find(roleId);
			Grade grade = gradeService.find(gradeId);
			
			OnshorePrice onshorePrice = onshorePriceService.getOnShorePriceIdFor(bline, role, grade);
			OnshorePriceDT onshorePriceDT = new OnshorePriceDT();
			onshorePriceDT.setOnshorepriceId(onshorePrice.getOnshorepriceId());
			onshorePriceDT.setPrice(onshorePrice.getPrice());
			onshorePriceDT.setLastUpdatedBy(onshorePrice.getLastUpdatedBy());
			onshorePriceDT.setLastUpdatedDatetime(onshorePrice.getLastUpdatedDatetime()+"");
			onshorePriceDT.setDescription(onshorePrice.getDescription());
			onshorePriceDT.setGradeType(onshorePrice.getGrade().getGradeType());
			onshorePriceDT.setRoleId(onshorePrice.getRole().getRoleId());
			onshorePriceDT.setRoleType(onshorePrice.getRole().getRoleType());
			onshorePriceDT.setGradeId(onshorePrice.getGrade().getGradeId());
			onshorePriceDT.setBusinessLineId(onshorePrice.getBusinessLine().getBusinesslineId());
			onshorePriceDT.setBusinessLineName(onshorePrice.getBusinessLine().getBusinesslineName());
			response.setSuccessResponse(onshorePriceDT);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse("Unable to find price for the given combination");
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}
		return response;
	}

}
