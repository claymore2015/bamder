package com.claymore.bamder.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author li.zhuo
 * @create 2018/12/4 9:40 PM
 * @since 1.0.0
 */
//@RestController
//@ControllerAdvice
public class ExceptionConfig {

    private Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

    
    /*@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("---DefaultException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setMessage("服务器繁忙请联系管理员");
        return response ;
    }*/
}
