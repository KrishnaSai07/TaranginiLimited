package com.taragani;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
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

import com.taragani.RestApi.CustomerAPI;
import com.taragani.model.Customer;
import com.taragani.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerAPI.class)
public class TaranginiCustoPlan {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private CustomerService custServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	@Test
	public void testListCustomersAction() throws Exception {
		assertThat(this.custServiceMock).isNotNull();

		List<Customer> empList = new ArrayList<>();
		empList.add(new Customer());

		when(custServiceMock.getAllCustomers()).thenReturn(empList);

		mockMvc.perform(get("/plans")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testGetCustomerAction() throws Exception{
		assertThat(this.custServiceMock).isNotNull();
		int empId = 1;
	

		Customer empAdded = new Customer();

		empAdded.setName("karan");
		empAdded.setCid(1);
		empAdded.setMbno("1234567890");
		empAdded.setAddress("ARAEA");
		empAdded.setTimeSlot("2-4");
		empAdded.setDor(java.time.LocalDate.of(2018, 5, 30));
		empAdded.setpTitle("TAR1111");
		

		when(custServiceMock.getCustomer(empId)).thenReturn(empAdded);

		mockMvc.perform(get("/customer/1")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testAddCustomerAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCustomerAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCustomerAction() {
		fail("Not yet implemented");
	}

}
