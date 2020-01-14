package com.ivo.config.exceptionHandler;

import com.ivo.common.exception.ResultException;
import com.ivo.common.result.Result;
import com.ivo.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.util.Objects;

/**
 * 全局统一异常处理
 * @author wj
 * @version 1.0
 */
@ControllerAdvice
@Slf4j
public class ResultExceptionHandler {

    // 拦截自定义异常
    @ExceptionHandler(ResultException.class)
    @ResponseBody
    public Result resultException(ResultException e) {
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    // 拦截表单验证异常
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        return ResultUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    // 拦截未知的运行时异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeException(RuntimeException e) {
        HttpRequestMethodNotSupportedException d;
        log.error("【系统异常】", e);
        return ResultUtil.error(500, "未知错误：EX4399--" + e.getMessage());
    }

    // 拦截ServletException
    @ExceptionHandler(ServletException.class)
    @ResponseBody
    public Result servletException(ServletException e) {
        log.error("【请求异常】", e);
        return ResultUtil.error(500, "请求失败--" + e.getMessage());
    }

}
