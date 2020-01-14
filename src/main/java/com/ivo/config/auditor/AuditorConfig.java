package com.ivo.config.auditor;

import com.ivo.system.entity.User;
import com.ivo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * 使用SpringData审计功能，审核员自动赋值配置
 * @author wj
 * @version 1.0
 */
@Configuration
@EnableJpaAuditing
public class AuditorConfig implements AuditorAware<User> {

    private final UserService userService;

    @Autowired
    public AuditorConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        User user = userService.getUser("admin");
        return Optional.ofNullable(user);
    }
}
