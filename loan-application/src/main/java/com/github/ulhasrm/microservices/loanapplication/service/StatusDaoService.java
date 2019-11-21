package com.github.ulhasrm.microservices.loanapplication.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.exception.ObjectNotFoundException;
import com.github.ulhasrm.microservices.loanapplication.repository.StatusRepository;

@Repository
@Transactional
public class StatusDaoService
{
    @Autowired
    StatusRepository statusRepository;

    public Status persist( Status status )
    {
        final Status savedLoanType = statusRepository.save( status );
        return savedLoanType;
    }

    public void delete( Status loanType )
    {
        statusRepository.delete( loanType );
    }

    public Status getLoanType( final String userName )
    {
        final Status loanType = statusRepository.findByName( userName );
        return loanType;
    }

    public Status getStatus( final Long statusId )
    {
        final Optional<Status> loanTypes = statusRepository.findById( statusId );
        if( !loanTypes.isPresent() )
        {
            throw new ObjectNotFoundException( Status.class.getSimpleName(), statusId );
        }

        return loanTypes.get();
    }

    public Status getStatus( final String statusName )
    {
        Status status = statusRepository.findByName( statusName );
        if( null == status )
        {
            throw new ObjectNotFoundException( Status.class.getSimpleName(), statusName );
        }

        return status;
    }

    public List<Status> getAllStatuses()
    {
        return statusRepository.findAll();
    }

}
