package com.ahut.core.service;

import com.ahut.core.dto.demo.DemoReq;
import com.ahut.core.dto.demo.DemoRes;

/**
 * Created by c2292 on 2017/10/23.
 */
public interface DemoService {
    DemoRes getStudent(DemoReq demoReq);

    DemoRes getByTest(DemoReq demoReq);
}
