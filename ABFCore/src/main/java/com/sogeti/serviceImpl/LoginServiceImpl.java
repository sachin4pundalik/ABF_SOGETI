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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.EmployeeSearchDao;
import com.sogeti.model.User;
import com.sogeti.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService
{

   /**
    * getEmployee from the database
    */
   @Autowired
   EmployeeSearchDao employeeDao;

   public User getEmployee(String email, String password) throws TechnicalException{
      return employeeDao.getEmployee(email, password);
   }

	public User authenticateUser(String email, String password) throws TechnicalException {
		 User user = employeeDao.getEmployeeByUserName(email);
		 try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashPassword = md.digest(password.getBytes());
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hashedBytes = digest.digest(password.getBytes("UTF-8"));
			String hashedPassword = convertByteArrayToHexString(hashedBytes);
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
	
	private static String convertByteArrayToHexString(byte[] arrayBytes) {
	    StringBuffer stringBuffer = new StringBuffer();
	    for (int i = 0; i < arrayBytes.length; i++) {
	        stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
	                .substring(1));
	    }
	    return stringBuffer.toString();
	}

}