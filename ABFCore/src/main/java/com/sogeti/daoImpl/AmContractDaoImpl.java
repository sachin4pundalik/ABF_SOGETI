package com.sogeti.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.AmContractDao;
import com.sogeti.db.models.AMContractResource;
import com.sogeti.db.models.Contract;

@Repository("amContractDao")
@Transactional
public class AmContractDaoImpl implements AmContractDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Contract getContractById(int contractId){
		try
	      {
	         return entityManager.find(Contract.class, contractId);
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in AmContractDaoImpl.getContractById()", e);
	      }
	}

	 @SuppressWarnings("unchecked")
	public List<AMContractResource> getAmContractResourcesByContractId(Contract contractId) {
		try
	      {
	         Query query = this.entityManager.createQuery("select amc from AMContractResource amc where amc.contract = :contract");
	         query.setParameter("contract", contractId);
	         return query.getResultList();
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in AmContractDaoImpl.getAmContractResourcesByContractId()", e);
	      }
	}

	public AMContractResource getAmContractById(int amId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveAmContractResource(AMContractResource amContractResource) throws TechnicalException{
		boolean result = false;
        try {
            entityManager.merge(amContractResource);
            flushAndClear();
            result = true;
        } catch (PersistenceException e) {
            throw new TechnicalException("ERROR WHILE SAVING AM CONTRACT RESOURCE",e);
        }

        return result;

	}

	public boolean saveAmContractResourceBatch(List<AMContractResource> amContractResources) {
		// TODO Auto-generated method stub
		return false;

	}
	
	private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

	public AMContractResource getAmContractResourceById(int amId) throws TechnicalException {
		// TODO Auto-generated method stub
		return null;
	}

}
