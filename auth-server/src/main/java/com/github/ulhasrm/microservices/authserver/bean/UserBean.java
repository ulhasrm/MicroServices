package com.github.ulhasrm.microservices.authserver.bean;

import java.util.List;

public class UserBean
{
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private boolean exists;
    private String password;
    private List<String> groups;

    public UserBean( String userName, String firstName, String lastName, Long id, String password )
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
        this.exists = true;
    }

    public UserBean()
    {
        this.exists = false;
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

    public List<String> getGroups()
    {
        return groups;
    }

    public void setGroups( List<String> groups )
    {
        this.groups = groups;
    }

    public boolean isExists()
    {
        return exists;
    }

    public void setExists( boolean exists )
    {
        this.exists = exists;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

}
