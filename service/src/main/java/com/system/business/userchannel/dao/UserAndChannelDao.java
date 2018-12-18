package com.system.business.userchannel.dao;

import com.system.business.userchannel.domain.UserAndChannelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserAndChannelDao extends JpaRepository<UserAndChannelDomain, Integer> {

    @Query("from UserAndChannelDomain uc where uc.isDeleted = 0 and uc.userId in:ids")
    List<UserAndChannelDomain> findByUserIds(@Param("ids") List<Integer> ids);

    List<UserAndChannelDomain> findAllByUserId(Integer userId);

    @Transactional
    @Modifying
    @Query("update UserAndChannelDomain t set t.isDeleted = :isDeleted where t.userId = :userId and t.channelId in:channelIds")
    Integer updateUserAndChannel(@Param("userId") Integer userId, @Param("channelIds") List<String> channelIds,
                                 @Param("isDeleted") Integer isDeleted);
}
