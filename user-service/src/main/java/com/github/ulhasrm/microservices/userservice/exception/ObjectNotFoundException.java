package com.github.ulhasrm.microservices.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( HttpStatus.NOT_FOUND )
public class ObjectNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = -8094146431171165897L;
    private String objectName;
    private String objectKey;

    public ObjectNotFoundException( final String objectName, final String objectKey )
    {
        super( objectName + " not found with key : " + objectKey );
        this.objectName = objectName;
        this.objectKey = objectKey;
    }

    public ObjectNotFoundException( final String objectName, final Long objectKey )
    {
        this( objectName, String.valueOf( objectKey ) );
    }

    public String getObjectName()
    {
        return objectName;
    }

    public void setObjectName( String objectName )
    {
        this.objectName = objectName;
    }

    public String getObjectKey()
    {
        return objectKey;
    }

    public void setObjectKey( String objectKey )
    {
        this.objectKey = objectKey;
    }

}
