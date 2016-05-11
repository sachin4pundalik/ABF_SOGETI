package com.sogeti.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.KtContractDAO;
import com.sogeti.db.models.Contract;
import com.sogeti.db.models.KtContract;

@Repository("ktContractDao")
@Transactional
public class KtContractDaoImpl implements KtContractDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<KtContract> getKtContractsByContractId(Contract contractId) throws TechnicalException {
		try
	      {
	         Query query = this.entityManager.createQuery("select ktc from KtContract ktc where ktc.contract = :contract");
	         query.setParameter("contract", contractId);
	         return query.getResultList();
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in KtContractDaoImpl.getKtContractsByContractId()", e);
	      }
	}

	public KtContract getKtContractById(int amId) throws TechnicalException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveKtContract(KtContract ktContract) throws TechnicalException {
		boolean result = false;
        try {
            entityManager.merge(ktContract);
            flushAndClear();
            result = true;
        } catch (PersistenceException e) {
            throw new TechnicalException("ERROR WHILE SAVING AM CONTRACT RESOURCE",e);
        }

        return result;
	}

	public boolean saveKtContractBatch(List<KtContract> ktContracts) throws TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}

	public Contract getContractById(int contractId) {
		try
	      {
	         return entityManager.find(Contract.class, contractId);
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in AmContractDaoImpl.getContractById()", e);
	      }
	}
	
	public boolean deleteKtContract(KtContract KtContract) throws TechnicalException{
		try
	      {
	         this.entityManager.remove(KtContract);
	         return true;
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in KtContractDaoImpl.deleteKtContract()", e);
	      }
	}
	
	private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

}
