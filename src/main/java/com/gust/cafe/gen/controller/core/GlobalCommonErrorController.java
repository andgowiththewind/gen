package com.gust.cafe.gen.controller.core;


import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class GlobalCommonErrorController implements ErrorController {
    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws Throwable {
        String key = "javax.servlet.error.exception";
        if (request.getAttribute(key) != null) {
            log.debug(StrUtil.format("异常在{}被捕获", this.getClass().getSimpleName()));
            // 再抛出以便让自定义的"全局异常处理器"(@RestControllerAdvice)捕获到并处理
            throw (Throwable) request.getAttribute(key);
        }
    }
}
