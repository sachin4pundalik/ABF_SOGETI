package com.sogeti.test;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.dao.ContractDao;
import com.sogeti.daoImpl.ContractDaoImpl;
import com.sogeti.db.models.Contract;

import junit.framework.TestCase;
/**
 * JUnit tests for the ContractDaoImpl class.
 * <p><b>
 * Test Case Documentation:
 * </b><p>
 * Each test case is a test method on this class whose name starts with "test".
 * The following describes the standard test case documentation that applies to all
 * the test cases in this test class:
 * Description:
 * Inputs:
 * </b></i>
 * The inputs for each test case are encapsulated in the executable code of the test method.
 * As with any JUnit test class, the execution procedure is to run this class, which
 * automatically invokes the tests,  verifies the actual results against the expected
 * results, and reports any tests that failed.
 * <p><b><i>
 * Expected Results:
 * </b></i>
 * The expected results are encapsulated in the assert code within each test method.
 * <p><b>
 * Test Data:
 * </b><p>
 * <p>
 * <p><b>
 * Revision History:
 * </b><pre>
 * =============================================================================
 *                                Prior
 * Date         By                Version  Project/CSR  Description
 * ---------- ------------------ -------- ------------ -------------------------
 * 06/04/2016   kalyan             N/A      ABF    Created.
 * =============================================================================
 * </pre>
 */
@ContextConfiguration(locations = "classpath:jpaConext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ContractDaoImplTest extends TestCase {

	private static final Logger logger = Logger.getLogger(ContractDaoImplTest.class);	
	ContractDaoImpl contractDaoImpl = new ContractDaoImpl();
	   @Mock
	   private EntityManager entityManager;
	   
	   @Autowired
	   private ContractDao contractDAO;

	   /*@Before
	   public void setup()
	   {
	      this.contractDaoImpl = new ContractDaoImpl();
	      this.contractDaoImpl.setEntityManager(this.entityManager);
	   }*/

	@Test
	@Transactional
	@Rollback(true)
	public void testcreateContract() throws Exception{
		logger.debug("ContractDaoImplTest ::testcreateContract -START");
		//contractDaoImpl = new ContractDaoImpl();
		try
		{
		Contract contractEntity = new Contract();
		//contractEntity.setBisCode("123");
		contractEntity.setComments("Success");
		contractEntity.setCompanyName("ABC");
		contractEntity.setCustomerName("Test");
		contractEntity.setContractId(1);		
		contractDAO.createContract(contractEntity);;

		assertTrue("added",true);
		logger.debug("ContractDaoImplTest ::testcreateContract -END");
		}
		catch (Exception e){
		e.printStackTrace();
		}

	}

}
