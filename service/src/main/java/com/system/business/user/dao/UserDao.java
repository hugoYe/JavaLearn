package com.system.business.user.dao;

import com.system.business.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * IBM针对JPA框架的介绍文章
 * https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/index.html
 */
@Repository
public interface UserDao extends JpaRepository<UserDomain, Integer> {

    /**
     * JPA框架内部实现了业务逻辑的处理，因此这个方法不需要写@Query操作就可以直接查询数据库，具体内部截取规则可以参照类头部给出的文章
     */
    UserDomain findByNameAndIsDeleted(String name, Integer isDeleted);

    UserDomain findByIdAndIsDeleted(Integer id, Integer isDeleted);
}
