package com.github.ulhasrm.microservices.loanapplication.datasetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.ulhasrm.microservices.loanapplication.entity.LoanType;
import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.exception.DataSeedingException;
import com.github.ulhasrm.microservices.loanapplication.repository.LoanTypeRepository;
import com.github.ulhasrm.microservices.loanapplication.repository.StatusRepository;

@Component
public class MyInitialRunner implements CommandLineRunner
{

    private static final Logger logger = LoggerFactory.getLogger( MyInitialRunner.class );

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    @Override
    public void run( String... args ) throws Exception
    {
        createDefaultStatuses();
        createDefaultLoanTypes();
    }

    private void createDefaultStatuses()
    {
        try
        {
            mayCreateStatus( "New Application" );
            mayCreateStatus( "Application Submitted" );
            mayCreateStatus( "Document Reviewed" );
            mayCreateStatus( "Credit Limit Checked" );
            mayCreateStatus( "Eligibility Checked" );
            mayCreateStatus( "Personal Verification Done" );
            mayCreateStatus( "Loan Approved" );
            mayCreateStatus( "Loan Rejected" );
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

            mayCreateLoanType( "Personal Loan", newApplicationStatus );
            mayCreateLoanType( "Car Loan", newApplicationStatus );
            mayCreateLoanType( "Home Loan", newApplicationStatus );
            mayCreateLoanType( "Retail Loan", newApplicationStatus );
            mayCreateLoanType( "Business Loan", newApplicationStatus );
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

    private void mayCreateLoanType( final String loanTypeName, final Status defaultStatus )
    {
        LoanType loanType = loanTypeRepository.findByName( loanTypeName );
        if( null == loanType )
        {
            loanTypeRepository.save( new LoanType( loanTypeName, defaultStatus ) );
        }
    }
}
