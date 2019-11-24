package com.github.ulhasrm.microservices.loanapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.service.StatusDaoService;

@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class StatusController
{
    @Autowired
    StatusDaoService statusService;

    @GetMapping( path = "/Status" )
    public List<Status> getLoanStatus()
    {
        final List<Status> allStatuses = statusService.getAllStatuses();
        return allStatuses;
    }
}
