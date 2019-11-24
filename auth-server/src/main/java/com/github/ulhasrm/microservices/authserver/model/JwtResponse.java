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
    private final String groups;
    private final boolean admin;

    public JwtResponse( final String token, final String id, final String username, String firstName, String lastName,
        final String groups, final boolean admin )
    {
        this.token = token;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groups = groups;
        this.admin = admin;
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

    public String getGroups()
    {
        return groups;
    }

    public boolean isAdmin()
    {
        return admin;
    }

}
