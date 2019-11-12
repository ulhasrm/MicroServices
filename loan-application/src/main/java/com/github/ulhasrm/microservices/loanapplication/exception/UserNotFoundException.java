package com.github.ulhasrm.microservices.loanapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class UserNotFoundException extends RuntimeException
{
    /**
     * 
     */
    private static final long serialVersionUID = -8518567073100136476L;

    public UserNotFoundException( String message )
    {
        super( message );
    }
}
