package com.github.ulhasrm.microservices.apigatewayserver.filter;

public class JWTValidationException extends RuntimeException
{
    private static final long serialVersionUID = -4371577060418211491L;

    public JWTValidationException( String message )
    {
        super( message );
    }
}
