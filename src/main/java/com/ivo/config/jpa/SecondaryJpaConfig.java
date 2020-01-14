package com.ivo.config.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 第二数据源的JPA配置 连接2.233 oracle
 * @author wj
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactorySecondary",
        transactionManagerRef="transactionManagerSecondary",
        basePackages= {"com.ivo.rest.oracle"})
public class SecondaryJpaConfig {

    private final DataSource secondaryDataSource;

    private final JpaProperties jpaProperties;

    private final HibernateProperties hibernateProperties;

    // 获取对应的数据库方言
    @Value("${spring.jpa.hibernate.secondary-dialect}")
    private String secondaryDialect;

    @Autowired
    public SecondaryJpaConfig( @Qualifier("secondaryDataSource") DataSource secondaryDataSource,
                           JpaProperties jpaProperties, HibernateProperties hibernateProperties) {
        this.secondaryDataSource = secondaryDataSource;
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties;
    }

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return Objects.requireNonNull(entityManagerFactory(builder).getObject()).createEntityManager();
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String,String> map = new HashMap<>();
        map.put("hibernate.dialect", secondaryDialect);
        jpaProperties.setProperties(map);
        Map<String, Object> propertiesMap= hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(secondaryDataSource)
                .properties(propertiesMap)
                .packages("com.ivo.rest.oracle") // 设置实体类所在位置
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }

    @Bean(name = "transactionManagerSecondary")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }


}
