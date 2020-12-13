package com.note.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @className: JwtUtil
 * @description: jwt令牌
 * @author: lgp
 * @date: 2020/11/25 9:57
 * @version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "lotus.jwt.config")
public class JwtUtil {

    // 密钥
    private String secretKey;

    //单位毫秒
    private long expire = 24*60*60*7;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    /**
     * 生成jwt令牌
     * @param expire
     * @param secretKey
     * @param isLogin
     * @return
     */
    public String createJwt(String id,String subject,Boolean isLogin){
        long nowMillion = System.currentTimeMillis();
        Date now = new Date(nowMillion);
        JwtBuilder builder = Jwts.builder().setId(id)//用户id
                .setSubject(subject) //设置主题用户名
                .setIssuedAt(now) // 设置时间
                .signWith(SignatureAlgorithm.HS256, secretKey)//设置加密方式
                .claim("isLogin",isLogin); // 登录状态
        if (expire>0){
            builder.setExpiration(new Date(nowMillion+expire*1000));// 设置有效时间
        }
        return builder.compact();//生成令牌
    }

    public Claims parseJwt(String jwtToken){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
    }
}
