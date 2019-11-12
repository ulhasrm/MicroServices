package com.github.ulhasrm.microservices.loanapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ulhasrm.microservices.loanapplication.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>
{
    List<Application> findByUserId( Long userId );
}
