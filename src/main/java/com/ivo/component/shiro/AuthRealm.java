package com.ivo.component.shiro;

import com.ivo.system.entity.User;
import com.ivo.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * 用户认证以及授权
 * @author wj
 * @version 1.0
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    private final UserService userService;

    @Autowired
    public AuthRealm(UserService userService) {
        this.userService = userService;
    }

    /**
     * 授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户Principal对象
        User user = (User) principalCollection.getPrimaryPrincipal();
        // 获取角色和资源
        Set<String> roles = userService.getRoles(user.getUserid());
        Set<String> permissions = userService.getPermissions(user.getUserid());
        // 赋予角色和资源授权
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }

    /**
     * 认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUser(token.getUsername());
        // 判断用户名是否存在
        if(user == null){
            throw new UnknownAccountException();
        }

        // 判断用户是否被冻结
        if(userService.isFrozen(user.getUserid())) {
            throw new LockedAccountException();
        }

        ByteSource salt = ByteSource.Util.bytes(user.getSalt());

        /* 传入密码自动判断是否正确
         * 参数1：传入对象给Principal
         * 参数2：正确的用户密码
         * 参数3：加盐处理
         * 参数4：固定写法
         */
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
    }

    /**
     * 自定义密码验证匹配器
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        setCredentialsMatcher(new SimpleCredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
                SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) authenticationInfo;
                // 获取明文密码及密码盐
                String password = String.valueOf(token.getPassword());
                String salt = CodecSupport.toString(info.getCredentialsSalt().getBytes());

                return equals(ShiroUtil.encrypt(password, salt), info.getCredentials());
            }
        });
    }
}
