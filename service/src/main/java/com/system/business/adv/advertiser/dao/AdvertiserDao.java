package com.system.business.adv.advertiser.dao;

import com.system.business.adv.advertiser.domain.AdvertiserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertiserDao extends JpaRepository<AdvertiserDomain, Integer>, JpaSpecificationExecutor<AdvertiserDomain> {

    AdvertiserDomain findByAdvIdAndIsDeleted(String advID, Integer isDeleted);

    @Query("from AdvertiserDomain t where t.isDeleted = 0")
    List<AdvertiserDomain> findAllAdvertiser();

    @Query("from AdvertiserDomain t where t.advId in:advIds and t.isDeleted=0")
    List<AdvertiserDomain> queryAdvertiserByIds(@Param("advIds") List<String> advIds);


}
