/*------------------------------------------------------------------------------
 *     Ident: Centre of Excellence Java
 *    Author: pawarraj
 * Copyright: (c) 10 august 2015 Sogeti Nederland B.V. All Rights Reserved.
 *------------------------------------------------------------------------------
 * Sogeti Nederland B.V.    |  No part of this file may be reproduced or 
 * Centre of Excellence Java|  transmitted in any form or by any means,        
 * Lange Dreef 17           |  electronic or mechanical, for the purpose,      
 * 4131 NJ VIANEN           |  without the express written permission of the   
 * The Netherlands          |  copyright holder.
 *------------------------------------------------------------------------------
 
package com.sogeti.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.generic.Status;

@Controller
@SessionAttributes("currentUser")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/loginController/{email}/{password}", method = RequestMethod.GET, produces = {
			"application/json", "application/xml" })
	@ResponseBody
	public Status getLoginDetails(@PathVariable("email") String email, @PathVariable("password") String password,
			HttpServletRequest httpServletRequest) {

		try {
			String emailId = "rajeev@gmail.com";
			String pass = "1234";

			if (email.equals(emailId) && password.equals(pass)) {
				httpServletRequest.getSession().setAttribute("currentUser", "Rajeev");
			}
			logger.info("Login successful");
			return new Status(1, "Login Successful");

		} catch (TechnicalException e) {

			logger.error("Login error");
			return new Status(0, "Login Failed ! Either User InActive OR Wrong Username And Password" + e);
		}
	}
}
*/

/*------------------------------------------------------------------------------
 *     Ident: Centre of Excellence Java
 *    Author: pawarraj
 * Copyright: (c) 10 august 2015 Sogeti Nederland B.V. All Rights Reserved.
 *------------------------------------------------------------------------------
 * Sogeti Nederland B.V.    |  No part of this file may be reproduced or 
 * Centre of Excellence Java|  transmitted in any form or by any means,        
 * Lange Dreef 17           |  electronic or mechanical, for the purpose,      
 * 4131 NJ VIANEN           |  without the express written permission of the   
 * The Netherlands          |  copyright holder.
 *------------------------------------------------------------------------------
 */
package com.sogeti.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.constants.ABFConstants;
import com.sogeti.model.ABFResponse;
import com.sogeti.model.User;
import com.sogeti.model.UserForm;
import com.sogeti.service.AmContractService;
import com.sogeti.service.LoginService;

@RestController
@SessionAttributes("currentUser")
@RequestMapping(value = "/login")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired(required = true)
	LoginService loginService;
	
	@Autowired(required = true)
	AmContractService amContractService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ABFResponse getLoginDetails(@RequestBody UserForm userObj, HttpServletRequest request) {

		ABFResponse response = new ABFResponse();
		
		User user = null;

		logger.info(" login controller email :: " + userObj.getUserName());
		logger.info(" login controller password :: " + userObj.getPassword());

		try {
			user = loginService.getEmployee(userObj.getUserName(), userObj.getPassword());
			//user = loginService.authenticateUser(userObj.getUserName(), userObj.getPassword());
			request.getSession().setAttribute("currentUser", user);
			logger.info("Login successful");
			response.setSuccessResponse(user);
			response.setStatus(ABFConstants.STATUS_SUCCESS);			
		} catch (TechnicalException e) {
			logger.error("Login error");
			response.setFailureResponse(e.getMessage());
			response.setStatus(ABFConstants.STATUS_FAILURE);
		}		
		return response;
	}
	
	
}
