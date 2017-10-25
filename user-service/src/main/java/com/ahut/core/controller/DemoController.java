package com.ahut.core.controller;

import com.ahut.core.common.packet.Packet;
import com.ahut.core.dto.demo.DemoReq;
import com.ahut.core.dto.demo.DemoRes;
import com.ahut.core.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by c2292 on 2017/10/20.
 */
@RestController//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    DemoService demoService;

    /*
    @RequestBody 首先@RequestBody需要接的参数是一个string化的json
    @RequestBody，从名称上来看也就是说要读取的数据在请求体里，所以要发post请求
    要设置contentType，contentType:"application/json，明确的告诉服务器发送的内容是json，而默认的contentType是application/x-www-form-urlencoded; charset=UTF-8
     */
    @RequestMapping(value = "/stu",method = RequestMethod.POST)
    public String learn(@RequestBody DemoReq request) throws Exception{
        DemoRes response = demoService.getByTest(request);
        return Packet.setResponse(response);
    }
}
