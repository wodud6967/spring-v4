package org.example.springv3.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.springv3.user.User;

import java.util.Date;

public class JwtUtil {
    public static String create(User user){
        String accessToken = JWT.create()
                .withSubject("바보")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .withClaim("id", user.getId())
                .withClaim("username",user.getUsername())
                .sign(Algorithm.HMAC512("metacoding"));
        return accessToken;
    }
    public static User verify(String jwt){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("metacoding")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();

        return User.builder()
                .id(id)
                .username(username)
                .build();
    }
}
