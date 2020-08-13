
package com.debug.teletubbies.tools;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 统一异常处理类
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 返回的Map对象会被@ResponseBody注解转换为JSON数据返回
     * @return
     */

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(HttpServletRequest request,Exception e){
        Map<String,Object> map=new LinkedHashMap<>();
        map.put("code",-1);
        map.put("url",request.getRequestURL().toString());
        map.put("msg",e.getMessage());
        return map;
    }
}
