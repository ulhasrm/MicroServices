package com.github.ulhasrm.microservices.loanapplication.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlow;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlowTransition;
import com.github.ulhasrm.microservices.loanapplication.exception.WorkflowTransitionNotFoundException;
import com.github.ulhasrm.microservices.loanapplication.repository.WorkFlowTransitionRepository;

@Repository
@Transactional
public class WorkflowTransitionDaoService
{
    @Autowired
    WorkFlowTransitionRepository workflowTransitionRepository;

    public WorkFlowTransition persist( WorkFlowTransition workflowTransition )
    {
        final WorkFlowTransition saveedTransition = workflowTransitionRepository.save( workflowTransition );
        return saveedTransition;
    }

    public void delete( WorkFlowTransition workflowTransition )
    {
        workflowTransitionRepository.delete( workflowTransition );
    }

    public WorkFlowTransition getWorkflowTransition( final Long id )
    {
        Optional<WorkFlowTransition> transitions = workflowTransitionRepository.findById( id );
        if( !transitions.isPresent() )
        {
            throw new WorkflowTransitionNotFoundException( "id : " + id );
        }

        return transitions.get();
    }

    public List<WorkFlowTransition> getWorkflowTransitionsByFrom( final Status from )
    {
        List<WorkFlowTransition> transitions = workflowTransitionRepository.findByFrom( from );
        return transitions;
    }

    public List<WorkFlowTransition> getWorkflowTransitionsByTo( final Status to )
    {
        List<WorkFlowTransition> transitions = workflowTransitionRepository.findByTo( to );
        return transitions;
    }

    public List<WorkFlowTransition> getWorkflowTransitionsByName( final String name )
    {
        List<WorkFlowTransition> transitions = workflowTransitionRepository.findByName( name );
        return transitions;
    }

    public WorkFlowTransition getWorkflowTransitionsByFromAndTo( final WorkFlow workflow, final Status from,
        final Status to )
    {
        WorkFlowTransition transition = workflowTransitionRepository.findByWorkflowAndFromAndTo( workflow, from, to );
        return transition;
    }
    
    public List<WorkFlowTransition> getWorkflowTransitionsByFrom( final WorkFlow workflow, final Status from )
    {
        final List<WorkFlowTransition> transitions =
            workflowTransitionRepository.findByWorkflowAndFrom( workflow, from );
        return transitions;
    }

    public List<WorkFlowTransition> getWorkflowTransitionsByWorkflow( final WorkFlow workflow )
    {
        return workflowTransitionRepository.findByWorkflow( workflow );
    }
}
