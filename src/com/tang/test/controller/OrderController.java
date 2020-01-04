package com.tang.test.controller;

import com.tang.test.domain.Order;
import com.tang.test.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname OrderController
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/23 19:15
 * @Created by ASUS
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/testAdd")
    public String order() {

        orderService.add();

        return "success";
    }

    @GetMapping("/testSelect")
    public Order getById(Integer id) {
        return orderService.getById(id);
    }

}