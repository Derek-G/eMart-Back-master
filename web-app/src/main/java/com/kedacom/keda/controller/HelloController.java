package com.kedacom.keda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 苏雄伟 on 2017/12/25.
 */

@Controller
public class HelloController {
    @RequestMapping("/test")
    @ResponseBody
    public String index() {
        return "test";
    }
}
