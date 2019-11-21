package com.github.ulhasrm.microservices.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ulhasrm.microservices.userservice.entity.SystemGroup;

public interface SystemGroupRepository extends JpaRepository<SystemGroup, Long>
{
    SystemGroup findByName( final String name );
}
