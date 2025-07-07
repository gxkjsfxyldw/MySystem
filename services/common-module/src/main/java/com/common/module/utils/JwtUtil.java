package com.common.module.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类，提供生成、验证和管理JWT令牌的功能
 * @author ldw by 20250701
 */
@Component
public class JwtUtil {

    // 从配置文件中注入密钥和过期时间
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // 刷新Token的提前时间（默认15分钟）
    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration = 1000 * 60 * 15;

    /**
     * 生成JWT令牌
     * @param userId 用户ID
     * @return 生成的JWT令牌
     */
    public String generateToken(String userId) {
        return generateToken(userId, new HashMap<>());
    }

    /**
     * 生成JWT令牌
     * @param userId 用户ID
     * @param claims 自定义声明
     * @return 生成的JWT令牌
     */
    public String generateToken(String userId, Map<String, Object> claims) {
        // 获取JWT生成器
        JWTCreator.Builder jwtBuilder = JWT.create();

        // 准备Header参数
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");   // 设置token的type为JWT
        headers.put("alg", "HS256");  // 加密算法为HS256

        // 添加自定义声明
        JWTCreator.Builder finalJwtBuilder = jwtBuilder.withHeader(headers)
                .withClaim("userId", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withIssuedAt(new Date(System.currentTimeMillis()));

        claims.forEach((key, value) -> {
            if (value instanceof String) {
                finalJwtBuilder.withClaim(key, (String) value);
            } else if (value instanceof Integer) {
                finalJwtBuilder.withClaim(key, (Integer) value);
            } else if (value instanceof Long) {
                finalJwtBuilder.withClaim(key, (Long) value);
            } else if (value instanceof Boolean) {
                finalJwtBuilder.withClaim(key, (Boolean) value);
            } else if (value instanceof Date) {
                finalJwtBuilder.withClaim(key, (Date) value);
            }
        });

        // 生成Token并签名
        return finalJwtBuilder.sign(Algorithm.HMAC256(secret));
    }

    /**
     * 验证JWT令牌
     * @param token 待验证的Token
     * @return 验证结果
     */
    public boolean isTokenValid(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();

        try {
            DecodedJWT decodedJWT = verifier.verify(token);

            // 可选：验证Token的有效期
            if (decodedJWT.getExpiresAt().before(new Date())) {
                return false;
            }

            return true;
        } catch (Exception e) {
            // Token验证失败
            return false;
        }
    }

    /**
     * 获取Token中的用户ID
     * @param token JWT令牌
     * @return 用户ID
     */
    public String getUserIdFromToken(String token) {
        if (!isTokenValid(token)) {
            return null;
        }

        DecodedJWT decodedJWT = JWT.decode(token);
        Claim userClaim = decodedJWT.getClaim("userId");

        return userClaim.isNull() ? null : userClaim.asString();
    }

    /**
     * 获取Token中的指定声明
     * @param token JWT令牌
     * @param claimKey 声明键
     * @return 声明值
     */
    public String getClaimFromToken(String token, String claimKey) {
        if (!isTokenValid(token) || !StringUtils.hasText(claimKey)) {
            return null;
        }

        DecodedJWT decodedJWT = JWT.decode(token);
        Claim claim = decodedJWT.getClaim(claimKey);

        return claim.isNull() ? null : claim.asString();
    }

    /**
     * 获取Token的剩余有效期
     * @param token JWT令牌
     * @return 剩余有效期（毫秒）
     */
    public long getTokenRemainingValidity(String token) {
        if (!isTokenValid(token)) {
            return 0;
        }

        DecodedJWT decodedJWT = JWT.decode(token);
        Date expiresAt = decodedJWT.getExpiresAt();

        return expiresAt.getTime() - System.currentTimeMillis();
    }

    /**
     * 检查是否需要刷新Token
     * @param token JWT令牌
     * @return 是否需要刷新
     */
    public boolean isTokenNeedRefresh(String token) {
        long remainingValidity = getTokenRemainingValidity(token);
        return remainingValidity > 0 && remainingValidity < refreshExpiration;
    }

    /**
     * 刷新JWT令牌
     * @param oldToken 旧Token
     * @return 新Token
     */
    public String refreshToken(String oldToken) {
        if (!isTokenValid(oldToken)) {
            return null;
        }

        // 解析旧Token中的用户信息
        String userId = getUserIdFromToken(oldToken);

        // 生成新Token
        return generateToken(userId);
    }
}
