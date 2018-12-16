package com.system.business.channel.dao;

import com.system.business.channel.domain.ChannelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChannelDao extends JpaRepository<ChannelDomain, Integer>, JpaSpecificationExecutor<ChannelDomain> {

    ChannelDomain findByChannelId(String channelId);

    ChannelDomain findByChannelIdAndIsDeleted(String channelId, Integer isDeleted);

    @Query("from ChannelDomain t where t.isDeleted = 0")
    List<ChannelDomain> findAllChannels();

    @Query("from ChannelDomain t where t.channelName in:channelNames and t.isDeleted = 0")
    List<ChannelDomain> queryChannelIdByNames(@Param("channelNames") List<String> channelNames);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ChannelDomain c set c.isDeleted=:isDeleted where c.channelId in:channelIds")
    Integer deleteChannelBatchByChannelId(@Param("channelIds") List<String> channelIds, @Param("isDeleted") Integer isDeleted);
}
