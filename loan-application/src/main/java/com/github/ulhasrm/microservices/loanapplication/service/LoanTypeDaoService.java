package com.github.ulhasrm.microservices.loanapplication.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.loanapplication.entity.LoanType;
import com.github.ulhasrm.microservices.loanapplication.exception.UserNotFoundException;
import com.github.ulhasrm.microservices.loanapplication.repository.LoanTypeRepository;

@Repository
@Transactional
public class LoanTypeDaoService
{
    @Autowired
    LoanTypeRepository loanTypeRepository;

    public LoanType persist( LoanType loanType )
    {
        final LoanType savedLoanType = loanTypeRepository.save( loanType );
        return savedLoanType;
    }

    public void delete( LoanType loanType )
    {
        loanTypeRepository.delete( loanType );
    }

    public LoanType getLoanType( final String userName )
    {
        final LoanType loanType = loanTypeRepository.findByName( userName );
        return loanType;
    }

    public LoanType getUser( final Long userId )
    {
        final Optional<LoanType> loanTypes = loanTypeRepository.findById( userId );
        if( !loanTypes.isPresent() )
        {
            throw new UserNotFoundException( "id : " + userId );
        }

        return loanTypes.get();
    }

    public List<LoanType> getAllLoanTypes()
    {
        return loanTypeRepository.findAll();
    }

}
