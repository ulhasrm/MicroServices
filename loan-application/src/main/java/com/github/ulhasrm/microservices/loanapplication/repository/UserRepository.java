package com.github.ulhasrm.microservices.loanapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ulhasrm.microservices.loanapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByLoginName( final String loginName );
}
