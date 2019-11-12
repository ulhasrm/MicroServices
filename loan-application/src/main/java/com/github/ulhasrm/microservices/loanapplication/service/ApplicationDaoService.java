package com.github.ulhasrm.microservices.loanapplication.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.loanapplication.entity.Application;
import com.github.ulhasrm.microservices.loanapplication.entity.User;
import com.github.ulhasrm.microservices.loanapplication.exception.ApplicationNotFoundException;
import com.github.ulhasrm.microservices.loanapplication.repository.ApplicationRepository;
import com.github.ulhasrm.microservices.loanapplication.repository.UserRepository;

@Repository
@Transactional
public class ApplicationDaoService
{
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    UserRepository userRepository;

    public Application persist( Application application )
    {
        final Application savedApplication = applicationRepository.save( application );
        return savedApplication;
    }

    public void delete( Application application )
    {
        applicationRepository.delete( application );
    }

    public Application getApplication( final Long applicationId )
    {
        final Optional<Application> users = applicationRepository.findById( applicationId );
        if( !users.isPresent() )
        {
            throw new ApplicationNotFoundException( "id : " + applicationId );
        }

        return users.get();
    }

    public List<Application> getApplications( final User user )
    {
        List<Application> applications = applicationRepository.findByUserId( user.getId() );
        return applications;
    }

    public List<Application> getAllApplications()
    {
        return applicationRepository.findAll();
    }
}
