package com.ivo.component.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Shiro配置
 * @author wj
 * @version 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
         *  过滤规则（注意优先级）
         *  —anon 无需认证(登录)可访问
         * 	—authc 必须认证才可访问
         * 	—perms[标识] 拥有资源权限才可访问
         * 	—role 拥有角色权限才可访问
         * 	—user 认证和自动登录可访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/logout", "anon");
        filterMap.put("/captcha", "anon");
        filterMap.put("/assets/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha", "anon");
        filterMap.put("/**", "authc");

        // 设置过滤规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthRealm authRealm,
                                                                  DefaultWebSessionManager sessionManager,
                                                                  CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * session管理器
     */
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager(ShiroProjectProperties properties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // Session会话超时时间，默认30分钟
        sessionManager.setGlobalSessionTimeout(properties.getGlobalSessionTimeout() * 1000);
        // Session会话检测间隔时间，默认15分钟
        sessionManager.setSessionValidationInterval(properties.getSessionValidationInterval() * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.validateSessions();
        // 去掉登录页面地址栏jsessionid
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * rememberMe管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("WcfHGU25gNnTxTlmJMeSpw=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * 创建一个简单的Cookie对象
     */
    @Bean
    public SimpleCookie rememberMeCookie(ShiroProjectProperties properties) {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        // cookie记住登录信息时间，默认7天
        simpleCookie.setMaxAge(properties.getRememberMeTimeout() * 24 * 60 * 60);
        return simpleCookie;
    }

}
