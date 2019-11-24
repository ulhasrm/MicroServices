package com.github.ulhasrm.microservices.loanapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.loanapplication.entity.Application;
import com.github.ulhasrm.microservices.loanapplication.entity.LoanType;
import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlow;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlowTransition;
import com.github.ulhasrm.microservices.loanapplication.service.ApplicationDaoService;
import com.github.ulhasrm.microservices.loanapplication.service.LoanTypeDaoService;
import com.github.ulhasrm.microservices.loanapplication.service.StatusDaoService;
import com.github.ulhasrm.microservices.loanapplication.service.WorkflowDaoService;
import com.github.ulhasrm.microservices.loanapplication.service.WorkflowTransitionDaoService;

@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class WorkFlowTransitionController
{
    @Autowired
    ApplicationDaoService applicationService;

    @Autowired
    LoanTypeDaoService loanTypeService;

    @Autowired
    StatusDaoService statusService;

    @Autowired
    WorkflowTransitionDaoService workflowTransitionService;

    @Autowired
    WorkflowDaoService workflowService;

    @GetMapping( path = "Workflow/{id}/WorkflowTransition/{from}/{to}" )
    public WorkFlowTransition getWorkFlowTransition( @PathVariable( value = "id" ) Long workflowId,
        @PathVariable( value = "from" ) Long from, @PathVariable( value = "to" ) Long to )
    {
        final WorkFlow workflow = workflowService.getWorkflow( workflowId );
        final Status fromStatus = statusService.getStatus( from );
        final Status toStatus = statusService.getStatus( to );

        final WorkFlowTransition workFlowTransition =
            workflowTransitionService.getWorkflowTransitionsByFromAndTo( workflow, fromStatus, toStatus );

        return workFlowTransition;
    }

    @GetMapping( path = "Workflow/{id}/WorkflowTransition" )
    public List<WorkFlowTransition> getWorkFlowTransition( @PathVariable( value = "id" ) Long workflowId )
    {
        final WorkFlow workflow = workflowService.getWorkflow( workflowId );

        List<WorkFlowTransition> workFlowTransitions =
            workflowTransitionService.getWorkflowTransitionsByWorkflow( workflow );

        return workFlowTransitions;
    }

    @GetMapping( path = "WorkflowTransition/{appId}" )
    public List<WorkFlowTransition> getWorkFlowTransitionForApplication( @PathVariable( value = "appId" ) Long appId )
    {
        final Application application = applicationService.getApplication( appId );
        final LoanType loanType = application.getLoanType();
        final WorkFlow workflow = loanType.getWorkflow();

        final List<WorkFlowTransition> workFlowTransitions =
            workflowTransitionService.getWorkflowTransitionsByWorkflow( workflow );
        return workFlowTransitions;
    }
    
    @GetMapping( path = "WorkflowTransition/NextAction/{appId}/{userId}" )
    public List<WorkFlowTransition> getWorkFlowTransitionNextAction( @PathVariable( value = "appId" ) Long appId,
        @PathVariable( value = "userId" ) Long userId )
    {
        final Application application = applicationService.getApplication( appId );
        final LoanType loanType = application.getLoanType();
        final WorkFlow workflow = loanType.getWorkflow();
        Status currentStatus = application.getStatus();

        final List<WorkFlowTransition> workFlowTransitions =
            workflowTransitionService.getWorkflowTransitionsByFrom( workflow, currentStatus );
        return workFlowTransitions;
    }
    
}
