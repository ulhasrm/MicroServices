package com.github.ulhasrm.microservices.authserver.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.ulhasrm.microservices.authserver.entity.User;
import com.github.ulhasrm.microservices.authserver.exception.UserNotFoundException;
import com.github.ulhasrm.microservices.authserver.repository.UserRepository;

@Repository
@Transactional
public class UserDaoService
{
    // @PersistenceContext
    // private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    public User persist( User user )
    {
        final User savedUser = userRepository.save( user );
        return savedUser;
    }

    public void delete( User user )
    {
        userRepository.delete( user );
    }

    public User getUser( final String loginName )
    {
        final User user = userRepository.findByLoginName( loginName );
        return user;
    }

    public User getUser( final Long userId )
    {
        final Optional<User> users = userRepository.findById( userId );
        if( !users.isPresent() )
        {
            throw new UserNotFoundException( "id : " + userId );
        }

        return users.get();
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

}
