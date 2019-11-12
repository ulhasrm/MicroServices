package com.github.ulhasrm.microservices.loanapplication.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity( name = "Application" )
@Table( name = "application" )
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "App_Seq")
    private Long id;

    // @ManyToOne
    // @JoinColumn( name = "userId", nullable = false, insertable = false, updatable = false )
    // @JsonIgnore
    @ManyToOne( cascade = CascadeType.ALL )
    private User user;
    private LoanStatus status;
    private LoanType loanType;
    private Date applicationDate;
    private Date updatedDate;
    private Long amount;
    private Long amountApproved;

    public Application()
    {
        super();
    }

    public Application( User user, LoanStatus status, Date applicationDate, Date updatedDate, Long amount,
        Long amountApproved )
    {
        super();
        this.user = user;
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

    public User getUser()
    {
        return user;
    }

    public void setUser( User userId )
    {
        this.user = userId;
    }

    public LoanStatus getStatus()
    {
        return status;
    }

    public void setStatus( LoanStatus status )
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
    
    

}
