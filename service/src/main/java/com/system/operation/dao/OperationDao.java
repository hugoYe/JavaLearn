package com.system.operation.dao;

import com.system.operation.domain.OperationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDao extends JpaRepository<OperationDomain, Integer> {
}
