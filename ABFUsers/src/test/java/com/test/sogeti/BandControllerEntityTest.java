package com.test.sogeti;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sogeti.model.BandDT;
import com.sogeti.service.BandService;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class BandControllerEntityTest {

	private static final int BAND_ID = 64;
	private static final String BAND_NAME = "BandName_modify";

	private MockMvc mockMvc;


	private BandService bandServiceMock = Mockito.mock(BandService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(bandServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addBandTest() throws Exception {

		BandDT bandDT = new BandDT();
		bandDT.setBandName(BAND_NAME);
		mockMvc.perform(post("/band/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(bandDT))).andExpect(status().isOk());
	}

	@Test
	public void addBandTestWithDBError() throws Exception {

		BandDT bandDT = new BandDT();
		bandDT.setBandId(999999);
		bandDT.setBandName(BAND_NAME);
		mockMvc.perform(post("/band/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(bandDT))).andExpect(status().isOk()).andExpect(
						jsonPath("$.failureResponse").value(containsString("org.hibernate.PersistentObjectException")));
	}

	@Test
	public void getAllBandsTest() throws Exception {
		mockMvc.perform(get("/band/all")).andExpect(status().isOk());
		Mockito.verifyZeroInteractions(bandServiceMock);
	}

	@Test
	public void findBandWithInvalidFindId() throws Exception {

		mockMvc.perform(get("/band/find/" + 34334434).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.successResponse").value(nullValue(String.class)));

	}

	@Test
	public void updateBandTestTthowException() {

		BandDT bandDT = new BandDT();
		bandDT.setBandName(null);
		try {
			mockMvc.perform(put("/band/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateBandTest() throws Exception {
		BandDT bandDT = new BandDT();
		bandDT.setBandId(BAND_ID);
		bandDT.setBandName(BAND_NAME);
		mockMvc.perform(put("/band/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(bandDT))).andExpect(jsonPath("$.status").value("success"));

		mockMvc.perform(get("/band/find/" + BAND_ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.successResponse.bandName").value(BAND_NAME));
	}

}
