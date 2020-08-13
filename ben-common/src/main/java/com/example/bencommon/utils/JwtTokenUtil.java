package com.example.bencommon.utils;

import com.example.bencommon.config.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangben
 * @Description: <p>jwt token工具类</p>
 * <pre>
 *     jwt的claim里一般包含以下几种数据:
 *         1. iss -- token的发行者
 *         2. sub -- 该JWT所面向的用户
 *         3. aud -- 接收该JWT的一方
 *         4. exp -- token的失效时间
 *         5. nbf -- 在此时间段之前,不会被处理
 *         6. iat -- jwt发布时间
 *         7. jti -- jwt唯一标识,防止重复使用
 * </pre>
 * @Date: 2020/8/5 14:24
 * @Version: 1.0
 */
@Component
public class JwtTokenUtil {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * jwt 秘钥
     */
    private String secret;

    /**
     * 过期时间:单位秒
     */
    private long expireTime;

    public  JwtTokenUtil(){
    }

    public JwtTokenUtil(String secret, Long expireTime) {
        this.secret = secret;
        this.expireTime = expireTime;
    }

    @PostConstruct
    public void postMethod(){
        this.secret=jwtProperties.getSecret();
        this.expireTime=jwtProperties.getExpiration();
    }

    /**
     * 获取jwt的payload(载荷)部分
     * JWT 是由三段字符串和两个 . 组成，每个字符串和字符串之间没有换行（类似于这样：xxxxxx.yyyyyy.zzzzzz），
     * 每个字符串代表了不同的功能，我们将这三个字符串的功能按顺序列出来并讲解：
     * 1. JWT 头 JSON
     * 2. 有效载荷 JSON
     * 3. 哈希签名 签名
     *
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取用户id从token中
     */
    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token).get("userId").toString();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt接收者
     */
    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 解析token是否正确,不正确会报异常<br>
     */
    public void parseToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public Boolean isTokenExpired(String token){
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * 生成token(通过用户名和签名时候用的随机数)
     */
    public String generateToken(String username,Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId",userId);
        return doGenerateToken(claims, username);
    }

    /**
     * 生成token
     */
    private String doGenerateToken(Map<String, Object> claims, String username) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + this.expireTime * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    /**
     * 获取用户id从token中
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }
}
