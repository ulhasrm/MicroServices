package com.github.ulhasrm.microservices.userservice.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table( name = "JoinedUserGroup" )
public class JoinedUserGroup
{
    @EmbeddedId
    private UserGroupId id;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "userId" )
    private SystemUser user;

    @ManyToOne( fetch = FetchType.LAZY )
    @MapsId( "groupId" )
    private SystemGroup group;

    public JoinedUserGroup()
    {
        super();
    }

    public JoinedUserGroup( SystemUser user, SystemGroup group )
    {
        super();
        this.user = user;
        this.group = group;
        this.setId( new UserGroupId( user.getId(), group.getId() ) );
    }

    public SystemUser getUser()
    {
        return user;
    }

    public void setUser( SystemUser user )
    {
        this.user = user;
    }

    public SystemGroup getGroup()
    {
        return group;
    }

    public void setGroup( SystemGroup group )
    {
        this.group = group;
    }

    public UserGroupId getId()
    {
        return id;
    }

    public void setId( UserGroupId id )
    {
        this.id = id;
    }

}
