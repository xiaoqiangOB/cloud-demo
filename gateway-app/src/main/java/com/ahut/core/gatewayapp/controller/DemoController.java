package com.ahut.core.gatewayapp.controller;

import com.ahut.core.gatewayapp.dto.demo.DemoReq;
import com.ahut.core.gatewayapp.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by c2292 on 2017/10/25.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoService demoService;

    @RequestMapping(value="/stu",method = RequestMethod.POST)
    public String getStu(@RequestBody DemoReq request){
        String result = demoService.getStu(request);
        return result;
    }
}
