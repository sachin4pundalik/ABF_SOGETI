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
import com.sogeti.db.models.AmContract;
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
	public List<AmContract> getAmContractsByContractId(Contract contractId) {
		try
	      {
	         Query query = this.entityManager.createQuery("select amc from AmContract amc where amc.contract = :contract");
	         query.setParameter("contract", contractId);
	         return query.getResultList();
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in AmContractDaoImpl.getAmContractsByContractId()", e);
	      }
	}

	public AmContract getAmContractById(int amId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveAmContract(AmContract AmContract) throws TechnicalException{
		boolean result = false;
        try {
            entityManager.merge(AmContract);
            flushAndClear();
            result = true;
        } catch (PersistenceException e) {
            throw new TechnicalException("ERROR WHILE SAVING AM CONTRACT RESOURCE",e);
        }

        return result;

	}

	public boolean saveAmContractBatch(List<AmContract> AmContracts) {
		// TODO Auto-generated method stub
		return false;

	}
	
	private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

}
