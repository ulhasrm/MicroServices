package com.github.ulhasrm.microservices.authserver.security;

public interface HashGenerator
{
    String ROLE = HashGenerator.class.getName();

    String hash( byte[] value );
}
