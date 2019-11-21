package com.github.ulhasrm.microservices.loanapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoanApplication
{

    public static void main( String[] args )
    {
        SpringApplication.run( LoanApplication.class, args );
    }

}
