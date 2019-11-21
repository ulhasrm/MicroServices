package com.github.ulhasrm.microservices.loanapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ulhasrm.microservices.loanapplication.entity.LoanType;

public interface LoanTypeRepository extends JpaRepository<LoanType, Long>
{
    LoanType findByName( final String name );
}
