package com.tang.test.service;


import com.tang.test.domain.Order;
import com.tang.test.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Classname OrderService
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/24 17:00
 * @Created by ASUS
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = {RuntimeException.class})
    public void add() {

        Order order = new Order();

        order.setName("new xxx");
        order.setOrderCreatetime(new Date());
        order.setOrderState(1);
        order.setOrderMoney(10.0);
        order.setCommodityId(30);

        orderMapper.insert(order);
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    public Order getById(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}