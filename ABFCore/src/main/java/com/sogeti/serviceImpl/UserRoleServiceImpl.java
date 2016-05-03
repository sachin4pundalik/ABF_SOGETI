/**
 * 
 */
package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.UserRoleDAO;
import com.sogeti.db.models.UserRole;
import com.sogeti.service.UserRoleService;

/**
 * @author vkalyana
 *
 */

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDAO userRoleDAO;
	
	public List<UserRole> findAll() {
		return userRoleDAO.findAll();
	}

	public void create(UserRole t) {
		userRoleDAO.create(t);
	}

	public void delete(Integer id) {
		userRoleDAO.delete(id);
	}

	public UserRole find(Integer id) {
		
		return userRoleDAO.find(id);
	}

	public UserRole update(UserRole t) {
		return userRoleDAO.update(t);
	}

}
