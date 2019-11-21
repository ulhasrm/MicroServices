package com.github.ulhasrm.microservices.authserver.model;

import java.io.Serializable;

public class JwtRequest implements Serializable
{
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;
    private String role;

    // need default constructor for JSON Parsing
    public JwtRequest()
    {
    }

    public JwtRequest( String username, String password, String role  )
    {
        this.setUsername( username );
        this.setPassword( password );
        this.setRole( role );
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole( String role )
    {
        this.role = role;
    }
    
}
