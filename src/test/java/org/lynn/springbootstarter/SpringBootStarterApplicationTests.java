package org.lynn.springbootstarter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lynn.springbootstarter.common.ResultEntity;
import org.lynn.springbootstarter.dao.UserDao;
import org.lynn.springbootstarter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class SpringBootStarterApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestRestTemplate testRestTemplate ;

	@Autowired
	private UserDao userDao;

	@Test
	public void testEcho() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/test/echo")).
				andExpect(MockMvcResultMatchers.status().isOk());
		Map paramMap = new HashMap<>();
		paramMap.put("id",1);
		ResultEntity resultEntity = testRestTemplate.getForObject("/test/getById?id={id}", ResultEntity.class,paramMap);
		Assert.assertEquals(1,resultEntity.getCode().longValue());
	}

	@Test
	public void testDao(){
		userDao.query(new User());
	}

}
