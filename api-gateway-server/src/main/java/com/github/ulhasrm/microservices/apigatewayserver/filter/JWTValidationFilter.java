package com.github.ulhasrm.microservices.apigatewayserver.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.github.ulhasrm.microservices.apigatewayserver.bean.UserBean;
import com.github.ulhasrm.microservices.apigatewayserver.communications.InterServiceCommunications;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@CrossOrigin( origins = "*", allowedHeaders = "*" )
public class JWTValidationFilter extends ZuulFilter
{
    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    InterServiceCommunications communication;

    @Value( "${service.authentication.serviceId}" )
    private String authenticationServiceId;

    @Value( "${zhuul.skip.validation}" )
    private boolean skipValidation;

    private void validate( HttpServletRequest request )
    {
        try
        {
            final String auth = request.getHeader( HttpHeaders.AUTHORIZATION );
            if( null == auth )
            {
                throw new JWTValidationException( "Bearer token is missing" );
            }

            final String jwtToken = auth.substring( 7 );
            final String userName;
            try
            {
                // TODO check the user from header and compare it with user parsed from JWT token
                userName = jwtTokenUtil.getUsernameFromToken( jwtToken );
                final UserBean userDetail = communication.getUserDetail( userName );
                if( !userDetail.isExists() )
                {
                    throw new JWTValidationException( "Invalid User" );
                }
            }
            catch( IllegalArgumentException e )
            {
                throw new JWTValidationException( "Unable to validate token : " + e.getMessage() );
            }
            catch( ExpiredJwtException e )
            {
                throw new JWTValidationException( "JWT Token has expired" );
            }

            logger.info( "Request validated with JWT token for user: " + userName );
        }
        catch( Exception e )
        {
            throw e;
        }
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    @Override
    public Object run() throws ZuulException
    {
        final HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        final String requestURI = request.getRequestURI();
        if( null != requestURI && !requestURI.startsWith( "/" + authenticationServiceId + "/authenticate" ) )
        {
            if( !skipValidation )
            {
                final String authToken = request.getHeader( HttpHeaders.AUTHORIZATION );
                if( null == authToken || "".equals( authToken ) )
                {
                    throw new ZuulException( "No Beader Token available", 401, "" );
                }

                validate( request );
            }
        }

        return null;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public int filterOrder()
    {
        return 1;
    }

    @Override
    public String filterType()
    {
        return "pre";
    }

}
