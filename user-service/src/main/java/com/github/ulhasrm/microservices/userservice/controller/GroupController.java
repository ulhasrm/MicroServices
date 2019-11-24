package com.github.ulhasrm.microservices.userservice.controller;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.userservice.entity.JoinedUserGroup;
import com.github.ulhasrm.microservices.userservice.entity.SystemGroup;
import com.github.ulhasrm.microservices.userservice.exception.InvalidValueException;
import com.github.ulhasrm.microservices.userservice.service.SystemGroupDaoService;

@RestController
public class GroupController
{
    @Autowired
    SystemGroupDaoService systemGroupService;
    
    @Autowired
    EntityManager entityManager;

    @GetMapping( path = "/SystemGroup" )
    public List<SystemGroup> getGroup( @RequestParam( value = "groupName", required = false ) String groupName )
    {
        if( null != groupName && !groupName.trim().equals( "" ) )
        {
            SystemGroup group = systemGroupService.getGroup( groupName );
            final List<SystemGroup> list = Collections.singletonList( group );
            return list;
        }
        else
        {
            return systemGroupService.getAllUsers();
        }
    }

    @GetMapping( path = "/SystemGroup/{groupName}" )
    public SystemGroup getUserByName( @PathVariable String groupName )
    {
        return systemGroupService.getGroup( groupName );
    }

    @PostMapping( path = "/SystemGroup" )
    public SystemGroup addGroup( final SystemGroup group )
    {
        if( group.getName() != null && !group.getName().equals( "" ) )
        {
            SystemGroup savedGroup = systemGroupService.persist( group );
            return savedGroup;
        }
        else
        {
            throw new InvalidValueException( "Group Name : " + group.getName() );
        }
    }
}
