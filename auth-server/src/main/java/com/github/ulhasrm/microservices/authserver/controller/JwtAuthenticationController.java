package com.github.ulhasrm.microservices.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ulhasrm.microservices.authserver.config.JWTTokenUtil;
import com.github.ulhasrm.microservices.authserver.entity.User;
import com.github.ulhasrm.microservices.authserver.model.JwtRequest;
import com.github.ulhasrm.microservices.authserver.model.JwtResponse;
import com.github.ulhasrm.microservices.authserver.services.UserAuthenticationManager;
import com.github.ulhasrm.microservices.authserver.services.UserDaoService;

@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class JwtAuthenticationController
{
    @Autowired
    private UserAuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    UserDaoService service;

    @RequestMapping( value = "/authenticate", method = RequestMethod.POST )
    public JwtResponse createAuthenticationToken( @RequestBody JwtRequest authenticationRequest ) throws Exception
    {
        final User user = service.getUser( authenticationRequest.getUsername() );
        if( null == user )
        {
            throw new UsernameNotFoundException( authenticationRequest.getUsername() );
        }
        authenticate( authenticationRequest.getUsername(), authenticationRequest.getPassword(), user );

        final String token = jwtTokenUtil.generateToken( user );
        final JwtResponse response = new JwtResponse( token, String.valueOf( user.getId() ), user.getLoginName(),
                                                      user.getFirstName(), user.getLastName(), user.getRole() );
        return response;
    }

    private void authenticate( final String username, final String password, final User user ) throws Exception
    {
        try
        {
            authenticationManager.authenticate( username, password, user );
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
    public User validateJWTToken( @RequestBody JwtRequest authenticationRequest )
    {
        // When it reaches here, means validation is already done
        final User user = service.getUser( authenticationRequest.getUsername() );
        return user;
    }
}
