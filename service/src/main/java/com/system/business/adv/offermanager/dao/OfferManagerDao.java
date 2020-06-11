package com.system.business.adv.offermanager.dao;

import com.system.business.adv.offermanager.domain.OfferManagerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferManagerDao extends JpaRepository<OfferManagerDomain, Integer>, JpaSpecificationExecutor<OfferManagerDomain> {

    OfferManagerDomain findByOfferIdAndIsDeleted(String OfferId, Integer isDeleted);

    @Query("from OfferManagerDomain t where t.isDeleted = 0")
    List<OfferManagerDomain> findAllOffer();

}
