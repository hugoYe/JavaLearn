package com.system.business.userchannel.domain;

import com.system.common.domain.BaseDomain;

import javax.persistence.*;

/**
 * 用户渠道关系表
 */
@Entity
@Table(name = "t_user_channel")
public class UserAndChannelDomain extends BaseDomain {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 渠道id
     */
    @Column(name = "channel_id")
    private String channelId;

    /**
     * 被删除的数据: 1.已删除，0.未删除
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
