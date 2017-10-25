package com.ahut.core.user;

import com.ahut.core.common.filter.ServicePackageFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by c2292 on 2017/10/20.
 */
@EnableDiscoveryClient//激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出
@SpringBootApplication
@ComponentScan(basePackages = {"com.ahut.core.common.db.config","com.ahut.core.common.exception","com.ahut.core.controller","com.ahut.core.service","com.ahut.core.biz","com.ahut.core.dao"})//扫描包路径
//@MapperScan(basePackages="com.ahut.core.dao",sqlSessionTemplateRef="sessionTemple")//将接口转换为Spring容器中的Bean
public class UserServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UserServiceApplication.class).web(true).run(args);
    }

    @Bean
    public FilterRegistrationBean pakcetFilterRegisteration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ServicePackageFilter());
        registration.addUrlPatterns("/*");
        registration.setName("packetFilter");
        registration.setOrder(1);
        return registration;
    }
}
