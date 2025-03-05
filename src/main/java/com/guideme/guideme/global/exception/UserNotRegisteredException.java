package com.guideme.guideme.global.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

public class UserNotRegisteredException extends OAuth2AuthenticationException {

    public UserNotRegisteredException(String errorCode) {
        super(new OAuth2Error("user_not_registered", errorCode, null));
    }
}
