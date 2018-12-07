package com.system.userchannel.dao;

import com.system.userchannel.domain.UserAndChannelDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAndChannelDao extends JpaRepository<UserAndChannelDomain, Integer> {
}
