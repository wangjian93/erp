package com.ivo.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivo.common.enums.ValidStatusEnum;
import com.ivo.system.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类模型
 * 注：1.该类作为建表的栏位参考，记录数据的逻辑删除和添加修改操作，在开发中不应参与到实际业务
 * 2.使用了JsonIgnore注释，在自动转换成json格式数据时不会转换这些属性
 * 3.使用EntityListeners注解引入Spring Data JPA的审计功能，创建者、修改者、创建时间、修改时间交由系统维护
 * @author wj
 * @version 1.0
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityModel implements Serializable {

    private static final long serialVersionUID = 4964093431069570632L;

    /**
     * 标识数据是否有效 用于逻辑删除
     */
    @JsonIgnore
    private Byte validFlag = ValidStatusEnum.VALID.getCode();

    /**
     * 创建时间
     */
    @JsonIgnore
    @CreatedDate
    private Date createDate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @LastModifiedDate
    private Date updateDate;

    /**
     * 创建者
     */
    @JsonIgnore
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private User creator;

    /**
     * 修改者
     */
    @JsonIgnore
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updater")
    private User updater;
}
