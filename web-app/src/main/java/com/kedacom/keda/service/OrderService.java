package com.kedacom.keda.service;

import com.kedacom.commons.vo.OrderVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order-service")
public interface OrderService {

    @PostMapping("/order/add") boolean add(@RequestBody OrderVo orderVo);

}
