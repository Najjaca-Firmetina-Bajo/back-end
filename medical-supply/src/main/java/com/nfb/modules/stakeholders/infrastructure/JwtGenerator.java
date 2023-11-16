package com.nfb.modules.stakeholders.infrastructure;

import com.nfb.modules.stakeholders.core.usecases.ITokenGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class JwtGenerator implements ITokenGenerator {

    @Override
    public String generateJWT(String id, String issuer, String subject, long ttlMillis) {
        // Set the signature algorithm (e.g., HS256)
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // Current time in milliseconds
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Create JWT claims
        Claims claims = Jwts.claims().setId(id).setIssuer(issuer).setSubject(subject);

        // Set the expiration time
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            claims.setExpiration(exp);
        }

        // Generate a random secret key
        SecretKey secretKey = generateSecretKey();

        // Build the JWT
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(secretKey, signatureAlgorithm)
                .compact();

        // Convert the secret key to a Base64-encoded string for storage
        String secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Generated Secret Key: " + secretKeyString);

        return jwt;
    }

    @Override
    public Claims decodeJWT(String jwt, String secretKey) {
        try {
            // Parse the JWT and verify its signature with the provided secret key
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();

            // Optional: Add additional validation or processing if needed

            return claims;

        } catch (Exception e) {
            // Handle exceptions such as JWT signature verification failure
            System.out.println("Error decoding JWT: " + e.getMessage());
            return null; // Or handle the error in a way that makes sense for your application
        }
    }

    private static SecretKey generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32]; // Adjust the size based on the algorithm's key size
        secureRandom.nextBytes(keyBytes);
        return new SecretKeySpec(keyBytes, "HmacSHA256"); // Use the appropriate algorithm
    }
}
