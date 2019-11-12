package com.github.ulhasrm.microservices.authserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloController
{
    @GetMapping( path = "/hello" )
    public String firstPage()
    {
        return "Hello World";
    }
}
