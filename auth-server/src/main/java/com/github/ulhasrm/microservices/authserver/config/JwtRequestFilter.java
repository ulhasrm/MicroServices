package com.github.ulhasrm.microservices.authserver.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.ulhasrm.microservices.authserver.bean.UserBean;
import com.github.ulhasrm.microservices.authserver.communication.InterServiceCommunications;
import com.github.ulhasrm.microservices.authserver.exception.UserNotFoundException;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{
    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    InterServiceCommunications communications;

    private HttpHeaders collectHeaders( final HttpServletRequest request )
    {
        final Enumeration<String> headerNames = request.getHeaderNames();
        HttpHeaders headers = new HttpHeaders();
        while( headerNames.hasMoreElements() )
        {
            final String element = headerNames.nextElement();
            headers.set( element, request.getHeader( element ) );
            System.out.println( element + " => " + request.getHeader( element ) );
        }
        return headers;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain chain )
        throws ServletException, IOException
    {
        final String requestTokenHeader = request.getHeader( "Authorization" );
        collectHeaders( request );
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if( requestTokenHeader != null && requestTokenHeader.startsWith( "Bearer " ) )
        {
            jwtToken = requestTokenHeader.substring( 7 );
            try
            {
                username = jwtTokenUtil.getUsernameFromToken( jwtToken );
            }
            catch( IllegalArgumentException e )
            {
                System.out.println( "Unable to get JWT Token" );
            }
            catch( ExpiredJwtException e )
            {
                System.out.println( "JWT Token has expired" );
            }
        }
        else
        {
            logger.warn( "JWT Token does not begin with Bearer String" );
        }
        // Once we get the token validate it.
        if( username != null && SecurityContextHolder.getContext().getAuthentication() == null )
        {
            final UserBean userBean = communications.getUserDetail( username );
            if( !userBean.isExists() )
            {
                throw new UserNotFoundException( "Invalid User : " + username );
            }
            // if token is valid configure Spring Security to manually set
            // authentication
            if( jwtTokenUtil.validateToken( jwtToken, userBean ) )
            {
                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken( userBean, null, null );
                usernamePasswordAuthenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication( usernamePasswordAuthenticationToken );
            }
        }
        chain.doFilter( request, response );
    }
}