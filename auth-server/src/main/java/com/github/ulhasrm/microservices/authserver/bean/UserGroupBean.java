package com.github.ulhasrm.microservices.authserver.bean;

import java.util.List;
import java.util.stream.Collectors;

public class UserGroupBean
{
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private List<GroupDet> groups;
    private String password;
    private boolean exist;
    private boolean admin;

    public UserGroupBean( Long id, String userName, String firstName, String lastName, String password )
    {
        super();
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.exist = true;
    }

    public UserGroupBean()
    {
        super();
    }

    public List<GroupDet> getGroups()
    {
        return groups;
    }

    public void setGroups( List<GroupDet> groups )
    {
        this.groups = groups;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public boolean isExist()
    {
        return exist;
    }

    public void setExist( boolean exist )
    {
        this.exist = exist;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public void setAdmin( boolean admin )
    {
        this.admin = admin;
    }

    public static class GroupDet
    {
        private Long id;
        private String name;

        public GroupDet()
        {
            super();
        }

        public GroupDet( Long id, String name )
        {
            super();
            this.id = id;
            this.name = name;
        }

        public Long getId()
        {
            return id;
        }

        public void setId( Long id )
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName( String name )
        {
            this.name = name;
        }
    }

    public String getOnlyGroupNames()
    {
        final String groupNames = groups.stream().map( det -> det.getName() ).collect( Collectors.joining( "," ) );
        return groupNames;
    }
}
