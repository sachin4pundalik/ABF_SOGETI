package com.test.sogeti;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sogeti.model.StayTypeDT;
import com.sogeti.service.StayTypeService;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;

/**
 * * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class StatusTypeEntityTest {

	private static final int StayType_ID = 1;
	private static final String StayType_NAME = "StayTypeName_modify";
	private static final String StayType_DESCRIPTION = "desc";
	private static final String StayType_NAME_MODIFIED = "StayTypeName_modify";
	private static final int StayTypeID_NOTINDB = 99999999;

	private MockMvc mockMvc;

	private StayTypeService StayTypeServiceMock = Mockito.mock(StayTypeService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(StayTypeServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addStayTypeTest() throws Exception {

		StayTypeDT stayTypeDT = new StayTypeDT();
		stayTypeDT.setStayType("stayType");
		mockMvc.perform(post("/stayType/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(stayTypeDT))).andExpect(status().isOk());
	}

	@Test
	public void addStayTypeTestWithDBError() throws Exception {

		StayTypeDT stayTypeDT = new StayTypeDT();
		stayTypeDT.setStayTypeId(999999);
		stayTypeDT.setStayType(null);
		mockMvc.perform(post("/stayType/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(stayTypeDT))).andExpect(status().isOk()).andExpect(
						jsonPath("$.failureResponse").value(containsString("org.hibernate.PersistentObjectException")));
	}

	@Test
	public void getAllStayTypesTest() throws Exception {
		mockMvc.perform(get("/stayType/all")).andExpect(status().isOk());
		Mockito.verifyZeroInteractions(StayTypeServiceMock);
	}

	@Test
	public void findStayTypeWithInvalidFindId() throws Exception {

		mockMvc.perform(get("/stayType/find/" + StayTypeID_NOTINDB).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.successResponse").value(nullValue(String.class)));

	}

	@Test
	public void updateStayTypeTestTthowException() {

		StayTypeDT stayTypeDT = new StayTypeDT();
		stayTypeDT.setStayTypeId(99999999);

		stayTypeDT.setStayType(null);
		try {
			mockMvc.perform(put("/stayType/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(stayTypeDT))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateStayTypeTest() throws Exception {
		StayTypeDT stayTypeDT = new StayTypeDT();
		stayTypeDT.setStayTypeId(StayType_ID);
		stayTypeDT.setStayType(StayType_NAME_MODIFIED);
		mockMvc.perform(put("/stayType/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(stayTypeDT)))
				.andExpect(jsonPath("$.status").value("success"));

		mockMvc.perform(get("/stayType/find/" + StayType_ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status").value("success"));
	}

}
