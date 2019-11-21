package com.github.ulhasrm.microservices.userservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserGroupId implements Serializable
{
    private static final long serialVersionUID = 3817254546660784210L;

    @Column( name = "user_id" )
    private Long userId;

    @Column( name = "group_id" )
    private Long groupId;

    public UserGroupId( Long userId, Long groupId )
    {
        super();
        this.userId = userId;
        this.groupId = groupId;
    }

    public UserGroupId()
    {
        super();
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId( Long userId )
    {
        this.userId = userId;
    }

    public Long getGroupId()
    {
        return groupId;
    }

    public void setGroupId( Long groupId )
    {
        this.groupId = groupId;
    }

    @Override
    public String toString()
    {
        return "UserGroupId [userId=" + userId + ", groupId=" + groupId + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( groupId == null ) ? 0 : groupId.hashCode() );
        result = prime * result + ( ( userId == null ) ? 0 : userId.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if( this == obj )
            return true;
        if( obj == null )
            return false;
        if( getClass() != obj.getClass() )
            return false;
        UserGroupId other = (UserGroupId)obj;
        if( groupId == null )
        {
            if( other.groupId != null )
                return false;
        }
        else if( !groupId.equals( other.groupId ) )
            return false;
        if( userId == null )
        {
            if( other.userId != null )
                return false;
        }
        else if( !userId.equals( other.userId ) )
            return false;
        return true;
    }

}
