package com.gust.cafe.gen.interceptor;

import cn.hutool.core.util.StrUtil;
import com.gust.cafe.gen.config.threadlocal.LocaleHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

/**
 * 国际化上下文记录拦截器
 */
@Component
public class LangInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 优先从请求参数中获取
        String lang = Optional.ofNullable(request).map(req -> req.getParameter("lang")).filter(StrUtil::isNotBlank).orElse(null);
        // 如果请求参数中没有，则从请求头中获取
        if (StrUtil.isBlank(lang)) {
            // 请求头优先读取 lang 参数,否则读取 Accept-Language
            lang = Optional.ofNullable(request).map(req -> req.getHeader("lang")).filter(StrUtil::isNotBlank).orElse(null);
            if (StrUtil.isBlank(lang)) {
                lang = Optional.ofNullable(request).map(req -> req.getHeader("Accept-Language")).filter(StrUtil::isNotBlank).orElse(null);
            }
        }
        if (StrUtil.isNotBlank(lang)) {
            Locale locale = Locale.forLanguageTag(lang);
            if (locale != null) {
                LocaleHolder.setLocale(locale);// 设置国际化上下文
            }
        }
        // 继续
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LocaleHolder.clear();
    }
}
