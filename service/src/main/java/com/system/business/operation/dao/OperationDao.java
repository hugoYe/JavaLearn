package com.system.business.operation.dao;

import com.system.business.operation.domain.OperationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OperationDao extends JpaRepository<OperationDomain, Integer>, JpaSpecificationExecutor<OperationDomain> {

    OperationDomain findByUserIdAndChannelIdAndDate(Integer userId, String channelId, Date date);
}
