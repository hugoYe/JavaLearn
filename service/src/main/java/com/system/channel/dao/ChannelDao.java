package com.system.channel.dao;

import com.system.channel.domain.ChannelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelDao extends JpaRepository<ChannelDomain, Integer> {
}
