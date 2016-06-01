package com.test.sogeti;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sogeti.db.models.BusinessLine;
import com.sogeti.model.OnshorePriceDT;
import com.sogeti.service.BusinessLineService;
import com.sogeti.service.OnshorePriceService;
import com.sogeti.serviceImpl.BusinessLineServiceImpl;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;

/**
 * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class OnshorePriceEntity {

	private static final int OnshorePrice_ID = 344;
	private static final String OnshorePrice_NAME = "OnshorePriceName_modify";

	private MockMvc mockMvc;
	BusinessLineService businessLineService;

	// @Autowired
	private OnshorePriceService OnshorePriceServiceMock = Mockito.mock(OnshorePriceService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(OnshorePriceServiceMock);

		businessLineService = Mockito.mock(BusinessLineServiceImpl.class);
		;
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllOnshorePricesTest() throws Exception {
		mockMvc.perform(get("/onshorePrice/all"));
		Mockito.verifyZeroInteractions(OnshorePriceServiceMock);
	}

	@Test
	public void updateOnshorePriceTestTthowException() {

		OnshorePriceDT onshorePriceDT = new OnshorePriceDT();
		onshorePriceDT.setOnshorepriceId(1);
		onshorePriceDT.setPrice(new BigDecimal(33));
		onshorePriceDT.setLastUpdatedBy(1);
		try {
			mockMvc.perform(put("/onshorePrice/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateOnshorePriceTest() throws Exception {
		Integer mockInt = new Integer(2);
		BusinessLine businessLineMock = Mockito.mock(BusinessLine.class);
		;
		OnshorePriceDT onshorePriceDT = new OnshorePriceDT();
		onshorePriceDT.setOnshorepriceId(OnshorePrice_ID);
		onshorePriceDT.setPrice(new BigDecimal(33));
		onshorePriceDT.setBusinessLineId(1);
		onshorePriceDT.setLastUpdatedBy(1);
		onshorePriceDT.setGradeId(1);
		onshorePriceDT.setRoleId(1);
		when(businessLineService.find(onshorePriceDT.getBusinessLineId())).thenReturn(businessLineMock);
		mockMvc.perform(put("/onshorePrice/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(onshorePriceDT))).andExpect(status().isOk());

	}

}
