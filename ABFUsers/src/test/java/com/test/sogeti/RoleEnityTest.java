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

import com.sogeti.model.RoleDT;
import com.sogeti.service.RoleService;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;

/**
 * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class RoleEnityTest {

	private static final int Role_ID = 1;
	private static final String Role_NAME = "RoleName_modify";

	private MockMvc mockMvc;

	private RoleService RoleServiceMock = Mockito.mock(RoleService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(RoleServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addRoleTest() throws Exception {

		RoleDT roleDT = new RoleDT();
		roleDT.setRoleType("Role");
		mockMvc.perform(post("/role/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(roleDT))).andExpect(status().isOk());
	}

	@Test
	public void addRoleTestWithDBError() throws Exception {

		RoleDT roleDT = new RoleDT();
		roleDT.setRoleId(999999);
		roleDT.setRoleType("Role");
		mockMvc.perform(post("/role/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(roleDT))).andExpect(status().isOk()).andExpect(
						jsonPath("$.failureResponse").value(containsString("org.hibernate.PersistentObjectException")));
	}

	@Test
	public void getAllRolesTest() throws Exception {
		mockMvc.perform(get("/role/all")).andExpect(status().isOk());
		Mockito.verifyZeroInteractions(RoleServiceMock);
	}

	@Test
	public void findRoleWithInvalidFindId() {
		try {

			mockMvc.perform(get("/role/find/" + 34334434).contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.successResponse").value(nullValue(String.class)));
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(), containsString("java.lang.NullPointerException"));
		}
	}

	@Test
	public void updateRoleTestTthowException() {

		RoleDT roleDT = new RoleDT();
		roleDT.setRoleType(null);
		try {
			mockMvc.perform(put("/role/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateRoleTest() throws Exception {
		RoleDT roleDT = new RoleDT();
		roleDT.setRoleId(Role_ID);
		roleDT.setRoleType(Role_NAME);
		mockMvc.perform(put("/role/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(roleDT))).andExpect(jsonPath("$.status").value("success"));

		mockMvc.perform(get("/role/find/" + Role_ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status").value("success"));
	}

}
