package com.sogeti.daoImpl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.sogeti.dao.BusinessLineDAO;
import com.sogeti.db.models.BusinessLine;



@Component("businessLineDAO")
public class BusinessLineDAOImpl extends GenericDaoImpl<BusinessLine> implements BusinessLineDAO {

	@SuppressWarnings("unchecked")
	public List<BusinessLine> getBusinessLinesForResource(Integer resourcetypeId) {
		Query q = em.createQuery("from BusinessLine bl where bl.resourceType.resourcetypeId = :resourcetypeId");
		q.setParameter("resourcetypeId", resourcetypeId);
		List<BusinessLine> fcList = q.getResultList();
		return fcList;
	}

	@SuppressWarnings("unchecked")
	public List<BusinessLine> getBusinessLinesForResourceAndSkill(Integer resourceTypeId, Integer skillId) {
		Query q = em.createQuery("from BusinessLine bl where bl.resourceType.resourcetypeId=  :resourcetypeId and bl.skill.skillId= :skillId");
		q.setParameter("resourcetypeId", resourceTypeId);
		q.setParameter("skillId", skillId);
		List<BusinessLine> fcList = q.getResultList();
		return fcList;
	}

}