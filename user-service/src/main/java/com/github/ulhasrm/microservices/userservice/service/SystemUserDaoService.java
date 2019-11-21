package com.github.ulhasrm.microservices.userservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.userservice.entity.SystemUser;
import com.github.ulhasrm.microservices.userservice.repository.SystemUserRepository;

@Repository
@Transactional
public class SystemUserDaoService
{
    @Autowired
    SystemUserRepository userRepository;

    public SystemUser persist( SystemUser user )
    {
        final SystemUser savedUser = userRepository.save( user );
        return savedUser;
    }

    public void delete( SystemUser user )
    {
        userRepository.delete( user );
    }

    public SystemUser getUser( final String userName )
    {
        final SystemUser user = userRepository.findByUserName( userName );
        return user;
    }

    public List<SystemUser> getAllUsers()
    {
        return userRepository.findAll();
    }

}
