package com.sogeti.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.dao.ResourceTypeDAO;
import com.sogeti.db.models.ResourceType;
import com.sogeti.service.ResourceTypeManager;


@Service("resourceTypeManager")
public class ResourceTypeManagerImpl  implements ResourceTypeManager{
	
	@Autowired
	ResourceTypeDAO resourceTypeDao;

	public List<ResourceType> findAll() {
		return resourceTypeDao.findAll();
	}

	

	public void delete(Integer resourceTypeId) {
		
		resourceTypeDao.delete(resourceTypeId);
	}

	public ResourceType find(Integer resourceTypeId ) {
		return resourceTypeDao.find(resourceTypeId);
	}

	public ResourceType update(ResourceType resourceType) {
			return resourceTypeDao.update(resourceType);
	}



	public void create(ResourceType resourceType) {
		resourceTypeDao.create(resourceType);
		
	}



	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}



	
	
	
	
}
