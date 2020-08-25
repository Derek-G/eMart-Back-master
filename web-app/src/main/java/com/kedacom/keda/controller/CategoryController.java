package com.kedacom.keda.controller;

import com.kedacom.category.model.Category;
import com.kedacom.commons.api.Result;
import com.kedacom.commons.util.ResultUtil;
import com.kedacom.keda.service.CategoryService;
import com.kedacom.keda.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired CategoryService categoryService;

    @GetMapping("/introduction/{id}")
    public Result home(@PathVariable long id) {
        Category category = categoryService.getCategory(id);
        return ResultUtil.success(category);
    }
}
