package org.example.demo.controller;

import org.example.DemoApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@TestPropertySource(locations = {"classpath:application.yml"})
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testList() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/demo/user/list2")
                        .param("pageNo", "1")
                        .param("pageSize", "3")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1000"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        // 与 andExpect(MockMvcResultMatchers.status().isOk()) 作用一样
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
