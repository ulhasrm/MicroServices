package com.github.ulhasrm.microservices.userservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.userservice.bean.UserGroupBean;
import com.github.ulhasrm.microservices.userservice.bean.UserGroupBean.GroupDet;
import com.github.ulhasrm.microservices.userservice.entity.JoinedUserGroup;
import com.github.ulhasrm.microservices.userservice.entity.SystemUser;
import com.github.ulhasrm.microservices.userservice.exception.InvalidValueException;
import com.github.ulhasrm.microservices.userservice.service.JoinedGroupDaoService;
import com.github.ulhasrm.microservices.userservice.service.SystemUserDaoService;

@RestController
public class JoinedGroupController
{
    @Autowired
    JoinedGroupDaoService joinedUserGroupService;

    @Autowired
    SystemUserDaoService systemUserService;

    @GetMapping( path = "/JoinedUserGroup/SystemUser/{userName}" )
    public UserGroupBean getJoinedGroupByUser( @PathVariable String userName )
    {
        final SystemUser user = systemUserService.getUser( userName );
        if( null != user )
        {
            final List<JoinedUserGroup> groups = joinedUserGroupService.getGroupByUser( user.getId() );
            final List<UserGroupBean> beans = beanMapper( groups );
            if( beans.size() > 0 )
            {
                return beans.get( 0 );
            }
        }

        final UserGroupBean empty = new UserGroupBean();
        empty.setExist( false );
        return empty;
    }

    @GetMapping( path = "/JoinedUserGroup/SystemGroup/{groupId}" )
    public List<UserGroupBean> getJoinedGroupByGroup( @PathVariable Long groupId )
    {
        final List<JoinedUserGroup> groups = joinedUserGroupService.getGroupByGroup( groupId );
        final List<UserGroupBean> beans = beanMapper( groups );
        return beans;
    }

    @GetMapping( path = "/JoinedUserGroup" )
    public List<UserGroupBean> getAllJoinedGroup()
    {
        final List<JoinedUserGroup> groups = joinedUserGroupService.getAllJoinedGroup();
        final List<UserGroupBean> beans = beanMapper( groups );
        return beans;
    }

    @PostMapping( path = "/JoinedUserGroup" )
    public JoinedUserGroup addGroup( final JoinedUserGroup group )
    {
        if( null != group.getGroup() && null != group.getUser() )
        {
            JoinedUserGroup saved = joinedUserGroupService.persist( group );
            return saved;
        }
        else
        {
            throw new InvalidValueException( "JoinedUserGroup : Group-" + group.getGroup() + " User-"
                + group.getUser() );
        }
    }

    private List<UserGroupBean> beanMapper( final List<JoinedUserGroup> groups )
    {
        final Map<SystemUser, List<UserGroupBean.GroupDet>> map = new HashMap<>();
        final Map<SystemUser, Boolean> userMapping = new HashMap<>();
        for( JoinedUserGroup group : groups )
        {
            final SystemUser user = group.getUser();
            if( !map.containsKey( user ) )
            {
                List<UserGroupBean.GroupDet> list = new ArrayList<>();
                map.put( user, list );
                userMapping.put( user, false );
            }

            final UserGroupBean.GroupDet det = new UserGroupBean.GroupDet();
            det.setId( group.getGroup().getId() );
            det.setName( group.getGroup().getName() );
            if( !group.getGroup().getName().equals( "Customer" ) )
            {
                userMapping.put( user, true );
            }
            map.get( user ).add( det );
        }

        final List<UserGroupBean> beans = new ArrayList<>();
        for( SystemUser user : map.keySet() )
        {
            UserGroupBean bean = new UserGroupBean();
            bean.setFirstName( user.getFirstName() );
            bean.setLastName( user.getLastName() );
            bean.setUserName( user.getUserName() );
            bean.setId( user.getId() );
            bean.setPassword( user.getPassword() );
            bean.setExist( true );
            bean.setAdmin( userMapping.get( user ) );

            List<GroupDet> list = map.get( user );
            bean.setGroups( list );
            beans.add( bean );
        }

        return beans;
    }
}
