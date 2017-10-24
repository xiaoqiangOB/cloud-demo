package com.ahut.core.biz;

import com.ahut.core.common.exception.BizException;
import com.ahut.core.common.packet.Packet;
import com.ahut.core.constants.DemoInfoCode;
import com.ahut.core.dao.DemoMapper;
import com.ahut.core.dao.entity.Demo;
import com.ahut.core.dto.demo.DemoReq;
import com.ahut.core.dto.demo.DemoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by c2292 on 2017/10/23.
 */
@Repository("demoBiz")
public class DemoBiz {
    private static org.slf4j.Logger  LOGGER = org.slf4j.LoggerFactory.getLogger(DemoBiz.class);
    @Autowired
    DemoMapper demoMapper;

    public DemoRes query(DemoReq demoReq) {
        DemoRes response = new DemoRes();
        Demo demo = new Demo();
        demo.setName(demoReq.getName());
        demo.setAge(demoReq.getAge());
        demo.setId(demoReq.getId());

        Map<String,String> param = new HashMap<>();
        param.put(Demo.NAME,demoReq.getName());
        param.put(Demo.AGE,demoReq.getAge());
        param.put(Demo.ID,demo.getId());

        Demo demoInfo = demoMapper.query(demo);
        if (demoInfo == null) {
            LOGGER.info("查询为空");
            throw new BizException(DemoInfoCode.FAIL.getErrCode(), DemoInfoCode.FAIL.getErrMsg());
        }
        LOGGER.info("处理应答数据");
        response.setName(demoInfo.getName());
        response.setAge(demoInfo.getAge());
        response.setId(demoInfo.getId());
        Packet.setBizSuccess();
        return response;
    }
}
