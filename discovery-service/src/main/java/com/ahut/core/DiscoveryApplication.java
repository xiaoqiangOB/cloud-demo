package com.ahut.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by c2292 on 2017/10/20.
 */
@EnableEurekaServer//.注解启动一个服务注册中心提供给其他应用进行对话
@SpringBootApplication
public class DiscoveryApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DiscoveryApplication.class).web(true).run(args);
    }
}
