package com.nfb.modules.stakeholders.core.usecases;

import io.jsonwebtoken.Claims;

public interface ITokenGenerator {
    String generateJWT(String id, String issuer, String subject, long ttlMillis);
    Claims decodeJWT(String jwt, String secretKey);
}
