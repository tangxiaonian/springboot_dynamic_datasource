package com.tang.test.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DynamicDataSource
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/1/4 16:23
 * @Created by ASUS
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Qualifier(value = "userDatasource")
    @Resource
    private DataSource rootDatasource;

    @Qualifier(value = "userDatasource")
    @Resource
    private DataSource userDatasource;

    /**
     *@MethodName determineCurrentLookupKey
     *@Description [ 这个方法的返回值key决定了到底使用那个数据源 ]
     *@Date 2020/1/4 16:35
     *@Param []
     *@return
     **/
    @Override
    protected Object determineCurrentLookupKey() {
        return ContextDataSourceHelp.getDbType();
    }

    @Override
    public void afterPropertiesSet() {

        Map<Object, Object> map = new HashMap<>();

//        key value: 对应数据源的名字
        map.put("rootDatasource", rootDatasource);
        map.put("userDatasource", userDatasource);

        // 设置所有数据源
        this.setTargetDataSources(map);

        // 默认使用的数据源
        this.setDefaultTargetDataSource(userDatasource);

        super.afterPropertiesSet();
    }
}