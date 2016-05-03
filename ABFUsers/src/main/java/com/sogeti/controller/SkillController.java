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
import com.sogeti.db.models.Skill;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.SkillDT;
import com.sogeti.service.ResourceTypeManager;
import com.sogeti.service.SkillService;

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
@RequestMapping("/skill")
public class SkillController {
	private Logger logger = Logger.getLogger(SkillController.class);

	@Autowired
	SkillService skillService;

	@Autowired
	ResourceTypeManager resourceTypeManager;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getSkills() {

		ABFResponse response = new ABFResponse();
		List<Skill> skills = new ArrayList<Skill>();
		List<SkillDT> skillsDT = new ArrayList<SkillDT>();
		skills = skillService.findAll();
		for(Skill skill : skills){
			SkillDT tempDT = new SkillDT();
			tempDT.setSkillId(skill.getSkillId());
			tempDT.setSkillName(skill.getSkillName());
			skillsDT.add(tempDT);
		}
		response.setSuccessResponse(skillsDT);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody SkillDT skillDT) {

		ABFResponse response = new ABFResponse();
		Skill skillEntity = new Skill();
		BeanUtils.copyProperties(skillDT, skillEntity);
		logger.info("skillData:" + skillDT);

		try {
			skillService.create(skillEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody SkillDT skillDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			Skill skillEntity = new Skill();
			BeanUtils.copyProperties(skillDT, skillEntity);
			skillService.update(skillEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") int skillId)

	{
		ABFResponse response = new ABFResponse();

		try {
			skillService.delete(skillId);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ABFResponse find(@PathVariable("id") int skillId)

	{
		ABFResponse response = new ABFResponse();

		try {

			Skill skill = skillService.find(skillId);
			response.setSuccessResponse(skill);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

}
