package com.github.ulhasrm.microservices.userservice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.userservice.entity.SystemUser;
import com.github.ulhasrm.microservices.userservice.exception.InvalidValueException;
import com.github.ulhasrm.microservices.userservice.security.DefaultHashGenerator;
import com.github.ulhasrm.microservices.userservice.service.SystemUserDaoService;

@RestController
public class UserController
{
    @Autowired
    SystemUserDaoService service;

    @Autowired
    private DefaultHashGenerator hashGenerator;

    @GetMapping( path = "/SystemUser" )
    public List<SystemUser> getUser( @RequestParam( value = "loginName", required = false ) String loginName )
    {
        if( null != loginName && !loginName.trim().equals( "" ) )
        {
            SystemUser user = service.getUser( loginName );
            final List<SystemUser> list = Collections.singletonList( user );
            return list;
        }
        else
        {
            return service.getAllUsers();
        }
    }

    @GetMapping( path = "/SystemUser/{userName}" )
    public SystemUser getUserByName( @PathVariable String userName )
    {
        return service.getUser( userName );
    }

    @PostMapping( path = "/SystemUser" )
    public SystemUser addUser( final SystemUser user )
    {
        if( user.getPassword() != null && !user.equals( "" ) )
        {
            final String hashedPwd = hashGenerator.hash( "amitkan123".getBytes() );
            user.setPassword( hashedPwd );
            service.persist( user );
            return user;
        }
        else
        {
            throw new InvalidValueException( "" );
        }
    }
}
