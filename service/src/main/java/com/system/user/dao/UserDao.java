package com.system.user.dao;

import com.system.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserDomain, Integer> {

    UserDomain findByName(String name);
}
