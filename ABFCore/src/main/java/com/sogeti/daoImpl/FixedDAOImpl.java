package com.sogeti.daoImpl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.sogeti.dao.FixedDAO;
import com.sogeti.db.models.FixedContract;

@Component("fixedDAO")
public class FixedDAOImpl extends GenericDaoImpl<FixedContract> implements FixedDAO {

	public List<FixedContract> getFixedContractsForAContract(Integer contractId) {
		Query q = em.createQuery("from FixedContract fc where fc.contract.contractId = :contractId");
		q.setParameter("contractId", contractId);
		List<FixedContract> fcList = q.getResultList();
		return fcList;
	}

}