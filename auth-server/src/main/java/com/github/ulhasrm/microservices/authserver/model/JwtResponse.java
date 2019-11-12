package com.github.ulhasrm.microservices.authserver.model;

import java.io.Serializable;

public class JwtResponse implements Serializable
{
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final String id;
    private final String username;
    private final String password = "";
    private final String firstName;
    private final String lastName;
    private String role;

    public JwtResponse( final String token, final String id, final String username, String firstName, String lastName, final String role )
    {
        this.token = token;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }


    public void setRole( String role )
    {
        this.role = role;
    }

    public String getToken()
    {
        return token;
    }

    public String getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

}
