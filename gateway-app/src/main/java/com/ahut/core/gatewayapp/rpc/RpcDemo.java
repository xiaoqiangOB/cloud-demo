package com.ahut.core.gatewayapp.rpc;

import com.ahut.core.gatewayapp.rpc.dto.demo.DemoRpcReq;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by c2292 on 2017/10/25.
 */
@FeignClient(value = "user-service")
public interface RpcDemo {

    @RequestMapping(value = "/demo/stu")
    String learn(@RequestBody DemoRpcReq request);
}
