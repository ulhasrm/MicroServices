package com.github.ulhasrm.microservices.loanapplication.security;

public interface HashGenerator
{
    String ROLE = HashGenerator.class.getName();

    String hash( byte[] value );
}
