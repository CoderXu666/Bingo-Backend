package com.bingo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 21:46
 * @Version 1.0
 * @Description: JWTUtil
 */
public class JWTUtil {
    public static final long EXPIRE = 86400000;
    public static final String JWT_SECRET = "Bingo_XuZhiBin_666";

    /**
     * 根据 uid 生成 Token
     */
    public static String generateToken(Long uid) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("Bingo")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("uid", uid)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
        return JwtToken;
    }

    /**
     * 解析 Token 获取 uid
     */
    public static Long resolveTokenToUid(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Long) claims.get("uid");
    }

    /**
     * 校验 Token 是否可用
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        JWTUtil.resolveTokenToUid("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCaW5nbyIsImlhdCI6MTY5NDAxMTU2MywiZXhwIjoxNjk0MDk3OTYzLCJ1aWQiOjEwMH0.-NQqgb5ZBcGQX1DZWsKRCz398bA4FarW1ypPz6hPnV4");
        System.out.println();
    }
}
