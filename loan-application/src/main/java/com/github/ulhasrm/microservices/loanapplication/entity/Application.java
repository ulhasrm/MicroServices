package com.github.ulhasrm.microservices.loanapplication.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity( name = "Application" )
@Table( name = "application" )
public class Application
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "App_Seq" )
    private Long id;

    @Column( name = "user_id", nullable = false )
    private Long userId;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "status" )
    private Status status;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "loanType" )
    private LoanType loanType;

    private Date applicationDate;
    private Date updatedDate;
    private Long amount;
    private Long amountApproved;
    private long mobileNumber;
    private String referenceNumber;

    public Application()
    {
        super();
    }

    public Application( Long userId, Status status, Date applicationDate, Date updatedDate, Long amount,
        Long amountApproved )
    {
        super();
        this.userId = userId;
        this.status = status;
        this.applicationDate = applicationDate;
        this.updatedDate = updatedDate;
        this.amount = amount;
        this.amountApproved = amountApproved;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus( Status status )
    {
        this.status = status;
    }

    public Date getApplicationDate()
    {
        return applicationDate;
    }

    public void setApplicationDate( Date applicationDate )
    {
        this.applicationDate = applicationDate;
    }

    public Date getUpdatedDate()
    {
        return updatedDate;
    }

    public void setUpdatedDate( Date updatedDate )
    {
        this.updatedDate = updatedDate;
    }

    public Long getAmount()
    {
        return amount;
    }

    public void setAmount( Long amount )
    {
        this.amount = amount;
    }

    public Long getAmountApproved()
    {
        return amountApproved;
    }

    public void setAmountApproved( Long amountApproved )
    {
        this.amountApproved = amountApproved;
    }

    public LoanType getLoanType()
    {
        return loanType;
    }

    public void setLoanType( LoanType loanType )
    {
        this.loanType = loanType;
    }

    public long getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber( long mobileNumber )
    {
        this.mobileNumber = mobileNumber;
    }

    public String getReferenceNumber()
    {
        return referenceNumber;
    }

    public void setReferenceNumber( String referenceNumber )
    {
        this.referenceNumber = referenceNumber;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId( Long userId )
    {
        this.userId = userId;
    }

}
