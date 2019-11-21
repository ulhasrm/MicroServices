package com.github.ulhasrm.microservices.loanapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "Status" )
public class Status
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "Status_Seq" )
    private Long id;

    @Column( name = "name", unique = true )
    private String name;

    @Column( name = "active" )
    private boolean active;

    public Status( String statusName )
    {
        this.name = statusName;
        this.active = true;
    }

    public Status()
    {
    }
}
