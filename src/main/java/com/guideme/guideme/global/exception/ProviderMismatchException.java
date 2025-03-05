package com.guideme.guideme.global.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

public class ProviderMismatchException extends OAuth2AuthenticationException {
    public ProviderMismatchException(String errorCode) {
        super(new OAuth2Error("provider_mismatch", errorCode, null));
    }
}
