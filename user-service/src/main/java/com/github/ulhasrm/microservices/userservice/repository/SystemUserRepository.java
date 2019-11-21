package com.github.ulhasrm.microservices.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ulhasrm.microservices.userservice.entity.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long>
{
    SystemUser findByUserName( final String userName );
}
