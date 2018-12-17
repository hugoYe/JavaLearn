package com.system.business.userchannel.dao;

import com.system.business.userchannel.domain.UserAndChannelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAndChannelDao extends JpaRepository<UserAndChannelDomain, Integer> {

    @Query("from UserAndChannelDomain uc where uc.isDeleted = 0 and uc.userId in:ids")
    List<UserAndChannelDomain> findByUserIds(@Param("ids") List<Integer> ids);
}
