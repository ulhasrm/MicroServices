package com.github.ulhasrm.microservices.loanapplication;

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

import com.github.ulhasrm.microservices.loanapplication.entity.Application;
import com.github.ulhasrm.microservices.loanapplication.entity.LoanStatus;
import com.github.ulhasrm.microservices.loanapplication.entity.User;
import com.github.ulhasrm.microservices.loanapplication.exception.ApplicationNotFoundException;
import com.github.ulhasrm.microservices.loanapplication.exception.UserNotFoundException;
import com.github.ulhasrm.microservices.loanapplication.service.ApplicationDaoService;
import com.github.ulhasrm.microservices.loanapplication.service.UserDaoService;

@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class ApplicationController
{
    @Autowired
    ApplicationDaoService applicationService;
    @Autowired
    UserDaoService userService;

    @GetMapping( path = "/User/{userId}/Application/{id}" )
    public List<Application> getApplication( @PathVariable( value = "userId" ) String userId,
        @PathVariable( value = "id" ) String applicationId )
    {
        Application application = applicationService.getApplication( Long.parseLong( applicationId ) );
        final List<Application> list = Collections.singletonList( application );
        return list;
    }

    @GetMapping( path = "/User/{userId}/Application" )
    public List<Application> getApplication( @PathVariable( value = "userId" ) String userId )
    {
        final User user = userService.getUser( Long.parseLong( userId ) );
        if( null == user )
        {
            throw new UserNotFoundException( "userId : " + userId );
        }

        List<Application> applications = applicationService.getApplications( user );
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

    @PostMapping( path = "/User/{userId}/Application" )
    public Application addApplication( @RequestBody final Application application,
        @PathVariable( value = "userId" ) String userId )
    {
        final User user = userService.getUser( Long.parseLong( userId ) );
        if( null == user )
        {
            throw new UserNotFoundException( "userId : " + userId );
        }

        application.setUser( user );
        if( null == application.getApplicationDate() )
        {
            final Date currentDate = new Date();
            application.setApplicationDate( currentDate );
            application.setUpdatedDate( currentDate );
        }

        if( null == application.getStatus() )
        {
            application.setStatus( LoanStatus.NEW );
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

        if( null == application.getStatus() )
        {
            application.setStatus( LoanStatus.NEW );
        }

        Application savedApplication = applicationService.persist( application );
        return savedApplication;
    }
    
}
