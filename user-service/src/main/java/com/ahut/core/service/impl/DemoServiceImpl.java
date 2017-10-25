package com.ahut.core.service.impl;


import com.ahut.core.biz.DemoBiz;
import com.ahut.core.dto.demo.DemoReq;
import com.ahut.core.dto.demo.DemoRes;
import com.ahut.core.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by c2292 on 2017/10/20.
 */
@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    DemoBiz demoBiz;

    @Override
    public DemoRes getStudent(DemoReq demoReq){
        return null;//demoBiz.query(demoReq);
    }

    @Override
    public DemoRes getByTest(DemoReq demoReq) {
        return demoBiz.getByTest(demoReq);
    }
}
