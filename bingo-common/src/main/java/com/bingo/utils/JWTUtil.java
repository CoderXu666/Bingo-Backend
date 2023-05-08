package com.bingo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 21:46
 * @Version 1.0
 * @Description: JWTUtil
 */
public class JWTUtil {
    public static final long EXPIRE = 24 * 60 * 60 * 1000;
    public static final String JWT_SECRET = "bingobingo666xuzhibin";

    /**
     * 生成token字符串的方法
     */
    public static String getJwtToken(String userId, Integer userType) {
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
     * 根据token获取 ResolveCustomer Info
     */
    public static Map<String, String> getResolveCustomerByToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String productCode = (String) claims.get("productCode");
        String accountId = (String) claims.get("accountId");
        String customerId = (String) claims.get("customerId");
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("productCode", productCode);
        resultMap.put("accountId", accountId);
        resultMap.put("customerId", customerId);
        return resultMap;
    }
}
