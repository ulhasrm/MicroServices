package com.github.ulhasrm.microservices.loanapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class ApplicationNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 7512040351093142337L;

    public ApplicationNotFoundException( String message )
    {
        super( message );
    }
}
