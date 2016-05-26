package com.sogeti.test;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.dao.AmContractDao;
import com.sogeti.dao.ContractDao;
import com.sogeti.dao.OffshorePriceDAO;
import com.sogeti.dao.OnshorePriceDAO;
import com.sogeti.daoImpl.ContractDaoImpl;
import com.sogeti.db.models.AmContract;
import com.sogeti.db.models.Contract;
import com.sogeti.db.models.OffshorePrice;
import com.sogeti.db.models.OnshorePrice;

import junit.framework.TestCase;

@ContextConfiguration(locations = "classpath:jpaConext_test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AmContractDaoImplTest extends TestCase{
	
	private static final Logger logger = Logger.getLogger(ContractDaoImplTest.class);	
	ContractDaoImpl contractDaoImpl = new ContractDaoImpl();
	@Mock
	private EntityManager entityManager;
	   
	@Autowired
	private AmContractDao amContractDao;
	
	@Autowired
	private ContractDao contractDAO;
	
	@Autowired
	private OnshorePriceDAO onshorePriceDAO;
	
	@Autowired 
	private OffshorePriceDAO offshorePriceDAO;
	/*
	@Mock
    private ContractDao contractDAO;*/

	@Test
	@Transactional
	@Rollback(true)
	public void testSaveAmContract() {
		AmContract amContract = new AmContract();
	    //  create mock
		Contract contract = new Contract();
		OffshorePrice offshorePrice = new OffshorePrice();
		OnshorePrice onShorePrice = new OnshorePrice();
		
		Contract contractEntity = new Contract();
		contractEntity.setCustomerName("ABN AMRO");
		contractEntity.setCompanyName("ABN NL");
		contractEntity.setContractName("PAYMENTS");
		
		contractDAO.createContract(contract);
		offshorePriceDAO.create(offshorePrice);
		onshorePriceDAO.create(onShorePrice);
		
		amContract.setContract(contract);
		amContract.setOffshorePrice(offshorePrice);
		amContract.setOnshorePrice(null);
		AmContract savedAmContract = amContractDao.saveAmContract(amContract);
		assertNotNull(savedAmContract);
	}

}
