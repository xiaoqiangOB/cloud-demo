package com.ahut.core.biz;

import com.ahut.core.common.exception.BizException;
import com.ahut.core.common.packet.Packet;
import com.ahut.core.constants.DemoInfoCode;
import com.ahut.core.dao.DemoServiceDao;
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
    DemoServiceDao demoServiceDao;

    public DemoRes getByTest(DemoReq request){
        DemoRes response = new DemoRes();
        Map<String,Object> paramMap = new HashMap<>();

        paramMap.put(Demo.NAME,request.getName());
        paramMap.put(Demo.AGE,request.getAge());
        paramMap.put(Demo.ID,request.getId());

        Demo demoInfo = demoServiceDao.getBy(paramMap);

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
