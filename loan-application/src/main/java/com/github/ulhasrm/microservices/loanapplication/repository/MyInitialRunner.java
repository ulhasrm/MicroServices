package com.github.ulhasrm.microservices.loanapplication.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.ulhasrm.microservices.loanapplication.entity.User;
import com.github.ulhasrm.microservices.loanapplication.security.DefaultHashGenerator;

@Component
public class MyInitialRunner implements CommandLineRunner
{

    private static final Logger logger = LoggerFactory.getLogger( MyInitialRunner.class );

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DefaultHashGenerator hashGenerator;

    @Override
    public void run( String... args ) throws Exception
    {
        try
        {
            String loginName = "peter";
            User user = userRepository.findByLoginName( loginName );
            if( null == user )
            {
                userRepository.save( new User( "Peter", "Royal", "Admin", "peter",
                                               hashGenerator.hash( "peter123".getBytes() ) ) );
            }

            loginName = "ulhas";
            user = userRepository.findByLoginName( loginName );
            if( null == user )
            {
                userRepository.save( new User( "Ulhas", "Manekar", "User", "ulhas",
                                               hashGenerator.hash( "ulhas123".getBytes() ) ) );
            }

            loginName = "jay";
            user = userRepository.findByLoginName( loginName );
            if( null == user )
            {
                userRepository.save( new User( "Jay", "Far", "User", "jay",
                                               hashGenerator.hash( "jay123".getBytes() ) ) );
            }

            loginName = "jerry";
            user = userRepository.findByLoginName( loginName );
            if( null == user )
            {
                userRepository.save( new User( "Jerry", "Duval", "User", "jerry",
                                               hashGenerator.hash( "jerry123".getBytes() ) ) );
            }
        }
        catch( Exception e )
        {
            logger.error( e.getLocalizedMessage() );
        }
    }

}
