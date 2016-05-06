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
import com.sogeti.db.models.Role;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.RoleDT;
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
@RequestMapping("/role")
public class RoleController {
	private Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	RoleService roleService;


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getRoles() {

		ABFResponse response = new ABFResponse();
		List<Role> roles = new ArrayList<Role>();
		List<RoleDT> rolesDT = new ArrayList<RoleDT>();
		roles = roleService.findAll();
		for(Role role : roles){
			RoleDT tempDT = new RoleDT();
			tempDT.setRoleId(role.getRoleId());
			tempDT.setRoleType(role.getRoleType());
			rolesDT.add(tempDT);
		}
		response.setSuccessResponse(rolesDT);
		response.setStatus(ABFConstants.STATUS_SUCCESS);

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody RoleDT RoleDT) {

		ABFResponse response = new ABFResponse();
		Role RoleEntity = new Role();
		BeanUtils.copyProperties(RoleDT, RoleEntity);
		logger.info("roleDT:" + RoleDT);

		try {
			roleService.create(RoleEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody RoleDT RoleDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			Role RoleEntity = new Role();
			BeanUtils.copyProperties(RoleDT, RoleEntity);
			roleService.update(RoleEntity);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") int roleId)

	{
		ABFResponse response = new ABFResponse();

		try {
			roleService.delete(roleId);
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
	public ABFResponse find(@PathVariable("id") int roleId)

	{
		ABFResponse response = new ABFResponse();

		try {

			Role role = roleService.find(roleId);
			response.setSuccessResponse(role);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

}
