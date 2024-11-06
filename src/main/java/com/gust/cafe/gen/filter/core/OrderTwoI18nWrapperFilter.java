package com.gust.cafe.gen.filter.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 此二号 filter 用于国际化处理,如果不需要国际化,可以不用此 filter
 */
@Slf4j
@Component
@Order(2) // 数值越小，优先级越高
public class OrderTwoI18nWrapperFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 约定请求参数中的语言字段为`lang`
        String lang = request.getParameter("lang");
        filterChain.doFilter(request, response);
    }
}
