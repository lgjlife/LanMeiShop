package com.lanmei.os.common.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: com-lanmei-parent
 * @description: 统一异常处理
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-07 08:43
 **/

@Component
public class ExceptionHandle implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView("/errorpage/exception");

        if(ex instanceof  Exception){
            mv.addObject("message",ex.getMessage());
            mv.addObject("cause",ex.getCause());
            mv.addObject("stack-trace",ex.getStackTrace());
        }


        return mv;
    }
}
