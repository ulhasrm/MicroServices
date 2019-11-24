package com.github.ulhasrm.microservices.loanapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlow;

public interface WorkFlowRepository extends JpaRepository<WorkFlow, Long>
{
    WorkFlow findByName( final String name );
}
