package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.RoleDAO;
import com.sogeti.db.models.Role;
import com.sogeti.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO roleTypeDAO;

	public List<Role> findAll() {
		return roleTypeDAO.findAll();
	}

	public void create(Role role) {
		roleTypeDAO.create(role);
	}

	public void delete(Integer roleId) {
		roleTypeDAO.delete(roleId);

	}

	public Role find(Integer roleId) {
		return roleTypeDAO.find(roleId);
	}

	public Role update(Role role) {
		return roleTypeDAO.update(role);
	}

}
