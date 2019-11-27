package com.github.ulhasrm.microservices.authserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class UserNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 7460185477275474785L;

    public UserNotFoundException( String message )
    {
        super( message );
    }
}
