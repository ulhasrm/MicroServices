package com.github.ulhasrm.microservices.loanapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "app_user" )
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Seq")
    private Long id;

    @Column( name = "firstName" )
    private String firstName;
    @Column( name = "lastName" )
    private String lastName;
    @Column( name = "role" )
    private String role;
    @Column( name = "loginName" )
    private String loginName;
    @Column( name = "password" )
    private String password;

    public User()
    {

    }

    public User( String firstName, String lastName, String role, String loginName, String password )
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.loginName = loginName;
        this.password = password;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User [userId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + "]";
    }
}
