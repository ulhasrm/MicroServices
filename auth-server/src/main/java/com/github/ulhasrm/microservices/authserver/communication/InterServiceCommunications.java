package com.github.ulhasrm.microservices.authserver.communication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.ulhasrm.microservices.authserver.bean.UserGroupBean;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Component
public class InterServiceCommunications
{
    @Autowired
    EurekaClient discoveryClient;

    @Value( "${spring.microservices.user.service.name}" )
    private String userServiceName;

    public UserGroupBean getUserDetail( final String userName )
    {
        Application application = discoveryClient.getApplication( userServiceName );
        final List<InstanceInfo> instances = application.getInstances();
        if( instances.size() > 0 )
        {
            final InstanceInfo instance = instances.get( 0 );
            final String url = instance.getHomePageUrl() + "/JoinedUserGroup/SystemUser/" + userName;
            final ResponseEntity<UserGroupBean> responseEntity =
                new RestTemplate().getForEntity( url, UserGroupBean.class );
            final UserGroupBean body = responseEntity.getBody();

            if( body.isExist() )
            {
                final UserGroupBean userGroupBean =
                    new UserGroupBean( body.getId(), body.getUserName(), body.getFirstName(), body.getLastName(),
                                       body.getPassword() );
                userGroupBean.setGroups( body.getGroups() );
                userGroupBean.setAdmin( body.isAdmin() );

                return userGroupBean;
            }
        }

        final UserGroupBean emptyBean = new UserGroupBean();
        emptyBean.setExist( false );
        return emptyBean;
    }
}
