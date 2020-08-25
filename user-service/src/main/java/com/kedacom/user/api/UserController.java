package com.kedacom.user.api;

import com.kedacom.user.model.User;
import com.kedacom.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired private UserService userService;
    @Autowired private DiscoveryClient client;

    @PostMapping(value = "/login")
    public User login(@RequestBody User user){
        //@PathVariable是用来获得请求url中的动态参数的，
        // 所以该注解只能支持将参数放在请求url的GET提交方式，所以不管你如何进行设置，@PathVariable都是无法支持Post请求的。
        User u = userService.findByName(user.getName());
        return u;
    }

    @PostMapping(value = "/insertUser")
    public boolean insertUser(@RequestBody User user){
        try{
            userService.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> save(User user, UriComponentsBuilder ucb) {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/user, host:" + instance.getHost() + ", serviceId:" + instance.getServiceId() + ",user id:" + user.getId() + ",user name:" + user.getName());

        User saved = userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/login/")
                .path(String.valueOf(saved.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        ResponseEntity<User> responseEntity = new ResponseEntity<>(saved, headers, HttpStatus.CREATED);

        return responseEntity;

    }

    /**
     * 测试Spring Session
     * @param session
     * @return
     */
    @RequestMapping("/test/cookie")
    public String cookie(HttpSession session) {
        //取出session中的browser
        Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
            System.out.println("不存在sessionBrowser");
        } else {
            System.out.println("存在session，browser=" + sessionBrowser.toString());
        }
        return "成功";
    }

    /**
     * 测试事务
     * @return
     */
    @RequestMapping("/test/testTransactional")
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public String testTransactional(){
        // 创建3条记录
        userService.save(new User("AAA", "123456"));
        //name长度不能超过5位，会抛出异常，来测试事务是否会回滚
        userService.save(new User("测试测试测试测试", "123456"));
        userService.save(new User("BBB", "123456"));
        return "成功";
    }
}
