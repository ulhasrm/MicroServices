package com.github.ulhasrm.microservices.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.ulhasrm.microservices.userservice.entity.JoinedUserGroup;

public interface JoinedUserGroupRepository extends JpaRepository<JoinedUserGroup, Long>
{
    @Query( value = "SELECT * FROM Joined_User_Group WHERE user_id = ?1", nativeQuery = true )
    List<JoinedUserGroup> findByUser( final Long user );

    @Query( value = "SELECT * FROM Joined_User_Group WHERE group_id=?1", nativeQuery = true )
    List<JoinedUserGroup> findByGroup( final Long groupId );

    @Query( value = "SELECT * FROM Joined_User_Group WHERE user_id = ?1 and group_id=?2 ", nativeQuery = true )
    JoinedUserGroup findByUserAndGroup( String user, final String group );
}
