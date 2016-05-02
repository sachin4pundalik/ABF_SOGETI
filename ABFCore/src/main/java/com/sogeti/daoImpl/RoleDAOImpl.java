package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.RoleDAO;
import com.sogeti.db.models.Role;



@Component("roleDAO")
public class RoleDAOImpl extends GenericDaoImpl<Role> implements RoleDAO {

}