package com.github.ulhasrm.microservices.authserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "SystemUser" )
public class SystemUser
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SystemUser_Seq" )
    private Long id;

    @Column( name = "userName" )
    private String userName;

    @Column( name = "firstName" )
    private String firstName;
    @Column( name = "lastName" )
    private String lastName;
    @Column( name = "password" )
    private String password;

    public SystemUser()
    {

    }

    public SystemUser( final String firstName, final String lastName, final String userName, final String password )
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
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

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
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
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + "]";
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    
}
