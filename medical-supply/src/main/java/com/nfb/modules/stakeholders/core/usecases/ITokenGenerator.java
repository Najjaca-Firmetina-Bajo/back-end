package com.nfb.modules.stakeholders.core.usecases;

import com.nfb.modules.stakeholders.API.dtos.AuthenticationTokensDto;
import io.jsonwebtoken.Claims;

public interface ITokenGenerator {
    AuthenticationTokensDto generateJWT(String id, String issuer, String subject, long ttlMillis);
    Claims decodeJWT(String jwt, String secretKey);
}
