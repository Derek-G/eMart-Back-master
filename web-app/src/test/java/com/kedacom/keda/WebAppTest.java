package com.kedacom.keda;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * API测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration
@ContextConfiguration
public class WebAppTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    /**
     * 单元测试，测试用户服务 --> 登录功能
     * @throws Exception
     */
    @Test
    public void testUserLogin() throws Exception {
        RequestBuilder request = null;
        //路径
        request = post("/users/login")
                //参数
                .param("name", "admin")
                .param("password","123456")
                //接受的数据类型
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                //状态码是否相等
                .andExpect(status().isOk())
                //得到的信息是否与特定字段匹配
                .andExpect(content().string("{\"code\":0,\"msg\":\"成功\",\"data\":null}"))
                //输出信息
                .andDo(print());

        /**
         * 执行后输出：
         * MockHttpServletRequest:
         *       HTTP Method = POST
         *       Request URI = /users/login
         *        Parameters = {name=[admin], password=[123456]}
         *           Headers = {}
         *
         * Handler:
         *              Type = com.kedacom.keda.controller.UserController
         *            Method = public com.kedacom.commons.api.Result com.kedacom.keda.controller.UserController.login(com.kedacom.user.model.User,javax.servlet.http.HttpSession)
         *
         * Async:
         *     Async started = false
         *      Async result = null
         *
         * Resolved Exception:
         *              Type = null
         *
         * ModelAndView:
         *         View name = null
         *              View = null
         *             Model = null
         *
         * FlashMap:
         *        Attributes = null
         *
         * MockHttpServletResponse:
         *            Status = 200
         *     Error message = null
         *           Headers = {Content-Type=[application/json;charset=UTF-8]}
         *      Content type = application/json;charset=UTF-8
         *              Body = {"code":0,"msg":"成功","data":null}
         *     Forwarded URL = null
         *    Redirected URL = null
         *           Cookies = []
         */
    }

    /**
     * 测试商品服务--> 获取商品
     * @throws Exception
     */
    @Test
    public void testGetCategoryById() throws Exception {
        RequestBuilder request = null;
        //路径
        request = get("/category/introduction/1");
        mvc.perform(request)
                //状态吗是否相等
                .andExpect(status().isOk())
                //输出信息
                .andDo(print());
        return;
    }

}
