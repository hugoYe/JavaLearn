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

    // nativeQuery = true 表明采用sql原生代码方式
    @Query(value = "select t.channel_id from t_channel t where t.channel_name in:channelNames and t.is_deleted = 0", nativeQuery = true)
    List<String> queryChannelIdByNames(@Param("channelNames") List<String> channelNames);

    @Query("from ChannelDomain t where t.channelId in:channelIds and t.isDeleted=0")
    List<ChannelDomain> queryChannelByIds(@Param("channelIds") List<String> channelIds);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update ChannelDomain c set c.isDeleted=:isDeleted where c.channelId in:channelIds")
    Integer deleteChannelBatchByChannelId(@Param("channelIds") List<String> channelIds, @Param("isDeleted") Integer isDeleted);
}
