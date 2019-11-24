package com.github.ulhasrm.microservices.loanapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
public class WorkflowActionException extends RuntimeException
{
    private static final long serialVersionUID = -3235052462240150830L;

    public WorkflowActionException( final String msg )
    {
        super( msg );
    }
}
