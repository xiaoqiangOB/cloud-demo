package com.ahut.core.gatewayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by c2292 on 2017/10/25.
 */
@EnableDiscoveryClient//激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出
@EnableFeignClients//注解开启Feign功能
@SpringBootApplication
@ComponentScan(basePackages = {"com.ahut.core.common.exception","com.ahut.core.gatewayapp"})
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }
}
