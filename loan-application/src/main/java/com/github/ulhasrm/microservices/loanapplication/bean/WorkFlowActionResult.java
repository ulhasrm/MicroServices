package com.github.ulhasrm.microservices.loanapplication.bean;

import com.github.ulhasrm.microservices.loanapplication.entity.Status;

public class WorkFlowActionResult
{
    private long applicationId;
    private long transactionId;
    private Status status;
    private boolean success;
    private String message;

    public WorkFlowActionResult( long applicationId, long transactionId, Status status, final boolean success,
        String message )
    {
        super();
        this.applicationId = applicationId;
        this.transactionId = transactionId;
        this.status = status;
        this.success = success;
        this.message = message;
    }

    public long getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId( long applicationId )
    {
        this.applicationId = applicationId;
    }

    public long getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId( long transactionId )
    {
        this.transactionId = transactionId;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus( Status status )
    {
        this.status = status;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess( boolean success )
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }
}
