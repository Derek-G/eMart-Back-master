package com.kedacom.keda.service;

import com.kedacom.category.model.Category;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("category-service")
public interface CategoryService {

    @GetMapping("/category/{id}")
    Category getCategory(@PathVariable("id") Long id);

}
