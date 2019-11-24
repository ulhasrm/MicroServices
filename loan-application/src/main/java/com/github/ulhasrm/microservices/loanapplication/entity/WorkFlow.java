package com.github.ulhasrm.microservices.loanapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity( name = "Workflow" )
@Table( name = "workflow" )
public class WorkFlow
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "WorkFlow_Seq" )
    private Long id;

    @Column( name = "name", unique = true )
    private String name;

    @Column( name = "description" )
    private String description;

    @Column( name = "active" )
    private boolean active;

    public WorkFlow()
    {
        super();
    }

    public WorkFlow( String name, String description )
    {
        super();
        this.name = name;
        this.description = description;
        this.active = true;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive( boolean active )
    {
        this.active = active;
    }

}
