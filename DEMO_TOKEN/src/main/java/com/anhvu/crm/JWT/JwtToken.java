package com.anhvu.crm.JWT;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;

import com.anhvu.crm.entity.User;
import com.anhvu.crm.exception.ExceptionError;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class JwtToken {
    private final String JWT_SECRET = "Yqs36pN/M2Hcc6IqrVkAWSOcn0TYkowp7Oc1Fz5jNdc=";
    private final long JWT_EXPIRATION = 604800000L;

    public JwtToken(BCryptPasswordEncoder bCryptPasswordEncoder) {
    }

    public String generateToken(User user) {
        try {
            Instant now = Instant.now();
            Instant expiryInstant = now.plus(JWT_EXPIRATION, ChronoUnit.MILLIS);
            long expirationTimestamp = expiryInstant.toEpochMilli();
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userName", user.getUserName());
            userInfo.put("userId", user.getId());
            userInfo.put("userRole", user.getRole());
            String userInfoJSON = new ObjectMapper().writeValueAsString(userInfo);

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(userInfoJSON)
                    .issueTime(Date.from(now))
                    .expirationTime(Date.from(expiryInstant))
                    .claim("exp", expirationTimestamp)
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
            signedJWT.sign(new MACSigner(JWT_SECRET));

            return signedJWT.serialize();
        } catch (JOSEException | IOException ex) {
            throw new ExceptionError("Failed to create JWT token");
        }
    }

    public String generateSecretKey() {
        try {
            byte[] secretKeyBytes = new byte[32];
            new SecureRandom().nextBytes(secretKeyBytes);
            return Base64.getEncoder().encodeToString(secretKeyBytes);
        } catch (Exception ex) {
            throw new ExceptionError("Failed to generate secret key");
        }
    }

    public Long getUserIdFromJWT(String token) throws java.text.ParseException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(JWT_SECRET);
            if (signedJWT.verify(verifier)) {
                JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
                String subject = claims.getSubject();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> subjectMap = objectMapper.readValue(subject, Map.class);
                if (subjectMap.containsKey("userId")) {
                    return Long.parseLong(subjectMap.get("userId").toString());
                }
            }
            throw new ExceptionError("Invalid JWT token");
        } catch (JOSEException | IOException ex) {
            throw new ExceptionError("Invalid JWT token");
        }
    }

    public boolean validateToken(String authToken) throws java.text.ParseException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(authToken);
            JWSVerifier verifier = new MACVerifier(JWT_SECRET);
            return signedJWT.verify(verifier);
        } catch (JOSEException ex) {
            throw new ExceptionError("Invalid JWT token");
        }
    }
}
