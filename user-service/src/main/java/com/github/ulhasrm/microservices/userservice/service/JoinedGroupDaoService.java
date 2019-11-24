package com.github.ulhasrm.microservices.userservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.userservice.entity.JoinedUserGroup;
import com.github.ulhasrm.microservices.userservice.repository.JoinedUserGroupRepository;

@Repository
@Transactional
public class JoinedGroupDaoService
{
    @Autowired
    JoinedUserGroupRepository joinedGroupRepository;

    public JoinedUserGroup persist( JoinedUserGroup group )
    {
        final JoinedUserGroup savedGroup = joinedGroupRepository.save( group );
        return savedGroup;
    }

    public void delete( JoinedUserGroup group )
    {
        joinedGroupRepository.delete( group );
    }

    public List<JoinedUserGroup> getGroupByGroup( final Long groupId )
    {
        List<JoinedUserGroup> groups = joinedGroupRepository.findByGroup( groupId );
        return groups;
    }

    public List<JoinedUserGroup> getGroupByUser( final Long userId )
    {
        List<JoinedUserGroup> joinedGroups = joinedGroupRepository.findByUser( userId );
        return joinedGroups;
    }

    public List<JoinedUserGroup> getAllJoinedGroup()
    {
        return joinedGroupRepository.findAll();
    }

}
