package com.github.ulhasrm.microservices.loanapplication.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Component;

@Component
public class DefaultHashGenerator implements HashGenerator
{
    private static final String DEFAULT_ALGORITM = "MD5";

    @Override
    public String hash( byte[] value )
    {
        try
        {
            final Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString( getMessageDigest().digest( value ) );
        }
        catch( final NoSuchAlgorithmException e )
        {
            throw new RuntimeException( e );
        }
    }

    private MessageDigest getMessageDigest() throws NoSuchAlgorithmException
    {
        return MessageDigest.getInstance( DEFAULT_ALGORITM );
    }
}
