package com.ivo.common.jpa;

import com.ivo.common.model.EntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * 封装基础仓库的操作
 * @author wj
 * @version 1.0
 */
@NoRepositoryBean
public interface BaseJpaRepository<T extends EntityModel, ID> extends JpaRepository<T, ID> {
}
