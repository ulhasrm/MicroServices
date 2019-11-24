package com.github.ulhasrm.microservices.loanapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity( name = "WorkflowTransition" )
@Table( name  = "WorkflowTransition" )
public class WorkFlowTransition
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "WorkFlowTransition_Seq" )
    private Long id;

    @ManyToOne( )
    @JoinColumn( name = "workflow" )
    private WorkFlow workflow;

    @OneToOne
    @JoinColumn( name = "statusFrom", nullable = false )
    private Status from;

    @OneToOne
    @JoinColumn( name = "statusTo", nullable = false )
    private Status to;

    private String name;
    private String decription;
    private boolean active;
    private boolean commentRequired;

    public WorkFlowTransition()
    {
        super();
    }

    public WorkFlowTransition( final WorkFlow workflow, Status from, Status to, String name, String description,
        boolean commentRequired )
    {
        super();
        this.workflow = workflow;
        this.from = from;
        this.to = to;
        this.name = name;
        this.decription = description;
        this.commentRequired = commentRequired;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public WorkFlow getWorkflow()
    {
        return workflow;
    }

    public void setWorkflow( WorkFlow workflow )
    {
        this.workflow = workflow;
    }

    public Status getFrom()
    {
        return from;
    }

    public void setFrom( Status from )
    {
        this.from = from;
    }

    public Status getTo()
    {
        return to;
    }

    public void setTo( Status to )
    {
        this.to = to;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDecription()
    {
        return decription;
    }

    public void setDecription( String decription )
    {
        this.decription = decription;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive( boolean active )
    {
        this.active = active;
    }

    public boolean isCommentRequired()
    {
        return commentRequired;
    }

    public void setCommentRequired( boolean commentRequired )
    {
        this.commentRequired = commentRequired;
    }

}
