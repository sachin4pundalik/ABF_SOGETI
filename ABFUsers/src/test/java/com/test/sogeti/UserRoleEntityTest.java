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

import com.sogeti.model.UserRoleDT;
import com.sogeti.service.UserRoleService;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;

/**
 * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class UserRoleEntityTest {

	private static final int UserRole_ID = 1;
	private static final String UserRole_NAME = "UserRoleName_modify";

	private MockMvc mockMvc;

	private UserRoleService UserRoleServiceMock = Mockito.mock(UserRoleService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(UserRoleServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addUserRoleTest() throws Exception {

		UserRoleDT userRoleDT = new UserRoleDT();
		userRoleDT.setUserRole("UserRole");
		mockMvc.perform(post("/userRole/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(userRoleDT))).andExpect(status().isOk());
	}

	@Test
	public void addUserRoleTestWithDBError() throws Exception {

		UserRoleDT userRoleDT = new UserRoleDT();
		userRoleDT.setUserRoleId(999999);
		userRoleDT.setUserRole("UserRole");
		mockMvc.perform(post("/userRole/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(userRoleDT))).andExpect(status().isOk()).andExpect(
						jsonPath("$.failureResponse").value(containsString("org.hibernate.PersistentObjectException")));
	}

	@Test
	public void getAllUserRolesTest() throws Exception {
		mockMvc.perform(get("/userRole/all")).andExpect(status().isOk());
		Mockito.verifyZeroInteractions(UserRoleServiceMock);
	}

	@Test
	public void findUserRoleWithInvalidFindId() {
		try {

			mockMvc.perform(get("/userRole/find/" + 34334434).contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.successResponse").value(nullValue(String.class)));
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(), containsString("java.lang.NullPointerException"));
		}
	}

	@Test
	public void updateUserRoleTestTthowException() {

		UserRoleDT userRoleDT = new UserRoleDT();
		userRoleDT.setUserRole(null);
		try {
			mockMvc.perform(put("/userRole/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateUserRoleTest() throws Exception {
		UserRoleDT userRoleDT = new UserRoleDT();
		userRoleDT.setUserRoleId(UserRole_ID);
		userRoleDT.setUserRole(UserRole_NAME);
		mockMvc.perform(put("/userRole/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(userRoleDT)))
				.andExpect(jsonPath("$.status").value("success"));

		mockMvc.perform(get("/userRole/find/" + UserRole_ID).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status").value("success"));
	}

}
