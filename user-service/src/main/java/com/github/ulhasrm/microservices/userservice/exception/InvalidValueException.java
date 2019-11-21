package com.github.ulhasrm.microservices.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.BAD_REQUEST )
public class InvalidValueException extends RuntimeException
{
    private static final long serialVersionUID = -6553633061037709854L;

    public InvalidValueException( String message )
    {
        super( message );
    }
}
