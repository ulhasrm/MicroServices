package com.github.ulhasrm.microservices.loanapplication.bean;

public class PertChartData
{
    private boolean success;
    private String result;

    public PertChartData( boolean success, String result )
    {
        super();
        this.success = success;
        this.result = result;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess( boolean success )
    {
        this.success = success;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult( String result )
    {
        this.result = result;
    }

}
