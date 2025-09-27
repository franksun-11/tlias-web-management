package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 与单元测试一致的密钥
    private static final String SECRET = "TGVlIFJlbWljaw==";
    // 12 小时过期
    private static final long EXPIRATION_MS = 12 * 60 * 60 * 1000L;

    /**
     * 生成 JWT 令牌
     * @param claims 业务载荷（例如：id、username 等）
     * @return 生成的 JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRATION_MS);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * 解析 JWT 令牌，返回 Claims（包含 id、username、exp 等）
     * 若令牌无效或过期，将抛出 io.jsonwebtoken.JwtException 及其子类
     * @param token JWT 字符串
     * @return Claims
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}