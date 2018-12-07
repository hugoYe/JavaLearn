package com.system.business.channel.dao;

import com.system.business.channel.domain.ChannelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelDao extends JpaRepository<ChannelDomain, Integer> {
}
