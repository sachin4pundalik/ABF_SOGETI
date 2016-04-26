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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.generic.Status;

@RestController
public class ChangePasswordController {

	private static final Logger logger = Logger.getLogger(ChangePasswordController.class);

	/**
	 * This method is for change password
	 * 
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changepassword/{password}", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	@ResponseBody
	public Status changePassword(@PathVariable("password") String password, HttpServletRequest request) {
		return null;
	}

}
