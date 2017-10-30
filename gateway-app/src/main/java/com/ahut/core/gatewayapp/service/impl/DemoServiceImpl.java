package com.ahut.core.gatewayapp.service.impl;

import com.ahut.core.gatewayapp.dto.demo.DemoReq;
import com.ahut.core.gatewayapp.dto.demo.DemoRes;
import com.ahut.core.gatewayapp.rpc.RpcDemo;
import com.ahut.core.gatewayapp.rpc.dto.demo.DemoRpcReq;
import com.ahut.core.gatewayapp.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by c2292 on 2017/10/25.
 */
@Service
public class DemoServiceImpl implements DemoService{

    @Autowired
    RpcDemo rpcDemo;

    @Override
    public String getStu(DemoReq demoReq) {
        DemoRpcReq request = new DemoRpcReq();
        request.setName(demoReq.getName());
        request.setAge(demoReq.getAge());
        request.setId(demoReq.getId());
        return rpcDemo.learn(request);
    }
}
