package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 测试生成JWT - Jwts.builder()
     */
    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "TGVlIFJlbWljaw==") // 指定加密算法，密钥
                .addClaims(dataMap) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 3600 * 1000)) //设置过期时间，毫秒值
                .compact();// 生成令牌
        System.out.println(jwt);
    }

    /**
     * 测试解析JWT - Jwts.parser()
     */
    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc1OTAxMjk3M30.Ar9TK6vYTxzKliyLCPZ6L2OjUsUt1WWa8o1iN8KwYCM";
        Claims claims = Jwts.parser().setSigningKey("TGVlIFJlbWljaw==")
                .parseClaimsJws(token)
                .getBody();  // 解析令牌，获取载荷（payload）部分的内容
        System.out.println(claims);
    }
}
