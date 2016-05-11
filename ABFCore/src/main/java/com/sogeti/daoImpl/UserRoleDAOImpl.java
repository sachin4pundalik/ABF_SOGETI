package com.sogeti.daoImpl;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.UserRoleDAO;
import com.sogeti.db.models.Login;
import com.sogeti.db.models.UserRole;

@Component("userRoleDAO")
public class UserRoleDAOImpl extends GenericDaoImpl<UserRole> implements UserRoleDAO {
	
	
	public UserRole getAdminRole(String adminRoleName) throws TechnicalException {
		String sqlQuery = "FROM UserRole ur where ur.userRole = :adminRoleName";
		
		Query query = this.em.createQuery(sqlQuery);
		query.setParameter("adminRoleName", adminRoleName);
		UserRole adminUser = (UserRole) query.getSingleResult();
		//LOGGER.info(" EmployeeSearchDaoImpl getEmployee:: " + user);
		return adminUser;
	}

}