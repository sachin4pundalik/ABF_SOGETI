package com.sogeti.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.OnshorePriceDAO;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.Grade;
import com.sogeti.db.models.OnshorePrice;
import com.sogeti.db.models.Role;

@Component("onshorePriceDAO")
public class OnshorePriceDAOImpl extends GenericDaoImpl<OnshorePrice> implements OnshorePriceDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public OnshorePrice getOnShorePriceIdFor(BusinessLine bline, Role role, Grade grade) throws TechnicalException{
		OnshorePrice onshorePrice = null;
		try
	      {
	         Query query = this.entityManager.createQuery("select onp from OnshorePrice onp where onp.businessLine = :bline and onp.role = :role and onp.grade = :grade ");
	         query.setParameter("bline", bline);
	         query.setParameter("role", role);
	         query.setParameter("grade", grade);
	         onshorePrice = (OnshorePrice) query.getSingleResult();	         
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in AmContractDaoImpl.getAmContractsByContractId()", e);
	      }
		return onshorePrice;
	}
}