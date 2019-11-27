package com.github.ulhasrm.microservices.authserver.controller;

import javax.management.relation.InvalidRoleValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.authserver.bean.UserGroupBean;
import com.github.ulhasrm.microservices.authserver.communication.InterServiceCommunications;
import com.github.ulhasrm.microservices.authserver.config.JWTTokenUtil;
import com.github.ulhasrm.microservices.authserver.model.JwtRequest;
import com.github.ulhasrm.microservices.authserver.model.JwtResponse;
import com.github.ulhasrm.microservices.authserver.services.UserAuthenticationManager;

@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class JwtAuthenticationController
{
    @Autowired
    private UserAuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    InterServiceCommunications communications;

    @RequestMapping( value = "/authenticate", method = RequestMethod.POST )
    public JwtResponse createAuthenticationToken( @RequestBody JwtRequest authenticationRequest ) throws Exception
    {
        final UserGroupBean userGroupBean = communications.getUserDetail( authenticationRequest.getUsername() );
        if( null == userGroupBean || !userGroupBean.isExist() )
        {
            throw new UsernameNotFoundException( authenticationRequest.getUsername() );
        }

        // Distinguish user
        final String role = authenticationRequest.getRole();
        final String onlyGroupNames =
            null != userGroupBean.getOnlyGroupNames() ? userGroupBean.getOnlyGroupNames() : "";

        if( "C".equals( role ) && !onlyGroupNames.contains( "Customer" ) )
        {
            throw new UsernameNotFoundException( "Permissions not available : " + authenticationRequest.getUsername() );
        }
        else if( "M".equals( role ) && ( onlyGroupNames.contains( "Customer" ) || onlyGroupNames.equals( "" ) ) )
        {
            throw new UsernameNotFoundException( "Permissions not available : " + authenticationRequest.getUsername() );
        }
        else if( !( "C".equals( role ) || "M".equals( role ) ) )
        {
            throw new InvalidRoleValueException();
        }

        authenticate( authenticationRequest.getUsername(), authenticationRequest.getPassword(),
                      authenticationRequest.getRole(), userGroupBean );

        final String token = jwtTokenUtil.generateToken( userGroupBean );
        final JwtResponse response =
            new JwtResponse( token, String.valueOf( userGroupBean.getId() ), userGroupBean.getUserName(),
                             userGroupBean.getFirstName(), userGroupBean.getLastName(),
                             userGroupBean.getOnlyGroupNames(), userGroupBean.isAdmin() );
        return response;
    }

    private void authenticate( final String username, final String password, final String role,
        final UserGroupBean user ) throws Exception
    {
        try
        {
            authenticationManager.authenticate( username, password, role, user );
        }
        catch( DisabledException e )
        {
            throw new Exception( "USER_DISABLED", e );
        }
        catch( BadCredentialsException e )
        {
            throw new Exception( "INVALID_CREDENTIALS", e );
        }
    }

    @RequestMapping( value = "/validate", method = RequestMethod.POST )
    public UserGroupBean validateJWTToken( @RequestBody JwtRequest authenticationRequest )
    {
        // When it reaches here, means validation is already done
        final UserGroupBean userBean = communications.getUserDetail( authenticationRequest.getUsername() );
        return userBean;
    }
}
