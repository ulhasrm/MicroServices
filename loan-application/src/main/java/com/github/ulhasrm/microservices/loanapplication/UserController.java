package com.github.ulhasrm.microservices.loanapplication;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.loanapplication.entity.User;
import com.github.ulhasrm.microservices.loanapplication.service.UserDaoService;

@RestController
public class UserController
{
    @Autowired
    UserDaoService service;

    @GetMapping( path = "/User" )
    public List<User> getUser( @RequestParam( value = "loginName", required = false ) String loginName )
    {
        if( null != loginName && !loginName.trim().equals( "" ) )
        {
            User user = service.getUser( loginName );
            final List<User> list = Collections.singletonList( user );
            return list;
        }
        else
        {
            return service.getAllUsers();
        }
    }

    @GetMapping( path = "/User/{id}" )
    public User getUser( @PathVariable long id )
    {
        return service.getUser( id );
    }

    @PostMapping( path = "/User" )
    public List<User> addUser( final User user )
    {
        return null;
    }
}
