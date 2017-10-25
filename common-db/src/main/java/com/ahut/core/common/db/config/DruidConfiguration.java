package com.ahut.core.common.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * Created by c2292 on 2017/10/24.
 */
/*
@EnableConfigurationProperties(ConnectionSettings.class)来明确指定需要用哪个实体类来装载配置信息
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DruidConfiguration {
    /**
     * @ConfigurationProperties可以直接定义在@bean的注解上，这是bean实体类就不用@Component和@ConfigurationProperties了
     * @param properties
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.*")
    public DruidDataSource dataSoure(DataSourceProperties properties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.determineDriverClassName());
        dataSource.setUrl(properties.determineUrl());
        dataSource.setUsername(properties.determineUsername());
        dataSource.setPassword(properties.determinePassword());
        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if(validationQuery != null){
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }

        try {
            dataSource.setFilters("mergeStat,wall,log4j");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
