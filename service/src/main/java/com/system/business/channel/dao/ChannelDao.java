package com.system.business.channel.dao;

import com.system.business.channel.domain.ChannelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelDao extends JpaRepository<ChannelDomain, Integer> {

    ChannelDomain findByChannelId(String channelId);

    ChannelDomain findByChannelIdAndIsDeleted(String channelId, Integer isDeleted);

    @Query("from ChannelDomain t where t.isDeleted = 0")
    List<ChannelDomain> findAllChannels();
}
