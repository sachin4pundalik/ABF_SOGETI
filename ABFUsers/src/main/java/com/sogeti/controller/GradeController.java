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
import com.sogeti.db.models.Grade;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.GradeDT;
import com.sogeti.service.GradeService;

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
@RequestMapping("/grade")
public class GradeController {
	private Logger logger = Logger.getLogger(GradeController.class);

	@Autowired
	GradeService gradeService;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getGrades() {

		ABFResponse response = new ABFResponse();
		List<Grade> grades = new ArrayList<Grade>();
		List<GradeDT> gradesDT = new ArrayList<GradeDT>();
		grades = gradeService.findAll();
		for(Grade grade : grades){
			GradeDT tempDt = new GradeDT();
			tempDt.setGradeId(grade.getGradeId());
			tempDt.setGradeType(grade.getGradeType());
			tempDt.setActive(grade.getActive());
			gradesDT.add(tempDt);
		}
		response.setSuccessResponse(gradesDT);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody GradeDT gradeDT) {

		ABFResponse response = new ABFResponse();
		Grade gradeEntity = new Grade();
		BeanUtils.copyProperties(gradeDT, gradeEntity);
		logger.info("skillData:" + gradeDT);

		try {
			gradeService.create(gradeEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody GradeDT gradeDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			Grade gradeEntity = new Grade();
			BeanUtils.copyProperties(gradeDT, gradeEntity);
			gradeService.update(gradeEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") int gradeId)

	{
		ABFResponse response = new ABFResponse();

		try {
			gradeService.delete(gradeId);
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
	public ABFResponse find(@PathVariable("id") int gradeId)

	{
		ABFResponse response = new ABFResponse();

		try {

			Grade grade = gradeService.find(gradeId);
			response.setSuccessResponse(grade);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

}

