package com.github.ulhasrm.microservices.userservice.datasetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.ulhasrm.microservices.userservice.entity.JoinedUserGroup;
import com.github.ulhasrm.microservices.userservice.entity.SystemGroup;
import com.github.ulhasrm.microservices.userservice.entity.SystemUser;
import com.github.ulhasrm.microservices.userservice.exception.DataSeedingException;
import com.github.ulhasrm.microservices.userservice.repository.JoinedUserGroupRepository;
import com.github.ulhasrm.microservices.userservice.repository.SystemGroupRepository;
import com.github.ulhasrm.microservices.userservice.repository.SystemUserRepository;
import com.github.ulhasrm.microservices.userservice.security.DefaultHashGenerator;

@Component
public class MyInitialRunner implements CommandLineRunner
{

    private static final Logger logger = LoggerFactory.getLogger( MyInitialRunner.class );

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private SystemGroupRepository systemGroupRepository;

    @Autowired
    private JoinedUserGroupRepository joinedUserGroupRepository;

    @Autowired
    private DefaultHashGenerator hashGenerator;

    @Override
    public void run( String... args ) throws Exception
    {
        createInitialSystemUsers();
        createDefaultSystemGroups();
        createDefaultJoinedUserGroup();
    }

    private void createInitialSystemUsers()
    {
        try
        {
            mayCreateUser( "amitkan", "Amit", "Kanthe", "amitkan" );
            mayCreateUser( "bharatkam", "Bharat", "Kamble", "bharatkam" );
            mayCreateUser( "ulhasrm", "Ulhas", "Manekar", "ulhasrm" );
            mayCreateUser( "admin", "Admin", "Admin", "admin" );
            mayCreateUser( "ganeshbha", "Ganesh", "Bhalerao", "ganeshbha" );

        }
        catch( Exception e )
        {
            logger.error( e.getLocalizedMessage() );
            throw new DataSeedingException( "SystemUser: " + e.getLocalizedMessage() );
        }
    }

    private void createDefaultJoinedUserGroup()
    {
        try
        {
            final SystemUser adminUser = systemUserRepository.findByUserName( "admin" );
            final SystemUser ulhasrm = systemUserRepository.findByUserName( "ulhasrm" );
            final SystemUser amitkan = systemUserRepository.findByUserName( "amitkan" );
            final SystemUser bharatkam = systemUserRepository.findByUserName( "bharatkam" );
            final SystemUser ganeshbha = systemUserRepository.findByUserName( "ganeshbha" );

            SystemGroup adminGroup = systemGroupRepository.findByName( "Admin" );
            SystemGroup customerGroup = systemGroupRepository.findByName( "Customer" );
            SystemGroup documentationGroup = systemGroupRepository.findByName( "Documentation" );
            SystemGroup verificationGroup = systemGroupRepository.findByName( "Verification" );
            SystemGroup bankManagerGroup = systemGroupRepository.findByName( "Bank Manager" );

            mayCreateJoinedUserGroup( adminUser, adminGroup );
            mayCreateJoinedUserGroup( ulhasrm, customerGroup );
            mayCreateJoinedUserGroup( amitkan, documentationGroup );
            mayCreateJoinedUserGroup( bharatkam, verificationGroup );
            mayCreateJoinedUserGroup( ganeshbha, bankManagerGroup );
        }
        catch( Exception e )
        {
            logger.error( e.getLocalizedMessage() );
            throw new DataSeedingException( "JoinedUserGroup: " + e.getLocalizedMessage() );
        }
    }

    private void createDefaultSystemGroups()
    {
        try
        {
            mayCreateSystemGroup( "Admin" );
            mayCreateSystemGroup( "Customer" );
            mayCreateSystemGroup( "Documentation" );
            mayCreateSystemGroup( "Verification" );
            mayCreateSystemGroup( "Bank Manager" );
        }
        catch( Exception e )
        {
            logger.error( e.getLocalizedMessage() );
            throw new DataSeedingException( "SystemGroups: " + e.getLocalizedMessage() );
        }
    }

    private void mayCreateSystemGroup( final String groupName )
    {
        SystemGroup group = systemGroupRepository.findByName( groupName );
        if( null == group )
        {
            systemGroupRepository.save( new SystemGroup( groupName ) );
        }
    }

    private void mayCreateJoinedUserGroup( final SystemUser user, final SystemGroup group )
    {
        try
        {
            joinedUserGroupRepository.save( new JoinedUserGroup( user, group ) );
        }
        catch( Exception e )
        {
            logger.error( e.getLocalizedMessage() );
            throw new DataSeedingException( "JoinedUserGroup: " + e.getLocalizedMessage() );
        }
    }

    private void mayCreateUser( final String loginName, final String firstName, final String lastName,
        final String password )
    {
        final SystemUser user = systemUserRepository.findByUserName( loginName );
        if( null == user )
        {
            systemUserRepository.save( new SystemUser( firstName, lastName, loginName,
                                                       hashGenerator.hash( password.getBytes() ) ) );
        }
    }

}
