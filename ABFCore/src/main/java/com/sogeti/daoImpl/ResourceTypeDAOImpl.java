package com.sogeti.daoImpl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.dao.ResourceTypeDAO;
import com.sogeti.db.models.ResourceType;



@Component("resourceTypeDao")
public class ResourceTypeDAOImpl extends GenericDaoImpl<ResourceType> implements ResourceTypeDAO {

}