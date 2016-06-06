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

import com.sogeti.model.StatusDT;
import com.sogeti.service.StatusService;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;
import static org.hamcrest.CoreMatchers.*;

/**
 * * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class StatusEntityTest {

	private static final int Status_ID = 1;
	private static final String STATUS_NAME = "StatusName_modify";
	private static final int StatusID_NOTINDB = 34566779;
	private static final String STATUS_DESCRIPTION = "desc";
	private static final String STATUS_NAME_CREAATED = "StatusName";

	private MockMvc mockMvc;

	private StatusService StatusServiceMock = Mockito.mock(StatusService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(StatusServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addStatusTest() throws Exception {

		StatusDT StatusDT1 = new StatusDT(0, STATUS_DESCRIPTION, STATUS_NAME_CREAATED);
		StatusDT StatusDT = new StatusDT();
		StatusDT.setDescription(STATUS_DESCRIPTION);
		StatusDT.setStatusName(STATUS_NAME);
		StatusDT.setStatusName("Status_Name");
		mockMvc.perform(post("/status/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(StatusDT))).andExpect(status().isOk());
	}

	@Test
	public void addStatusTestWithDBError() throws Exception {

		StatusDT StatusDT = new StatusDT(999999, STATUS_DESCRIPTION, STATUS_NAME_CREAATED);
		mockMvc.perform(post("/status/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(StatusDT))).andExpect(status().isOk()).andExpect(
						jsonPath("$.failureResponse").value(containsString("org.hibernate.PersistentObjectException")));
	}

	@Test
	public void getAllStatussTest() throws Exception {
		mockMvc.perform(get("/status/all")).andExpect(status().isOk());
		Mockito.verifyZeroInteractions(StatusServiceMock);
	}

	@Test
	public void findStatusWithInvalidFindId() throws Exception {

		mockMvc.perform(get("/status/find/" + StatusID_NOTINDB).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.successResponse").value(nullValue(String.class)));

	}

	@Test
	public void updateStatusTestTthowException() {

		StatusDT StatusDT = new StatusDT(999999, null, null);
		try {
			mockMvc.perform(put("/status/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateStatusTest() throws Exception {
		StatusDT StatusDT = new StatusDT(999999, STATUS_DESCRIPTION, STATUS_NAME);
		StatusDT.setStatusId(999999);
		StatusDT.setDescription(STATUS_DESCRIPTION);
		StatusDT.setStatusName(STATUS_NAME);
		mockMvc.perform(put("/status/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(StatusDT))).andExpect(jsonPath("$.status").value("success"));

		mockMvc.perform(get("/status/find/" + Status_ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status").value("success"));
	}

}
