package com.system.business.adv.customer.dao;

import com.system.business.adv.customer.domain.CustomerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<CustomerDomain, Integer>, JpaSpecificationExecutor<CustomerDomain> {

    CustomerDomain findByCustIdAndIsDeleted(String custID, Integer isDeleted);

    @Query("from CustomerDomain t where t.isDeleted = 0")
    List<CustomerDomain> findAllCustomer();

    @Query("from CustomerDomain t where t.custId in:custIds and t.isDeleted=0")
    List<CustomerDomain> queryCustomerByIds(@Param("custIds") List<String> custIds);


}
