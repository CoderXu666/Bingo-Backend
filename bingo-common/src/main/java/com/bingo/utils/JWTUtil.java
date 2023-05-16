package com.bingo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 21:46
 * @Version 1.0
 * @Description: JWTUtil
 */
public class JWTUtil {
    public static final long EXPIRE = 86400000;
    public static final String JWT_SECRET = "bingo666xuzhibin";

    /**
     * 生成 Token
     */
    public static String generateToken(String userId, Integer userType) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("Lark XR SAAS")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("userId", userId)
                .claim("userType", userType)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
        return JwtToken;
    }

    /**
     * 解析 Token
     */
    public static Map<String, String> resolveToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String productCode = (String) claims.get("productCode");
        String accountId = (String) claims.get("accountId");
        String customerId = (String) claims.get("customerId");
        return null;
    }
}
