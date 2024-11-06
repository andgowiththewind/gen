package com.gust.cafe.gen.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gust.cafe.gen.dto.core.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * 全局异常处理器
 *
 * @datetime 2024-11-06 09:32:28
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public R handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        // 记录日志,可以使用任意日志框架
        String logMessage = StrUtil.format("[{}][{}]发生系统异常:", request.getMethod(), request.getRequestURI());
        log.error(logMessage, e);

        // 返回给前端的信息,可能需要对信息进行适当的隐藏或简化,避免暴露敏感信息
        // 整合国际化
        // String msg = WindyLang.msg("i18n_1827983611958267904");// 系统错误,请联系管理员
        return R.error("系统错误,请联系管理员");
    }

    /**
     * 自定义异常
     * <p>设计上,当主动抛出指定的自定义异常,可以仅DEBUG打印</p>
     */
    @ExceptionHandler(GenServiceException.class)
    public R HandleWindyException(GenServiceException e, HttpServletRequest request) {
        log.debug("捕获自定义异常'{}'", request.getRequestURI(), e.getMessage());
        return R.error(e.getMessage());
    }

    /**
     * 权限校验异常
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public R handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return R.error("没有权限,请联系管理员授权");
    }

    /**
     * 请求方式不支持
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return R.error(e.getMessage());
    }


    /**
     * 请求路径中缺少必需的路径变量
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingPathVariableException.class)
    public R handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求路径中缺少必需的路径变量'{}',发生系统异常.", requestURI, e);
        return R.error(String.format("请求路径中缺少必需的路径变量[%s]", e.getVariableName()));
    }

    /**
     * 请求参数类型不匹配
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI, e);
        String msg = String.format("请求参数类型不匹配,参数[%s]要求类型为：'%s',但输入值为：'%s'", e.getName(), e.getRequiredType().getName(), e.getValue());
        return R.error(msg);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String name = e.getParameterName();
        String type = e.getParameterType();
        String msg = StrUtil.format("类型为[{}]的请求参数[{}]不存在,请检查", type, name);
        log.error(StrUtil.format("[{}]-{}", requestURI, msg), e);
        return R.error(msg);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return R.error("系统异常,请联系管理员");
    }

    /**
     * 自定义验证异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public R handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.error(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 自定义验证异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.error(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 提供的JSON字段类型不匹配
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleHttpMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        // TODO
        // TODO
        // TODO
        // TODO
        // TODO
        // TODO
        String message = "malformed json input";
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) cause;
            String fieldName = "unknown field";
            List<JsonMappingException.Reference> path = ife.getPath();
            if (!path.isEmpty()) {
                // 获取导致错误的最后一个路径部分
                fieldName = path.get(path.size() - 1).getFieldName();
            }
            String simpleName = ife.getTargetType().getSimpleName();
            Object value = ife.getValue();
            message = StrUtil.format("字段 [{}]: 期望类型 [{}] but got [{}]", fieldName, simpleName, value);
        }
        return R.error(HttpStatus.BAD_REQUEST.value(), message);
    }
}