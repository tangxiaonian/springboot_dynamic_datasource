package com.tang.test.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname AopDataSource
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/1/4 16:15
 * @Created by ASUS
 */
@Component
@Aspect
@Order(0)
public class AopDataSource {

    @Before(value = "execution(public * com.tang.test.service.*.*(..))")
    public void methodBefore(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();

        System.out.println(methodName + "方法执行....");

        List<String> list = new ArrayList<>();

        list.add("get");
        list.add("select");

        boolean isMatch = list.stream()
                .anyMatch(methodName::startsWith);

        // 读写判断

        ContextDataSourceHelp.setDbType(isMatch ? "userDatasource" : "rootDatasource");
    }

}