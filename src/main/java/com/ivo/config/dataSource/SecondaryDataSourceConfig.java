package com.ivo.config.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 第二数据源
 * @author wj
 * @version 1.0
 */
@Configuration
@Slf4j
public class SecondaryDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        log.info("创建第二数据源...");
        return DataSourceBuilder.create().build();
    }
}
