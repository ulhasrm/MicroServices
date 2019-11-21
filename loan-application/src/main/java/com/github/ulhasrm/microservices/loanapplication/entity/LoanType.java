package com.github.ulhasrm.microservices.loanapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "LoanType" )
public class LoanType
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "LoanType_Seq" )
    private Long id;

    @Column( name = "name", unique = true )
    private String name;

    @Column( name = "active" )
    private String active;

    @OneToOne
    @JoinColumn( name = "defaultStatus", nullable = false )
    private Status defaultStatus;

    public LoanType()
    {
        super();
    }

    public LoanType( final String loanTypeName, final Status defaultStatus )
    {
        this.name = loanTypeName;
        this.defaultStatus = defaultStatus;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getActive()
    {
        return active;
    }

    public void setActive( String active )
    {
        this.active = active;
    }

    public Status getDefaultStatus()
    {
        return defaultStatus;
    }

    public void setDefaultStatus( Status defaultStatus )
    {
        this.defaultStatus = defaultStatus;
    }

}
