package com.github.ulhasrm.microservices.apigatewayserver.model;

public class User
{
    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String loginName;

    public User()
    {
        System.out.println( "" );
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
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

    public String getRole()
    {
        return role;
    }

    public void setRole( String role )
    {
        this.role = role;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName( String loginName )
    {
        this.loginName = loginName;
    }
}
