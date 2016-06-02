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

import com.sogeti.model.ResourceTypeDT;
import com.sogeti.service.ResourceTypeManager;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;

/**
 * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class ResourceTypeEntityTest {

	private static final int ResourceType_ID = 1;
	private static final String ResourceType_NAME = "ResourceTypeName_modify";

	private MockMvc mockMvc;

	private ResourceTypeManager ResourceTypeServiceMock = Mockito.mock(ResourceTypeManager.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(ResourceTypeServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addResourceTypeTest() throws Exception {

		ResourceTypeDT ResourceTypeDT = new ResourceTypeDT();
		ResourceTypeDT.setResourceType("resourceType");
		mockMvc.perform(post("/resourcetype/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(ResourceTypeDT))).andExpect(status().isOk());
	}

	@Test
	public void addResourceTypeTestWithDBError() throws Exception {

		ResourceTypeDT resourceTypeDT = new ResourceTypeDT();
		resourceTypeDT.setResourcetypeId(999999);
		resourceTypeDT.setResourceType("ResourceType");
		mockMvc.perform(post("/resourcetype/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(resourceTypeDT))).andExpect(status().isOk()).andExpect(
						jsonPath("$.failureResponse").value(containsString("org.hibernate.PersistentObjectException")));
	}

	@Test
	public void getAllResourceTypesTest() throws Exception {
		mockMvc.perform(get("/resourcetype/all")).andExpect(status().isOk());
		Mockito.verifyZeroInteractions(ResourceTypeServiceMock);
	}

	@Test
	public void findResourceTypeWithInvalidFindId() throws Exception {

		mockMvc.perform(get("/resourcetype/find/" + 34334434).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.successResponse").value(nullValue(String.class)));

	}

	@Test
	public void updateResourceTypeTestTthowException() {

		ResourceTypeDT resourceTypeDT = new ResourceTypeDT();
		resourceTypeDT.setResourceType("ResourceType");
		try {
			mockMvc.perform(put("/resourcetype/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateResourceTypeTest() throws Exception {
		ResourceTypeDT resourceTypeDT = new ResourceTypeDT();
		resourceTypeDT.setResourcetypeId(ResourceType_ID);
		resourceTypeDT.setResourceType(ResourceType_NAME);
		mockMvc.perform(put("/resourcetype/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(resourceTypeDT)))
				.andExpect(jsonPath("$.status").value("success"));

	}

}
