package com.system.business.operation.dao;

import com.system.business.operation.domain.OperationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDao extends JpaRepository<OperationDomain, Integer>, JpaSpecificationExecutor<OperationDomain> {
}
