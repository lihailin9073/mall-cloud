package com.wzliulan.mall.cloud.oauth2.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 全局异常处理器
 *
 * @author lihailin973,767679879@qq.com,13802780104
 * @since 2020-12-13
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        String message = "系统发生异常，异常信息跟踪如下：" + e.toString();
        Map<String, Object> data = new HashMap<>();
        data.put("code", 500);
        data.put("message", message);
        data.put("data", message);

        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        mv.addAllObjects(data);
        return mv;
    }
}