package com.github.ulhasrm.microservices.loanapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.BAD_GATEWAY )
public class DataSeedingException extends RuntimeException
{
    private static final long serialVersionUID = -2828070164843808775L;

    public DataSeedingException( final String msg )
    {
        super( msg );
    }
}
