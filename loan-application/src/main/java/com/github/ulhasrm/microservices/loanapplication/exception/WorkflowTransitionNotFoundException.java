package com.github.ulhasrm.microservices.loanapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class WorkflowTransitionNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = -7463644592356218426L;

    public WorkflowTransitionNotFoundException( String message )
    {
        super( message );
    }
}
