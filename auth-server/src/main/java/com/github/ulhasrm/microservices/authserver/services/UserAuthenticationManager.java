package com.github.ulhasrm.microservices.authserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.github.ulhasrm.microservices.authserver.bean.UserBean;
import com.github.ulhasrm.microservices.authserver.security.DefaultHashGenerator;

@Component
public class UserAuthenticationManager
{
    @Autowired
    private DefaultHashGenerator hashGenerator;

    public void authenticate( String username, String password, String role, UserBean user )
    {
        if( null != user )
        {
            final String dbPassword = user.getPassword();
            String generatedPassword = hashGenerator.hash( password.getBytes() );
            if( !dbPassword.equals( generatedPassword ) )
            {
                throw new BadCredentialsException( "" );
            }
        }
    }
}
