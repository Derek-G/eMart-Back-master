package com.kedacom.order.service.impl;

import com.kedacom.commons.vo.OrderVo;
import com.kedacom.order.model.Order;
import com.kedacom.order.model.OrderCategory;
import com.kedacom.order.repository.OrderCategoryRepository;
import com.kedacom.order.repository.OrderRepository;
import com.kedacom.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderCategoryRepository orderCategoryRepository;

    @Override public boolean save(OrderVo orderVo) {
        Order order = new Order();
        order.setDatetime(new Date());
        order.setState(0);
        order.setUserId(orderVo.getUserId());
        order.setSummoney(orderVo.getSummoney());

        Order order1 = orderRepository.save(order);

        OrderCategory orderCategory = new OrderCategory();
        orderCategory.setCategoryId(orderVo.getCategoryId());
        orderCategory.setCreateTime(new Date());
        orderCategory.setOrderId(order1.getId());
        //TODO 默认设置为 1
        orderCategory.setNum(1);
        orderCategoryRepository.save(orderCategory);
        return true;
    }

}
