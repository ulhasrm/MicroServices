package com.github.ulhasrm.microservices.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "SystemGroup" )
public class SystemGroup
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SystemGroup_Seq" )
    private Long id;

    @Column( name = "name", unique = true, nullable = false )
    private String name;
    @Column( name = "active" )
    private boolean active;

    public SystemGroup()
    {
        super();
    }

    public SystemGroup( final String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive( boolean active )
    {
        this.active = active;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

}
