/**
 * 
 */
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
import com.sogeti.db.models.UserRole;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.UserRoleDT;
import com.sogeti.service.ContractManager;
import com.sogeti.service.UserRoleService;

/**
 * User Role controller class  provides implementations for the contract. 
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
 * 03/05/2016       vkalyana                  N/A          ABF        Created.
 * 
 * =============================================================================
 * 
 * </PRE>
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

	private Logger logger = Logger.getLogger(UserRoleController.class);
	
	@Autowired
	UserRoleService userRoleService; 
	
	@Autowired
	ContractManager contractManager;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ABFResponse getAllUserRoles() {

		ABFResponse response = new ABFResponse();
		List<UserRole> userRoles = null;
		List<UserRoleDT> roleDtList = new ArrayList<>();
		UserRoleDT roleDT = null;
		try {
			userRoles = userRoleService.findAll();
			for (UserRole role : userRoles) {
				roleDT = new UserRoleDT(role.getUserRoleId(), role.getUserRole());
				
				roleDtList.add(roleDT);
			}
			
			response.setSuccessResponse(roleDtList);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (Exception e) {
			logger.error("Exception : ", e);
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ABFResponse create(@RequestBody UserRoleDT UserRoleDt) {

		ABFResponse response = new ABFResponse();
		UserRole role = new UserRole();
		BeanUtils.copyProperties(UserRoleDt, role);
		logger.info("ContractData:" + role);

		try {
			
			userRoleService.create(role);
			response.setStatus(ABFConstants.STATUS_SUCCESS);

		} catch (PersistenceException e) {
			logger.error("Exception : ", e);
			response.setStatus(ABFConstants.STATUS_FAILURE);
			response.setFailureResponse(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ABFResponse update(@RequestBody UserRoleDT costDT)

	{
		ABFResponse response = new ABFResponse();

		try {
			UserRole role = new UserRole();
			BeanUtils.copyProperties( costDT, role);
			userRoleService.update(role);
			
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method..." + e);

			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ABFResponse delete(@PathVariable("id") Integer fixedContractId)

	{
		ABFResponse response = new ABFResponse();

		try {
			userRoleService.delete(fixedContractId);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ... " + e);
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ABFResponse find(@PathVariable("id") int UserRoleId)

	{
		ABFResponse response = new ABFResponse();

		try {
			
			UserRole role = userRoleService.find(UserRoleId);
			UserRoleDT roleDT = new UserRoleDT(role.getUserRoleId(), role.getUserRole());
			
			response.setSuccessResponse(roleDT);
			response.setStatus(ABFConstants.STATUS_SUCCESS);
		} catch (TechnicalException e) {
			logger.error("Exception in method ..." + e);
			response.setFailureResponse("Cannot find data.");
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}

		return response;
	}
	
}
