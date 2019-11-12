package com.github.ulhasrm.microservices.apigatewayserver.filter;

public class JWTValidationException extends RuntimeException
{
    public JWTValidationException( String message )
    {
        super( message );
    }
}
