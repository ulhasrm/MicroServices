package com.github.ulhasrm.microservices.userservice.security;

public interface HashGenerator
{
    String ROLE = HashGenerator.class.getName();

    String hash( byte[] value );
}
