package com.github.ulhasrm.microservices.loanapplication.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlow;
import com.github.ulhasrm.microservices.loanapplication.exception.WorkflowNotFoundException;
import com.github.ulhasrm.microservices.loanapplication.repository.WorkFlowRepository;

@Repository
@Transactional
public class WorkflowDaoService
{
    @Autowired
    WorkFlowRepository workflowRepository;

    public WorkFlow persist( WorkFlow workflow )
    {
        final WorkFlow saveed = workflowRepository.save( workflow );
        return saveed;
    }

    public void delete( WorkFlow workflow )
    {
        workflowRepository.delete( workflow );
    }

    public WorkFlow getWorkflow( final Long id )
    {
        Optional<WorkFlow> workFlow = workflowRepository.findById( id );
        if( !workFlow.isPresent() )
        {
            throw new WorkflowNotFoundException( "id : " + id );
        }

        return workFlow.get();
    }

    public WorkFlow getWorkflowByFrom( final String name )
    {
        WorkFlow workflows = workflowRepository.findByName( name );
        return workflows;
    }
}
