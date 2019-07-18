package com.system.business.operation.dao;

import com.system.business.operation.domain.OperationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationDao extends JpaRepository<OperationDomain, Integer>, JpaSpecificationExecutor<OperationDomain> {

    OperationDomain findByUserIdAndChannelIdAndDate(Integer userId, String channelId, Date date);

    OperationDomain findByIdAndIsDeleted(Integer id, Integer isDeleted);

    @Query("from OperationDomain t where t.isDeleted = 0 and t.date >= :preMonthDate and t.date < :curDate")
    List<OperationDomain> queryOneMonth(@Param("preMonthDate") Date preMonthDate, @Param("curDate") Date curDate);

    @Query("from OperationDomain t where t.isDeleted = 0 and t.userId = :userId and t.date >= :preMonthDate and t.date < :curDate")
    List<OperationDomain> queryOneMonthByUserId(@Param("userId") Integer userId, @Param("preMonthDate") Date preMonthDate, @Param("curDate") Date curDate);
}
