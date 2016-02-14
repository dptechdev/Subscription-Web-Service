package com.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SubscriptionWebServiceApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void contextLoads() {

	}


	@Test
	public void restHtmlClient() throws Exception {
		mockMvc.perform(get("/client").contentType("text/html; charset=utf-8"))
				.andExpect(status().isOk());
	}

	@Test
	public void testSubscriptionControllerListAllSubscriptions() throws Exception {
		mockMvc.perform(get("/subscription/list"))
				.andExpect(status().isOk());

	}

	@Test
	public void testSubscriptionControllerSelectSubscriptionBySubscriptionId() throws Exception {
		mockMvc.perform(get("/subscription/select/1"))
				.andExpect(status().isOk());
	}

	@Test
	public void testSubscriptionControllerCreateSubscriptionA() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Subscription subscriptionToPost = new Subscription("Max", 555555);
		String jsonToPost = mapper.writeValueAsString(subscriptionToPost);
		mockMvc.perform(post("/subscription/create").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonToPost))
				.andExpect(status().isCreated());
	}

	@Test
	public void testSubscriptionControllerRemoveSubscriptionBySubscriptionIdB() throws Exception {
		mockMvc.perform(get("/subscription/remove/16"))
				.andExpect(status().isOk());
	}

	/*
		TODO --fixme-- 6:34PM 2/13/2016
		see if @Transactional will allow the create and remove tests to deal with the same entity
		so that the id on the remove method doesn't have to be changed everytime.
		We should not be directly manipulating the production database.
	 */


}
