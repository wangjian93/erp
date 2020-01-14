package com.ivo.config.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 第三数据源 连接81/EIFDB
 * @author wj
 * @version 1.0
 */
@Configuration
@Slf4j
public class ThirdDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.third")
    public DataSource thirdDataSource() {
        log.info("创建第三数据源...");
        return DataSourceBuilder.create().build();
    }
}
