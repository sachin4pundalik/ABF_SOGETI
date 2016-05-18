package com.sogeti.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.sogeti.GenericExceptions.TechnicalException;
import com.sogeti.dao.OffshorePriceDAO;
import com.sogeti.db.models.Band;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.OffshorePrice;
import com.sogeti.db.models.StayType;

@Component("offShorePriceDAO")
public class OffshorePriceDAOImpl extends GenericDaoImpl<OffshorePrice> implements OffshorePriceDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public OffshorePrice getOnShorePriceIdFor(BusinessLine bline, Band band, StayType stayType) throws TechnicalException{
		OffshorePrice offShorePrice = null;
		try
	      {
	         Query query = this.entityManager.createQuery("select ofp from OffshorePrice ofp where ofp.businessLine = :bline and ofp.band = :band and ofp.stayType = :stayType");
	         query.setParameter("bline", bline);
	         query.setParameter("band", band);
	         query.setParameter("stayType", stayType);
	         offShorePrice = (OffshorePrice) query.getSingleResult();	         
	      }
	      catch (PersistenceException e)
	      {
	         throw new TechnicalException("Technical Exception in AmContractDaoImpl.getAmContractsByContractId()", e);
	      }
		return offShorePrice;
	
	}

}