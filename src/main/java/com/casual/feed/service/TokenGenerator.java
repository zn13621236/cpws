package com.casual.feed.service;

import com.google.common.base.Charsets;
import org.glassfish.jersey.internal.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * @author jinglongyang
 */
@Component
public class TokenGenerator {
    private SecureRandom random;

    @Value("${session.token.length:16}")
    private int tokenLength;

    public TokenGenerator() {
        random = new SecureRandom();
    }

    public String generateToken() {
        return generateTokenData(tokenLength);
    }

    public String generateTokenData(int tokenLength) {
        byte[] newToken = new byte[tokenLength];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken), Charsets.UTF_8);
    }
}