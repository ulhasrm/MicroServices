package com.github.ulhasrm.microservices.userservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.userservice.entity.SystemGroup;
import com.github.ulhasrm.microservices.userservice.repository.SystemGroupRepository;

@Repository
@Transactional
public class SystemGroupDaoService
{
    @Autowired
    SystemGroupRepository systemGroupRepository;

    public SystemGroup persist( SystemGroup group )
    {
        final SystemGroup savedGroup = systemGroupRepository.save( group );
        return savedGroup;
    }

    public void delete( SystemGroup group )
    {
        systemGroupRepository.delete( group );
    }

    public SystemGroup getGroup( final String groupName )
    {
        final SystemGroup group = systemGroupRepository.findByName( groupName );
        return group;
    }

    public List<SystemGroup> getAllUsers()
    {
        return systemGroupRepository.findAll();
    }

}
