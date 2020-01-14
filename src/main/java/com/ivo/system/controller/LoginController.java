package com.ivo.system.controller;

import com.ivo.common.constant.ViewMapperConst;
import com.ivo.common.data.URL;
import com.ivo.common.enums.ResultEnum;
import com.ivo.common.exception.ResultException;
import com.ivo.common.result.Result;
import com.ivo.common.utils.CaptchaUtil;
import com.ivo.common.utils.HttpServletUtil;
import com.ivo.common.utils.ResultUtil;
import com.ivo.component.shiro.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author wj
 * @version 1.0
 */
@Slf4j
@Controller
public class LoginController {

    /**
     * 跳转到登录页面
     */
    @GetMapping("/login")
    public String toLogin() {
        return ViewMapperConst.view_login;
    }

    /**
     * 实现登录
     */
    @PostMapping("/login")
    @ResponseBody
    public Result doLogin(String username, String password, String captcha, String rememberMe) {
        log.info("用户执行登录" + username + "来自IP" + ShiroUtil.getIp());

        // 判断账号密码是否为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ResultException(ResultEnum.USER_NAME_PWD_NULL);
        }

        // 判断验证码
        if (!CaptchaUtil.ver(captcha, HttpServletUtil.getRequest())) {
            CaptchaUtil.clear(HttpServletUtil.getRequest());
            throw new ResultException(ResultEnum.USER_CAPTCHA_ERROR);
        }

        // 1.获取Subject主体对象
        Subject subject = SecurityUtils.getSubject();

        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 判断是否自动登录
        if (rememberMe != null) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }

        // 3.执行登录，进入自定义Realm类中
        try {
            subject.login(token);
            log.info("用户" + username + "登录成功");
            return ResultUtil.success("登录成功", new URL("/"));
        } catch (LockedAccountException e) {
            log.info("用户" + username + "登录失败，该账号已被冻结");
            return ResultUtil.error("该账号已被冻结");
        } catch (AuthenticationException e) {
            log.info("用户" + username + "登录失败，用户名或密码错误");
            return ResultUtil.error("用户名或密码错误");
        }
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    /**
     * 图形验证码
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            CaptchaUtil.outPng(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
