package com.sogeti.daoImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.EmployeeSearchDao;
import com.sogeti.db.models.Login;
import com.sogeti.db.models.UserRole;

@Repository("employeeDao")
@Transactional
public class EmployeeSearchDaoImpl extends GenericDaoImpl<Login> implements EmployeeSearchDao {

	private static final Logger LOGGER = Logger.getLogger(EmployeeSearchDaoImpl.class.getName());
	private static final String loginHql = "FROM Login u WHERE u.userName = :email and u.password = :password";

	/**
	 * This used only for junit test cases
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager)
	{
	   this.em = entityManager;
	}

	/**
	 * Get employee details from database
	 */
	public Login getEmployee(String email) throws TechnicalException {

		Query query = this.em.createQuery(loginHql);
		query.setParameter("email", email);
		Login user=null;
		try {
			user = (Login) query.getSingleResult();
		} catch (Exception e) {
			LOGGER.log(Level.FINE, e.getMessage());
			user=null;
		}
		LOGGER.info(" EmployeeSearchDaoImpl getEmployee:: " + user);
		return user;
	}

	public Login getEmployeeByUserName(String email) throws TechnicalException {
		String sqlQuery = "FROM Login u where u.userName = :email";
		
		Query query = this.em.createQuery(sqlQuery);
		query.setParameter("email", email);
		Login user = (Login) query.getSingleResult();
		LOGGER.info(" EmployeeSearchDaoImpl getEmployee:: " + user);
		return user;
	}
	
	public List<Login> getAllNonAdminUsers(UserRole adminRole) throws TechnicalException {
		
		try
	      {
			 Query query = this.em.createQuery("select l from Login l where l.userRole != :adminRole");
	         query.setParameter("adminRole", adminRole);
	         LOGGER.info(" EmployeeSearchDaoImpl getAllNonAdminUsers:: ");
	         return query.getResultList();
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in EmployeeSearchDaoImpl.getAllNonAdminUsers()", e);
	      }
		
	}
	
}
