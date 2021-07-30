package com.bearman.demo.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bearman.demo.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public String tokenize(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expireAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer) // who is create
                .withClaim("principle", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(expireAt)
                .sign(algorithm);
    }
}
