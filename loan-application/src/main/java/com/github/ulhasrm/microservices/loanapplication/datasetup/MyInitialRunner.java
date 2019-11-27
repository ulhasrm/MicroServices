package com.github.ulhasrm.microservices.loanapplication.datasetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.ulhasrm.microservices.loanapplication.entity.LoanType;
import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlow;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlowTransition;
import com.github.ulhasrm.microservices.loanapplication.exception.DataSeedingException;
import com.github.ulhasrm.microservices.loanapplication.repository.LoanTypeRepository;
import com.github.ulhasrm.microservices.loanapplication.repository.StatusRepository;
import com.github.ulhasrm.microservices.loanapplication.repository.WorkFlowRepository;
import com.github.ulhasrm.microservices.loanapplication.repository.WorkFlowTransitionRepository;

@Component
public class MyInitialRunner implements CommandLineRunner
{

    private static final Logger logger = LoggerFactory.getLogger( MyInitialRunner.class );

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    @Autowired
    private WorkFlowRepository workflowRepository;

    @Autowired
    private WorkFlowTransitionRepository workflowTransitionRepository;

    @Override
    public void run( String... args ) throws Exception
    {
        createDefaultStatuses();
        createDefaultWorkFlow();
        createDefaultLoanTypes();
    }

    private void createDefaultWorkFlow()
    {
        WorkFlow homeLoanWorkFlow =
            mayCreateWorkFlow( "Home Loan WorkFlow", "WorkFlow designed for Home Loan Process" );
        //WorkFlow defaultWorkFlow = mayCreateWorkFlow( "Default WorkFlow", "Default WorkFlow" );

        mayCreateWorkFlowTransition( "Submit Application", homeLoanWorkFlow, "New Application", "Application Submitted",
                                     "", false );
        mayCreateWorkFlowTransition( "Review Document", homeLoanWorkFlow, "Application Submitted", "Document Reviewing",
                                     "", true );
        mayCreateWorkFlowTransition( "Approve Documents", homeLoanWorkFlow, "Document Reviewing", "Documents Approved",
                                     "", true );
        mayCreateWorkFlowTransition( "Reject Documents", homeLoanWorkFlow, "Document Reviewing", "Documents Rejected",
                                     "", true );

        mayCreateWorkFlowTransition( "Check Credit Limit", homeLoanWorkFlow, "Documents Approved",
                                     "Credit Limit Checking", "", true );

        mayCreateWorkFlowTransition( "Low Credit", homeLoanWorkFlow, "Credit Limit Checking", "Loan Rejected", "",
                                     true );
        mayCreateWorkFlowTransition( "Credit Approve", homeLoanWorkFlow, "Credit Limit Checking", "Credit Approved", "",
                                     true );

        mayCreateWorkFlowTransition( "Personal Verification", homeLoanWorkFlow, "Credit Approved",
                                     "Personal Verification - InProgress", "", true );

        mayCreateWorkFlowTransition( "Verification Approve", homeLoanWorkFlow, "Personal Verification - InProgress",
                                     "Personal Verification Success", "", true );
        mayCreateWorkFlowTransition( "Verification Reject", homeLoanWorkFlow, "Personal Verification - InProgress",
                                     "Personal Verification Failed", "", true );

        mayCreateWorkFlowTransition( "Manager Approval", homeLoanWorkFlow, "Personal Verification Success",
                                     "Loan Approved", "", true );

        mayCreateWorkFlowTransition( "Start Documentation", homeLoanWorkFlow, "Loan Approved",
                                     "Loan Documentation - InProcess", "", true );
        mayCreateWorkFlowTransition( "Documentation Done", homeLoanWorkFlow, "Loan Documentation - InProcess",
                                     "Documentation Done", "", false );

        mayCreateWorkFlowTransition( "Disburse", homeLoanWorkFlow, "Documentation Done", "Loan Disbursed", "", false );
        mayCreateWorkFlowTransition( "Close", homeLoanWorkFlow, "Loan Disbursed", "Loan Closed", "", true );
    }

    private WorkFlowTransition mayCreateWorkFlowTransition( final String name, final WorkFlow workFlow,
        final String from, final String to, final String description, final boolean commentRequired )
    {
        final WorkFlowTransition workFlowTransition =
            workflowTransitionRepository.findByNameAndWorkflow( name, workFlow );
        if( null == workFlowTransition )
        {
            final Status fromStatus = statusRepository.findByName( from );
            final Status toStatus = statusRepository.findByName( to );
            final WorkFlowTransition savedWorkFlowTransition =
                workflowTransitionRepository.save( new WorkFlowTransition( workFlow, fromStatus, toStatus, name,
                                                                           description, commentRequired ) );

            return savedWorkFlowTransition;
        }

        return workFlowTransition;
    }

    private WorkFlow mayCreateWorkFlow( final String name, final String description )
    {
        final WorkFlow workFlow = workflowRepository.findByName( name );
        if( null == workFlow )
        {
            WorkFlow savedWorkFlow = workflowRepository.save( new WorkFlow( name, description ) );
            return savedWorkFlow;
        }

        return workFlow;
    }

    private void createDefaultStatuses()
    {
        try
        {
            mayCreateStatus( "New Application" );
            mayCreateStatus( "Application Submitted" );
            mayCreateStatus( "Document Reviewing" );
            mayCreateStatus( "Documents Approved" );
            mayCreateStatus( "Documents Rejected" );
            mayCreateStatus( "Credit Limit Checking" );
            mayCreateStatus( "Credit Approved" );
            mayCreateStatus( "Personal Verification - InProgress" );
            mayCreateStatus( "Personal Verification Success" );
            mayCreateStatus( "Personal Verification Failed" );
            mayCreateStatus( "Loan Approved" );
            mayCreateStatus( "Loan Rejected" );
            mayCreateStatus( "Loan Documentation - InProcess" );
            mayCreateStatus( "Documentation Done" );
            mayCreateStatus( "Loan Disbursed" );
            mayCreateStatus( "Loan Closed" );
        }
        catch( Exception e )
        {
            logger.error( e.getLocalizedMessage() );
            throw new DataSeedingException( "Status: " + e.getLocalizedMessage() );
        }
    }

    private void createDefaultLoanTypes()
    {
        try
        {
            final Status newApplicationStatus = statusRepository.findByName( "New Application" );
            final WorkFlow homeLoanWorkFlow = workflowRepository.findByName( "Home Loan WorkFlow" );
            final WorkFlow defaultWorkFlow = workflowRepository.findByName( "Default WorkFlow" );

            mayCreateLoanType( "Personal Loan", newApplicationStatus, defaultWorkFlow );
            mayCreateLoanType( "Car Loan", newApplicationStatus, defaultWorkFlow );
            mayCreateLoanType( "Home Loan", newApplicationStatus, homeLoanWorkFlow );
            mayCreateLoanType( "Retail Loan", newApplicationStatus, defaultWorkFlow );
            mayCreateLoanType( "Business Loan", newApplicationStatus, defaultWorkFlow );
        }
        catch( Exception e )
        {
            logger.error( e.getLocalizedMessage() );
            throw new DataSeedingException( "LoanType: " + e.getLocalizedMessage() );
        }
    }

    private void mayCreateStatus( final String statusName )
    {
        Status status = statusRepository.findByName( statusName );
        if( null == status )
        {
            statusRepository.save( new Status( statusName ) );
        }
    }

    private void mayCreateLoanType( final String loanTypeName, final Status defaultStatus, final WorkFlow workflow )
    {
        LoanType loanType = loanTypeRepository.findByName( loanTypeName );
        if( null == loanType )
        {
            loanTypeRepository.save( new LoanType( loanTypeName, defaultStatus, workflow ) );
        }
        else
        {
            loanType.setWorkflow( workflow );
            loanTypeRepository.save( loanType );
        }
    }
}
