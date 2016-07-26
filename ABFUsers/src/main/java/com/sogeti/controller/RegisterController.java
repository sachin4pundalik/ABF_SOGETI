/**
 * 
 */
package com.sogeti.controller;

import java.util.Date;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.constants.ABFConstants;
import com.sogeti.db.models.Login;
import com.sogeti.db.models.UserRole;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.LoginDT;
import com.sogeti.service.LoginService;
import com.sogeti.service.UserRoleService;

/**
 * @author vkalyana
 *
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

	private static final Logger logger = Logger.getLogger(RegisterController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping( value = "/create", method = RequestMethod.POST)
	public ABFResponse registerUser(@RequestBody LoginDT login) {
		logger.info("Register creation starts");
		ABFResponse abfResponse = new ABFResponse();
		Login existUser;
		
		try {
			existUser = loginService.getEmployee(login.getUserName());
			if(Objects.isNull(existUser)){
				existUser = new Login();
				
				BeanUtils.copyProperties(login, existUser);
				
				existUser.setUserRole(userRoleService.find(2)); //fetch user role
				existUser.setLastLoginDatetime(new Date(System.currentTimeMillis()));
				existUser.setActive(1);
				
				existUser = loginService.registerUser(existUser);
				
				abfResponse.setStatus(ABFConstants.STATUS_SUCCESS);
				abfResponse.setSuccessResponse(existUser);
			}else {
				abfResponse.setStatus(ABFConstants.STATUS_FAILURE);
				abfResponse.setFailureResponse("User already exists.");
			}
		} catch (TechnicalException e) {
			abfResponse.setStatus(ABFConstants.STATUS_FAILURE);
			abfResponse.setFailureResponse(e.getMessage());
		}
		
		logger.info("Register creation ends");
		return abfResponse;
	}
}
