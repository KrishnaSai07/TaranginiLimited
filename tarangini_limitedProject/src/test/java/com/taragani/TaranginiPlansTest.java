package com.taragani;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.taragani.RestApi.PlanAPI;
import com.taragani.model.Plan;
import com.taragani.service.PlanService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PlanAPI.class)
public class TaranginiPlansTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private PlanService planServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}
	@Test
	public void testListPlansAction() throws Exception{
		assertThat(this.planServiceMock).isNotNull();

		List<Plan> empList = new ArrayList<>();
		empList.add(new Plan());

		when(planServiceMock.getAllPlans()).thenReturn(empList);

		mockMvc.perform(get("/plans")).andExpect(status().isOk()).andDo(print());
		
	}

	
	@Test
	public void testGetPlanAction() throws Exception{
		assertThat(this.planServiceMock).isNotNull();
		String empId = "TAR1111";
	

		Plan empAdded = new Plan();

		empAdded.setpTitle("TAR1111");;
		empAdded.setSpeed(15);
		empAdded.setCharge(1250);
		empAdded.setMaxUsage(250);;
		

		when(planServiceMock.getPlan(empId)).thenReturn(empAdded);

		mockMvc.perform(get("/plans/TAR1111")).andExpect(status().isOk()).andDo(print());
	}
  
	


}
