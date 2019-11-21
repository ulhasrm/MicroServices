package com.github.ulhasrm.microservices.loanapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ulhasrm.microservices.loanapplication.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long>
{
    Status findByName( final String name );
}
