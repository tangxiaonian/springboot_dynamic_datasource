package com.tang.test.mapper;


import com.tang.test.domain.Order;

/**
 * @Classname ${NAME}
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/23 19:23
 * @Created by ASUS
 */
public interface OrderMapper {

    int insert(Order record);

    Order selectByPrimaryKey(Integer id);

}