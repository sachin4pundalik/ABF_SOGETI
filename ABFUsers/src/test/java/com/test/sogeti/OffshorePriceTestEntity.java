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
import com.sogeti.model.OffshorePriceDT;
import com.sogeti.service.BusinessLineService;
import com.sogeti.service.OffshorePriceService;
import com.sogeti.serviceImpl.BusinessLineServiceImpl;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;

/**
 * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class OffshorePriceTestEntity {

	private static final int OffshorePrice_ID = 344;
	private static final String OffshorePrice_NAME = "OffshorePriceName_modify";

	private MockMvc mockMvc;
	BusinessLineService businessLineService;


	private OffshorePriceService OffshorePriceServiceMock = Mockito.mock(OffshorePriceService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(OffshorePriceServiceMock);

		businessLineService = Mockito.mock(BusinessLineServiceImpl.class);
		;
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllOffshorePricesTest() throws Exception {
		mockMvc.perform(get("/OffshorePrice/all")).andExpect(status().isOk());
		Mockito.verifyZeroInteractions(OffshorePriceServiceMock);
	}

	@Test
	public void updateOffshorePriceTestTthowException() {

		OffshorePriceDT OffshorePriceDT = new OffshorePriceDT();
		OffshorePriceDT.setOffshorepriceId(1);
		OffshorePriceDT.setPrice(new BigDecimal(33));
		OffshorePriceDT.setStayTypeName("name");
		try {
			mockMvc.perform(put("/offshorePrice/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateOffshorePriceTest() throws Exception {
		Integer mockInt = new Integer(2);
		BusinessLine businessLineMock = Mockito.mock(BusinessLine.class);
		;
		OffshorePriceDT offshorePriceDT = new OffshorePriceDT();
		offshorePriceDT.setOffshorepriceId(OffshorePrice_ID);
		offshorePriceDT.setPrice(new BigDecimal(33));
		offshorePriceDT.setStayTypeName("name");
		offshorePriceDT.setBusinessLineId(1);
		offshorePriceDT.setBandId(1);
		offshorePriceDT.setStayTypeId(1);
		when(businessLineService.find(offshorePriceDT.getBusinessLineId())).thenReturn(businessLineMock);
		mockMvc.perform(put("/offshorePrice/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(offshorePriceDT))).andExpect(status().isOk());

	}

}
