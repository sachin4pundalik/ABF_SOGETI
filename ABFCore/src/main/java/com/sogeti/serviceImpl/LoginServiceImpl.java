/*------------------------------------------------------------------------------
 *     Ident: Centre of Excellence Java
 *    Author: shahrush
 * Copyright: (c) 10 august 2015 Sogeti Nederland B.V. All Rights Reserved.
 *------------------------------------------------------------------------------
 * Sogeti Nederland B.V.    |  No part of this file may be reproduced or 
 * Centre of Excellence Java|  transmitted in any form or by any means,        
 * Lange Dreef 17           |  electronic or mechanical, for the purpose,      
 * 4131 NJ VIANEN           |  without the express written permission of the   
 * The Netherlands          |  copyright holder.
 *------------------------------------------------------------------------------
 */

package com.sogeti.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.EmployeeSearchDao;
import com.sogeti.dao.UserRoleDAO;
import com.sogeti.db.models.Login;
import com.sogeti.db.models.UserRole;
import com.sogeti.service.LoginService;
import com.sogeti.utils.DigestUtil;

@Service("loginService")
public class LoginServiceImpl implements LoginService
{

	private Logger logger = Logger.getLogger(LoginServiceImpl.class);
   /**
    * getEmployee from the database
    */
   @Autowired
   EmployeeSearchDao employeeDao;
   
   @Autowired
   UserRoleDAO userRoleDao;

   public Login getEmployee(String email) throws TechnicalException{
      return employeeDao.getEmployee(email);
   }

   	public Login updateEmployee(Login user) throws TechnicalException {
   		return employeeDao.update(user);
   	}
   	
	public Login authenticateUser(String email, String password) throws TechnicalException {
		 Login user = employeeDao.getEmployeeByUserName(email);
		 try {
			String hashedPassword = DigestUtil.getEncripted(password);
			if(hashedPassword.equals(user.getPassword())){
				return user;
			}else {
				throw new TechnicalException("Login Failed ", new Exception());
			}
		} catch (NoSuchAlgorithmException e) {
			throw new TechnicalException("No Such Algorithm", e);
		} catch (UnsupportedEncodingException e) {
			throw new TechnicalException("Unsupported Encoding Exception", e);
		}
	}
	

	public List<Login> getAllNonAdminUsers() throws TechnicalException {
		UserRole adminUserRole = userRoleDao.getAdminRole("Admin");
		return employeeDao.getAllNonAdminUsers(adminUserRole);
	}

	public Login registerUser(Login login) throws TechnicalException {
		logger.info("register user service" + login);
		try {
			login.setPassword(DigestUtil.getEncripted(login.getPassword()));
			login = employeeDao.create(login);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new TechnicalException(e.getMessage(), e);
		}
		logger.info("register user service" + login);
		return login;
	}
}