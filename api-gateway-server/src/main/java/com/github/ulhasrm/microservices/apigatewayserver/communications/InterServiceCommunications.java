package com.github.ulhasrm.microservices.apigatewayserver.communications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.ulhasrm.microservices.apigatewayserver.bean.UserBean;
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

    public UserBean getUserDetail( final String userName )
    {
        Application application = discoveryClient.getApplication( userServiceName );
        final List<InstanceInfo> instances = application.getInstances();
        if( instances.size() > 0 )
        {
            final InstanceInfo instance = instances.get( 0 );
            final String url = instance.getHomePageUrl() + "SystemUser/" + userName;
            final ResponseEntity<UserBean> responseEntity = new RestTemplate().getForEntity( url, UserBean.class );
            final UserBean body = responseEntity.getBody();

            final UserBean userBean = new UserBean( body.getUserName(), body.getFirstName(), body.getLastName(),
                                                    body.getId(), body.getPassword() );
            return userBean;
        }

        return new UserBean();
    }
}
