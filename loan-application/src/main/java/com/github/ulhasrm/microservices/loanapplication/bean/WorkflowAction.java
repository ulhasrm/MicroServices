package com.github.ulhasrm.microservices.loanapplication.bean;

public class WorkflowAction
{
    private long applicationId;
    private long transactionId;

    public WorkflowAction()
    {
        super();
    }

    public WorkflowAction( long applicationId, long transactionId )
    {
        super();
        this.applicationId = applicationId;
        this.transactionId = transactionId;
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

}
