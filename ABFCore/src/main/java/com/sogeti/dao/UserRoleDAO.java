package com.sogeti.dao;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.db.models.UserRole;

public interface UserRoleDAO  extends GenericDao<UserRole>{
	
	UserRole getAdminRole(String adminRoleName) throws TechnicalException;

}
