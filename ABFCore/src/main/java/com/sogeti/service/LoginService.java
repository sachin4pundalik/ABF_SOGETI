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

package com.sogeti.service;

import java.util.List;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.Login;

public interface LoginService
{

    Login getEmployee(String email) throws TechnicalException;
    
    Login authenticateUser(String email, String password) throws TechnicalException;
    
    List<Login> getAllNonAdminUsers() throws TechnicalException;
    
    Login registerUser(Login login) throws TechnicalException;
    Login updateEmployee(Login user) throws TechnicalException;
}
