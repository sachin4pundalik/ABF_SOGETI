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

import com.sogeti.model.SkillDT;
import com.sogeti.service.SkillService;
import com.test.sogeti.config.TestContext;
import com.test.sogeti.config.WebAppContext;
import static org.hamcrest.CoreMatchers.*;

/**
 * * @author Mohan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class SkillEntityTest {

	private static final int SKILL_ID = 64;
	private static final String SKILL_NAME = "SkillName_modify";
	private static final int SKILLID_NOTINDB = 34566779;

	private MockMvc mockMvc;

	private SkillService skillServiceMock = Mockito.mock(SkillService.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(skillServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addSkillTest() throws Exception {

		SkillDT SkillDT = new SkillDT();
		SkillDT.setSkillName("skill_Name");
		mockMvc.perform(post("/skill/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(SkillDT))).andExpect(status().isOk());
	}

	@Test
	public void addSkillTestWithDBError() throws Exception {

		SkillDT SkillDT = new SkillDT();
		SkillDT.setSkillId(999999);
		SkillDT.setSkillName(SKILL_NAME);
		mockMvc.perform(post("/skill/create").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(SkillDT))).andExpect(status().isOk()).andExpect(
						jsonPath("$.failureResponse").value(containsString("org.hibernate.PersistentObjectException")));
	}

	@Test
	public void getAllSkillsTest() throws Exception {
		mockMvc.perform(get("/skill/all")).andExpect(status().isOk());
		Mockito.verifyZeroInteractions(skillServiceMock);
	}

	@Test
	public void findSkillWithInvalidFindId() throws Exception {

		mockMvc.perform(get("/skill/find/" + SKILLID_NOTINDB).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.successResponse").value(nullValue(String.class)));

	}

	@Test
	public void updateSkillTestTthowException() {

		SkillDT SkillDT = new SkillDT();
		SkillDT.setSkillName(null);
		try {
			mockMvc.perform(put("/skill/update").contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.convertObjectToJsonBytes(null))).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(),
					containsString("java.lang.IllegalArgumentException: Source must not be null"));
		}

	}

	@Test
	public void updateSkillTest() throws Exception {
		SkillDT SkillDT = new SkillDT();
		SkillDT.setSkillId(SKILL_ID);
		SkillDT.setSkillName(SKILL_NAME);
		mockMvc.perform(put("/skill/update").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(SkillDT))).andExpect(jsonPath("$.status").value("success"));

		mockMvc.perform(get("/skill/find/" + SKILL_ID).contentType(MediaType.APPLICATION_JSON));
	}

}
