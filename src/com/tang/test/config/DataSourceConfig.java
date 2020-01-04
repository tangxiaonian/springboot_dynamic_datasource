package com.tang.test.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Classname DataSourceConfig
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/1/4 15:55
 * @Created by ASUS
 */
@Configuration
public class DataSourceConfig {


    @Bean(name = "rootHikarConfig")
    @ConfigurationProperties(prefix = "spring.datasource.root")
    public HikariConfig rootHikarConfig() {
        return new HikariConfig();
    }

    @Bean(name = "rootDatasource")
    public DataSource rootDatasource(
            @Qualifier(value = "rootHikarConfig") HikariConfig rootHikarConfig) {
        return new HikariDataSource(rootHikarConfig);
    }

    @Bean(name = "userHikarConfig")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public HikariConfig userHikarConfig() {
        return new HikariConfig();
    }

    @Bean(name = "userDatasource")
    public DataSource userDatasource(
            @Qualifier(value = "userHikarConfig") HikariConfig userHikarConfig) {
        return new HikariDataSource(userHikarConfig);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

        sessionFactoryBean.setDataSource(dynamicDataSource);

        sessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:/mapper/*.xml")
        );

        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}