package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;

import com.sogeti.dao.UserRoleDAO;
import com.sogeti.db.models.UserRole;

@Component("userRoleDAO")
public class UserRoleDAOImpl extends GenericDaoImpl<UserRole> implements UserRoleDAO {

}