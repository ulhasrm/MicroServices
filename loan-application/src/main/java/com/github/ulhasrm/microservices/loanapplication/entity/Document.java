package com.github.ulhasrm.microservices.loanapplication.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "document" )
public class Document
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "Doc_Seq" )
    private Long id;

    @ManyToOne( cascade = CascadeType.ALL )
    private Application application;
    private String type;
    private String url;
    private boolean approved;

    public Document()
    {
        super();
    }

    public Document( Application application, String url )
    {
        super();
        this.application = application;
        this.url = url;
        this.approved = false;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Application getApplication()
    {
        return application;
    }

    public void setApplication( Application application )
    {
        this.application = application;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public boolean isApproved()
    {
        return approved;
    }

    public void setApproved( boolean approved )
    {
        this.approved = approved;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

}
