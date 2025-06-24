package com.euansu;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {

    @Test
    public void testGen(){

        // 生成JWT对象
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username", "euansu");
        String token = JWT.create()
                .withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .sign(Algorithm.HMAC256("euansu"));
        System.out.println(token);
    }

    @Test
    public void testParse(){
        // 解析JWT对象
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImV1YW5zdSJ9LCJleHAiOjE3NDk4MTkzMjd0.5q-XBmgY6XDPS_f_F-OVW1Gn6jMC7VvooAfvphaSPeI";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("euansu")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
