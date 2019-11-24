package com.github.ulhasrm.microservices.loanapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class WorkflowNotFoundException extends RuntimeException
{

    private static final long serialVersionUID = -4539527170040242348L;

    public WorkflowNotFoundException( String message )
    {
        super( message );
    }
}
