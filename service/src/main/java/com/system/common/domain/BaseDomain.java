package com.system.common.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @MappedSuperclass 使用环境：
 * 1.@MappedSuperclass注解使用在父类上面，是用来标识父类的
 * <p>
 * 2.@MappedSuperclass标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够映射在其子类对应的数据库表中
 * <p>
 * 3.@MappedSuperclass标识得类不能再有@Entity或@Table注解
 */
@MappedSuperclass
public class BaseDomain {

    /**
     * 创建人
     */
    @Column(name = "create_by")
    @CreatedBy
    private Integer createBy;

    /**
     * 最后修改人
     */
    @Column(name = "update_by")
    @LastModifiedBy
    private Integer updateBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @LastModifiedDate
    private Date updateTime;


    @PrePersist
    public void prePersist() {
        this.createTime = new Date();
        this.updateTime = new Date();
        this.createBy = 0;
        this.updateBy = 0;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = new Date();
        this.updateBy = 0;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BaseDomain{" +
                "createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
