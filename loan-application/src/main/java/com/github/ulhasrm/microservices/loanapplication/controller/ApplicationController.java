package com.github.ulhasrm.microservices.loanapplication.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.loanapplication.bean.PertChartData;
import com.github.ulhasrm.microservices.loanapplication.bean.PertChartGenerator;
import com.github.ulhasrm.microservices.loanapplication.bean.WorkFlowActionResult;
import com.github.ulhasrm.microservices.loanapplication.bean.WorkflowAction;
import com.github.ulhasrm.microservices.loanapplication.entity.Application;
import com.github.ulhasrm.microservices.loanapplication.entity.LoanType;
import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlow;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlowTransition;
import com.github.ulhasrm.microservices.loanapplication.exception.ApplicationNotFoundException;
import com.github.ulhasrm.microservices.loanapplication.exception.InvalidValueException;
import com.github.ulhasrm.microservices.loanapplication.service.ApplicationDaoService;
import com.github.ulhasrm.microservices.loanapplication.service.LoanTypeDaoService;
import com.github.ulhasrm.microservices.loanapplication.service.StatusDaoService;
import com.github.ulhasrm.microservices.loanapplication.service.WorkflowTransitionDaoService;

@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class ApplicationController
{
    @Autowired
    ApplicationDaoService applicationService;

    @Autowired
    LoanTypeDaoService loanTypeService;

    @Autowired
    StatusDaoService statusService;

    @Autowired
    WorkflowTransitionDaoService workflowTransitionService;

    @GetMapping( path = "/SystemUser/{userId}/Application/{id}" )
    public List<Application> getApplication( @PathVariable( value = "userId" ) String userId,
        @PathVariable( value = "id" ) String applicationId )
    {
        Application application = applicationService.getApplication( Long.parseLong( applicationId ) );
        final List<Application> list = Collections.singletonList( application );
        return list;
    }

    @GetMapping( path = "/SystemUser/{userId}/Application" )
    public List<Application> getApplication( @PathVariable( value = "userId" ) Long userId )
    {
        // TODO check with user-service if its a valid user
        // final SystemUser user = userService.getUser( userName );
        /*if( null == user )
        {
            throw new UserNotFoundException( "userName : " + userName );
        }*/

        List<Application> applications = applicationService.getApplications( userId );
        return applications;
    }

    @GetMapping( path = "/Application/{id}" )
    public Application getApplication2( @PathVariable( value = "id" ) String applicationId )
    {
        final Application application = applicationService.getApplication( Long.parseLong( applicationId ) );
        if( null == application )
        {
            throw new ApplicationNotFoundException( "applicationId : " + applicationId );
        }

        return application;
    }

    @PostMapping( path = "/SystemUser/{userId}/Application" )
    public Application addApplication( @RequestBody final Application application,
        @PathVariable( value = "userId" ) Long userId )
    {
        // TODO - check with user-service if its a valid user
        final LoanType loanType = application.getLoanType();
        if( null == loanType )
        {
            throw new InvalidValueException( "Invalid Application.loanType - " + loanType );
        }

        application.setUserId( userId );
        if( null == application.getApplicationDate() )
        {
            final Date currentDate = new Date();
            application.setApplicationDate( currentDate );
            application.setUpdatedDate( currentDate );
        }

        if( null == application.getStatus() )
        {
            final LoanType refreshedLoanType = loanTypeService.getLoanType( loanType.getName() );
            final Status defaultStatus = refreshedLoanType.getDefaultStatus();
            application.setStatus( defaultStatus );
        }

        Application savedApplication = applicationService.persist( application );
        return savedApplication;
    }

    @GetMapping( path = "/Application" )
    public List<Application> getApplication( final Application application )
    {
        final List<Application> applications = applicationService.getAllApplications();
        return applications;
    }

    @PutMapping( path = "/Application" )
    public Application updateApplication( @RequestBody final Application application )
    {
        final Date currentDate = new Date();
        application.setUpdatedDate( currentDate );

        final LoanType loanType = application.getLoanType();
        if( null == loanType )
        {
            throw new InvalidValueException( "Invalid Application.loanType - " + loanType );
        }

        if( null == application.getStatus() )
        {
            application.setStatus( loanType.getDefaultStatus() );
        }

        Application savedApplication = applicationService.persist( application );
        return savedApplication;
    }

    @PostMapping( path = "Application/PerformAction" )
    public WorkFlowActionResult performAction( @RequestBody final WorkflowAction action )
    {
        final long applicationId = action.getApplicationId();
        final Application application = applicationService.getApplication( applicationId );
        final long transactionId = action.getTransactionId();
        final WorkFlowTransition workflowTransition = workflowTransitionService.getWorkflowTransition( transactionId );
        final Status toStatus = workflowTransition.getTo();

        application.setStatus( toStatus );
        final Date currentDate = new Date();
        application.setUpdatedDate( currentDate );

        try
        {
            applicationService.persist( application );
        }
        catch( Exception e )
        {
            return new WorkFlowActionResult( applicationId, transactionId, toStatus, false, e.getLocalizedMessage() );
        }

        return new WorkFlowActionResult( applicationId, transactionId, toStatus, true,
                                         "Application status updated as - " + toStatus.getName() );
    }
    
    @GetMapping( path = "Application/WorkFlowChart/{appId}/{transitionId}" )
    public PertChartData getWorkFlowChart( @PathVariable( value = "appId" ) Long appId,
        @PathVariable( value = "transitionId" ) Long transitionId )
    {
        final Application application = applicationService.getApplication( appId );
        final LoanType loanType = application.getLoanType();
        final WorkFlow workflow = loanType.getWorkflow();
        Status currentStatus = application.getStatus();
        final List<WorkFlowTransition> transitions =
            workflowTransitionService.getWorkflowTransitionsByWorkflow( workflow );

        final PertChartGenerator chartGenerator = new PertChartGenerator();
        final PertChartData chartData = chartGenerator.generateChart( transitions, currentStatus );
        return chartData;
    }

}
