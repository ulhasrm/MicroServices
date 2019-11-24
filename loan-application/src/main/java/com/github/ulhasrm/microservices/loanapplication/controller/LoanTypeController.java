package com.github.ulhasrm.microservices.loanapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.loanapplication.entity.LoanType;
import com.github.ulhasrm.microservices.loanapplication.service.LoanTypeDaoService;

@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class LoanTypeController
{
    @Autowired
    LoanTypeDaoService loanTypeService;

    @GetMapping( path = "/LoanType" )
    public List<LoanType> getLoanType()
    {
        final List<LoanType> allLoanTypes = loanTypeService.getAllLoanTypes();
        return allLoanTypes;
    }
}
