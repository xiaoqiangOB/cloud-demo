package com.ahut.core.common.exception;

import com.ahut.core.common.constant.SysErrCode;
import com.ahut.core.common.packet.Packet;
import com.google.common.base.Strings;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by c2292 on 2017/10/23.
 */
/*
@ControllerAdvice注解内部使用@ExceptionHandler、@InitBinder、@ModelAttribute注解的方法应用到所有的 @RequestMapping注解的方法
 */
//统一异常处理 减少代码侵入
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler({Exception.class})
    String handleException(Exception ex){
        LOGGER.error("Exception: {}","\n", ExceptionUtils.getFullStackTrace(ex));
        Packet.setException();
        Packet.setBizeResult(SysErrCode.E9999.code(),SysErrCode.E9999.desc());
        return Packet.getBizResult();
    }
    /*
    @ExceptionHandler，异常处理器，此注解的作用是当出现其定义的异常时进行处理的方法
     */

    @ResponseBody
    @ExceptionHandler(BizException.class)
    String handleBizException(BizException ex){
        String errCode = ex.getErrCode();
        String errMsg = ex.getErrMsg();
        LOGGER.error("BizException ErrCode: {} ErrMsg: {}",errCode,errMsg);
        if(Strings.isNullOrEmpty(errCode)){
            Packet.setBizeResult(SysErrCode.E9999.code(),SysErrCode.E9999.desc());
        }else{
            Packet.setBizeResult(errCode,errMsg);
        }
        Packet.setBizException();
        return Packet.getBizResult();
    }
}

