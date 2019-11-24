package com.github.ulhasrm.microservices.loanapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlow;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlowTransition;

public interface WorkFlowTransitionRepository extends JpaRepository<WorkFlowTransition, Long>
{
    List<WorkFlowTransition> findByName( final String name );

    WorkFlowTransition findByNameAndWorkflow( final String name, final WorkFlow workflow );

    List<WorkFlowTransition> findByFrom( final Status from );

    List<WorkFlowTransition> findByTo( final Status to );

    WorkFlowTransition findByWorkflowAndFromAndTo( final WorkFlow workflow, final Status from, final Status to );
    
    List<WorkFlowTransition> findByWorkflowAndFrom( final WorkFlow workflow, final Status from );

    List<WorkFlowTransition> findByWorkflow( final WorkFlow workflow );
}
